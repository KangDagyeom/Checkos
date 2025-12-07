<template>
  <div>
    <h1>Giỏ hàng</h1>
    <div v-for="(i, index) in cart" :key="index">
      <p>{{ i.title }} x {{ i.quantity }} - {{ i.price }}$</p>
      <button @click="remove(index)">X</button>
    </div>
    <div>
      <button @click="checkout">Đặt hàng</button>
    </div>
  </div>
</template>

<script>
import axios from "../axios";
export default {
  data() {
    return { cart: JSON.parse(localStorage.getItem("cart") || "[]") };
  },
  methods: {
    remove(i) {
      this.cart.splice(i, 1);
      localStorage.setItem("cart", JSON.stringify(this.cart));
    },
    checkout() {
      const token = localStorage.getItem("token");
      if (!token) {
        alert("Đăng nhập trước");
        this.$router.push("/login");
        return;
      }
      const body = {
        items: this.cart.map((c) => ({ bookId: c.id, quantity: c.quantity })),
      };
      axios
        .post("/user/orders", body)
        .then(() => {
          alert("Đặt hàng thành công");
          localStorage.removeItem("cart");
          this.cart = [];
        })
        .catch(() => alert("Lỗi"));
    },
  },
};
</script>
