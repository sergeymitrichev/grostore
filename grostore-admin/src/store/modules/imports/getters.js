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
    let maxRowLength = 0;
    state.productImport.raw.map(l => {
      if(maxRowLength < l.length) {
        maxRowLength = l.length;
      }
    });
    for(let i = 0; i < maxRowLength; i++) {
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
