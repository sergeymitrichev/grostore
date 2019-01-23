<template>
  <v-layout>
    product id
    {{ hgu }}
  </v-layout>
</template>

<script>
export default {
  layout: 'catalog',
  data() {
    return {
      products: [
        {
          id: 1,
          hgu: 'product-1'
        }
      ]
    }
  }
}
</script>
