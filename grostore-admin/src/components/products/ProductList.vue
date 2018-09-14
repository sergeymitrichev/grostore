<template>
  <v-container grid-list-md>
    <v-layout row wrap>
      <v-flex md12>
        <h1>Products</h1>
        <v-dialog v-model="dialog" max-width="500px">
          <v-btn slot="activator" color="success" dark @click="editedItem = defaultItem; editedIndex = -1" class="mb-2">New Item</v-btn>
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container grid-list-md>
                <v-layout wrap>
                  <input type="hidden" :v-model="editedItem.id">
                  <v-flex xs12 sm12 md12>
                    <v-text-field v-model="editedItem.name" label="Item name"></v-text-field>
                  </v-flex>
                  <v-flex xs12 sm12 md12>
                    <v-select
                      v-model="editedItem.categories"
                      :items="editedItem.categories"
                      item-text="name"
                      item-value="id"
                      attach
                      chips
                      label="Categories"
                      multiple
                    ></v-select>
                  </v-flex>
                  <v-flex xs12 sm6 md4>
                    <v-text-field v-model="editedItem.sku" label="SKU"></v-text-field>
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
          :headers="headers"
          :items="products"
          :loading="loading"
          class="elevation-10"
        >
          <v-progress-linear v-if="loading" slot="progress" color="danger" indeterminate></v-progress-linear>
          <template slot="items" slot-scope="props">
            <tr @click="editItem(props.item)" class="active-row">
              <td>
                {{props.item.id}}
              </td>
              <td>
                {{props.item.name}}
              </td>
              <td>
                {{props.item.sku}}
              </td>
              <td>
                {{props.item.categories}}
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
  import * as _ from "lodash";

  export default {
    name: "ProductList",
    data() {
      return {
        dialog: false,
        editedIndex: -1,
        editedItem: {
          name: '',
          sku: '',
          categories: []
        },
        defaultItem: {
          name: '',
          sku: '',
          categories: []
        }
      }
    },
    computed: {
      products() {
        return store.getters.products;
      },
      headers() {
        return store.getters.headers;
      },
      productFields() {
        return store.getters.productFields;
      },
      loading() {
        return store.getters.loading
      },
      formTitle() {
        return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
      }
    },
    methods: {
      editItem(item) {
        this.editedIndex = this.products.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true
      },

      deleteItem(item) {
        const index = this.products.indexOf(item)
        confirm('Are you sure you want to delete this item?') && this.products.splice(index, 1)
      },

      close() {
        this.dialog = false;
      },
      save() {
        this.close();
        let action = this.editedItem.id ? 'updateProduct' : 'createProduct';
        return store.dispatch(action, {editedItem: this.editedItem, editedIndex: this.editedIndex}).then(r => {
          alert('success');
        }).catch(e => {
          alert('fail');
        })
      }
    },
    watch: {
      dialog(val) {
        val || this.close()
      }
    },
    created() {
      Promise.all([
        store.dispatch('getProductFields'),
        store.dispatch('initProductList')
      ]).then();
    },
  }
</script>

<style scoped>
  .active-row {
    cursor: pointer;
  }
</style>
