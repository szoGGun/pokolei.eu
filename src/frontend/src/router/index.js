import {createRouter, createWebHistory} from 'vue-router'
import HomePage from "@/views/HomePage.vue";
import DeparturesTimetable from "@/views/DeparturesTimetable.vue";
import ArrivalsTimetable from "@/views/ArrivalsTimetable.vue";
import AdminPanel from "@/views/AdminPanel.vue";
import Login from "@/views/Login"
import Trains from "@/views/Trains.vue";
import TrainComposition from "@/views/TrainComposition.vue";
import AdminPanelTrain from "@/components/AdminPanelTrain.vue";
import AddTrain from "@/components/AddTrain.vue";
const routes = [
    {
        path: '/',
        name: 'home',
        component: HomePage
    },
    {
        path: '/departures',
        name: 'departures',
        component: DeparturesTimetable
    },
    {
        path: '/arrivals',
        name: 'arrivals',
        component: ArrivalsTimetable
    },
    {
        path: '/admin',
        name: 'admin',
        component: AdminPanel
    },
    {
        path: '/login',
        name: 'login',
        component: Login
    },
    {
        path: '/trains',
        name: 'trains',
        component: Trains,
        params: true
    },
    {
        path: '/train-composition/:trainId/:trainFullName',
        name: 'trainComposition',
        component: TrainComposition,
        params: true
    },
    {
        path: '/temp',
        name: 'adminPanelTrain',
        component: AdminPanelTrain,
        params: true
    },
    {
        path: '/admin/add-train',
        name: 'addTrain',
        component: AddTrain,
        params: true
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
