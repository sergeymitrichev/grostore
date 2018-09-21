import Types from "./types/mutation";

export default {
  [Types.INIT_CATEGORIES](state, payload) {
    state.categories = payload;
  },
  [Types.SET_CATEGORIES](state, payload) {
    state.categories = payload;
  }
}
