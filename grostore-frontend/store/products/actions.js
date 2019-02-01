import * as types from '@/store/mutation-types'

export default {
  async getProductList({ commit }, payload) {
    let cidPath = payload.cid ? `/${payload.cid}` : ''
    const { data } = await this.$axios.get(`/products${cidPath}`)
    commit(types.SET_PRODUCTS_LIST, data)
  },
  async getProductDetail({ commit }, payload) {
    const { data } = await this.$axios.get(`/products/${payload.id}`)
    commit(types.SET_CATEGORY, data)
  }
}
