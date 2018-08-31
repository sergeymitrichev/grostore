<template>
  <v-app
    id="inspire"
    dark
  >
    <Navbar></Navbar>
    <AppContent></AppContent>
  </v-app>
</template>

<script>
  import Navbar from "./Navbar";
  import AppContent from "./AppContent";

  export default {
    name: "Layout",
    components: {AppContent, Navbar}
  }
</script>

<style scoped>

</style>
