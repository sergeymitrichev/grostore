import axios from 'axios';

const BASE_URL = '/api/tasks';

export default class TaskService {

  static createTask(formData) {
    const url = `${BASE_URL}/create`;
    return axios.post(url, formData);
  }

  static getTaskList(formData) {
    const url = `${BASE_URL}/?page=${formData.page}&size=${formData.size}&sort=${formData.sort||'id'},${formData.desc}`;
    return axios.get(url);
  }

  static updateTask(formData, id) {
    const url = `${BASE_URL}/${id}`;
    return axios.post(url, formData);
  }

  static deleteTask(id) {
    const url = `${BASE_URL}/${id}`;
    return axios.delete(url);
  }

  static deleteTasks(items) {
    const url = `${BASE_URL}/`;
    return axios.delete(url, {data: items});
  }
}
