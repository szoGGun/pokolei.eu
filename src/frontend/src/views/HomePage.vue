<template>
  <div class="container-fluid m-0 p-0">

    <!--HOME PAGE-->
    <div id="home-page" class="full-height p-5">
      <div class="p-5">
        <div class="container pt-3">
          <div class="row pb-5 h-100 d-flex align-items-center justify-content-center">
            <div class="col-lg-6 my-auto pt-4">
              <div id="slogan" class="text-center text-white p-4 mb-4">
                <h1 class="display-3">POKOLEI.EU</h1>
                <h3>Zestawienia pociągów i wiele więcej</h3>
              </div>

              <div class="border p-md-5 bg-white rounded shadow">
                <h2>Wyszukiwarka zestawień</h2>
                <form>
                  <div class="form-group">
                    <label for="trainNumber">Jakiego pociągu szukasz?</label>
                    <input id="trainNumber"
                           placeholder="Wpisz numer pociągu"
                           class="form-control mt-3"
                    >
                    <small id="trainHelp" class="form-text text-muted ">Pociągu możesz wyszukać za pomocą jego nazwy np.
                      Sobieski, ale również w formie numeru np. 106 </small>
                    <div class="p-d-flex p-jc-center">
                      <button type="submit" class="btn btn-success mt-2 p-jc-center">Wyszukaj</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!--ABOUT-->
    <div id="about" class="bg-light p-3 p-md-5">
      <div class="row">
        <div class="col-lg">
          <img src="../assets/icons/timetableRail.png" height="120" class="m-4">
          <h4 class="ml-md-4 mr-md-4 color-indigo">Tablice Informacyjne</h4>
          <h5 class="ml-md-4 mr-md-4">
            Czekasz na stacji, ale najbliższa tablica informacyjna jest daleko lub nie ma jej wcale? Sprawdź najbliższe
            odjazdy
            z twojej stacji.
          </h5>
        </div>
        <div class="col-lg">
          <img src="../assets/icons/carriage.png" height="120" class="m-4">
          <h4 class="ml-md-4 mr-md-4 color-indigo">Zestawienia i schematy wagonów</h4>
          <h5 class="ml-md-4 mr-md-4">
            Chcesz podróżować z bliską osobą, ale nie wiesz czy miejsca na bilecie który kupujesz są obok siebie?
            Sprawdź zestawienia i schematy wagonów.
          </h5>
        </div>
        <div class="col-lg">
          <img src="../assets/icons/timetable.png" height="120" class="m-4">
          <h4 class="ml-md-4 mr-md-4 color-indigo">Tablice z historią opóźnień</h4>
          <h5 class="ml-md-4 mr-md-4">
            Jesteś zmuszony wybrać relację z przesiadką, ale boisz się, że twój pierwszy pociąg się spóźni?
            Sprawdź historyczne opóźnienia pociągów.
          </h5>
        </div>
      </div>
    </div>

    <!--CONTACT FORM-->
    <div id="contact" class="bg-info p-4">
      <div class="row justify-content-center mt-3 mb-3">

        <div class="col-lg-4">
          <h2>Masz jakieś pytania albo uwagi?</h2>
          <p>Skontaktuj się z nami wypełniając poniższy formularz.</p>

          <form>
            <div class="form-group text-left">
              <input type="email"
                     class="form-control"
                     placeholder="Wprowadź swój email"
              >
              <textarea class="form-control mt-3"
                        placeholder="Wprowadź swoją wiadomość"
                        rows="5"
              ></textarea>
            </div>
            <button type="submit" class="btn btn-primary mt-2 p-jc-center">Wyślij wiadomość</button>
          </form>
        </div>
      </div>
    </div>

    <!--FOOTER-->
    <Footer>
      <small>Autor zdjęcia w tle: (&copy;) 2022, Franciszek. P</small>
    </Footer>
  </div>
</template>

<script>
import Footer from "@/components/Footer.vue";

export default {
  name: "HomePage",
  components: {
    Footer,
  },
  data() {

  }
}
</script>

<style lang="scss" scoped>
#home-page {
  background: linear-gradient(0deg, rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.7)), url('../assets/backgrounds/home-page-bg.jpg') no-repeat center center;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}

#about {
  min-height: 40vh;
}

#contact {
  background-color: #57AEAF !important;
}
</style>
