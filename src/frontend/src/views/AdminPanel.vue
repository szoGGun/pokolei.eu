<template>
  <div>
    <div>
      <button @click="displayForm('cars')">Cars</button>
      <button @click="displayForm('locomotives')">Locomotives</button>
      <button @click="displayForm('trains')">Trains</button>
    </div>

    <div v-if="showForm && formType === 'cars'">
      <h3>Add a car</h3>
      <form>
        <!-- form fields here -->
        <input  type="text" placeholder="Name" />
        <button @click="addCar">Add</button>
      </form>
      <table>
        <thead>
        <tr>
          <th>Name</th>
          <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="car in cars" :key="car.id">
          <td>{{ car.name }}</td>
          <td>
            <button @click="editCar(car)">Edytuj</button>
            <button @click="removeCar(car)">Usuń</button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <div v-if="showForm && formType === 'locomotives'">
      <!-- similar to cars section -->
    </div>
    <div v-if="showForm && formType === 'trains'">
      <!-- similar to cars section -->
    </div>
  </div>
</template>

<script>
export default {
  name: "AdminPanel.vue",
  data() {
    return {
      showForm: false,
      formType: "",
      cars: [
        { name: "Car 1" },
        { name: "Car 2" }
      ],
      locomotives: [],
      trains: []
    };
  },
  methods: {
    displayForm(formType) {
      this.formType = formType;
      this.showForm = true;
    },
    addCar(e) {
      e.preventDefault();
      // add car logic here
    }
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
