<template>
  <div class="container-fluid m-0 p-0">
    <div id="trains-table" class="full-height p-5">
      <div class="row pb-5 h-100 d-flex align-items-center justify-content-center">
        <h1 class="ml-md-4 mr-md-4 p-3">Lista wszystkich lokomotyw w bazie</h1>
      </div>
      <form v-if="editForm" @close="editForm = false">
        <EditLocomotive :selectedLocomotiveId="selectedLocomotiveId" @close-edit-panel="closeEditPanel"/>
      </form>
      <div class="form-outline p-2 d-flex align-items-center">
        <label for="nameFilter" style="font-weight: bold">Filtruj po nazwie:</label>
        <input type="text" v-model="nameFilter" class="form-control" id="nameFilter">
      </div>
      <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
          <th>ID</th>
          <th>Nazwa</th>
          <th>Wygląd</th>
          <th>Prędkość</th>
          <th>Waga</th>
          <th>Akcje</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="locomotive in filteredLocomotives" :key="locomotive.locomotiveId">
          <td>{{ locomotive.locomotiveId }}</td>
          <td>{{ locomotive.name }}</td>
          <td><img :src="getLocomotiveUrl(locomotive.pictureLink)" alt=""/></td>
          <td>{{ locomotive.drivingSpeed }}</td>
          <td>{{ locomotive.weight }}</td>
          <td>
            <div style="display: flex; justify-content: center">
              <button class="btn btn-success" @click="selectedLocomotiveId = locomotive.locomotiveId; editForm = true">Edytuj</button>
              <button class="btn btn-danger" @click="openConfirmModal(locomotive.locomotiveId)">Usuń</button>
            </div>
          </td>
        </tr>
        </tbody>
      </table>

      <!-- Remove confirmation modal -->
      <div v-if="showConfirmModal" class="modal modal-overlay" style="display:flex; justify-content:center; align-items:center;">
        <div class="modal-dialog modal-lg" role="document">
          <div class="modal-content">
            <div class="modal-body">
              <h5 class="modal-title">Czy na pewno chcesz usunąć tą lokomotywę?</h5>
              <button class="btn btn-danger" @click="deleteLocomotive(selectedLocomotiveId)">Tak</button>
              <button class="btn btn-primary" @click="showConfirmModal = false">Nie</button>
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import EditLocomotive from "@/components/EditLocomotive.vue";

export default {
  name: "AdminPanelLocomotive",
  components: {EditLocomotive},
  data() {
    return {
      editForm: false,
      locomotives: [],
      showImage: false,
      nameFilter: '',
      selectedLocomotiveId: null,
      showConfirmModal: false,
    };
  },
  created() {
    this.fetchLocomotives();
  },
  methods: {
    fetchLocomotives() {
      fetch(`http://localhost:3081/api/locomotives`)
          .then((res) => res.json())
          .then((data) => {
            this.locomotives = data;
          });
    },
    deleteLocomotive(locomotiveId) {
      fetch(`http://localhost:3081/api/locomotives/delete/${locomotiveId}`, {
        method: "DELETE",
      })
          .then(() => {
            this.showConfirmModal = false;
            this.fetchLocomotives()
          })
          .catch((error) => {
            console.error("Error deleting locomotive:", error);
          });
    },
    getLocomotiveUrl: function (img) {
      return require(`../assets/locomotives/${img}`);
    },
    openConfirmModal(locomotiveId) {
      this.showConfirmModal = true;
      this.selectedLocomotiveId = locomotiveId;
    },
    closeEditPanel() {
      this.editForm = false;
      this.fetchLocomotives()
    }
  },
  computed: {
    filteredLocomotives() {
      return this.locomotives.filter(locomotive => locomotive.name.toLowerCase().includes(this.nameFilter.toLowerCase()))
          .sort((a, b) => a.locomotiveId - b.locomotiveId);
    },
  },
}
</script>

<style scoped>
button {
  font-size: 14px;
  padding: 5px 10px;
  margin: 2px;
}

table {
  margin-left: 0;
}

.modal-dialog {
  position: relative;
  display: table;
  overflow-y: auto;
  overflow-x: auto;
  width: auto;
  min-width: 300px;
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.25);
}
.modal-overlay {
  background-color: rgba(0, 0, 0, 0.5);
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: none;
}
</style>
