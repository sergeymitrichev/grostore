<template>
  <div id="app">
    <v-app>
      <v-content>
        <v-container><ProductList></ProductList></v-container>
      </v-content>
    </v-app>
  </div>
</template>

<script>
  import ProductList from './components/ProductList'

  export default {
    name: 'App',
    components: {
      ProductList
    }
  }
</script>
