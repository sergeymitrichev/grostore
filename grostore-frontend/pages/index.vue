<template>
  <div>
    <v-parallax 
      height="700"
      class="warning"
      src="/index.png">
      <v-container 
        fluid>
        <v-layout
          wrap>
          <v-flex 
            xs12
            sm6
            md4
            lg3
            order-xs2
            order-sm1
            order-md1
            pa-0>
            <div class="text-xs-center text-sm-left text-md-left">
              <div class="display-1 mb-2 mt-2">+7(831)410-74-43</div>
              <h3>ДОСТАВКА СЕГОДНЯ 19:00 - 21:00</h3>
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
            offset-lg1
            order-xs3
            order-sm2
            order-md3
            pa-0
            mb-3>
            <div class="text-xs-center text-sm-right text-md-right">
              <v-btn 
                v-if="!isAuthenticated"
                large
                round
                color="info"
                @click="dialog = true"
              >
                <template v-if="isLoading">
                  <v-progress-circular
                    indeterminate
                    color="white"
                  />
                </template>
                <template v-else>
                  Вход
                </template>
              </v-btn>
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
          v-if="!isLoading"
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
                    color="success"
                    @click.stop="showCategoryTree"
                  >Каталог продуктов</v-btn>
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
    <category-tree :drawer="categoryTree.drawer"/>
  </div>
</template>

<script>
import Logo from '~/components/Logo.vue'
import VuetifyLogo from '~/components/VuetifyLogo.vue'
import RegisterForm from '~/components/account/RegisterForm.vue'
import Notification from '~/components/Notification.vue'
import AccountMenu from '~/components/account/AccountMenu'
import CategoryTree from '~/components/category/CategoryTree.vue'
import { mapGetters } from 'vuex'

export default {
  layout: 'home',
  components: {
    Logo,
    VuetifyLogo,
    RegisterForm,
    Notification,
    AccountMenu,
    CategoryTree
  },
  data() {
    return {
      categoryTree: {
        drawer: false
      },
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
    ...mapGetters(['isAuthenticated', 'isLoading'])
  },
  mounted() {
    this.$store.commit('SET_LOADING', false)
  },
  methods: {
    async login() {
      this.$store.commit('SET_LOADING', true)
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
        console.log(e)
        this.error = e.response.data.message
      } finally {
        this.$store.commit('SET_LOADING', false)
      }
    },
    showCategoryTree() {
      this.categoryTree.drawer = null
      this.categoryTree.drawer = true
    }
  }
}
</script>
