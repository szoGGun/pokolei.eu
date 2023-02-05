<template>
  <div class="container-fluid m-0 p-0">
    <div id="trains-table" class="full-height p-5">
      <div class="row pb-5 h-100 d-flex align-items-center justify-content-center">
        <h1 class="ml-md-4 mr-md-4 p-3">Lista wszystkich wagonów w bazie</h1>
      </div>
      <form v-if="editForm" @close="editForm = false">
        <EditCar :selectedCarId="selectedCarId" @close-edit-panel="closeEditPanel"/>
      </form>
      <div class="form-outline p-2 d-flex align-items-center">
        <label for="nameFilter" style="font-weight: bold">Filtruj po nazwie:</label>
        <input type="text" v-model="nameFilter" class="form-control" id="nameFilter">
      </div>
      <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
          <th>ID</th>
          <th>Typ</th>
          <th>Nazwa</th>
          <th>Zdjęcie</th>
          <th>Liczba siedzeń</th>
          <th>Klasa</th>
          <th>Klimatyzacja</th>
          <th>Barowy</th>
          <th>Rowery</th>
          <th>Bezprzedziałowy</th>
          <th>Restauracyjny</th>
          <th>Winda dla niepeł.</th>
          <th>Miejsce dla niepeł.</th>
          <th>Gniazdka</th>
          <th>Sypialny</th>
          <th>Toaleta</th>
          <th>WIFI</th>
          <th>Schemat</th>
          <th>Akcje</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="car in filteredCars" :key="car.carId">
          <td>{{ car.carId }}</td>
          <td>{{ car.carType }}</td>
          <td>{{ car.name }}</td>
          <td><img :src="getCarUrl(car.pictureLink)" alt=""/></td>
          <td>{{ car.numberOfSeats }}</td>
          <td>{{ car.travelClass }}</td>
          <td>{{ car.amenities.airConditioning ? '✔' : '' }}</td>
          <td>{{ car.amenities.barCar ? '✔' : '' }}</td>
          <td>{{ car.amenities.bicycles ? '✔' : '' }}</td>
          <td>{{ car.amenities.compartmentless ? '✔' : '' }}</td>
          <td>{{ car.amenities.diningCar ? '✔' : '' }}</td>
          <td>{{ car.amenities.disabledLift ? '✔' : '' }}</td>
          <td>{{ car.amenities.disabledSeats ? '✔' : '' }}</td>
          <td>{{ car.amenities.electricalOutlets ? '✔' : '' }}</td>
          <td>{{ car.amenities.sleepingCar ? '✔' : '' }}</td>
          <td>{{ car.amenities.toilet ? '✔' : '' }}</td>
          <td>{{ car.amenities.wifi ? '✔' : '' }}</td>
          <td>
            <div v-if="car.schemaLink">
              <img @click="showSchema(car.schemaLink, car.name)" id="schema"
                   src="@/assets/pictograms/schema.svg" alt=""/>
            </div>
          </td>
          <td>
            <div style="display: flex;">
              <button class="btn btn-success" @click="selectedCarId = car.carId; editForm = true">Edytuj</button>
              <button class="btn btn-danger" @click="openConfirmModal(car.carId)">Usuń</button>
            </div>
          </td>
        </tr>
        </tbody>
      </table>

      <!-- Remove confirmation modal -->
      <div v-if="showConfirmModal" class="modal modal-overlay" style="display:flex; justify-content:center; align-items:center;">
        <div class="modal-dialog modal-lg" role="document">
          <div class="modal-content">
            <div class="modal-body">
              <h5 class="modal-title">Czy na pewno chcesz usunąć ten wagon?</h5>
                <button class="btn btn-danger" @click="deleteCar(selectedCarId)">Tak</button>
                <button class="btn btn-primary" @click="showConfirmModal = false">Nie</button>
            </div>
          </div>
        </div>
      </div>

      <!-- Car schema modal -->
      <div v-if="showImage" class="modal modal-overlay" style="display:flex; justify-content:center; align-items:center;">
        <div class="modal-dialog modal-lg" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Schemat dla wagonu: {{ carName }}</h5>
              <button @click="closeSchema" type="button" class="btn-close" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <img :src="schemaSrc" alt=""/>
              <div class="-credit-card">Rysunki autorstwa (©): rysmichala</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import EditCar from "@/components/EditCar.vue";

export default {
  name: "AdminPanelCar",
  components: {EditCar},
  data() {
    return {
      editForm: false,
      cars: [],
      showImage: false,
      schemaSrc: '',
      nameFilter: '',
      selectedCarId: null,
      showConfirmModal: false,
    };
  },
  created() {
    this.fetchCars();
  },
  methods: {
    fetchCars() {
      fetch(`http://localhost:3081/api/cars`)
          .then((res) => res.json())
          .then((data) => {
            this.cars = data;
          });
    },
    deleteCar(carId) {
      fetch(`http://localhost:3081/api/cars/delete/${carId}`, {
        method: "DELETE",
      })
          .then(() => {
            this.showConfirmModal = false;
            this.fetchCars()
          })
          .catch((error) => {
            console.error("Error deleting car:", error);
          });
    },
    getCarUrl: function (img) {
      return require(`../assets/cars/${img}`);
    },
    showSchema(schemaLink, carName) {
      this.carName = carName;
      this.showImage = true;
      this.schemaSrc = require(`@/assets/car-schemas/${schemaLink}`);
    },
    closeSchema() {
      this.showImage = false;
      this.schemaSrc = '';
    },
    openConfirmModal(carId) {
      this.showConfirmModal = true;
      this.selectedCarId = carId;
    },
    closeEditPanel() {
      this.editForm = false;
      this.fetchCars()
    }
  },
  computed: {
    filteredCars() {
      return this.cars.filter(car => car.name.toLowerCase().includes(this.nameFilter.toLowerCase()))
          .sort((a, b) => a.carId - b.carId);
    },
  },
}
</script>

<style scoped>
#schema {
  width: 40px;
  height: 40px;
}

button {
  font-size: 12px;
  padding: 5px 10px;
  margin: 1px;
}

table {
  margin-left: 0;
}

.modal-dialog {
  position: relative;
  display: table;
  overflow-y: auto;
  overflow-x: auto;
  width: auto;
  min-width: 300px;
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.25);
}
.modal-overlay {
  background-color: rgba(0, 0, 0, 0.5);
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: none;
}
</style>
