import MutationTypes from './types/mutation'
import ActionTypes from './types/action'
import HttpService from '../../../services/http.service'
import router from '../../../router';


export default {
  [ActionTypes.initPriceLists]({commit}) {
    commit(MutationTypes.SET_LOADING, {loading: true});
    return new Promise((resolve, reject) => {
      HttpService.getPriceLists()
        .then((response) => {
          commit(MutationTypes.CLEAR_PRICE_LISTS);
          commit(MutationTypes.ADD_PRICE_LISTS, response.data);
          commit(MutationTypes.SET_LOADING, {loading: false});
          resolve()
        })
        .catch(reject)
    })
  },


  [ActionTypes.initProductImportById]({commit}, payload) {
    commit(MutationTypes.SET_LOADING, {loading: true});
    return new Promise((resolve, reject) => {
      HttpService.getPriceList(payload.id)
        .then((response) => {
          commit(MutationTypes.SET_PRODUCT_IMPORT, response.data);
          commit(MutationTypes.SET_LOADING, {loading: false});
          resolve()
        })
        .catch(reject)
    })
  },
  [ActionTypes.getProductImportFields]({commit}, payload) {
    return new Promise((resolve, reject) => {
      HttpService.getProductImportFields()
        .then((response) => {
          commit(MutationTypes.SET_PRODUCT_IMPORT_FIELDS, response.data);
          resolve()
        })
        .catch(reject)
    })
  },
  [ActionTypes.updateProductImport]({commit}, payload) {
    return new Promise((resolve, reject) => {
      HttpService.updateProductImport(payload.id, payload)
        .then((response) => {
          commit(MutationTypes.SET_PRODUCT_IMPORT, response.data);
          resolve()
        })
        .catch(reject)
    })
  },
  [ActionTypes.updateProductImportFile]({commit}, payload) {
    return new Promise((resolve, reject) => {
      HttpService.updateProductImportFile(payload.id, payload.file)
        .then((response) => {
          commit(MutationTypes.SET_PRODUCT_IMPORT, response.data);
          resolve()
        })
        .catch(reject)
    })
  },  
  [ActionTypes.uploadProductImport]({commit}, payload) {
    return new Promise((resolve, reject) => {
      HttpService.uploadProductImport(payload.id)
        .then((response) => {
          // commit(MutationTypes.SET_PRODUCT_IMPORT, response.data);
          resolve()
        })
        .catch(reject)
    })
  },


  [ActionTypes.createPriceList]({commit}, payload) {
    commit(MutationTypes.SET_LOADING, {loading: true});
    return new Promise((resolve, reject) => {
      HttpService.createPriceList(payload)
        .then((response) => {
          commit(MutationTypes.ADD_PRICE_LIST, response.data);
          commit(MutationTypes.SET_LOADING, {loading: false});
          router.push(`/imports/${response.data.id}`);
          resolve();


        })
        .catch(reject)
        .finally(() => commit(MutationTypes.SET_LOADING, {loading: false}))
    })
  },
  [ActionTypes.importPriceList]({commit}, payload) {
    return new Promise((resolve, reject) => {
      HttpService.createPriceList({
        'id': payload.id
      })
        .then((response) => {
          response.data.map(e => commit(MutationTypes.ADD_PRICE_LIST, e));
          resolve()
        })
        .catch(reject)
    })
  }
}
