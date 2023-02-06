<template>
  <div>
    <div>
      <button class="btn btn-dark" @click="displayPanel('cars')">Cars</button>
      <button class="btn btn-dark" @click="displayPanel('locomotives')">Locomotives</button>
      <button class="btn btn-dark" @click="displayPanel('trains')">Trains</button>
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
  }
};
</script>

<style scoped>
button {
  margin: 2px;
}
</style>
