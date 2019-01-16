<template>
  <div>
    <v-form v-show="isPhone">
      <v-container>
        <v-layout>
          <v-flex
            xs4
          >
            <v-select
              :items="phoneCodes"
              v-model="phoneMask"
              item-text="name_ru"
              item-value="mask"
            />
          </v-flex>  
          <v-flex
            xs8
          >
            <v-text-field
              ref="phoneNumber"
              v-model="phone"
              :mask="phoneMask"
              prepend-icon="phone"
              append-outer-icon="close"
              @click:append-outer="clear"
              @keyup.enter="submit"
            />
          </v-flex>
        </v-layout>
      </v-container>
    </v-form>
    <v-text-field
      v-show="!isEmail && !isPhone "
      v-model="contact"
      label="Укажите email или телефон"
      required
      @keyup="validate($event)"
    />
    {{ phone }} 
    <br>
    {{ email }}
  </div>
</template>
<script>
export default {
  props: {
    required: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      contact: '',
      validated: {},

      isPhone: false,
      isEmail: false,

      email: '',

      phone: '+7(###)###-##-##',
      phoneMask: '+7(###)###-##-##'
    }
  },
  computed: {
    phoneCodes() {
      return this.$store.state.phoneCodes.list
    }
  },
  methods: {
    validate(event) {
      if (event.key.match(/[0-9]|\+/)) {
        this.isEmail = false
        this.isPhone = true
        this.$nextTick(() => this.$refs.phoneNumber.focus())
      } else if (event.key.match(/[A-z]|@|./)) {
        this.isPhone = false
        this.isEmail = true
      }

      if (event.key === 13) {
        this.submit()
      }
    },

    submit() {
      this.phone += ' phone submitted'
    },

    clear() {
      this.contact = ''
      this.phone = '+7(###)###-##-##'
      this.isEmail = false
      this.isPhone = false
    }
  }
}
</script>
