import axios from 'axios';

const BASE_URL = '/';

export default class ProductService {

  static createProduct(formData) {
    const url = `${BASE_URL}products/create`;
    return axios.post(url, formData);
  }

  static getProductList(formData) {
    const url = `${BASE_URL}products/`;
    return axios.get(url, formData);
  }

  static getProductFields() {
    const url = `${BASE_URL}products/fields`;
    return axios.get(url);
  }

  static updateProduct(formData, id) {
    const url = `${BASE_URL}products/${id}`;
    return axios.post(url, formData);
  }

  static deleteProduct(id) {
    const url = `${BASE_URL}products/${id}`;
    return axios.delete(url);
  }
}
