<template>
  <div class="app">
    <NavigationBar v-if="$route.name !== 'admin' && $route.name !== 'login' && $route.name !== 'trains'"/>
    <router-view/>
  </div>
</template>

<script>
import NavigationBar from "@/components/NavigationBar.vue";
export default {
  name:'App',
  components: {
    NavigationBar,
  }
}
</script>

<style lang="scss">
@import url("https://fonts.googleapis.com/css2?family=Raleway:wght@400;500&display=swap");

#app {
  font-family: "Raleway", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

.full-height {
  min-height: 100vh;
}
</style>
