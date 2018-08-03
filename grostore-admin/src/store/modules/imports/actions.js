import MutationTypes from './types/mutation'
import ActionTypes from './types/action'
import HttpService from '../../../services/http.service'

export default {
  [ActionTypes.initPriceLists]({commit}) {
    return new Promise((resolve, reject) => {
      HttpService.getPriceLists()
        .then((response) => {
          commit(MutationTypes.CLEAR_PRICE_LISTS);
          response.data.map(e => commit(MutationTypes.ADD_PRICE_LIST, e));
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
          // commit(MutationTypes.REMOVE_PRICE_LIST, payload);
          commit(MutationTypes.ADD_PRICE_LIST, response.data);
          // response.data.map(e => commit(MutationTypes.ADD_PRICE_LIST, e));
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
