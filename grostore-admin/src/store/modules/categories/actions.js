import ActionTypes from "./types/action";
import MutationTypes from "./types/mutation";
import CategoryService from "./../../../services/category.service";

export default {
  [ActionTypes.saveCategories]({commit}, payload) {
    return new Promise((resolve, reject) => {
      CategoryService.saveAll()
        .then((response) => {
          commit(MutationTypes.SET_CATEGORIES, response.data);
          resolve();
        })
        .catch(reject)
    });
  },
  [ActionTypes.saveCategory]({commit}, payload) {
    return new Promise((resolve, reject) => {
      CategoryService.save(payload)
        .then((response) => {
          commit(MutationTypes.SET_CATEGORIES, response.data);
          resolve();
        })
        .catch(reject)
    });
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
