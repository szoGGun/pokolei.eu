<template>
  <div class="container my-5">
    <h3 class="text-center mb-3">Dodaj wagon</h3>
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="carType">Typ wagonu:</label>
        <input type="text" id="carType" v-model="carType" required>
      </div>
      <div class="form-group">
        <label for="name">Nazwa:</label>
        <input type="text" id="name" v-model="name" required>
      </div>
      <div class="form-group">
        <label for="numberOfSeats">Liczba siedzeń:</label>
        <input type="number" id="numberOfSeats" v-model="numberOfSeats" required>
      </div>
      <div class="form-group">
        <label for="travelClass">Klasa wagonu:</label>
        <select id="travelClass" v-model="travelClass" required>
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">1/2</option>
        </select>
      </div>
      <div class="form-group">
        <label for="amenities.airConditioning">Klimatyzacja:</label>
        <input type="checkbox" id="amenities.airConditioning" v-model="amenities.airConditioning">
      </div>
      <div class="form-group">
        <label for="amenities.barCar">Wagon Barowy:</label>
        <input type="checkbox" id="amenities.barCar" v-model="amenities.barCar">
      </div>
      <div class="form-group">
        <label for="amenities.bicycles">Miejsca dla rowerów:</label>
        <input type="checkbox" id="amenities.bicycles" v-model="amenities.bicycles">
      </div>
      <div class="form-group">
        <label for="amenities.compartmentless">Bezprzedziałowy:</label>
        <input type="checkbox" id="amenities.compartmentless" v-model="amenities.compartmentless">
      </div>
      <div class="form-group">
        <label for="amenities.diningCar">Wagon Barowy:</label>
        <input type="checkbox" id="amenities.diningCar" v-model="amenities.diningCar">
      </div>
      <div class="form-group">
        <label for="amenities.disabledLift">Winda dla niepełnosprawnych:</label>
        <input type="checkbox" id="amenities.disabledLift" v-model="amenities.disabledLift">
      </div>
      <div class="form-group">
        <label for="amenities.disabledSeats">Siedzenia dla niepełnosprawnych:</label>
        <input type="checkbox" id="amenities.disabledSeats" v-model="amenities.disabledSeats">
      </div>
      <div class="form-group">
        <label for="amenities.electricalOutlets">Gniazdka:</label>
        <input type="checkbox" id="amenities.electricalOutlets" v-model="amenities.electricalOutlets">
      </div>
      <div class="form-group">
        <label for="amenities.sleepingCar">Wagon sypialny:</label>
        <input type="checkbox" id="amenities.sleepingCar" v-model="amenities.sleepingCar">
      </div>
      <div class="form-group">
        <label for="amenities.toilet">Toaleta:</label>
        <input type="checkbox" id="amenities.toilet" v-model="amenities.toilet">
      </div>
      <div class="form-group">
        <label for="amenities.wifi">WI-FI:</label>
        <input type="checkbox" id="amenities.wifi" v-model="amenities.wifi">
      </div>
    </form>
    <button type="submit">Submit</button>
  </div>
</template>

<script>
export default {
  name: "AddCar",
  data() {
    return {
      carType: '',
      name: '',
      numberOfSeats: '',
      travelClass: '',
      amenities: {
        airConditioning: false,
        barCar: false,
        bicycles: false,
        compartmentless: false,
        diningCar: false,
        disabledLift: false,
        disabledSeats: false,
        electricalOutlets: false,
        sleepingCar: false,
        toilet: false,
        wifi: false,
      },
    };
  },
  methods: {
    submitForm() {
      // Perform form submission
    },
  },
};
</script>

<style scoped>
.add-car-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

label {
  font-weight: bold;
  margin-top: 20px;
}

form {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  margin: 2rem 0;
}

.form-group {
  display: inline-flex;
  flex-direction: column;
  align-items: center;
  margin: 0 1rem;
  width: 30%;
}

label {
  margin-bottom: 0.5rem;
}

input[type="text"],
input[type="number"],
input[type="checkbox"] {
  width: 100%;
  padding: 0.5rem;
  margin-bottom: 1rem;
}

button[type="submit"] {
  padding: 0.5rem 1rem;
  margin-top: 1rem;
  background-color: #333;
  color: #fff;
}

.container {
  border: 1px solid #ddd;
  padding: 2rem;
  border-radius: 10px;
}
</style>
