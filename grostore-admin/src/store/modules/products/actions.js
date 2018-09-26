import MutationTypes from './types/mutation'
import ActionTypes from './types/action'
import ProductService from "../../../services/product.service";


export default {

  [ActionTypes.createProduct]({commit}, payload) {
    commit(MutationTypes.SET_LOADING, true);
    return new Promise((resolve, reject) => {
      ProductService.createProduct(payload.editedItem)
        .then((response) => {
          commit(MutationTypes.ADD_PRODUCT, response.data);
          commit(MutationTypes.SET_LOADING, false);
          resolve();
        })
        .catch(reject)
    });
  },

  [ActionTypes.deleteProduct]({commit}, payload) {
    commit(MutationTypes.SET_LOADING, true);
    return new Promise((resolve, reject) => {
      ProductService.deleteProduct(payload)
        .then((response) => {
          commit(MutationTypes.REMOVE_PRODUCT, response.data);
          commit(MutationTypes.SET_LOADING, false);
          resolve();
        })
        .catch(reject)
    });
  },

  [ActionTypes.deleteProducts]({commit}, payload) {
    commit(MutationTypes.SET_LOADING, true);
    return new Promise((resolve, reject) => {
      ProductService.deleteProducts(payload)
        .then(() => {
          commit(MutationTypes.REMOVE_PRODUCTS, payload);
          commit(MutationTypes.SET_LOADING, false);
          resolve();
        })
        .catch(reject)
    });
  },

  [ActionTypes.getProductFields]({commit}) {
    commit(MutationTypes.SET_LOADING, true);
    return new Promise((resolve, reject) => {
      ProductService.getProductFields()
        .then((response) => {
          commit(MutationTypes.SET_PRODUCT_FIELDS, response.data);
          commit(MutationTypes.SET_LOADING, false);
          resolve();
        })
        .catch(reject)
    });
  },

  [ActionTypes.initProductList]({commit}, payload) {
    commit(MutationTypes.SET_LOADING, true);
    return new Promise((resolve, reject) => {
      ProductService.getProductList(payload)
        .then((response) => {
          commit(MutationTypes.SET_PRODUCT_LIST, response.data.content);
          commit(MutationTypes.SET_LOADING, false);
          resolve();
        })
        .catch(reject)
    });
  },

  [ActionTypes.getProductList]({commit}, payload) {
    commit(MutationTypes.SET_LOADING, true);
    return new Promise((resolve, reject) => {
      ProductService.getProductList(payload)
        .then((response) => {
          commit(MutationTypes.SET_PRODUCT_LIST, response.data);
          commit(MutationTypes.SET_LOADING, false);
          resolve();
        })
        .catch(reject)
    });
  },

  [ActionTypes.updateProduct]({commit}, payload) {
    commit(MutationTypes.SET_LOADING, true);
    return new Promise((resolve, reject) => {
      ProductService.updateProduct(payload.editedItem, payload.editedItem.id)
        .then(() => {
          commit(MutationTypes.SET_LOADING, false);
          commit(MutationTypes.UPDATE_PRODUCT, payload)
          resolve();
        })
        .catch(reject)
    });
  }
}
