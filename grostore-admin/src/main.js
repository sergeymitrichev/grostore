import '@babel/polyfill'
import Vue from 'vue'
import './plugins/vuetify'
import App from './App'
import router from './router'
import {store} from './store'
import '@fortawesome/fontawesome-free/css/all.css'

Vue.config.productionTip = false;

import VueDragTree from 'vue-drag-tree'
import 'vue-drag-tree/dist/vue-drag-tree.min.css'

Vue.use(VueDragTree);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
});
