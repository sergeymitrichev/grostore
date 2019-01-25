export const state = () => ({
  loading: true
})

export const getters = {
  isAuthenticated(state) {
    return state.auth.loggedIn
  },

  isLoading(state) {
    return state.loading
  },

  loggedInUser(state) {
    return state.auth.user
  }
}

export const mutations = {
  SET_LOADING: (state, loading) => {
    state.loading = loading
  }
}

export const actions = {
  async nuxtServerInit(store) {
    console.log('*** init. store is:')
    console.log(store)
    await store.dispatch('categories/GET_CATEGORIES_TREE')
    await store.dispatch('categories/GET_CATEGORIES_LIST')
  }
}
