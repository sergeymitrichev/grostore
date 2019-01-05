<template>
  <v-menu
    v-model="menu"
    :close-on-content-click="false"
    :nudge-width="0"
    offset-x
  >
    <v-btn 
      slot="activator"
      large
      round
      color="info"
    >{{ loggedInUser.email || loggedInUser.phone }}</v-btn>
    <v-card>
      <v-list>
        <v-list-tile avatar>
          <v-list-tile-avatar>
            <img 
              src="https://cdn.vuetifyjs.com/images/john.jpg" 
              alt="John">
          </v-list-tile-avatar>
          <v-list-tile-content>
            <v-list-tile-title>{{ loggedInUser.name }}</v-list-tile-title>
            <v-list-tile-sub-title>{{ loggedInUser.email }}</v-list-tile-sub-title>
          </v-list-tile-content>
          <v-list-tile-action>
            <v-btn
              icon
              @click="logout"
            >
              <v-icon>logout</v-icon>
            </v-btn>
          </v-list-tile-action>
        </v-list-tile>
      </v-list>
      <v-divider />
      <v-list>
        <v-list-tile>
          <v-list-tile-action>
            <v-icon>list</v-icon>
          </v-list-tile-action>  
          <v-list-tile-title>
            <nuxt-link to="/me/orders">Мои заказы</nuxt-link>
          </v-list-tile-title>
        </v-list-tile>
        <v-list-tile>
          <v-list-tile-action>
            <v-icon>favorite</v-icon>
          </v-list-tile-action>  
          <v-list-tile-title>
            <nuxt-link to="/me/favorite">Мои сохраненные продукты</nuxt-link>
          </v-list-tile-title>
        </v-list-tile>
      </v-list>
    </v-card>
  </v-menu>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  computed: {
    ...mapGetters(['loggedInUser'])
  },
  methods: {
    async logout() {
      await this.$auth.logout()
    }
  }
}
</script>
