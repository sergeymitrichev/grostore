import Vue from 'vue'
import Vuex from 'vuex'

import imports from './modules/imports'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {},
  actions: {},
  mutations: {},
  getters: {},
  modules: {
    imports: imports
  }
});

export {store}
