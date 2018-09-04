<template>
  <v-container grid-list-md>
    <v-layout row wrap>
      <v-flex md12>
        <h1>Products</h1>
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
            <td>
              <v-edit-dialog
                :return-value.sync="props.item.name"
                lazy
                @save="save"
                @cancel="cancel"
                @open="open"
                @close="close"
              >
                {{props.item.name}}
                <v-text-field
                  slot="input"
                  v-model="props.item.name"
                  :rules="[max160chars]"
                  label="Edit"
                  single-line
                  counter
                  @input="change(props.item)"
                ></v-text-field>
              </v-edit-dialog>
            </td>
            <td>
              <v-edit-dialog
                :return-value.sync="props.item.sku"
                lazy
                @save="save"
                @cancel="cancel"
                @open="open"
                @close="close"
              >
                {{props.item.sku}}
                <v-text-field
                  slot="input"
                  v-model="props.item.sku"
                  :rules="[max160chars]"
                  label="Edit"
                  single-line
                  counter
                ></v-text-field>
              </v-edit-dialog>
            </td>
          </template>
        </v-data-table>
        <v-snackbar v-model="snack" :timeout="3000" :color="snackColor">
          {{ snackText }}
          <v-btn flat @click="snack = false">Close</v-btn>
        </v-snackbar>
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
        snack: false,
        snackColor: '',
        snackText: 'ENTER - save, ESC - cancel',
        max160chars: (v) => v.length <= 160 || 'Input too long!',
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
      }
    },
    methods: {
      change(product) {
         _.debounce(this.save, 1000)(product);
      },
      save(product) {
        console.log("save");
        this.snack = true;
        this.snackColor = 'success';
        this.snackText = 'Data saved';
        return store.dispatch('updateProduct', product);
      },
      cancel() {
        this.snack = true;
        this.snackColor = 'error';
        this.snackText = 'Canceled';
      },
      open() {
        console.log("open");
        this.snack = true;
        this.snackColor = 'info';
        this.snackText = 'Dialog opened';
      },
      close() {
        console.log('Dialog closed');
      }
    },
    beforeRouteEnter(to, form, next) {
      Promise.all([
        store.dispatch('getProductFields'),
        store.dispatch('initProductList')
      ]).then(() => next());
    }
  }
</script>

<style scoped>

</style>
