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
        <v-btn color="info" @click="edit()">Save</v-btn>
        <v-btn color="error" @click="upload()">Upload</v-btn>
      </v-flex>
      <v-flex md12>
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
        productImportFile: null
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
      }
    },
    methods: {
      edit() {
        // this.productImport.fields = [];
        // this.selectedFields.map((f, i) => {
        //   this.productImport.fields.push({
        //     type: f,
        //     columnNumber: i,
        //     identity: false,
        //     productImportId: this.productImport.id
        //   })
        // })
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
    beforeRouteEnter(to, form, next) {
      Promise.all([
        store.dispatch('initProductImportById', {id: to.params.id}),
        store.dispatch('getProductImportFields')
      ]).then(() => next());
    },
  }
</script>
