import Vue from 'vue'
import Vuex from 'vuex'

import imports from './modules/imports'
import products from './modules/products'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {},
  actions: {},
  mutations: {},
  getters: {},
  modules: {
    imports,
    products
  }
});

export {store}
