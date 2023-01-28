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
      fetch("api/auth/signin", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          username: this.username,
          password: this.password
        })
      })
          .then(response => {
            if (response.status === 200) {
              this.$router.push({name: "admin"});
            } else {
              throw new Error("Invalid credentials");
            }
          })
          .catch(error => {
            console.log(error);
            this.error = "Invalid credentials";
          });
    }
  }
}
</script>

<style>
.card {
  margin-top: 70%;
}
</style>
