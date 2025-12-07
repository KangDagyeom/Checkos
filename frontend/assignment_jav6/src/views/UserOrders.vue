<template>
  <div>
    <h1>Đơn hàng của tôi</h1>
    <div v-for="o in orders" :key="o.id">
      <p>Order #{{ o.id }} - {{ o.createdAt }}</p>
      <div v-for="it in o.items" :key="it.id">
        {{ it.book.title }} x {{ it.quantity }}
      </div>
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
      .get("/user/orders")
      .then((r) => (this.orders = r.data))
      .catch(() => (this.orders = []));
  },
};
</script>
