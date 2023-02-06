<template>
  <div class="container my-5 border border-">
    <h3 class="text-center mb-3 pt-5 pb-0">Edytuj wagon</h3>
    <form>
      <div class="form-group">
        <label for="name">Nazwa:</label>
        <input type="text" id="name" v-model="name" required>
      </div>
      <div class="form-group">
        <label for="weight">Waga:</label>
        <input type="text" id="weight" v-model="weight">
      </div>
      <div class="form-group">
        <label for="drivingSpeed">Prędkość:</label>
        <input type="text" id="drivingSpeed" v-model="drivingSpeed">
      </div>
      <button type="submit" class="submit-button btn btn-primary" @click.prevent="submitForm">Submit</button>
    </form>
  </div>
</template>

<script>
export default {
  props: ['selectedLocomotiveId'],
  name: "EditLocomotive",
  data() {
    return {
      name: '',
      drivingSpeed: '',
      weight: '',
    };
  },
  created() {
    this.getLocomotiveData(this.selectedLocomotiveId).then(locomotiveData => {
      this.name = locomotiveData.name;
      this.weight = locomotiveData.weight;
      this.drivingSpeed = locomotiveData.drivingSpeed;
    });
  },
  methods: {
    getLocomotiveData(selectedLocomotiveId) {
        return fetch(`http://localhost:3081/api/locomotives/get/${selectedLocomotiveId}`)
          .then(response => {
            if (!response.ok) {
              throw new Error(`Failed to fetch locomotive with id: ${selectedLocomotiveId}`);
            }
            return response.json();
          })
          .catch(error => {
            console.error(error);
          });
    },
    submitForm() {
      let editLocomotiveData = {
        name: this.name,
        weight: this.weight,
        drivingSpeed: this.drivingSpeed,
      };
      this.handleFormSubmit(editLocomotiveData);
    },
    handleFormSubmit(editLocomotiveData) {
      fetch('http://localhost:3081/api/locomotives/update/' + this.selectedLocomotiveId, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(editLocomotiveData)
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

label {
  font-weight: bold;
  margin-top: 20px;
  margin-bottom: 0.5rem;
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

.submit-button {
  display: block;
  margin: 20px auto 0 46%;
}
</style>
