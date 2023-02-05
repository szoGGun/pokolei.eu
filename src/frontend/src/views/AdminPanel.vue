<template>
  <div>
    <div>
      <button @click="displayPanel('cars')">Cars</button>
      <button @click="displayPanel('locomotives')">Locomotives</button>
      <button @click="displayPanel('trains')">Trains</button>
    </div>

    <div v-if="showPanel && panelType === 'cars'">
      <AdminPanelCar/>
    </div>
    <div v-if="showPanel && panelType === 'trains'">
      <!-- similar to cars section -->
    </div>
  </div>
</template>

<script>
import AdminPanelCar from "@/components/AdminPanelCar.vue";

export default {
  name: "AdminPanel.vue",
  components: {AdminPanelCar},
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
table {
  border-collapse: collapse;
}

th,
td {
  border: 1px solid #dddddd;
  padding: 8px;
  text-align: left;
}
</style>
