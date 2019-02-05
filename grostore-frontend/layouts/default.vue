<template>
  <v-app>
    <app-header />
    <nuxt/>
    <app-footer />
  </v-app>
</template>

<script>
import AppHeader from '~/components/core/AppHeader'
import AppFooter from '~/components/core/AppFooter'

export default {
  components: {
    AppHeader,
    AppFooter
  }
}
</script>
