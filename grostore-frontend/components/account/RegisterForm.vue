<template>
  <form 
    ref="form" 
    :v-model="valid" 
    lazy-validation>
    <v-card class="px-2">
      <v-card-title 
        primary-title 
        class="pb-0">
        <h3 class="headline">{{ title }}</h3>
      </v-card-title>
      <v-card-text class="pb-0">
        <v-text-field
          v-model="email"
          label="E-mail или телефон"
          required
        />
      </v-card-text>   
      <v-card-actions>
        <v-spacer/>
        <v-btn 
          class="mb-2"
          large
          round
          color="success"
          @click="register">{{ btnValue }}</v-btn>
      </v-card-actions>
    </v-card>
  </form>
</template>

<script>
export default {
  props: {
    title: {
      type: String,
      default: 'Регистрация'
    },
    btnValue: {
      type: String,
      default: 'Зарегистрироваться'
    },
    simple: {
      type: Boolean,
      default: true
    }
  },
  data: () => ({
    valid: true,
    email: '',
    password: ''
  }),
  methods: {
    async register() {
      try {
        const resp = await this.$axios.post('api/accounts/register', {
          email: this.email,
          password: this.password
        })

        console.log(resp)

        await this.$auth.loginWith('local', {
          data: {
            email: resp.data.email,
            password: resp.data.password
          }
        })

        this.$router.push('/profile')
      } catch (e) {
        this.error = e.response.data.message
      }
    },
    clear() {
      this.name = ''
      this.email = ''
      this.password = ''
    }
  }
}
</script>
