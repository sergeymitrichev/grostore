<template>
  <v-content>
    <v-container grid-list-md>
      <v-layout row wrap>
        <v-flex xs-12>
          <router-view/>
        </v-flex>
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
