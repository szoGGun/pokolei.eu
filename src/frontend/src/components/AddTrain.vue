<template>
  <div class="container-fluid m-0 p-0 ">
    <div id="trains-table" class="full-height p-5">
      <div class="row pb-5 h-100 d-flex align-items-center justify-content-center">
        <h1 class="text-center mb-5 pt-5 pb-0">Dodaj pociąg:</h1>
        <div class="container my-5 border bg-light">
          <div class="container my-5">

            <!--Tabela z obecnym składem pociągu-->
            <div class="d-flex flex-column align-items-center justify-content-center"
                 v-if="selectedCars.length || selectedLocomotives.length">
              <h4 class="mt-5">Obecny skład pociągu:</h4>
              <table id="select-table" class="table-sm table-striped table-bordered mb-3">
                <thead>
                <tr>
                  <th>Numer wagonu</th>
                  <th>Typ</th>
                  <th>Nazwa</th>
                  <th>Wygląd</th>
                  <th>Więcej Informacji</th>
                  <th>Akcje</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="(locomotive, order) in selectedLocomotives" :key="locomotive.locomotiveId">
                  <td></td>
                  <td></td>
                  <td>{{ locomotive.name }}</td>
                  <td><img :src="getPhotoUrl(locomotive. pictureLink, 'locomotives')" alt=""/></td>
                  <td>
                    {{ locomotive.drivingSpeed }}, {{ locomotive.weight }}<br>
                    <div class="form-group m-2">
                    <div class="form-group">
                      <input type="text" id="additionalInfoLocomotive" v-model="locomotive.additionalInfo" style="width: 400px"/>
                    </div>
                  </div></td>
                  <td>
                    <button class="btn btn-primary btn-sm m-2" v-if="order !== 0"
                            @click="moveItemUp(order, 'locomotives')">&uarr;
                    </button>
                    <button class="btn btn-primary btn-sm m-2" v-if="order !== selectedLocomotives.length - 1"
                            @click="moveItemDown(order, 'locomotives')">&darr;
                    </button>
                    <button class="btn btn-danger btn-sm m-2" @click="removeItem(order, 'locomotives')">Usuń</button>
                  </td>
                </tr>
                <tr v-for="(car, order) in selectedCars" :key="car.carId">
                  <td><input type="text" v-model="car.carNumber"></td>
                  <td>{{ car.carType }}</td>
                  <td>{{ car.name }}</td>
                  <td><img :src="getPhotoUrl(car. pictureLink, 'cars')" alt=""/></td>
                  <td>
                      <div class="form-group m-2">
                        <div class="form-group">
                          <input type="text" id="additionalInfoCar" v-model="car.additionalInfo" style="width: 400px"/>
                        </div>
                      </div>
                  </td>
                  <td>
                    <button class="btn btn-primary btn-sm m-2" v-if="order !== 0" @click="moveItemUp(order, 'cars')">
                      &uarr;
                    </button>
                    <button class="btn btn-primary btn-sm m-2" v-if="order !== selectedCars.length - 1"
                            @click="moveItemDown(order, 'cars')">&darr;
                    </button>
                    <button class="btn btn-danger btn-sm m-2" @click="removeItem(order, 'cars')">Usuń</button>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>

            <!--Formularz dodawania pociągu-->
            <form>
              <div class="form-group">
                <label for="trainNumber">Numer:</label>
                <input pattern="[0-9/()]" id="trainNumber" v-model="trainNumber" required>
              </div>
              <div class="form-group">
                <label for="trainName">Nazwa pociągu:</label>
                <input type="text" id="trainName" v-model="trainName">
              </div>
              <div class="form-group">
                <label for="route">Trasa:</label>
                <input type="text" id="route" v-model="route" required>
              </div>
              <div class="form-group">
                <label for="runningDates">Kursuje:</label>
                <input type="text" id="runningDates" v-model="runningDates" required>
              </div>
              <div class="form-group">
                <label for="additionalInfo">Dodatkowe informacje:</label>
                <textarea type="text" id="additionalInfo" v-model="additionalInfoTrain"/>
              </div>
              <div class="col-md-12 text-center">
                <button type="submit" class="mt-5 submit-button btn btn-primary" @click.prevent="submitForm"
                        :disabled="!selectedCars.length || !selectedLocomotives.length">Zatwierdź
                </button>
              </div>
            </form>
          </div>
        </div>

        <!--Tabela z wybieraniem wagonów i lokomotyw-->
        <h3 class="mt-5">Wybierz wagony oraz lokomotywy do nowego pociągu: </h3>
        <div>
          <button class="btn btn-dark m-2" @click="displayCars = true; displayLocomotives = false">Wagony</button>
          <button class="btn btn-dark" @click="displayLocomotives = true; displayCars = false">Lokomotywy</button>
        </div>

        <!--Filtr-->
        <div class="form-outline p-2 d-flex align-items-center">
          <label for="nameFilter" style="font-weight: bold">Filtruj po nazwie:</label>
          <input type="text" v-model="nameFilter" class="form-control" id="nameFilter">
        </div>
        <!--Wagony-->
        <div v-if="displayCars">
          <table class="table table-striped table-bordered">
            <thead class="table-dark">
            <tr>
              <th>ID</th>
              <th>Typ</th>
              <th>Nazwa</th>
              <th>Wygląd</th>
              <th>Akcje</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="car in filteredTable" :key="car.carId">
              <td>{{ car.carId }}</td>
              <td>{{ car.carType }}</td>
              <td>{{ car.name }}</td>
              <td><img :src="getPhotoUrl(car.pictureLink, 'cars')" alt=""/></td>
              <td>
                <div style="display: flex; justify-content: center">
                  <button class="btn btn-success" @click="selectCar(car.carId)">Wybierz</button>
                </div>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
        <!--Lokomotywy-->
        <div v-if="displayLocomotives">
          <table class="table table-striped table-bordered">
            <thead class="table-dark">
            <tr>
              <th>ID</th>
              <th>Wygląd</th>
              <th>Nazwa</th>
              <th>Prędkość</th>
              <th>Waga</th>
              <th>Akcje</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="locomotive in filteredTable" :key="locomotive.locomotiveId">
              <td>{{ locomotive.locomotiveId }}</td>
              <td>{{ locomotive.name }}</td>
              <td><img :src="getPhotoUrl(locomotive.pictureLink, 'locomotives')" alt=""/></td>
              <td>{{ locomotive.drivingSpeed }}</td>
              <td>{{ locomotive.weight }}</td>
              <td>
                <div style="display: flex; justify-content: center">
                  <button class="btn btn-success" @click="selectLocomotive(locomotive.locomotiveId)">Wybierz</button>
                </div>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      displayCars: true,
      displayLocomotives: false,
      filteredCars: [],
      filteredLocomotives: [],
      selectedCarId: 0,
      editForm: false,
      nameFilter: '',
      trainNumber: '',
      trainName: '',
      route: '',
      runningDates: '',
      additionalInfoTrain: '',
      additionalInfoCar: '',
      additionalInfoLocomotive: '',
      cars: [],
      locomotives: [],
      selectedCars: [],
      selectedLocomotives: []
    }
  },
  created() {
    this.fetchCars();
    this.fetchLocomotives();
  },
  methods: {
    fetchCars() {
      fetch(`http://localhost:3081/api/cars`)
          .then((res) => res.json())
          .then((data) => {
            this.cars = data;
          });
    },
    fetchLocomotives() {
      fetch(`http://localhost:3081/api/locomotives`)
          .then((res) => res.json())
          .then((data) => {
            this.locomotives = data;
          });
    },
    submitForm() {
      if (!this.trainNumber.match(/^[0-9()]+$/)) {
        alert('Numer pociągu może zawierać tylko cyfry, nawiasy i ukośniki!')
      } else if (this.trainNumber && this.route && this.runningDates) {
        let addTrainData = {
          trainNumber: this.trainNumber,
          trainName: this.trainName,
          route: this.route,
          runningDates: this.runningDates,
          additionalInfoTrain: this.additionalInfoTrain,
          selectedCars: this.selectedCars,
          selectedLocomotives: this.selectedLocomotives
        };
        this.handleFormSubmit(addTrainData);
      } else {
        alert('Wypełnij wszystkie pola!')
      }
    },
    handleFormSubmit(addTrainData) {
      fetch('http://localhost:3081/api/trains/add', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(addTrainData)
      })
          .then(res => res.json())
          .then(() => {
          })
          .catch(err => {
            console.error(err)
          })
    },
    getPhotoUrl: function (img, folder) {
      return require(`../assets/${folder}/${img}`);
    },
    selectCar(carId) {
      let selectedCar = this.cars.find(car => car.carId === carId)
      let uniqueCar = Object.assign({}, selectedCar)
      this.selectedCars.push(uniqueCar)
      console.log(this.selectedCars)
    },
    selectLocomotive(locomotiveId) {
      let selectedLocomotive = this.locomotives.find(locomotive => locomotive.locomotiveId === locomotiveId)
      this.selectedLocomotives.push(selectedLocomotive)
      console.log(this.selectedLocomotives)
    },
    removeItem(index, type) {
      if (type === 'cars') {
        this.selectedCars.splice(index, 1);
        console.log(this.selectedCars)
      } else if (type === 'locomotives') {
        this.selectedLocomotives.splice(index, 1);
        console.log(this.selectedLocomotives)
      }
    },
    moveItemUp(order, type) {
      if (type === 'locomotives') {
        let currentItem = this.selectedLocomotives[order];
        this.selectedLocomotives.splice(order, 1);
        this.selectedLocomotives.splice(order - 1, 0, currentItem);
      } else if (type === 'cars') {
        let currentItem = this.selectedCars[order];
        this.selectedCars.splice(order, 1);
        this.selectedCars.splice(order - 1, 0, currentItem);
      }
    },
    moveItemDown(order, type) {
      if (type === 'locomotives') {
        let currentItem = this.selectedLocomotives[order];
        this.selectedLocomotives.splice(order, 1);
        this.selectedLocomotives.splice(order + 1, 0, currentItem);
      } else if (type === 'cars') {
        let currentItem = this.selectedCars[order];
        this.selectedCars.splice(order, 1);
        this.selectedCars.splice(order + 1, 0, currentItem);
      }
    },
  },
  computed: {
    filteredTable() {
      if (this.displayLocomotives === true) {
        return this.locomotives.filter(locomotive => locomotive.name.toLowerCase().includes(this.nameFilter.toLowerCase()))
            .sort((a, b) => a.locomotiveId - b.locomotiveId);
      } else {
        return this.cars.filter(car => car.name.toLowerCase().includes(this.nameFilter.toLowerCase()))
            .sort((a, b) => a.carId - b.carId);
      }
    },
  },
}
</script>

<style scoped>
#select-table thead {
  background-color: #0099CC;
  color: white;
}

form {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  margin: 2rem 0;
}

.form-group {
  display: inline-flex;
  flex-direction: column;
  align-items: center;
  margin: 0 1rem;
  width: 30%;
}

label {
  margin-top: 20px;
  margin-bottom: 0.5rem;
}

input {
  width: 100%;
}

textarea {
  width: 100%;
  height: 150px;
  resize: none;
}
</style>
