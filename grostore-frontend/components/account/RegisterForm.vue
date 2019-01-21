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
        <masked-input />
        <v-text-field
          v-if="!simple"
          v-model="name"
          :rules="nameRules"
          :counter="10"
          label="Имя"
          required
        />
        <v-text-field
          v-model="email"
          label="E-mail или телефон"
          required
        />
        <v-text-field
          v-if="!simple"
          v-model="password"
          label="Пароль"
          required
        />
        <v-checkbox
          v-if="!simple"
          v-model="checkbox"
          value="1"
          label="Согласие на обработку информации"
          data-vv-name="checkbox"
          type="checkbox"
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
import MaskedInput from '~/components/input/MaskedInput'
export default {
  components: {
    MaskedInput
  },
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
    name: '',
    nameRules: [
      v => !!v || 'Имя есть у каждого :)',
      v => (v && v.length <= 2) || 'Введите хотя бы 2 символа'
    ],
    email: '',
    emailRules: [
      v => !!v || 'E-mail обязателен',
      v => /.+@.+/.test(v) || 'E-mail некорректен'
    ],
    password: '',
    checkbox: null
  }),

  mounted() {},

  methods: {
    async register() {
      try {
        const resp = await this.$axios.post('api/accounts/register', {
          email: this.email,
          phone: this.phone,
          password: this.password
        })

        console.log(resp)

        await this.$auth.loginWith('local', {
          data: {
            email: resp.data.email,
            password: resp.data.password
          }
        })

        this.$router.push('/me')
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
