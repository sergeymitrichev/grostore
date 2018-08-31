<template>
  <v-container grid-list-md>
    <v-layout row wrap>
      <v-flex md12>
        <h1>Import Product</h1>
      </v-flex>
      <v-flex md6>
        <v-form v-on:submit="createPriceList($event)">
          <v-text-field
            label="Name"
            required
          ></v-text-field>
          <input type="file" :disabled="loading" multiple name="file" required/>
          <v-btn color="success" :disabled="loading" type="submit">Save</v-btn>
        </v-form>
      </v-flex>
      <v-flex md12>
        <v-data-table
          :headers="headers"
          :items="priceLists"
          :loading="loading"
          hide-actions
          class="elevation-10"
        >
          <v-progress-linear v-if="loading" slot="progress" color="blue" indeterminate></v-progress-linear>
          <template slot="items" slot-scope="props">
            <td>{{props.item.id}}</td>
            <td>{{props.item.name}}</td>
            <td>{{new Date(props.item.created).toLocaleString()}}</td>
            <td>{{new Date(props.item.updated).toLocaleString()}}</td>
            <td class="data-table-crud text-xs-center">
              <v-btn flat icon :disabled="loading" @click="editItem(props.item)">
                <v-icon small>edit</v-icon>
              </v-btn>
              <v-btn flat icon :disabled="loading" @click="itemToDelete = props.item.id; deleteItemDialog = true">
                <v-icon small>delete</v-icon>
              </v-btn>
            </td>
          </template>
        </v-data-table>
      </v-flex>
    </v-layout>
    <template>
      <div class="text-xs-center">
        <v-dialog
          v-model="deleteItemDialog"
          width="500"
        >
          <v-card>
            <v-card-text>
              Delete product import configuration?
            </v-card-text>
            <v-divider></v-divider>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
              flat
              @click="deleteItemDialog = false"
            >
              Cancel
            </v-btn>

              <v-btn
                color="error"
                flat
                @click="deleteItem(itemToDelete);deleteItemDialog = false;"
              >
                Delete
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>
    </template>
  </v-container>
</template>

<script>
  import {store} from '../../store'

  export default {
    name: 'ProductImportList',
    data() {
      return {
        // loading: true,
        headers: [
          {text: 'ID', value: 'id'},
          {text: 'Название', value: 'name'},
          // {text: 'Файл'},
          // {text: 'Признак уникальности'},
          {text: 'Дата создания', value: 'created'},
          {text: 'Дата загрузки', value: 'updated'},
          {text: 'Действия', sortable: false, align: 'right', value: 'actions'}
        ],
        itemToDelete: 0,
        deleteItemDialog: false
      }
    },
    computed: {
      priceLists() {
        return store.getters.priceLists
      },
      loading() {
        return store.getters.loading
      }
    },
    beforeRouteEnter(to, form, next) {
      Promise.all([
        store.dispatch('initPriceLists')
      ]).then(() => next());
    },
    methods: {
      createPriceList(e) {
        //TODO add validate
        e.preventDefault();
        let file = e.target[1].files[0];
        let name = e.target[0].value || file.name;

        let formData = new FormData();
        formData.append('file', file, file.name);
        formData.append('name', name);
        return store.dispatch('createPriceList', formData);
      },
      editItem(item) {
        this.$router.push(`/imports/${item.id}`);
      },
      deleteItem(id) {
        return store.dispatch('deleteProductImport', id);
      }
    }
  }
</script>

<style>
.data-table-crud {
  width: 175px;
}
</style>
