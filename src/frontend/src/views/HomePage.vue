<template>
  <div class="container-fluid m-0 p-0">

    <div id="home-page" class="full-height p-5">
      <div class = "p-5">
      <div class="container pt-3">
        <div class="row pb-5 h-100 d-flex align-items-center justify-content-center">
          <div class="col-lg-6 my-auto pt-4">
            <div id = "slogan" class="text-center text-white p-4 mb-4">
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
    <div id="about" class="bg-warning p-4"></div>
    <div id="contact" class="bg-info p-4"></div>
    <div id="footer" class="bg-primary p-4"></div>
  </div>
</template>

<script>
export default {
  data() {
  }
}
</script>

<style lang="scss" scoped>
#home-page {
  background: linear-gradient(0deg, rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.7)), url('../assets/bg-mt.jpg') no-repeat center center;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
</style>