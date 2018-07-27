<template>
  <div>
    <v-card>
      <v-card-title><h1>Import Product</h1></v-card-title>
      <ProductImportXls></ProductImportXls>
    </v-card>
    <v-card>
      <v-card-title>
        <h1>Product List</h1>
        <!--<v-spacer></v-spacer>-->
        <!--<v-text-field-->
          <!--v-model="search"-->
          <!--append-icon="search"-->
          <!--label="Search"-->
          <!--single-line-->
          <!--hide-details-->
        <!--&gt;</v-text-field>-->
      </v-card-title>
      <v-data-table
        :headers="headers"
        :items="products"
        :loading="loading"
        :search="search"
        hide-actions
        class="elevation-10"
      >
        <v-progress-linear v-if="loading" slot="progress" color="blue" indeterminate></v-progress-linear>
        <template slot="items" slot-scope="props">
          <td>
            <v-edit-dialog
              :return-value.sync="props.item.title"
              lazy
              @save="save"
              @cancel="cancel"
              @open="open"
              @close="close"
            >
              {{props.item.title}}
              <v-text-field
                slot="input"
                v-model="props.item.title"
                :rules="[max25chars]"
                label="Edit"
                single-line
                counter
              ></v-text-field>
            </v-edit-dialog>
          </td>
          <td>
            <v-edit-dialog
              :return-value.sync="props.item.price"
              lazy
              @save="save"
              @cancel="cancel"
              @open="open"
              @close="close"
            >
              {{props.item.price}}
              <v-text-field
                slot="input"
                v-model="props.item.price"
                :rules="[max25chars]"
                label="Edit"
                single-line
                counter
              ></v-text-field>
            </v-edit-dialog>
          </td>
        </template>
        <v-alert slot="no-results" :value="true" color="error" icon="warning">
          Your search for "{{ search }}" found no results.
        </v-alert>
      </v-data-table>
    </v-card>
  </div>
</template>

<script>
  import ProductImportXls from "./products/ProductImportXls";

  export default {
    components: {ProductImportXls},
    data() {
      return {
        loading: false,
        snack: false,
        snackColor: '',
        snackText: '',
        max25chars: (v) => v.length <= 25 || 'Input too long!',
        headers: [
          {text: 'Title', value: 'title'},
          {text: 'Price', value: 'price'}
        ]
      }
    },
    methods: {
      save() {
        this.snack = true
        this.snackColor = 'success'
        this.snackText = 'Data saved'
      },
      cancel() {
        this.snack = true
        this.snackColor = 'error'
        this.snackText = 'Canceled'
      },
      open() {
        this.snack = true
        this.snackColor = 'info'
        this.snackText = 'Dialog opened'
      },
      close() {
        console.log('Dialog closed')
      }
    },
    computed: {
      products() {
        return this.$store.getters.availableProducts
      }
    },

    created() {
      this.loading = true
      this.$store.dispatch('fetchProducts')
        .then(() => this.loading = false)
    }
  }
</script>

<style scoped>

</style>
