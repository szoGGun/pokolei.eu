<template>
  <div class="container-fluid m-0 p-0">
    <div id="trains-table" class="full-height p-5">
      <div class="row pb-5 h-100 d-flex align-items-center justify-content-center">
        <h1 class="ml-md-4 mr-md-4 p-3">Lista wszystkich lokomotyw w bazie</h1>
      </div>

      <div class="form-outline p-2 d-flex align-items-center">
        <label for="trainNameFilter" style="font-weight: bold">Filtruj po nazwie:</label>
        <input type="text" v-model="trainNameFilter" class="form-control" id="trainNameFilter">
      </div>
      <button class="btn btn-primary add-button" @click="redirectToAddTrain">Dodaj pociąg</button>
      <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
          <th>Numer</th>
          <th>Nazwa pociągu</th>
          <th>Trasa</th>
          <th>Kursuje</th>
          <th>Dodatkowe informacje</th>
          <th>Akcje</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="train in filteredTrains" v-bind:key="train.trainId">
          <td> {{ train.trainNumber }}</td>
          <td> {{ train.trainName }}</td>
          <td> {{ train.route }}</td>
          <td> {{ train.runningDates }}</td>
          <td> {{ train.additionalInfo }}</td>
          <td>
            <div style="display: flex; justify-content: center">
              <button class="btn btn-success" @click="redirectToEditTrain(train.trainId)">Edytuj</button>
              <button class="btn btn-danger" @click="openConfirmModal(train.trainId)">Usuń</button>
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
              <h5 class="modal-title">Czy na pewno chcesz usunąć ten pociąg?</h5>
              <button class="btn btn-danger" @click="deleteTrain(selectedTrainId)">Tak</button>
              <button class="btn btn-primary" @click="showConfirmModal = false">Nie</button>
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>

export default {
  name: "AdminPanelTrain",
  data() {
    return {
      trains: [],
      showImage: false,
      trainNameFilter: '',
      selectedTrainId: null,
      showConfirmModal: false,
    };
  },
  created() {
    this.fetchTrains();
  },
  methods: {
    fetchTrains() {
      fetch(`http://localhost:3081/api/trains`)
          .then((res) => res.json())
          .then((data) => {
            this.trains = data;
          });
    },
    deleteTrain(trainId) {
      fetch(`http://localhost:3081/api/trains/delete/${trainId}`, {
        method: "DELETE",
      })
          .then(() => {
            this.showConfirmModal = false;
            this.fetchTrains()
          })
          .catch((error) => {
            console.error("Error deleting train:", error);
          });
    },
    openConfirmModal(trainId) {
      this.showConfirmModal = true;
      this.selectedTrainId = trainId;
    },
    redirectToAddTrain() {
      this.$router.push({name: "addTrain"});
    },
    redirectToEditTrain(trainId) {
      this.$router.push({
        name: "editTrain",
        params: {
          trainId: trainId
        }
      });
    },
  },
  computed: {
    filteredTrains() {
      return this.trains.filter(train => train.trainName.toLowerCase().includes(this.trainNameFilter.toLowerCase()))
          .sort((a, b) => (a.trainNumber > b.trainNumber) ? 1 : -1);
    },
  }
}
</script>

<style scoped>
button {
  font-size: 14px;
  padding: 5px 10px;
  margin: 2px;
}

.add-button {
  margin-top: 20px;
  margin-bottom: 20px;
  font-size: 30px;
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
