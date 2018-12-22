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
          v-validate="'required'"
          v-model="name"
          :rules="nameRules"
          :counter="10"
          label="Имя"
          required
        />
        <v-text-field
          v-validate="'required|email'"
          v-model="email"
          label="E-mail"
          required
        />
        <v-text-field
          v-validate="'required'"
          v-model="password"
          label="Пароль"
          required
        />
        <v-checkbox
          v-validate="'required'"
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
          @submit="submit">{{ btnValue }}</v-btn>
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
    submit() {
      console.log('submit')
      if (this.$refs.form.validate()) {
        // Native form submission is not yet supported
        axios.post('/api/submit', {
          name: this.name,
          email: this.email,
          select: this.select,
          checkbox: this.checkbox
        })
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
