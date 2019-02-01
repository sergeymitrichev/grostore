<template>
  <v-container 
    fluid
    pt-0>
    <v-layout wrap>
      <v-flex 
        xs12
        sm6
        md4
        lg3
        order-xs2
        order-sm1
        order-md1
        pt-3>
        <div class="text-xs-center text-sm-left text-md-left">
          <div class="display-1 mb-2">
            <phone-block/>
          </div>
        </div>
      </v-flex>
      <v-flex 
        lg4 
        offset-lg1
        md4 
        sm12 
        xs12
        order-xs1
        order-sm3
        order-md2
        pa-0
        mt-3>
        <div class="text-xs-center">
          <logo-block 
            title 
            slogan />
        </div>
      </v-flex>
      <v-flex 
        xs12
        sm6
        md4
        lg3
        offset-lg1
        order-xs3
        order-sm2
        order-md3
        pt-3
        mb-3>
        <div class="text-xs-center text-sm-right text-md-right">
          <template v-if="!isLoading">
            <account-menu v-if="isAuthenticated" />
            <login-menu v-else />
          </template>
          <template v-else>
            <v-btn 
              large
              round
              color="info"
              disabled
            >
              <v-progress-circular
                indeterminate
                color="white"
              />
            </v-btn>
          </template>
        </div>
      </v-flex>
    </v-layout>
  </v-container>
</template>
<script>
import { mapGetters } from 'vuex'
import PhoneBlock from '~/components/block/PhoneBlock'
import LogoBlock from '~/components/block/LogoBlock'
import AccountMenu from '~/components/account/AccountMenu'
import LoginMenu from '~/components/account/LoginMenu'

export default {
  components: {
    PhoneBlock,
    LogoBlock,
    AccountMenu,
    LoginMenu
  },
  computed: {
    ...mapGetters(['isAuthenticated', 'isLoading'])
  }
}
</script>
