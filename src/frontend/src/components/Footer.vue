<template>
  <div id="footer" class="p-4">
    <footer class="text-light p-1">
<!--      <small>Autor zdjęcia w tle: &copy; 2022, Franciszek. P</small>-->
<!--      <br>-->
      <slot></slot>
      <br>
      <small>&copy; 2023, Przemysław Rutkowski, pokolei.eu</small>
    </footer>
  </div>
</template>

<script>
export default {
  name: "Footer.vue"
}
</script>

<style scoped>
#footer {
  max-height: 13vh;
  background-color: #252223;
}
</style>
