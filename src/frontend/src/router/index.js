import { createRouter, createWebHashHistory } from 'vue-router'
import TrainsComponent from "@/components/TrainsComponent.vue";
import HomePage from "@/views/HomePage.vue";

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomePage
  },
  {
    path: '/trains',
    name: 'trains',
    component: TrainsComponent
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
