import ActionTypes from "./types/action";
import MutationTypes from "./types/mutation";


export default {
  [ActionTypes.changeSelectedPath]({commit}, payload) {
    commit(MutationTypes.CHANGE_SELECTED_PATH, payload);
  }
}
