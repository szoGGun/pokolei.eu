import { createRouter, createWebHashHistory } from 'vue-router'
import HomePage from "@/views/HomePage.vue";
import DeparturesTimetable from "@/views/DeparturesTimetable.vue";
import ArrivalsTimetable from "@/views/ArrivalsTimetable.vue";

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
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
