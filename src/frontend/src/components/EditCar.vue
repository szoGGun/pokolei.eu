<template>
  <div class="container my-5 border border-">
    <h3 class="text-center mb-3 pt-5 pb-0">Edytuj wagon</h3>
    <form>
      <div class="form-group">
        <label for="carType">Typ wagonu:</label>
        <input type="text" id="carType" v-model="carType">
      </div>
      <div class="form-group">
        <label for="name">Nazwa:</label>
        <input type="text" id="name" v-model="name" required>
      </div>
      <div class="form-group">
        <label for="numberOfSeats">Liczba siedzeń:</label>
        <input type="number" id="numberOfSeats" v-model="numberOfSeats">
      </div>
      <div class="form-group">
        <label for="travelClass">Klasa wagonu:</label>
        <select id="travelClass" v-model="travelClass">
          <option value="">Żadna</option>
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
      <button type="submit" class="submit-button btn btn-primary" @click.prevent="submitForm">Submit</button>
    </form>
  </div>
</template>

<script>
export default {
  props: ['selectedCarId'],
  name: "EditCar",
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
  created() {
    this.getCartData(this.selectedCarId).then(cartData => {
      this.carType = cartData.carType;
      this.name = cartData.name;
      this.numberOfSeats = cartData.numberOfSeats;
      this.travelClass = cartData.travelClass;
      this.amenities = cartData.amenities;
    });
  },
  methods: {
    getCartData(selectedCarId) {
      return fetch(`http://localhost:3081/api/cars/get/${selectedCarId}`)
          .then(response => {
            if (!response.ok) {
              throw new Error(`Failed to fetch cart with id: ${selectedCarId}`);
            }
            return response.json();
          })
          .catch(error => {
            console.error(error);
          });
    },
    submitForm() {
      let editCarData = {
        carType: this.carType,
        name: this.name,
        numberOfSeats: this.numberOfSeats,
        travelClass: this.travelClass,
        amenities: this.amenities,
      };
      this.handleFormSubmit(editCarData);
    },
    handleFormSubmit(editCarData) {
      fetch('http://localhost:3081/api/cars/update/' + this.selectedCarId, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(editCarData)
      })
          .then(res => res.json())
          .then(() => {
            this.editForm = false
            this.$emit("close-edit-panel");
          })
          .catch(err => {
            console.error(err)
          })
    }
  },
};
</script>

<style scoped>

.edit-car-container {
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

.submit-button {
  display: block;
  margin: 20px auto 0 46%;
}
</style>
