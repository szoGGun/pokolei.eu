<template>
  <div class="container-fluid m-0 p-0">

    <div id="info" class="full-height p-5">
      <div class="row pb-5 h-100 d-flex align-items-center justify-content-center">
        <div class="col-lg-6 my-auto pt-4">
          <div class="row p-5 mt-5">
            <div class="container pt-5 border p-md-5 mt-5 bg-white rounded shadow">
              <h1 class="ml-md-4 mr-md-4 p-3">Zestawienia pociągów</h1>
              <h5 class="ml-md-4 mr-md-4 p-4">
                Nie jesteś pewien, w którym miejscu powinieneś usiąść w pociągu, na który czekasz? A może nie wiesz, który
                w kolejności jest twój wagon? Nie martw się, pomożemy Ci w tym problemie. Wystarczy, że wpiszesz nazwę
                pociągu, a my wyświetlimy wszystkie informacje, które Cię interesują.
              </h5>
            </div>
          </div>
        </div>
      </div>
    </div>


    <div id="trains-table" class="full-height p-5">
      <h1 id="headerTitle" class="text-center mb-3">Lista zestawień pociągów</h1>
      <div class="border shadow">
        <div class="form-outline p-2 d-flex align-items-center bg-dark">
          <label class="text-white">Szukaj:</label>
          <input type="search" id="trainInput" v-model="this.searchInput" class="form-control" placeholder="Wpisz nazwę numer lub nazwę pociągu..." aria-label="Szukaj" @keyup="search"/>
        </div>
        <table class="table table-striped table-bordered">
          <thead class="table-dark">
          <tr>
            <th>Numer</th>
            <th>Nazwa pociągu</th>
            <th>Trasa</th>
            <th>Kursuje</th>
            <th>Dodatkowe informacje</th>
          </tr>

          </thead>
          <tbody>
          <tr v-for="train in trains" v-bind:key="train.trainId" @click="goToTrainComposition(train.trainId, train.trainNumber, train.trainName)">
            <td> {{ train.trainNumber }}</td>
            <td> {{ train.trainName }}</td>
            <td> {{ train.route }}</td>
            <td> {{ train.runningDates }}</td>
            <td> {{ train.additionalInfo }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
    <Footer>

    </Footer>
  </div>
</template>
<script>
import Footer from "@/components/Footer.vue";

export default {
  components: {
    Footer,
  },

  data() {
    return {
      trains: [],
      searchInput: ''
    }
  },
  created() {
    this.searchInput = this.$route.params.searchTerm || '';
    this.search();
  },
  methods: {
    search() {
      fetch(`http://localhost:3081/api/trains/search?searchTerm=${this.searchInput}`)
          .then(res => res.json())
          .then(data => {
            this.trains = data;
            this.trains.sort((a, b) => (a.trainNumber > b.trainNumber) ? 1 : -1);
          });
    },
    goToTrainComposition(trainId, trainNumber, trainName) {
      this.$router.push({
        name: 'trainComposition',
        params: {
          trainId: trainId,
          trainFullName: trainNumber + trainName,
        }
      });
    }
  }
}
</script>

<style scoped>
#info {
  background: linear-gradient(0deg, rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.9)), url('../assets/backgrounds/trains-list-bg.jpg') no-repeat center center;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}

#trains-table {
  background-color: #E5E5E5;
}

#headerTitle {
  font-weight: bold;
}

table {
  background-color: #ffffff;
  margin: 0;
}

label {
  margin-left: 0.4rem;
  margin-right: 0.8rem;
  font-weight: bold;
}
</style>
