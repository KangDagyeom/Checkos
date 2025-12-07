<template>
  <div>
    <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/cart">Cart</router-link> |
      <router-link to="/orders">My Orders</router-link> |
      <router-link to="/admin/books">Admin</router-link> |
      <router-link to="/login">Login</router-link>
    </nav>
    <router-view />
  </div>
</template>

<script>
export default { name: "App" };
</script>
