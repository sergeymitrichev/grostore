<template>
  <v-content>
    <v-container fluid>
      <v-layout children-flex>
          <router-view/>
      </v-layout>
    </v-container>
  </v-content>
</template>

<script>
  export default {
    name: "Content"
  }
</script>

<style scoped>

</style>
