import axios from 'axios'

const BASE_URL = '/';

export default class HttpService {

  static createPriceList(formData) {
    const url = `${BASE_URL}imports/create`;
    return axios.post(url, formData, {
      headers: {
        'Content-Type': undefined
      }
    });
  }

  static getPriceLists() {
    return axios.get(`${BASE_URL}imports/`)
  }

  static getPriceList(id) {
    return axios.get(`${BASE_URL}imports/${id}`)
  }

  static getProductImportFields() {
    return axios.get(`${BASE_URL}imports/fields`)
  }

  static updateProductImportFile(id, file) {
    return axios.post(`${BASE_URL}imports/${id}/file`, file, {
      headers: {
        'Content-Type': undefined
      }
    });
  }

  static updateProductImport(id, productImport) {
    return axios.post(`${BASE_URL}imports/${id}`, productImport);
  }

  static uploadProductImport(id) {
    return axios.post(`${BASE_URL}imports/${id}/upload`);
  }
}
