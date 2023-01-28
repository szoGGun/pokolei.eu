import { createRouter, createWebHistory } from 'vue-router'
import HomePage from "@/views/HomePage.vue";
import DeparturesTimetable from "@/views/DeparturesTimetable.vue";
import ArrivalsTimetable from "@/views/ArrivalsTimetable.vue";
import AdminPanel from "@/views/AdminPanel.vue";
import Login from "@/views/Login"
import Trains from "@/components/Trains.vue";

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
    component: Trains
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
