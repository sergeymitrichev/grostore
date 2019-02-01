<template>
  <v-card>
    <v-card-text>
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
          label="Email или телефон"
          required/>
        <v-text-field
          v-model="password"
          :rules="passwordRules"
          label="Пароль"
          type="password"
          required/>
        <v-btn @click="login">Login</v-btn>
      </v-form>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      valid: true,
      email: 'sergeymitrichev@gmail.com',
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+/.test(v) || 'E-mail must be valid'
      ],
      password: 'rootroot',
      passwordRules: [v => !!v || 'Password is required'],
      error: null
    }
  },
  methods: {
    async login() {
      this.$store.commit('SET_LOADING', true)
      try {
        await this.$auth.loginWith('local', {
          data: {
            email: this.email,
            password: this.password
          }
        })
        this.$router.push('/')
        this.dialog = false
      } catch (e) {
        this.error = e.response.data.message
      } finally {
        this.$store.commit('SET_LOADING', false)
      }
    }
  }
}
</script>
