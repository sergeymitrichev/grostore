<template>
  <section class="section">
    <div class="container">
      <div class="columns">
        <div class="column is-4 is-offset-4">
          <h2 class="title has-text-centered">Welcome back!</h2>

          <Notification 
            v-if="error"
            :message="error"/>

          <v-form 
            ref="form" 
            v-model="valid"
            lazy-validation>
            <v-text-field
              v-model="email"
              :rules="emailRules"
              label="E-mail"
              required/>
            <v-text-field
              v-model="password"
              :rules="passwordRules"
              label="Password"
              required/>
            <v-btn @click="login">Login</v-btn>
          </v-form>
          <div 
            class="has-text-centered" 
            style="margin-top: 20px">
            <p>
              Don't have an account? <nuxt-link to="/register">Register</nuxt-link>
            </p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Notification from '~/components/Notification'

export default {
  components: {
    Notification
  },

  data() {
    return {
      valid: true,
      email: '',
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+/.test(v) || 'E-mail must be valid'
      ],
      password: '',
      passwordRules: [v => !!v || 'Password is required'],
      error: null
    }
  },

  methods: {
    async login() {
      try {
        await this.$auth.loginWith('local', {
          data: {
            email: this.email,
            password: this.password
          }
        })

        this.$router.push('/')
      } catch (e) {
        console.log(e)
        this.error = e.response.data.message
      }
    }
  },
  middleware: 'guest'
}
</script>
