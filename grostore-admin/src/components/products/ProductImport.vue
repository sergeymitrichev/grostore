<template>
  <v-container grid-list-md>
    <v-layout row wrap>
      <v-flex md12>
        <h1>{{productImport.name}}</h1>
        <v-text-field
          label="Name"
          v-model="productImport.name"
        ></v-text-field>
      </v-flex>
      <v-flex md6><input type="file" multiple name="file" required v-on:change="updateProductImportFile($event)"/>
      </v-flex>
      <v-flex md6 text-xs-right>
        <v-btn @click="edit()" :disabled="loading">Save</v-btn>
        <v-btn color="success" @click="upload()" :disabled="loading"
               :loading="loading">Upload</v-btn>
      </v-flex>
      <v-flex md12>
        <v-tabs slider-color="error">
          <v-tab>
            Settings
          </v-tab>
          <v-tab>
            Uploaded products
          </v-tab>
          <v-tab>
            Uploaded categories
          </v-tab>
          <v-tab-item>
            <v-data-table
              :headers="headers"
              :items="productImport.raw"
              :loading="loading"
              class="elevation-10"
            >
              <v-progress-linear v-if="loading" slot="progress" color="blue" indeterminate></v-progress-linear>
              <template slot="headers" slot-scope="props">
                <td v-for="header in props.headers">
                  <v-select :items="productImportFields" item-text="type" item-value="columnNumber"
                            v-model="productImport.fields[header.columnNumber]"></v-select>
                </td>
              </template>
              <template slot="items" slot-scope="props">
                <td v-for="cell in props.item">
                  {{cell}}
                </td>
              </template>
            </v-data-table>
          </v-tab-item>
          <v-tab-item>
            <v-card flat>
              <v-data-table
                :items="result.products"
                :loading="loading"
                class="elevation-10"
              >
                <v-progress-linear v-if="loading" slot="progress" color="blue" indeterminate></v-progress-linear>
                <template slot="items" slot-scope="props">
                  <td>{{props.item.id}}</td>
                  <td>{{props.item.name}}</td>
                  <td>{{props.item.sku}}</td>
                  <td>{{props.item.categories}}</td>
                </template>
              </v-data-table>
            </v-card>
          </v-tab-item>
          <v-tab-item>
            <v-card flat>
              <v-data-table
                :items="result.categories"
                :loading="loading"
                class="elevation-10"
              >
                <v-progress-linear v-if="loading" slot="progress" color="blue" indeterminate></v-progress-linear>
                <template slot="items" slot-scope="props">
                  <td>{{props.item.id}}</td>
                  <td>{{props.item.name}}</td>
                </template>
              </v-data-table>
            </v-card>
          </v-tab-item>
        </v-tabs>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import {store} from '../../store'

  export default {
    name: 'ProductImport',
    data() {
      return {
        productImportFile: null,
        uploadDialog: false
      }
    },
    computed: {
      productImport() {
        return store.getters.productImport
      },
      productImportFields() {
        return store.getters.productImportFields
      },
      loading() {
        return store.getters.loading
      },
      headers() {
        return store.getters.headers
      },
      result() {
        return store.getters.result
      }
    },
    methods: {
      edit() {
        return store.dispatch('updateProductImport', this.productImport);
      },
      upload() {
        return store.dispatch('uploadProductImport', {id: this.productImport.id});
      },
      updateProductImportFile(e) {
        let formData = new FormData();
        formData.append('file', e.target.files[0], e.target.files[0].name);
        return store.dispatch('updateProductImportFile', {file: formData, id: this.productImport.id});
      }
    },
    watch: {
      uploadDialog(val) {
        if(!val) {
          return
        }
        this.uploadDialog = store.getters.loading;
      }
    },
    beforeRouteEnter(to, form, next) {
      Promise.all([
        store.dispatch('initProductImportById', {id: to.params.id}),
        store.dispatch('getProductImportFields')
      ]).then(() => next());
    },
  }
</script>
