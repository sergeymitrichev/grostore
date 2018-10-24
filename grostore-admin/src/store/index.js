import Vue from 'vue'
import Vuex from 'vuex'

import imports from './modules/imports'
import products from './modules/products'
import categories from './modules/categories'
import tasks from './modules/tasks'


Vue.use(Vuex)

const store = new Vuex.Store({
  state: {loading: true},
  actions: {},
  mutations: {},
  getters: {},
  modules: {
    imports,
    tasks,
    products,
    categories
  }
});

export {store}
