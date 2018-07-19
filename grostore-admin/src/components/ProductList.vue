<template>
  <div>
    <h1 class="md-title">Product List</h1>
    <v-data-table
      :items="products"
      hide-actions
      class="elevation-1"
    >
      <template slot="items" slot-scope="props">
        <td>{{props.item.title}}</td>
        <td>{{props.item.price}}</td>
      </template>
    </v-data-table>
  </div>
</template>

<script>
  import shop from '@/api/shop'
  import store from '@/store/index'

  export default {
    computed: {
      products() {
        return store.getters.availableProducts
      }
    },

    created() {
      shop.getProducts(products => {
        store.commit('setProducts', products)
      })
    }
  }
</script>

<style scoped>

</style>
