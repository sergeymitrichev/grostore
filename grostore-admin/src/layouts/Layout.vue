<template>
  <v-app
    id="inspire"
    dark
  >
    <Navbar></Navbar>
    <Content></Content>
  </v-app>
</template>

<script>
  import Navbar from "./Navbar";
  import Content from "./Content";

  export default {
    name: "Layout",
    components: {Content, Navbar}
  }
</script>

<style scoped>

</style>
