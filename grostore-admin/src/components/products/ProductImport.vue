<template>
  <v-card>
    <v-card-title><h1>Price Import #{{this.$route.params.id}}</h1></v-card-title>
    <div>
      <v-text-field :value="productImport.name"></v-text-field>
      {{productImport.raw}}
    </div>
  </v-card>
</template>

<script>
  import {store} from '../../store'

  export default {
    name: 'ProductImport',
    data() {
      return {
      }
    },
    computed: {
      productImport() {
        return store.getters.productImport
      },
      loading() {
        return store.getters.loading
      }
    },
    beforeRouteEnter(to, form, next) {
      Promise.all([
        store.dispatch('initProductImportById', {'id': to.params.id})
      ]).then(() => next());
    },
  }
</script>
