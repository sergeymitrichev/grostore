export default {
  products(state) {
    return state.products
  },
  loading(state) {
    return state.loading
  },
  headers(state) {
    state.headers = [];
    state.productFields.forEach(f => {
      state.headers.push({text: f.toString(), value: f.toString()})
    });
    state.headers.push({text: 'Actions', sortable: false, align: 'right', value: 'actions'});
    console.log(state.headers);
    return state.headers
  },
  productFields(state) {
    return state.productFields
  }
}
