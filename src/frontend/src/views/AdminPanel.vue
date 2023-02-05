<template>
  <div>
    <div>
      <button class="btn btn-dark" @click="displayPanel('cars')">Cars</button>
      <button class="btn btn-dark" @click="displayPanel('locomotives')">Locomotives</button>
      <button class="btn btn-dark" @click="displayPanel('trains')">Trains</button>
    </div>

    <div v-if="showPanel && panelType === 'cars'">
      <AdminPanelCar/>
    </div>
    <div v-if="showPanel && panelType === 'locomotives'">
      <AdminPanelLocomotive/>
    </div>
  </div>
</template>

<script>
import AdminPanelCar from "@/components/AdminPanelCar.vue";
import AdminPanelLocomotive from "@/components/AdminPanelLocomotive.vue";

export default {
  name: "AdminPanel.vue",
  components: {
    AdminPanelCar,
    AdminPanelLocomotive
  },
  data() {
    return {
      showPanel: false,
      panelType: "",
      locomotives: [],
      trains: []
    };
  },
  methods: {
    displayPanel(panelType) {
      this.panelType = panelType;
      this.showPanel = true;
    },
  }
};
</script>

<style scoped>
button {
  margin: 2px;
}
</style>
