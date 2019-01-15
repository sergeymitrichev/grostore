<template>
  <div>
    <v-form v-show="isPhone">
      <v-container>
        <v-layout>
          <v-flex
            xs4
          >
            <v-select
              ref="phoneCountryPrefix"
              :items="phoneCountryPrefixes"
              v-model="phoneCountryPrefix"
              prepend-icon="phone"
            />
          </v-flex>  
          <v-flex
            xs3
          >
            <v-text-field
              ref="phonePrefix"
              v-model="phonePrefix"
              size="3"
              mask="(###)"
              @keyup="changePhonePrefix"
            />
          </v-flex>
          <v-flex
            xs5
          >
            <v-text-field
              ref="phoneNumber"
              v-model="phoneNumber"
              mask="###-##-##"
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
import _ from 'lodash'
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

      phone: '',
      phoneNumber: '',
      phonePrefix: '',
      phoneCountryPrefix: '',
      phoneCountryPrefixes: ['+7', '+1', '+38']
    }
  },
  methods: {
    validate(event) {
      if (event.key.match(/[0-9]|\+/)) {
        this.isEmail = false
        this.isPhone = true
        this.phoneCountryPrefix = this.phoneCountryPrefixes[0]
        this.phonePrefix = this.contact
        this.$refs.phonePrefix.focus()
      } else if (event.key.match(/[A-z]|@|./)) {
        this.isPhone = false
        this.isEmail = true
      }

      if (event.key == 13) {
        this.submit()
      }
    },

    selectPhoneCountryPrefix() {},

    changePhonePrefix() {
      if (this.phonePrefix.length == 3) {
        this.$refs.phoneNumber.focus()
      }
    },

    submit() {
      this.phone = this.phoneCountryPrefix + this.phonePrefix + this.phoneNumber
    },

    clear() {
      this.contact = ''
      this.isEmail = false
      this.isPhone = false
    }
  }
}
</script>
