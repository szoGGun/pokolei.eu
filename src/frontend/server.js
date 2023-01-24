const createClient = require("hafas-client");
const pkpProfile = require('hafas-client/p/pkp/index.js');
const express = require('express')
const cors = require('cors')
const app = express()
app.use(cors())

const client = createClient(pkpProfile, 'train-api');

app.use(require('cookie-parser')());

async function getNArrivals(station, n) {
    let arrivals = [];
    for (let i = 0; i < n; i += 10) {
        try {
            var batch = await client.arrivals(station, { products: {bus: 1, tram: 1}, results: 10, duration: 600, when: (arrivals.length === 0 ? new Date() : arrivals[arrivals.length - 1].when)});
        } catch (e) {
            var batch = [{direction: "Station not found", platform: "N/A", line: {mode: "special"}, when: new Date(2069, 1, 1)}];
        }
        for (let arrival of batch) {
            arrivals.push(arrival);
        }
    }
    const uniqueArrivals = [];
    for (let arrival of arrivals) {
        if (!uniqueArrivals.some(d => new Date(d.when).getTime() === new Date(arrival.when).getTime())) {
            uniqueArrivals.push(arrival);
        }
    }
    return uniqueArrivals;
}

async function getNDepartures(station, n) {
    let departures = [];
    for (let i = 0; i < n; i += 10) {
        try {
            var batch = await client.departures(station, { products: {bus: 1, tram: 1}, results: 10, duration: 600, when: (departures.length === 0 ? new Date() : departures[departures.length - 1].when)});
        } catch (e) {
            var batch = [{direction: "Station not found", platform: "N/A", line: {mode: "special"}, when: new Date(2069, 1, 1)}];
        }
        for (let departure of batch) {
            departures.push(departure);
        }
    }
    const uniqueDepartures = [];
    for (let departure of departures) {
        if (!uniqueDepartures.some(d => new Date(d.when).getTime() === new Date(departure.when).getTime())) {
            uniqueDepartures.push(departure);
        }
    }
    return uniqueDepartures;
}

app.get("/departures", async function (req, res) {
    try {
        var { station, amount } = req.query;
    } catch (error) {
        res.send(error);
        return false;
    }

    // check if station and amount are set
    if (!station || !amount) {
        res.status(400).send([{direction: "Please enter a station name!", id: "0", platform: "400", line: {mode: "special"}, when: new Date(2069, 1, 1)}]);
        return false;
    }

    if (station === "kendlbat--nostation") {
        res.status(400).send([{direction: "Please enter a station name!", id: "0", platform: "400", line: {mode: "special"}, when: new Date(2069, 1, 1)}]);
        return false;
    }

    // Check if a cookie with the station name already exists
    if (req.cookies && req.cookies.stationName) {
        res.status(429).send([{direction: "Too many requests!", id: "0", platform: "429", line: {mode: "special"}, when: new Date(2069, 1, 1)}]);
        return;
    } else {
        res.cookie("stationName", station, { maxAge: 10000, sameSite: true });
    }

    console.log(String("New request for: " + station).padEnd(30));
    client.locations(station).then((data) => {
        var stationID = data[0].id;
        getNDepartures(stationID, amount).then((departures) => {
            res.send(departures);
        });
    });
});

app.get("/arrivals", async function (req, res) {
    try {
        var { station, amount } = req.query;
    } catch (error) {
        res.send(error);
        return false;
    }

    // check if station and amount are set
    if (!station || !amount) {
        res.status(400).send([{direction: "Please enter a station name!", id: "0", platform: "400", line: {mode: "special"}, when: new Date(2069, 1, 1)}]);
        return false;
    }

    if (station === "kendlbat--nostation") {
        res.status(400).send([{direction: "Please enter a station name!", id: "0", platform: "400", line: {mode: "special"}, when: new Date(2069, 1, 1)}]);
        return false;
    }

    // Check if a cookie with the station name already exists
    if (req.cookies && req.cookies.stationName) {
        res.status(429).send([{direction: "Too many requests!", id: "0", platform: "429", line: {mode: "special"}, when: new Date(2069, 1, 1)}]);
        return;
    } else {
        res.cookie("stationName", station, { maxAge: 10000, sameSite: true });
    }

    console.log(String("New request for: " + station).padEnd(30));
    client.locations(station).then((data) => {
        var stationID = data[0].id;
        getNArrivals(stationID, amount).then((arrivals) => {
            res.send(arrivals);
        });
    });
});

app.listen(process.env.PORT || 3080, () => {
    console.log('train api reachable on http://localhost:' + (process.env.PORT || 3080));
});
