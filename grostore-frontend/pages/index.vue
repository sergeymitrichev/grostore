<template>
  <div>
    <v-parallax 
      height="700"
      src="/index.png">
      <v-container 
        fluid 
        grid-list-xl>
        <v-layout 
          row 
          justify-space-between>
          <v-flex 
            xs12
            sm6
            md4
            lg2
            pa-0>
            <div class="display-1 mb-0">+7(831)410-74-43</div>
          </v-flex>
          <v-flex 
            xs12
            sm6
            md4
            lg2
            pa-0>
            <div class="text-sm-right">
              <v-btn 
                large
                round
                color="info">Вход</v-btn>
            </div>
          </v-flex>
        </v-layout>
        <v-layout 
          row 
          justify-center>
          <v-flex 
            lg3 
            md4 
            sm6 
            xs12
            pa-0>
            <div class="text-sm-center">
              <img
                src="/minutka-logo.png"
              >
              <h1 class="display-1 font-weight-thin mb-1">СВОБОДНАЯ МИНУТКА</h1>
              <h4 class="subheading  mb-3">онлайн гипермаркет с доставкой продуктов</h4>
            </div>
          </v-flex>
        </v-layout>
        <v-layout 
          row 
          justify-center>
          <v-flex 
            xs12
            sm6
            md4
            lg3>
            <div class="text-sm-center">
              <register-form 
                title="Вы в шаге от новой жизни" 
                btn-value="Начать"/>
            </div>
          </v-flex>
        </v-layout>
      </v-container>
    </v-parallax>
    <v-container
      fluid 
      grid-list-xl
      text-sm-center>
      <v-layout>
        <v-flex>1</v-flex>
        <v-flex>2</v-flex>
        <v-flex>3</v-flex>
        <v-flex>4</v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import Logo from '~/components/Logo.vue'
import VuetifyLogo from '~/components/VuetifyLogo.vue'
import RegisterForm from '~/components/account/RegisterForm.vue'

export default {
  layout: 'home',
  components: {
    Logo,
    VuetifyLogo,
    RegisterForm
  }
}
</script>
