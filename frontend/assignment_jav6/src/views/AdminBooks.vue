<template>
  <div>
    <h1>Admin - Books</h1>
    <div>
      <input v-model="title" placeholder="title" />
      <input v-model="author" placeholder="author" />
      <input v-model.number="price" placeholder="price" />
      <button @click="create">Create</button>
    </div>
    <div v-for="b in books" :key="b.id">
      <p>{{ b.title }} - {{ b.price }}</p>
      <button @click="del(b.id)">Del</button>
    </div>
  </div>
</template>

<script>
import axios from "../axios";
export default {
  data() {
    return { books: [], title: "", author: "", price: 0 };
  },
  mounted() {
    this.load();
  },
  methods: {
    load() {
      axios
        .get("/admin/books")
        .then((r) => (this.books = r.data))
        .catch(() => (this.books = []));
    },
    create() {
      axios
        .post("/admin/books", {
          title: this.title,
          author: this.author,
          price: this.price,
        })
        .then(() => this.load())
        .catch(() => alert("Err"));
    },
    del(id) {
      axios
        .delete("/admin/books/" + id)
        .then(() => this.load())
        .catch(() => alert("Err"));
    },
  },
};
</script>
