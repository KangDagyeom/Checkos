import { createRouter, createWebHistory } from "vue-router";
import Home from "./views/Home.vue";
import BookDetail from "./views/BookDetail.vue";
import Cart from "./views/Cart.vue";
import Login from "./views/Login.vue";
import Register from "./views/Register.vue";
import UserOrders from "./views/UserOrders.vue";
import AdminBooks from "./views/AdminBooks.vue";
import AdminOrders from "./views/AdminOrders.vue";

const routes = [
  { path: "/", component: Home },
  { path: "/book/:id", component: BookDetail },
  { path: "/cart", component: Cart },
  { path: "/login", component: Login },
  { path: "/register", component: Register },
  { path: "/orders", component: UserOrders },
  { path: "/admin/books", component: AdminBooks },
  { path: "/admin/orders", component: AdminOrders },
];

const router = createRouter({ history: createWebHistory(), routes });
export default router;
