<template>
  <v-list>
    <v-list-tile
      v-for="(category, i) in categories"
      :to="category.to"
      :key="i"
      router
      exact
    >
      <v-list-tile-content>
        <v-list-tile-title v-text="category.title" />
      </v-list-tile-content>
    </v-list-tile>
  </v-list>
</template>
<script>
export default {
  data() {
    return {
      categories: [
        {
          id: 1,
          title: 'Овощи, грибы, фрукты, зелень',
          to: '/category1/url',
          childrens: [
            {
              id: 2,
              title: 'Овощи, грибы, фрукты, зелень'
            },
            {
              id: 3,
              title: 'Category 3'
            },
            {
              id: 4,
              title: 'Category 4'
            },
            {
              id: 5,
              title: 'Category 5'
            }
          ]
        },
        {
          id: 6,
          title: 'Category 6',
          childrens: [
            {
              id: 7,
              title: 'Category 7'
            },
            {
              id: 8,
              title: 'Category 8'
            },
            {
              id: 9,
              title: 'Category 9'
            },
            {
              id: 10,
              title: 'Category 10'
            }
          ]
        }
      ]
    }
  }
}
</script>
<style>
.css {
  background: #fff;
}
</style>
