<template>
  <v-container grid-list-md>
    <v-layout row wrap>
      <v-flex md12>
        <h1>Products</h1>
        <v-btn color="red" dark @click="deleteItem(selected)" class="mb-2" :disabled="selected.length === 0">
          Delete selected ({{ selected.length }})
        </v-btn>
        <v-dialog v-model="dialog" max-width="500px">
          <v-btn slot="activator" color="success" dark @click="editedItem = defaultItem; editedIndex = -1" class="mb-2">
            New Item
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
                  <v-flex xs12 sm6 md4>
                    <input type="hidden" :v-model="editedItem.prices[0].productId">
                    <v-text-field v-model="editedItem.prices[0].type" label="Price in"></v-text-field>
                    <v-text-field v-model="editedItem.prices[0].value" label="Price in"></v-text-field>
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
          :items="products.content"
          :length='products.totalPages'
          :pagination.sync="pagination"
          :total-items="products.totalElements"
          :rows-per-page-items="[20,50,100]"

          class="elevation-10"
        >
          <v-progress-linear v-if="loading" slot="progress" color="danger" indeterminate></v-progress-linear>
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
                {{props.item.sku}}
              </td>
              <td @click="editItem(props.item)">
                <div v-if="props.item.categories !== null">
                  <div v-for="category in props.item.categories">
                    {{category.name}}
                  </div>
                </div>
              </td>
              <td @click="editItem(props.item)">
                <div v-if="props.item.prices !== null">
                  <div v-for="price in props.item.prices">
                    {{price.type}}: {{price.value}}
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
  import * as _ from "lodash";

  export default {
    name: "ProductList",
    data() {
      return {
        selected: [],
        dialog: false,
        headers: [
          {text: 'ID', value: 'id'},
          {text: 'Name', value: 'name'},
          {text: 'SKU', value: 'sku'},
          {text: 'Categories', value: 'categories'},
          {text: 'Prices', value: 'prices'},
        ],
        editedIndex: -1,
        editedItem: {
          name: '',
          sku: '',
          categories: [],
          prices: [
            {
              type: 'PRICE_TYPE_IN',
              value: 0,
              productId: 0
            }
          ]
        },
        defaultItem: {
          name: '',
          sku: '',
          categories: [],
          prices: [
            {
              type: 'PRICE_TYPE_IN',
              value: 0,
              productId: 0
            }
          ]
        },
        pagination: {}
      }
    },
    watch: {
      pagination: {
        handler () {
          store.dispatch('getProductList', {page: (this.pagination.page - 1), size: this.pagination.rowsPerPage, sort: this.pagination.sortBy, desc: this.pagination.descending ? 'DESC' : ''})
          // this.getDataFromApi()
          //   .then(data => {
          //     this.desserts = data.items
          //     this.totalDesserts = data.total
          //   })
        },
        deep: true
      },
      dialog(val) {
        val || this.close()
      }
    },
    computed: {
      products() {
        return store.getters.products;
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
        this.editedIndex = this.products.content.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true
      },

      deleteItem(items) {
        // const result = _.difference(this.products.content, items);
        confirm('Are you sure you want to delete this item?') && store.dispatch('deleteProducts', items);
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
    created() {
      Promise.all([
        store.dispatch('getProductFields'),
        // store.dispatch('initProductList', {page: 0, size: 10})
      ]).then();
    },
  }
</script>

<style scoped>
  .active-row {
    cursor: pointer;
  }
</style>
