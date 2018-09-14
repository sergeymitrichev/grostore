import Types from './types/mutation'

export default {
  [Types.ADD_PRODUCT] (state, payload) {
    state.products.push(payload)
  },
  [Types.REMOVE_PRODUCT] (state, payload) {
    state.products = state.products.filter(e => e.id !== payload.id);
  },
  [Types.UPDATE_PRODUCT] (state, payload) {
    state.products[payload.editedIndex] = payload.editedItem;
  },
  [Types.CLEAR_PRODUCT_LIST] (state) {
    state.products = []
  },
  [Types.SET_PRODUCT_LIST] (state, payload) {
    state.products = payload
  },
  [Types.SET_LOADING] (state, payload) {
    state.loading = payload
  },
  [Types.SET_PRODUCT_FIELDS] (state, payload) {
    state.productFields = payload
  }
}
