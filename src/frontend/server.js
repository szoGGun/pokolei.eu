const createClient = require("hafas-client");
const pkpProfile = require('hafas-client/p/oebb/index.js');
const express = require('express')
const cors = require('cors')
const app = express()
app.use(cors())

const client = createClient(pkpProfile, 'train-api');

app.use(require('cookie-parser')());

async function getNStationInfo(station, n, endpoint) {
    let stationInfo = [];
    for (let i = 0; i < n; i += 10) {
        try {
            var batch = await endpoint(station, { products: {bus: 1, tram: 1}, results: 10, duration: 600, when: (stationInfo.length === 0 ? new Date() : stationInfo[stationInfo.length - 1].when)});
        } catch (e) {
            var batch = [{direction: "Station not found", platform: "N/A", line: {mode: "special"}, when: new Date(2069, 1, 1)}];
        }
        for (let info of batch) {
            stationInfo.push(info);
        }
    }
    const uniqueStationInfo = [];
    for (let info of stationInfo) {
        if (!uniqueStationInfo.some(d => new Date(d.when).getTime() === new Date(info.when).getTime())) {
            uniqueStationInfo.push(info);
        }
    }
    return uniqueStationInfo;
}

async function getNArrivals(station, n) {
    return await getNStationInfo(station, n, client.arrivals);
}

async function getNDepartures(station, n) {
    return await getNStationInfo(station, n, client.departures);
}

async function handleStationRequest(req, res, endpointFunc) {
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
        endpointFunc(stationID, amount).then((results) => {
            res.send(results);
        });
    });
}

app.get("/departures", async function (req, res) {
    handleStationRequest(req, res, getNDepartures);
});

app.get("/arrivals", async function (req, res) {
    handleStationRequest(req, res, getNArrivals);
});

app.listen(process.env.PORT || 3080, () => {
    console.log('train api reachable on http://localhost:' + (process.env.PORT || 3080));
});