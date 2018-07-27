import axios from 'axios'

const BASE_URL = 'http://localhost:8080';

export default class HttpService {

  static upload(formData) {
    const url = `${BASE_URL}/import`;
    return axios.post(url, formData, {
      headers: {
        'Content-Type': undefined
      }
    });
  }
}
