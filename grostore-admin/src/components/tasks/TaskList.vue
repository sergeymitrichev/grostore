<template>
  <v-container grid-list-md>
    <v-layout row wrap>
      <v-flex md12>
        <h1>Tasks ({{loading ? '1' : '0'}})</h1>
        <v-btn color="red" dark @click="deleteItem(selected)" class="mb-2" :disabled="selected.length === 0">
          Delete selected ({{ selected.length }})
        </v-btn>
        <v-dialog v-model="dialog" max-width="500px">
          <v-btn slot="activator" color="success" dark @click="editedItem = defaultItem; editedIndex = -1" class="mb-2">
            New Task
          </v-btn>
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container grid-list-md>
                <v-layout wrap>
                  <input type="hidden" :v-model="editedItem.id">
                  <v-flex xs12 sm12 md12>
                    <v-text-field v-model="editedItem.name" label="Task name"></v-text-field>
                  </v-flex>
                  <v-flex xs6 sm6 md6>
                    <v-text-field v-model="editedItem.delay" label="Task delay"></v-text-field>
                  </v-flex>
                  <v-flex xs6 sm6 md6>
                    <v-checkbox
                      label="Is periodic"
                      v-model="editedItem.periodic"
                    ></v-checkbox>
                  </v-flex>
                  <v-flex xs12 sm12 md12>
                    <v-select
                      v-model="editedItem.type"
                      :items="taskTypes"
                      label="Task parse type"
                    ></v-select>
                  </v-flex>
                  <!--TODO learn vue dynamic forms https://vueschool.io/lessons/the-dynamic-form -->
                  <v-flex xs12 sm12 md12 v-for="(url, i) in editedItem.url" :key="i">
                    <v-text-field v-model="url.link" label="URL to parse"></v-text-field>
                    <!--<v-text-field v-model="url.category.name" label="Category to save"></v-text-field>-->
                    <v-tree-select :data="categories" placeholder="Select category to save" :whole-row="true" text-field-name="title" value-field-name="id" v-model="url.category.id"></v-tree-select>
                    <!--<v-select-->
                      <!--v-model="url.category"-->
                      <!--:items="categories"-->
                      <!--label="Category to save"-->
                    <!--&gt;</v-select>-->
                  </v-flex>
                  <v-flex xs12 sm12 md12>
                    <v-select
                      v-model="editedItem.status"
                      :items="taskStatuses"
                      label="Task parse status"
                    ></v-select>
                  </v-flex>
                </v-layout>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="darken-1" flat @click.native="close">Cancel</v-btn>
              <v-btn color="success darken-1" flat @click.native="save">Save</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-flex>
      <v-flex md12>
        <v-data-table
          v-model="selected"
          select-all
          :headers="headers"
          :items="tasks.content"
          :length='tasks.totalPages'
          :pagination.sync="pagination"
          :total-items="tasks.totalElements"
          :rows-per-page-items="[20,50,100]"
          class="elevation-10"
          :loading="loading"
        >
          <v-progress-linear slot="progress" color="success" indeterminate></v-progress-linear>
          <template slot="items" slot-scope="props">
            <tr class="active-row">
              <td>
                <v-checkbox
                  v-model="props.selected"
                  red
                  hide-details
                  color="red"
                ></v-checkbox>
              </td>
              <td @click="editItem(props.item)">
                {{props.item.id}}
              </td>
              <td @click="editItem(props.item)">
                {{props.item.name}}
              </td>
              <td @click="editItem(props.item)">
                {{props.item.type}}
              </td>
              <td @click="editItem(props.item)">
                {{props.item.status}}
              </td>
              <td @click="editItem(props.item)">
                <div v-if="props.item.url !== null">
                  <div v-for="url in props.item.url">
                    {{url.link}} ({{url.category.name}})
                  </div>
                </div>
              </td>
            </tr>
          </template>
        </v-data-table>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import {store} from '../../store'
  import Action from "../../store/modules/tasks/types/action";

  export default {
    name: "TaskList",
    data() {
      return {
        selected: [],
        taskStatuses: [
          'SCHEDULED_TASK_NEW',
          'SCHEDULED_TASK_WAITING',
          'SCHEDULED_TASK_RUNNING',
          'SCHEDULED_TASK_DONE',
          'SCHEDULED_TASK_CANCELLED'
        ],
        taskTypes: [
          'SCHEDULED_TASK_METRO_PARSE_PRODUCTS',
          'SCHEDULED_TASK_AUCHAN_PARSE_PRODUCTS'
        ],
        dialog: false,
        headers: [
          {text: 'ID', value: 'id'},
          {text: 'Name', value: 'name'},
          {text: 'Type', value: 'type'},
          {text: 'Status', value: 'status'},
          {text: 'URL\'s', value: 'url'}
        ],
        editedIndex: -1,
        editedItem: {
          id: '',
          type: '',
          status: '',
          periodic: false,
          delay: 0,
          parse: [
            {
              link: '',
              category: {
                id: 0
              }
            }
          ]
        },
        defaultItem: {
          id: '',
          type: '',
          status: '',
          periodic: false,
          delay: 0,
          url: [
            {
              link: '',
              category: {
                id: 0
              }
            }
          ]
        },
        pagination: {}
      }
    },
    watch: {
      pagination: {
        handler() {
          store.dispatch(Action.getTaskList, {page: (this.pagination.page - 1), size: this.pagination.rowsPerPage, sort: this.pagination.sortBy, desc: this.pagination.descending ? 'DESC' : ''})
        },
        deep: true
      },
      dialog(val) {
        val || this.close()
      }
    },
    computed: {
      tasks() {
        return store.getters.tasks;
      },
      loading() {
        return store.getters.loading;
      },
      formTitle() {
        return this.editedIndex === -1 ? 'New Task' : 'Edit Task'
      },
      categories() {
        return store.getters.categories;
      }
    },
    methods: {
      editItem(item) {
        this.editedIndex = this.tasks.content.indexOf(item);
        this.editedItem = Object.assign({}, item);
        this.dialog = true
      },

      deleteItem(items) {
        // const result = _.difference(this.products.content, items);
        confirm('Are you sure you want to delete this task?') && store.dispatch(Action.deleteTasks, items);
        this.selected = [];
      },

      close() {
        this.dialog = false;
      },
      save() {
        this.close();
        let action = this.editedItem.id ? Action.updateTask : Action.createTask;
        return store.dispatch(action, {editedItem: this.editedItem, editedIndex: this.editedIndex}).then(r => {
          alert('success');
        }).catch(e => {
          console.log(e);
          alert('fail');
        })
      }
    },
    created() {
      Promise.all([
        store.dispatch('initCategories')
      ]).then();
    },
  }
</script>

<style>
  .tree-select, .tree-select-open, .tree-select-dropdown{
    border-color: transparent;
    border-bottom: 1px solid #fff;
  }
  .tree-select, .tree-select > .tree-select-single, .tree-selec-allow, .tree-select-open, .tree-select-dropdown {
    background: inherit;
    color: inherit;
    font-size: inherit;
    line-height: inherit;
  }

</style>
