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

  static importPriceList(id) {
    return axios.post(`${BASE_URL}imports/${id}`);
  }

  static getPriceLists() {
    return axios.get(`${BASE_URL}imports`)
  }
}
