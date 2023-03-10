<template>
  <div>
    <div id="hud" class="d-flex align-items-center">
      <div id="clock" class="text-sm-2">{{ currentTime }}</div>
      <div id="stationInput" class="col-sm-4">
        <input type="text" v-model="stationName" class="form-control" placeholder="Wpisz nazwę stacji...">
      </div>
      <button id="search-button" class="btn btn-success p-jc-center" @click="search()">Szukaj</button>
    </div>
    <div style="overflow-x:auto;">
      <table class="hud-table">
        <thead>
        <tr>
          <th>Czas</th>
          <th>Pociąg</th>
          <th v-if="this.endpoint === 'arrivals'">Ze stacji</th>
          <th v-else>Do stacji</th>
          <th>Peron/Platforma</th>
          <th>Przewoźnik</th>
          <th>Opóźnienie</th>
          <th>Nazwa Pociągu</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="train in trains" :key="train.id">
          <td>{{ formatDate(train.when) }}</td>
          <td>{{ train.line.name }}</td>
          <td v-if="this.endpoint === 'arrivals'">{{train.provenance}}</td>
          <td v-else>{{ train.direction }}</td>
          <td>{{ train.platform }}</td>
          <td>{{ train.line.productName }}</td>
          <td>{{ formatTimeDifference(train.plannedWhen, train.when) }}</td>
          <td v-if="filterRemarks(train)">{{filterRemarks(train).text}}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    endpoint: {
      type: String,
      required: true
    },
  },
  data() {
    return {
      trains: [],
      stationName: "",
      currentTime: "",
    }
  },
  created() {
    setInterval(() => {
      this.currentTime = new Date().toLocaleTimeString([], {
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit'
      });
    }, 1);
  },
  methods: {
    async search() {
      const res = await fetch("http://localhost:3080/" + this.endpoint + "/?station=" + this.stationName + "&amount=30");
      this.trains = await res.json();
    },
    filterRemarks(train) {
      return train.remarks.find(remark => remark.code === "ZN") || " ";
    },
    formatDate(date) {
      const formattedDate = new Date(date);
      return formattedDate.toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'});
    },
    formatTimeDifference(planned, actual) {
      const difference = (new Date(actual) - new Date(planned)) / 60000;
      return difference ? (difference > 0 ? "+" : "-") + Math.abs(difference) : "";
    },
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Roboto&display=swap');
@import url('https://fonts.googleapis.com/css2?family=JetBrains+Mono&display=swap');

#hud {
  top: 0px;
  background-color: #0000ff;
  left: 0px;
  width: 100%;
  overflow: hidden;
  color: white;
  font-family: 'JetBrains Mono', monospace;
  border-bottom: 1px solid black;
}

#clock {
  font-size: 3rem;
  padding-left: 20px;
}

#stationInput {
  font-family: "Roboto", sans-serif;
  padding-left: 50px;
}

#stationInput > input {
  height: 100%;
  font-size: 2rem;
  background-color: #0000bb;
  border: 0px;
  color: white;
  border-radius: 4px;
  padding: 1px .2em;
  width: calc(100% - 3.2rem);
}

.hud-table {
  position: sticky;
  top: 4.4rem;
  width: 100%;
  background-color: #0000bb;
  color: white;
  font-size: 1em;
  font-family: "Roboto", sans-serif;
  border-collapse: collapse;
}

tr:nth-child(even) {
  background-color: #0000bb;
}

tr:nth-child(odd) {
  background-color: #0000ff;
}

tr {
  border-bottom: 1px solid black;
  height: 5em;
}

th {
  background-color: #0000bb;
  font-size: 1.5em;
}

td {
  font-size: 1.2em;
}

@media only screen and (max-width: 600px) {
  #clock {
    font-size: 1.5rem;
  }
}

@media only screen and (max-width: 600px) {
  #stationInput {
    font-size: 1.5rem;
  }
}
</style>
