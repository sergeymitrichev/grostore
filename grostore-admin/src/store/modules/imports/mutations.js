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
    state.priceLists = state.priceLists.filter(e => e.id !== payload.id);
  },
  [Types.CLEAR_PRICE_LISTS] (state) {
    state.priceLists = []
  },
  [Types.SET_PRODUCT_IMPORT] (state, payload) {
    state.productImport = payload
  },
  [Types.SET_PRODUCT_IMPORT_FIELDS] (state, payload) {
    state.productImportFields = payload;
    state.productImportFields.unshift("_IGNORE");
  },
  [Types.SET_LOADING] (state, payload) {
    state.loading = payload.loading
  },
  [Types.SET_UPLOAD_RESULTS] (state, payload) {
    state.result = payload
  }
}
