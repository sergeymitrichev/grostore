import Types from './types/mutation'

export default {
  [Types.ADD_TASK] (state, payload) {
    state.tasks.push(payload)
  },
  [Types.REMOVE_TASK] (state, payload) {
    state.tasks = state.tasks.filter(e => e.id !== payload.id);
  },
  [Types.REMOVE_TASKS] (state, payload) {
    state.products.content = _.difference(state.products.content, payload);
  },
  [Types.UPDATE_TASK] (state, payload) {
    state.tasks[payload.editedIndex] = payload.editedItem;
  },
  [Types.CLEAR_TASK_LIST] (state) {
    state.tasks = []
  },
  [Types.SET_TASK_LIST] (state, payload) {
    state.tasks = payload
  },
  [Types.SET_LOADING] (state, payload) {
    state.loading = payload
  }
}
