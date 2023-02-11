<template>
  <div class="container-fluid m-0 p-0">
    <div id="trains-table" class="full-height p-5">
      <div class="row p-5 mt-5">
        <div class="row pb-5 h-100 d-flex align-items-center justify-content-center">
          <h1 class="ml-md-4 mr-md-4 p-3">Zestawienie pociągu {{ trainFullName }}</h1>
          <div class="row p-5">
            <table class="table table-striped table-bordered">
              <thead class="table-dark">
              <tr>
                <th>Numer wagonu</th>
                <th>Nazwa</th>
                <th>Wygląd</th>
                <th>Udogodnienia</th>
                <th>Liczba siedzeń</th>
                <th>Dodatkowe informacje</th>
                <th>Schemat wagonu</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="locomotive in locomotives" :key="locomotive.locomotiveId">
                <td></td>
                <td>{{ locomotive.name }}</td>
                <td><img :src="getLocomotiveUrl(locomotive.pictureLink)" alt=""/></td>
                <td></td>
                <td></td>
                <td style='font-size:80%'>Prędkość: {{ locomotive.drivingSpeed }}, <br> Waga:{{ locomotive.weight }},
                  <br> {{ locomotive.locomotiveAdditionalInfo }}
                </td>
                <td></td>
              </tr>
              <tr v-for="(car, index) in cars" :key="car.carId">
                <td>{{ car.carNumber }}</td>
                <td>{{ car.name }}</td>
                <td><img :src="getCarUrl(car.pictureLink)" alt=""/></td>
                <td>
                  <div class="pictograms-container">
                    <img id="pictograms"
                         v-if="getPictogramForTravelClass(car.travelClass)"
                         :src="getPictogramForTravelClass(car.travelClass)"
                         :title="getPictogramTitleForTravelClass(car.travelClass)"
                         alt=""/>
                    <div v-for="feature in features" :key="index + '-' + feature.name">
                      <img id="pictograms"
                           v-if="car[feature.name]"
                           :src="getPictogramUrl(feature.pictogram)"
                           :title="feature.title" alt=""/>
                    </div>
                  </div>
                </td>
                <td>{{ car.numberOfSeats }}</td>
                <td style='font-size:80%'>{{ car.additionalInfo }}</td>
                <td>
                  <div v-if="car.schemaLink">
                    <img @click="toggleSchema(car.schemaLink, car.name)" id="pictograms"
                         src="@/assets/pictograms/schema.svg" alt=""/>
                  </div>
                </td>
              </tr>
              </tbody>
            </table>
          </div>

          <!-- Car schema modal -->
          <div v-if="showImage" class="modal modal-overlay"
               style="display:flex; justify-content:center; align-items:center;">
            <div class="modal-dialog modal-lg" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title">Schemat dla wagonu: {{ carName }}</h5>
                  <button @click="closeSchema" type="button" class="btn-close" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                  <img :src="schemaSrc" alt=""/>
                  <div class="-credit-card">Rysunki autorstwa <br> (©): rysmichala (©): DarekP</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <Footer>
    <small>Autorzy rysunków (&copy;): Łukasz Paduch , EN57 , Krzysztof Dobrzański , Sebastian , pd .</small>
    <br>
    <small>Schematy autorstwa (&copy;): rysmichala (©): DarekP</small>
  </Footer>
</template>

<script>
import Footer from "@/components/Footer.vue";

export default {
  name: "TrainComposition",
  components: {
    Footer,
  },
  data() {
    return {
      trainId: this.$route.params.trainId,
      trainFullName: this.$route.params.trainFullName,
      cars: [],
      locomotives: [],
      showImage: false,
      schemaSrc: '',
      carName: '',
      features: [
        {name: "airConditioning", pictogram: "air-conditioning", title: "Klimatyzacja"},
        {name: "barCar", pictogram: "bar", title: "Wagon barowy"},
        {name: "bicycles", pictogram: "bikes", title: "Miejsce dla rowerów"},
        {name: "compartmentless", pictogram: "compartmentless-car", title: "Wagon bezprzedziałowy"},
        {name: "diningCar", pictogram: "restaurant", title: "Wagon restauracyjny"},
        {name: "disabledLift", pictogram: "disabled-lift", title: "Winda dla niepełnosprawnych"},
        {name: "disabledSeats", pictogram: "disabled", title: "Miejsca dla niepełnosprawnych"},
        {name: "electricalOutlets", pictogram: "230V", title: "Gniazdka 230V"},
        {name: "sleepingCar", pictogram: "sleeping-car", title: "Wagon sypialny"},
        {name: "toilet", pictogram: "wc", title: "Toaleta"},
        {name: "wifi", pictogram: "wi-fi", title: "WI-FI"}
      ]
    };
  },
  methods: {
    fetchCars() {
      fetch(`http://localhost:3081/api/${this.trainId}/locomotive`)
          .then((res) => res.json())
          .then((data) => {
            this.locomotives = data;
          });
    },
    fetchLocomotives() {
      fetch(`http://localhost:3081/api/${this.trainId}/cars`)
          .then((res) => res.json())
          .then((data) => {
            this.cars = data;
          });
    },
    getCarUrl: function (img) {
      return require(`../assets/cars/${img}`);
    },
    getLocomotiveUrl: function (img) {
      return require(`../assets/locomotives/${img}`);
    },
    getPictogramForTravelClass(travelClass) {
      if (!travelClass) return null;
      switch (travelClass) {
        case 1:
          return this.getPictogramUrl('1class');
        case 2:
          return this.getPictogramUrl('2class');
        case 3:
          return this.getPictogramUrl('1-2class');
        default:
          return null;
      }
    },
    getPictogramTitleForTravelClass(travelClass) {
      switch (travelClass) {
        case 1:
          return '1 Klasa';
        case 2:
          return '2 Klasa';
        case 3:
          return '1-2 Klasa';
        default:
          return '';
      }
    },
    getPictogramUrl: function (pictogram) {
      return require(`../assets/pictograms/${pictogram}.svg`);
    },
    toggleSchema(schemaLink, name) {
      this.showSchema(schemaLink, name);
      this.schemaVisible = !this.schemaVisible;
    },
    showSchema(schemaLink, carName) {
      this.carName = carName;
      this.showImage = true;
      this.schemaSrc = require(`@/assets/car-schemas/${schemaLink}`);
    },
    closeSchema() {
      this.showImage = false;
      this.schemaSrc = '';
    }
  },
  created() {
    this.fetchCars();
    this.fetchLocomotives();
  },
};
</script>

<style scoped>
.pictograms-container {
  display: flex;
  grid-template-columns: repeat(4, 1fr);
}

table {
  border-collapse: collapse;
  border-spacing: 1em;
}

#pictograms {
  width: 40px;
  height: 40px;
  margin: 1px;
}

.modal-dialog {
  position: fixed;
  display: table;
  overflow-y: auto;
  overflow-x: auto;
  width: auto;
  min-width: 300px;
}

.modal-overlay {
  background-color: rgba(0, 0, 0, 0.5);
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: none;
}
</style>
