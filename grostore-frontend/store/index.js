export const state = () => ({
  user: null
})

export const getters = {
  isAuthenticated(state) {
    return state.auth.loggedIn
  },

  loggedInUser(state) {
    return state.auth.user
  }
}

export const mutations = {
  SET_PROFILE: (state, user) => {
    state.user = user
  }
}

export const actions = {
  async nuxtServerInit(store) {
    console.log('*** init. store is:')
    console.log(store)
    await store.dispatch('categories/GET_CATEGORIES_TREE')
  }
}
