import MutationTypes from './types/mutation'
import ActionTypes from './types/action'
import TaskService from "../../../services/task.service";


export default {

  [ActionTypes.createTask]({commit}, payload) {
    commit(MutationTypes.SET_LOADING, true);
    return new Promise((resolve, reject) => {
      TaskService.createTask(payload.editedItem)
        .then((response) => {
          commit(MutationTypes.ADD_TASK, response.data);
          commit(MutationTypes.SET_LOADING, false);
          resolve();
        })
        .catch(reject)
    });
  },

  [ActionTypes.deleteTask]({commit}, payload) {
    commit(MutationTypes.SET_LOADING, true);
    return new Promise((resolve, reject) => {
      TaskService.deleteTask(payload)
        .then((response) => {
          commit(MutationTypes.REMOVE_TASK, response.data);
          commit(MutationTypes.SET_LOADING, false);
          resolve();
        })
        .catch(reject)
    });
  },

  [ActionTypes.deleteTasks]({commit}, payload) {
    commit(MutationTypes.SET_LOADING, true);
    return new Promise((resolve, reject) => {
      TaskService.deleteTasks(payload)
        .then(() => {
          commit(MutationTypes.REMOVE_TASKS, payload);
          commit(MutationTypes.SET_LOADING, false);
          resolve();
        })
        .catch(reject)
    });
  },

  [ActionTypes.initTaskList]({commit}, payload) {
    commit(MutationTypes.SET_LOADING, true);
    return new Promise((resolve, reject) => {
      TaskService.getTaskList(payload)
        .then((response) => {
          commit(MutationTypes.SET_TASK_LIST, response.data.content);
          commit(MutationTypes.SET_LOADING, false);
          resolve();
        })
        .catch(reject)
    });
  },

  [ActionTypes.getTaskList]({commit}, payload) {
    commit(MutationTypes.SET_LOADING, true);
    return new Promise((resolve, reject) => {
      TaskService.getTaskList(payload)
        .then((response) => {
          commit(MutationTypes.SET_TASK_LIST, response.data);
          commit(MutationTypes.SET_LOADING, false);
          resolve();
        })
        .catch(reject)
    });
  },

  [ActionTypes.updateTask]({commit}, payload) {
    commit(MutationTypes.SET_LOADING, true);
    return new Promise((resolve, reject) => {
      TaskService.updateTask(payload.editedItem, payload.editedItem.id)
        .then(() => {
          commit(MutationTypes.SET_LOADING, false);
          commit(MutationTypes.UPDATE_TASK, payload)
          resolve();
        })
        .catch(reject)
    });
  }
}
