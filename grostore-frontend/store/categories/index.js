export const state = () => ({
  tree: [],
  list: [],
  categoryDetail: null
})

import {
  SET_CATEGORIES_TREE,
  SET_CATEGORIES_LIST,
  SET_CATEGORY
} from '../mutation-types'

export const mutations = {
  [SET_CATEGORIES_TREE]: (state, tree) => {
    state.tree = tree
  },
  [SET_CATEGORIES_LIST]: (state, page) => {
    state.list = page.content
  },
  [SET_CATEGORY]: (state, categoryDetail) => {
    state.categoryDetail = categoryDetail
  }
}

export const getters = {
  getTree(state) {
    return state.tree
  },
  getByHgu(state, queryHgu) {
    return state.list.find(({ hgu }) => hgu === queryHgu)
  },
  getCategoryDetail(state) {
    return state.categoryDetail
  }
}

import * as types from '@/store/mutation-types'

export const actions = {
  async GET_CATEGORIES_TREE({ commit }) {
    const { data } = await this.$axios.get('/categories/tree')
    commit(types.SET_CATEGORIES_TREE, data)
  },
  async GET_CATEGORIES_LIST({ commit }) {
    const { data } = await this.$axios.get('/categories')
    commit(types.SET_CATEGORIES_LIST, data)
  },
  async GET_CATEGORY({ commit }, payload) {
    const { data } = await this.$axios.get(`/categories/1`)
    commit(types.SET_CATEGORY, data)
  }
}
