<template>
  <v-layout>
    {{ categories[0].title }}
  </v-layout>
</template>

<script>
export default {
  layout: 'catalog',
  data() {
    return {
      categories: [
        {
          id: 1,
          title: 'Овощи, грибы, фрукты, зелень',
          to: '/url1/1'
        }
      ]
    }
  }
}
</script>
