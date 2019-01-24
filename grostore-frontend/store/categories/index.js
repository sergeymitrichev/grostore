export const state = () => ({
  tree: []
})

export const mutations = {
  SET_CATEGORIES_TREE: (state, tree) => {
    state.tree = tree
  }
}

export const getters = {
  getTree(state) {
    return state.tree
  }
}

export const actions = {
  async GET_CATEGORIES_TREE({ commit }) {
    const { data } = await this.$axios.get('/categories/tree')
    commit('SET_CATEGORIES_TREE', data)
  }
}
