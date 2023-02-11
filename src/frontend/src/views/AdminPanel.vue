<template>
  <div>
    <div>
      <button class="btn btn-primary" @click="displayPanel('cars')">Wagony</button>
      <button class="btn btn-primary" @click="displayPanel('locomotives')">Lokomotywy</button>
      <button class="btn btn-primary" @click="displayPanel('trains')">Pociągi</button>
      <button class="btn btn-danger" @click="logout">Wyloguj</button>
    </div>

    <div id="cars-button"  v-if="panelType === 'cars'">
      <AdminPanelCar/>
    </div>
    <div id="locomotives-button" v-if="panelType === 'locomotives'">
      <AdminPanelLocomotive/>
    </div>
    <div id="trains-button" v-if="panelType === 'trains'">
      <AdminPanelTrain/>
    </div>
  </div>
</template>

<script>
import AdminPanelCar from "@/components/AdminPanelCar.vue";
import AdminPanelLocomotive from "@/components/AdminPanelLocomotive.vue";
import AdminPanelTrain from "@/components/AdminPanelTrain.vue";

export default {
  name: "AdminPanel.vue",
  components: {
    AdminPanelTrain,
    AdminPanelCar,
    AdminPanelLocomotive
  },
  data() {
    return {
      panelType: "",
      locomotives: [],
      trains: []
    };
  },
  methods: {
    displayPanel(panelType) {
      this.panelType = panelType;
    },
    logout() {
      localStorage.removeItem("token");
      this.$router.push({ path: "/" });
    }
  }
};
</script>

<style scoped>
button {
  margin: 2px;
}
</style>
