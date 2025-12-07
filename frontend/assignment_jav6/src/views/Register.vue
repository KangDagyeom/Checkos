<template>
  <div>
    <h1>Register</h1>
    <input v-model="user" placeholder="username" />
    <input v-model="pass" placeholder="password" type="password" />
    <button @click="register">Register</button>
  </div>
</template>

<script>
import axios from "../axios";
export default {
  data() {
    return { user: "", pass: "" };
  },
  methods: {
    register() {
      axios
        .post("/auth/register", { username: this.user, password: this.pass })
        .then(() => {
          alert("OK");
          this.$router.push("/login");
        })
        .catch(() => alert("Err"));
    },
  },
};
</script>
