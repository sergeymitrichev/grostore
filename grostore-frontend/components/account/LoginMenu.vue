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
    >
      {{ loginText }}        
    </v-btn>
    <login-form/>
  </v-menu>
</template>

<script>
import { mapGetters } from 'vuex'
import LoginForm from '~/components/account/LoginForm'

export default {
  components: {
    LoginForm
  },
  data() {
    return {
      menu: false,
      loginText: 'Войти'
    }
  },
  computed: {
    ...mapGetters(['isAuthenticated'])
  }
}
</script>
