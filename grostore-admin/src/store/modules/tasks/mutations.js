import Types from './types/mutation'

export default {
  [Types.ADD_TASK](state, payload) {
    state.tasks.content.push(payload);
    state.tasks.totalElements++;
  },
  [Types.REMOVE_TASK](state, payload) {
    state.tasks.content = state.tasks.content.filter(e => e.id !== payload.id);
    state.tasks.totalElements--;
  },
  [Types.REMOVE_TASKS](state, payload) {
    state.tasks.content = _.difference(state.tasks.content, payload);
  },
  [Types.UPDATE_TASK](state, payload) {
    state.tasks.content[payload.editedIndex] = payload.editedItem;
  },
  [Types.CLEAR_TASK_LIST](state) {
    state.tasks.content = [];
    state.tasks.totalElements = 0;
  },
  [Types.SET_TASK_LIST](state, payload) {
    state.tasks = payload
  },
  [Types.SET_LOADING](state, payload) {
    state.loading = payload
  }
}
