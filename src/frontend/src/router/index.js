import { createRouter, createWebHashHistory } from 'vue-router'
import HomePage from "@/views/HomePage.vue";
import StationTimetable from "@/views/StationTimetable.vue";
import DelaysHistory from "@/views/DelaysHistory.vue";

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomePage
  },
  {
    path: '/timetable',
    name: 'timetable',
    component: StationTimetable
  },
  {
    path: '/delays',
    name: 'delays',
    component: DelaysHistory
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
