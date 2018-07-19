import Vuex from 'vuex'
import Vue from 'vue'

Vue.use(Vuex);

export default new Vuex.Store({

  state: {// = data
    products: []
  },

  getters: {
    availableProducts(state, getters) {
      return state.products.filter(products => products.inventory > 0)
    }
  },

  actions: {
    fetchProducts () {
      // make the call
      // run serProducts mutation
    }
  },

  mutations: {
    setProducts (state, products) {
      // update products
      state.products = products
    }
  }
})
