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
            <div class="display-1 mb-0 mt-2">+7(831)410-74-43</div>
          </v-flex>
          <v-flex 
            xs12
            sm6
            md4
            lg2
            pa-0>
            <div class="text-xs-right">
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
            md6 
            sm9 
            xs12
            pa-0>
            <div class="text-xs-center">
              <img
                src="/minutka-logo.png"
              >
              <h1 class="display-1 font-weight-thin mb-1">СВОБОДНАЯ МИНУТКА</h1>
              <h2 class="subheading mb-3">онлайн гипермаркет с доставкой продуктов</h2>
            </div>
          </v-flex>
        </v-layout>
        <v-layout 
          row 
          justify-center>
          <v-flex 
            xs12
            sm9
            md6
            lg4>
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
      mt-5
    >
      <v-layout 
        row 
        justify-center>
        <v-flex 
          sm12
          md8>
          <h2 class="display-1 font-weight-thin mb-2">Как это работает?</h2>
        </v-flex>
      </v-layout>
      <v-layout 
        row 
        justify-center>
        <v-flex 
          xs12
          sm6
          md2>
          <v-card>
            <v-img src="https://images.pexels.com/photos/5205/food-healthy-vegetables-potatoes.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500"/>
            <v-card-title class="pb-0">
              <v-chip 
                color="success" 
                text-color="white">
                <v-avatar class="success darken-2">1</v-avatar>
                ВЫБЕРИ ПРОДУКТЫ
              </v-chip>
            </v-card-title>
            <v-card-text>
              <div class="ml-2">У нас есть все твои любимые товары, которые ты привык покупать в супермаркетах!</div>
            </v-card-text>
          </v-card>
        </v-flex>
        <v-flex 
          xs12
          sm6
          md2>
          <v-card>
            <v-img src="https://images.pexels.com/photos/36351/the-eleventh-hour-disaster-alarm-clock-clock.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500"/>
            <v-card-title class="pb-0">
              <v-chip 
                color="success" 
                text-color="white">
                <v-avatar class="success darken-2">2</v-avatar>
                УКАЖИ ВРЕМЯ
              </v-chip>
            </v-card-title>
            <v-card-text>
              <div class="ml-2">
                Утренний, дневной и вечерний интервалы. Делай, как тебе комфортно!
              </div>
            </v-card-text>
          </v-card>
        </v-flex>
        <v-flex 
          xs12
          sm6
          md2>
          <v-card>
            <v-img 
              height="167" 
              src="https://images.pexels.com/photos/1600757/pexels-photo-1600757.jpeg?auto=compress&cs=tinysrgb&dpr=1&h=160"/>
            <v-card-title class="pb-0">
              <v-chip 
                color="success" 
                text-color="white">
                <v-avatar class="success darken-2">3</v-avatar>
                МЫ ПОЕДЕМ В МАГАЗИН
              </v-chip>
            </v-card-title>
            <v-card-text>
              <div class="ml-2">
                Купим все продукты: обязательно проверим качество и сроки годности!
              </div>
            </v-card-text>
          </v-card>
        </v-flex>  
        <v-flex 
          xs12
          sm6
          md2>
          <v-card>
            <v-img src="https://t3.ftcdn.net/jpg/00/96/37/86/240_F_96378623_3KwikzlO2ADdX9PPVCdjzSkktLTRWyyP.jpg"/>
            <v-card-title class="pb-0">
              <v-chip 
                color="success" 
                text-color="white">
                <v-avatar class="success darken-2">4</v-avatar>
                ПРИМИ ПОКУПКИ
              </v-chip>
            </v-card-title>
            <v-card-text>
              <div class="ml-2">
                Бережно доставим, донесем до двери и примем оплату удобным для тебя способом.
              </div>
            </v-card-text>
          </v-card>
        </v-flex>
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
