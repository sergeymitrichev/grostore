<template>
  <nuxt-link to="/">
    <img
      :src="logoSrc"
      :alt="sloganValue"
      :title="sloganValue">
    <h1
      v-if="title"
      class="display-1 font-weight-thin mb-1">{{ titleValue }}</h1>
    <h2 
      v-if="slogan"
      class="subheading mb-3">{{ sloganValue }}</h2>
  </nuxt-link>
</template>
<script>
export default {
  props: {
    slogan: {
      type: Boolean,
      default: false
    },
    title: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      logoSrc: '/minutka-logo.png',
      titleValue: 'СВОБОДНАЯ МИНУТКА',
      sloganValue: 'онлайн гипермаркет с доставкой продуктов'
    }
  }
}
</script>

<style>
a {
  text-decoration: none;
}
</style>
