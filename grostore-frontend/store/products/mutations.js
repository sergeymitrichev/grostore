import { SET_PRODUCTS_LIST, SET_PRODUCTS_DETAIL } from '../mutation-types'

export default {
  [SET_PRODUCTS_LIST]: (state, list) => {
    state.list = list
  },
  [SET_PRODUCTS_DETAIL]: (state, detail) => {
    state.detail = detail
  }
}
