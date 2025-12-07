<template>
  <div>
    <h1>Login</h1>
    <input v-model="user" placeholder="username" />
    <input v-model="pass" placeholder="password" type="password" />
    <button @click="login">Login</button>
  </div>
</template>

<script>
import axios from "../axios";
export default {
  data() {
    return { user: "", pass: "" };
  },
  methods: {
    login() {
      axios
        .post("/auth/login", { username: this.user, password: this.pass })
        .then((r) => {
          localStorage.setItem("token", r.data.token);
          localStorage.setItem("role", r.data.role);
          alert("OK");
          this.$router.push("/");
        })
        .catch(() => alert("Login failed"));
    },
  },
};
</script>
