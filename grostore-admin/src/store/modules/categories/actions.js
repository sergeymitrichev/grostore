import ActionTypes from "./types/action";
import MutationTypes from "./types/mutation";
import CategoryService from "./../../../services/category.service";

export default {
  [ActionTypes.changeSelectedPath]({commit}, payload) {
    commit(MutationTypes.CHANGE_SELECTED_PATH, payload);
  },
  [ActionTypes.initCategories]({commit}, payload) {
    return new Promise((resolve, reject) => {
      CategoryService.getAllRoot()
        .then((response) => {
          commit(MutationTypes.INIT_CATEGORIES, response.data);
          resolve();
        })
        .catch(reject)
    });
  }
}
