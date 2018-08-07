import _ from 'lodash'

export default {
  priceLists(state) {
    return state.priceLists
  },
  loading(state) {
    return state.loading
  },
  headers(state) {
    state.headers = [];
    for(let i = 0; i < state.productImport.rowLength; i++) {
      state.headers.push({'columnNumber': i});
    }
    return state.headers
  },
  productImport(state) {
    return state.productImport
  },
  productImportFields(state) {
    return state.productImportFields
  }
}
