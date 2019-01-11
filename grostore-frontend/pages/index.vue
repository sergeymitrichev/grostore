<template>
  <div>
    <v-parallax 
      height="500"
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
            lg3
            pa-0>
            <div class="display-1 mb-2 mt-2">+7(831)410-74-43</div>
            <h3>Доставка сегодня 19:00 - 21:00</h3>
          </v-flex>
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
          <v-flex 
            xs12
            sm6
            md4
            lg3
            pa-0>
            <div class="text-xs-right">
              <v-btn 
                v-if="!isAuthenticated"
                large
                round
                color="info"
                @click="dialog = true"
              >Вход</v-btn>
              <account-menu v-if="isAuthenticated" />
            </div>
          </v-flex>
        </v-layout>
        <v-dialog
          v-model="dialog"
          width="500"
        >
          <v-card>
            <v-card-text>
              <Notification 
                v-if="error"
                :message="error"/>

              <v-form 
                ref="form" 
                v-model="valid"
                lazy-validation>
                <v-text-field
                  v-model="email"
                  :rules="emailRules"
                  label="E-mail"
                  required/>
                <v-text-field
                  v-model="password"
                  :rules="passwordRules"
                  label="Password"
                  required/>
                <v-btn @click="login">Login</v-btn>
              </v-form>
            </v-card-text>
          </v-card>
        </v-dialog>
        <v-layout 
          row 
          justify-center>
          <v-flex 
            xs12
            sm9
            md6
            lg4>
            <div>
              <register-form 
                v-if="!isAuthenticated"
                class="text-sm-center"
                title="Вы в шаге от новой жизни" 
                btn-value="Выбрать продукты"/>
              <v-card 
                v-else
                class="px-2 pb-2">
                <v-card-title 
                  primary-title 
                >
                  <h3 class="headline">Рады видеть вас снова!</h3>
                </v-card-title>
                <v-card-text>
                  Вы можете перейти в каталог и выбрать из списка товаров или создать новый заказ на основе одного из предыду
                </v-card-text>
                <v-card-actions class="pb-3">
                  <v-btn 
                    flat
                    large
                    round 
                    color="success">Каталог продуктов</v-btn>
                  <v-btn 
                    large
                    round
                    color="success">Повторить заказ</v-btn>
                </v-card-actions>
              </v-card>
            </div>
          </v-flex>
        </v-layout>
      </v-container>
    </v-parallax>
    <v-container
      grid-list-xl
      pt-5
    >
      <h2 class="display-1 font-weight-thin mb-2">Как работает доставка продуктов на дом?</h2>
      <v-layout 
        row 
        justify-center>
        <v-flex 
          xs12
          sm6
          md3>
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
          md3>
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
          md3>
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
          md3>
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
    <v-container
      fluid
      grid-list-xl
      pt-5
      warning
    >
      <v-container>
        <h2 class="display-1 font-weight-thin mb-2">Как мы гарантируем качество продуктов?</h2>
        <v-layout>
          <v-flex>
            <ul>
              <li> Выбор срока годности </li>
              <li> Опыт поиска продуктов </li>
              <li> Согласование спорных продуктов </li>
            </ul>
          </v-flex>
        </v-layout>
      </v-container>
    </v-container>
  </div>
</template>

<script>
import Logo from '~/components/Logo.vue'
import VuetifyLogo from '~/components/VuetifyLogo.vue'
import RegisterForm from '~/components/account/RegisterForm.vue'
import Notification from '~/components/Notification.vue'
import AccountMenu from '~/components/account/AccountMenu'
import { mapGetters } from 'vuex'

export default {
  layout: 'home',
  components: {
    Logo,
    VuetifyLogo,
    RegisterForm,
    Notification,
    AccountMenu
  },
  data() {
    return {
      dialog: false,
      valid: true,
      email: 'sergeymitrichev@gmail.com',
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+/.test(v) || 'E-mail must be valid'
      ],
      password: 'rootroot',
      passwordRules: [v => !!v || 'Password is required'],
      error: null
    }
  },
  computed: {
    ...mapGetters(['isAuthenticated'])
  },
  methods: {
    async login() {
      try {
        await this.$auth.loginWith('local', {
          data: {
            email: this.email,
            password: this.password
          }
        })
        this.$router.push('/')
        this.dialog = false
      } catch (e) {
        console.log(Cookie.get('auth._token.local'))
        console.log(e)
        this.error = e.response.data.message
      }
    }
  }
}
</script>
