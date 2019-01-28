<template>
  <v-layout>
    {{ hgu }}
  </v-layout>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  layout: 'catalog',
  computed: mapGetters({
    product: 'products/getDetail'
  })
}
</script>
