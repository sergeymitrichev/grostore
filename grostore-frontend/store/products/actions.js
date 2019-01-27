import * as types from '@/store/mutation-types'

export default {
  async getProductList({ commit, axois }) {
    const { data } = await this.$axios.get('/products')
    commit(types.SET_PRODUCTS_LIST, data)
  },
  async getCategoryProductList({ commit, axois }, payload) {
    console.log(axois)
    const { data } = await this.$axios.get(`/${payload.catId}/products`)
    commit(types.SET_PRODUCTS_LIST, data)
  },
  async getProductDetail({ commit }, payload) {
    const { data } = await this.$axios.get(`/products/${payload.id}`)
    commit(types.SET_CATEGORY, data)
  }
}
