export default {
    getList(state) {
      return state.tree
    },
    getDetail(state) {
      return state.list.find(({ hgu }) => hgu === queryHgu)
    }
  }