import MutationTypes from './types/mutation'
import ActionTypes from './types/action'
import HttpService from '../../../services/http.service'

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
  [ActionTypes.createPriceList]({commit}, payload) {
    commit(MutationTypes.SET_LOADING, {loading: true});
    return new Promise((resolve, reject) => {
      HttpService.createPriceList(payload)
        .then((response) => {
          commit(MutationTypes.ADD_PRICE_LIST, response.data);
          commit(MutationTypes.SET_LOADING, {loading: false});
          //TODO redirect to price list page /imports/{id}
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
