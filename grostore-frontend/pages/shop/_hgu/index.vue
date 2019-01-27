<template>
  <v-layout>    
    <pre>{{ category }}</pre>
  </v-layout>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  layout: 'catalog',
  computed: {
    ...mapGetters({
      category: 'categories/getCategoryDetail',
      isLoading: 'isLoading'
    })
  },
  mounted() {
    this.$store.commit('SET_LOADING', false)
  },
  async fetch({ store, params }) {
    await store.dispatch('categories/GET_CATEGORY', params)
    await store.dispatch('products/getCategoryProductList', {
      catId: store.state.categories.categoryDetail.id
    })
  }
}
</script>
