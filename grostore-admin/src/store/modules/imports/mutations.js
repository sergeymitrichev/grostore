import _ from 'lodash'
import Types from './types/mutation'

export default {
  [Types.ADD_PRICE_LIST] (state, payload) {
    state.priceLists.push(payload)
  },
  [Types.ADD_PRICE_LISTS] (state, payload) {
    state.priceLists.push(...payload)
  },
  [Types.REMOVE_PRICE_LIST] (state, payload) {
    _.remove(state.priceLists, (e) => e.id === payload.id)
  },
  [Types.CLEAR_PRICE_LISTS] (state) {
    state.priceLists = []
  },
  [Types.SET_LOADING] (state, payload) {
    state.loading = payload.loading
  }
}
