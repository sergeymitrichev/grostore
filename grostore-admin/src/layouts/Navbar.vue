<template>
  <div>
    <v-navigation-drawer
      :value="drawer"
      fixed
      clipped
      app
    >
      <v-list dense>
        <v-list-tile v-for="item in navigator" :key="item.text" @click="" :to="item.link">
          <v-list-tile-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>
              {{ item.text }}
            </v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile class="mt-3" @click="">
          <v-list-tile-action>
            <v-icon color="grey darken-1">add_circle_outline</v-icon>
          </v-list-tile-action>
          <v-list-tile-title class="grey--text text--darken-1">Browse Channels</v-list-tile-title>
        </v-list-tile>
        <v-list-tile @click="">
          <v-list-tile-action>
            <v-icon color="grey darken-1">settings</v-icon>
          </v-list-tile-action>
          <v-list-tile-title class="grey--text text--darken-1">Manage Subscriptions</v-list-tile-title>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
    <v-toolbar
      color="red"
      dense
      fixed
      clipped-left
      app
    >
      <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
      <v-toolbar-title class="mr-5 align-center">
        <span class="title">Grostore Admin</span>
      </v-toolbar-title>
    </v-toolbar>

  </div>

</template>

<script>
  export default {
    data: () => ({
      drawer: true,
      navigator: [
        {icon: 'fa fa-boxes', text: 'Products', link: 'products'},
        {icon: 'fa fa-users', text: 'Customers', link: 'customers'},
        {icon: 'fa fa-shopping-basket', text: 'Orders', link: 'orders'}
      ]
    }),
    name: "Navbar"
  }
</script>

<style scoped>

</style>
