<template>
  <v-app
    id="inspire"
    dark
  >
    <v-navigation-drawer
      v-model="drawer"
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
        <!--<v-subheader class="mt-3 grey&#45;&#45;text text&#45;&#45;darken-1">SUBSCRIPTIONS</v-subheader>-->
        <!--<v-list>-->
          <!--<v-list-tile v-for="item in items2" :key="item.text" avatar @click="">-->
            <!--<v-list-tile-avatar>-->
              <!--<img :src="`https://randomuser.me/api/portraits/men/${item.picture}.jpg`" alt="">-->
            <!--</v-list-tile-avatar>-->
            <!--<v-list-tile-title v-text="item.text"></v-list-tile-title>-->
          <!--</v-list-tile>-->
        <!--</v-list>-->
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
      <v-spacer></v-spacer>
      <v-layout row align-center style="max-width: 350px">
        <v-text-field
          :append-icon-cb="() => {}"
          placeholder="Search..."
          single-line
          append-icon="search"
          color="white"
          hide-details
        ></v-text-field>
      </v-layout>
    </v-toolbar>
    <v-content>
      <v-container grid-list-md>
        <v-layout row wrap>
          <v-flex xs-12>
            <router-view />
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
  import ProductList from './components/ProductList'

  export default {
    data: () => ({
      drawer: true,
      navigator: [
        {icon: 'fa fa-boxes', text: 'Products', link: 'products'},
        {icon: 'fa fa-users', text: 'Customers', link: 'customers'},
        {icon: 'fa fa-shopping-basket', text: 'Orders', link: 'orders'}
      ]
    }),
    name: 'App',
    components: {
      ProductList
    }
  }
</script>
