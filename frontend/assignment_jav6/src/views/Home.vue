<template>
  <div>
    <h1>Danh sách sách</h1>
    <div v-for="b in books" :key="b.id">
      <p>{{ b.title }} - {{ b.price }}$</p>
      <button @click="view(b.id)">Xem</button>
      <button @click="add(b)">Thêm vào giỏ</button>
    </div>
    <p><router-link to="/cart">Giỏ hàng</router-link></p>
  </div>
</template>

<script>
import axios from "../axios";
export default {
  data() {
    return { books: [] };
  },
  mounted() {
    axios
      .get("/books")
      .then((r) => (this.books = r.data))
      .catch(() => (this.books = []));
  },
  methods: {
    view(id) {
      this.$router.push("/book/" + id);
    },
    add(b) {
      let cart = JSON.parse(localStorage.getItem("cart") || "[]");
      const idx = cart.findIndex((x) => x.id === b.id);
      if (idx >= 0) cart[idx].quantity++;
      else cart.push({ ...b, quantity: 1 });
      localStorage.setItem("cart", JSON.stringify(cart));
      alert("Đã thêm");
    },
  },
};
</script>
