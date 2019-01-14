import axios from 'axios';

const BASE_URL = '/api/';

export default class ProductService {

  static createProduct(formData) {
    const url = `${BASE_URL}products/create`;
    return axios.post(url, formData);
  }

  static getProductList(formData) {
    console.log(formData);
    const url = `${BASE_URL}products/?page=${formData.page}&size=${formData.size}&sort=${formData.sort},${formData.desc}`;
    return axios.get(url);
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

  static deleteProducts(items) {
    const url = `${BASE_URL}products/`;
    return axios.delete(url, {data: items});
  }
}
