import axios from "axios";

const BASE_URL = '/';

export default class CategoryService {
  static getAll() {
    const url = `${BASE_URL}categories/`;
    return axios.get(url);
  }
}
