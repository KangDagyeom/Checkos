<template>
  <div>
    <h1>{{ book.title }}</h1>
    <p>{{ book.author }}</p>
    <p>{{ book.description }}</p>
    <p>{{ book.price }}$</p>
    <button @click="add">Thêm vào giỏ</button>
  </div>
</template>

<script>
import axios from "../axios";
export default {
  data() {
    return { book: {} };
  },
  mounted() {
    axios
      .get("/books/" + this.$route.params.id)
      .then((r) => (this.book = r.data))
      .catch(() => (this.book = {}));
  },
  methods: {
    add() {
      let cart = JSON.parse(localStorage.getItem("cart") || "[]");
      const idx = cart.findIndex((x) => x.id === this.book.id);
      if (idx >= 0) cart[idx].quantity++;
      else cart.push({ ...this.book, quantity: 1 });
      localStorage.setItem("cart", JSON.stringify(cart));
      alert("Đã thêm");
    },
  },
};
</script>
