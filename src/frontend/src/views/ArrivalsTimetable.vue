<template>
  <div class="container-fluid m-0 p-0">
    <div id="info" class="full-height p-5">
      <div class="row pb-5 h-100 d-flex align-items-center justify-content-center">
        <div class="col-lg-6 my-auto pt-4">
          <div class="row p-5 mt-5">
            <div class="container pt-5 border p-md-5 mt-5 bg-white rounded shadow">
              <h1 class="ml-md-4 mr-md-4 p-3">Tablica Przyjazdów</h1>
              <h5 class="ml-md-4 mr-md-4 p-4">
                Stoisz na małej stacji bez tablicy i nie wiesz kiedy przyjedzie pociąg, a może ma opóźnienie?
                Wpisz nazwę stacji i uzyskasz potrzebną ci wiedzę.
              </h5>
            </div>
          </div>
        </div>
      </div>
    </div>

    <TimetableComponent endpoint="arrivals"/>
    <Footer/>
  </div>
</template>

<script>
import Footer from "@/components/Footer.vue"
import TimetableComponent from "@/components/TimetableComponent.vue";

export default {
  name: "ArrivalsTimetable",
  components: {
    Footer,
    TimetableComponent,
  },
  methods: {}
}
</script>

<style scoped>

#info {
  background: linear-gradient(0deg, rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.9)), url('../assets/backgrounds/arrivals-bg.jpeg') no-repeat center center;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
</style>
