export const state = () => ({
  list: [{ id: 1, hgu: 'product1' }, { id: 2, hgu: 'product2' }]
})

export const mutations = {
  add(state, title) {
    state.list.push(title)
  }
}

export const getters = {
  get(state) {
    return state.list
  }
}
