<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4 mx-auto">
        <div class="card text-center">
          <div class="card-header">
            <h1>Zaloguj się</h1>
          </div>
          <div class="card-body">
            <form @submit.prevent="login">
              <div class="form-group">
                <label for="username">Login</label>
                <input type="text" class="form-control" id="username" v-model="username" required>
              </div>
              <div class="form-group">
                <label for="password">Hasło</label>
                <input type="password" class="form-control" id="password" v-model="password" required>
              </div>
              <button type="submit" class="btn btn-primary mt-2">Zatwierdź</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: "",
      password: ""
    }
  },
  methods: {
    login() {
      let requestBody = {
        username: this.username,
        password: this.password
      };

      fetch("http://localhost:3081/api/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(requestBody)
      })
          .then(response => response.json())
          .then(data => {
            console.log(data);
            localStorage.setItem("token", data.token);
            this.$router.push({ name: 'admin'})
          })
          .catch(error => {
            alert("Błędny login lub hasło")
            console.error(error);
          });
    }
  }
};
</script>

<style scoped>
.card {
  margin-top: 70%;
}
</style>
