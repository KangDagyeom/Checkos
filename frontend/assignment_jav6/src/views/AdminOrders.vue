<template>
  <div>
    <h1>Admin - Orders</h1>
    <div v-for="o in orders" :key="o.id">
      <p>Order #{{ o.id }} - {{ o.createdAt }}</p>
    </div>
  </div>
</template>

<script>
import axios from "../axios";
export default {
  data() {
    return { orders: [] };
  },
  mounted() {
    axios
      .get("/admin/orders")
      .then((r) => (this.orders = r.data))
      .catch(() => (this.orders = []));
  },
};
</script>
