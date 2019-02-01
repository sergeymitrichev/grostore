<template>
  <v-app>
    <header-block/>
    <v-toolbar 
      dark 
      color="error">
      <v-toolbar-side-icon/>
      <v-toolbar-title>Title</v-toolbar-title>
      <v-spacer/>
      <v-toolbar-items class="hidden-sm-and-down">
        <v-btn flat>Link One</v-btn>
        <v-btn flat>Link Two</v-btn>
        <v-btn flat>Link Three</v-btn>
      </v-toolbar-items>
    </v-toolbar>
    <v-content 
      class="mt-5" 
      style="height: 700px;">
      <nuxt/>
    </v-content>
    <footer-block/>
  </v-app>
</template>

<script>
import HeaderBlock from '~/components/block/HeaderBlock'
import FooterBlock from '~/components/block/FooterBlock'

export default {
  components: {
    HeaderBlock,
    FooterBlock
  }
}
</script>
