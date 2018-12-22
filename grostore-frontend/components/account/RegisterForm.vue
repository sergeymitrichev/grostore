<template>
  <form>
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
          @click="submit">{{ btnValue }}</v-btn>
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
    name: '',
    email: '',
    password: '',
    checkbox: null,
    dictionary: {
      attributes: {
        email: 'Email Address'
      },
      custom: {
        name: {
          required: () => 'Имя есть у каждого :)',
          max: 'Придумайте имя покороче :)'
        },
        password: {
          required: 'Пароль не может быть пуст'
        }
      }
    }
  }),

  mounted() {},

  methods: {
    submit() {},
    clear() {
      this.name = ''
      this.email = ''
      this.password = ''
    }
  }
}
</script>
