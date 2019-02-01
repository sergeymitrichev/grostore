<template>
  <v-layout>
    {{ $store.state.categories.tree }}
  </v-layout>
</template>

<script>
export default {
  layout: 'catalog'
}
</script>
