<template>
  <div class="container-fluid m-0 p-0">

    <div id="trains-table" class="full-height p-5">
      <div class="row p-5 mt-5">
        <div class="row pb-5 h-100 d-flex align-items-center justify-content-center">
          <h1 class="ml-md-4 mr-md-4 p-3">Zestawienie pociągu {{ trainFullName }}</h1>
        </div>
        <div class="row p-5">
          <table class="table table-striped table-bordered">
            <thead class="table-dark">
            <tr>
              <th>Numer wagonu</th>
              <th>Nazwa</th>
              <th>Wygląd</th>
              <th>Udogodnienia</th>
              <th>Dodatkowe informacje</th>
              <th>Kolejność wagonu w składzie</th>
              <th>Schemat wagonu</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="locomotive in locomotives" :key="locomotive.locomotiveId">
              <td></td>
              <td>{{ locomotive.name }}</td>
              <td><img :src="getLocomotiveUrl(locomotive.pictureLink)" alt=""/></td>
              <td></td>
              <td style='font-size:80%'>Prędkość: {{ locomotive.drivingSpeed }}, Waga:{{ locomotive.weight }}</td>
              <td></td>
              <td></td>
            </tr>
            <tr v-for="(car, index) in cars" :key="car.carId">
              <td>{{ car.carNumber }}</td>
              <td>{{ car.name }}</td>
              <td><img :src="getCarUrl(car.pictureLink)" alt=""/></td>
              <td>
                <div class="image-container">
                  <div v-for="feature in features" :key="index + '-' + feature.name">
                    <img id="pictograms" v-if="car[feature.name]" :src="getPictogramUrl(feature.pictogram)"
                         :title="feature.title" alt=""/>
                  </div>
                </div>
              </td>
              <td style='font-size:80%'>{{ car.additionalInfo }}</td>
              <td>{{ car.order }}</td>
              <td>x</td>
            </tr>
            </tbody>
          </table>
        </div>
        <a @click="showImage = !showImage">Show Image </a>
        <img v-if="showImage" :src="require('@/assets/car-schemas/111A-20_B9nopuz.png')" alt=""/>
      </div>
    </div>
  </div>
  <Footer/>
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
      features: [
        {name: "travelClass", pictogram: "2class", title: "Klasa podróży"},
        {name: "placesForBicycles", pictogram: "bikes", title: "Miejsce dla rowerów"},
        {name: "diningCar", pictogram: "restaurant", title: "Wagon restauracyjny"},
        {name: "barCar", pictogram: "bar", title: "Wagon barowy"},
        {name: "airConditioning", pictogram: "air-conditioning", title: "Klimatyzacja"},
        {name: "electricalOutlets", pictogram: "230V", title: "Gniazdka 230V"},
        {name: "toilet", pictogram: "wc", title: "Toaleta"}
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
    getPictogramUrl: function (pictogram) {
      return require(`../assets/pictograms/${pictogram}.svg`);
    }
  },
  created() {
    this.fetchCars();
    this.fetchLocomotives();
  },
};
</script>

<style scoped>
.image-container {
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
</style>
