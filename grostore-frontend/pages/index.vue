<template>
  <div>
    <v-parallax 
      height="600"
      src="/index.png">
      <v-container 
        fluid 
        grid-list-xl>
        <v-layout 
          row 
          justify-space-between>
          <v-flex xs2>
            <div class="display-1">+7(831)410-74-43</div>
          </v-flex>
          <v-flex xs2>
            <div class="text-sm-right">
              <v-btn 
                large="true"
                round="true"
                color="info">Вход</v-btn>
            </div>
          </v-flex>
        </v-layout>
        <v-layout 
          row 
          justify-center>
          <v-flex xs4>
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
          <v-flex xs2>
            <div class="text-sm-center">
              <v-card>
                <v-card-title primary-title>
                  <div>
                    <h3 class="headline mb-0">Kangaroo Valley Safari</h3>
                    <div>Located two hours south of Sydney in the <br>Southern Highlands of New South Wales, ...</div>
                  </div>
                </v-card-title>
                <v-card-actions>
                  <v-btn 
                    flat 
                    color="orange">Share</v-btn>
                  <v-btn 
                    flat 
                    color="orange">Explore</v-btn>
                </v-card-actions>
              </v-card>
            </div>
          </v-flex>
        </v-layout>
      </v-container>
      
    </v-parallax>
    <v-btn color="warning">Warning</v-btn>
    <v-layout
      align-start
      column
      justify-start
    >
      <div class="display-1 mt-3">+7(831)410-74-43</div>
    </v-layout>
    <v-layout
      align-end
      column
      justify-start
    >
      <div class="display-1 mt-3">
        <a href="#">Войти</a>
      </div>
    </v-layout>
  </div>
</template>

<script>
import Logo from '~/components/Logo.vue'
import VuetifyLogo from '~/components/VuetifyLogo.vue'

export default {
  layout: 'home',
  components: {
    Logo,
    VuetifyLogo
  }
}
</script>
