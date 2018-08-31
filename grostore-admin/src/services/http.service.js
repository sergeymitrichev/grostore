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
    return axios.get(`${BASE_URL}imports/${id}`).catch(error => {
      if (error.response) {
        // The request was made and the server responded with a status code
        // that falls out of the range of 2xx
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      } else if (error.request) {
        // The request was made but no response was received
        // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
        // http.ClientRequest in node.js
        console.log(error.request);
      } else {
        // Something happened in setting up the request that triggered an Error
        console.log('Error', error.message);
      }
      console.log(error.config);
    })
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

  static deleteProductImport(id) {
    return axios.delete(`${BASE_URL}imports/${id}`);
  }

  static updateProductImport(id, productImport) {
    return axios.post(`${BASE_URL}imports/${id}`, productImport);
  }

  static uploadProductImport(id) {
    return axios.post(`${BASE_URL}imports/${id}/upload`);
  }
}
