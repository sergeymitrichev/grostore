import axios from "axios";

const BASE_URL = '/api/';

export default class CategoryService {
  static getAll() {
    const url = `${BASE_URL}categories/`;
    return axios.get(url);
  }
  static getAllRoot() {
    const url = `${BASE_URL}categories/tree`;
    return axios.get(url);
  }

  static saveAll(categories = []) {
    const url = `${BASE_URL}categories/tree`;
    return axios.post(url, categories);
  }

  static save(category) {
    let id = category.id || 'create';
    const url = `${BASE_URL}categories/${id}`;
    return axios.post(url, category);
  }
}
