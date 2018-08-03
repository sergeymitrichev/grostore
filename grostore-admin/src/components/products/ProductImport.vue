<template>
  <v-card>
    <v-card-title><h1>Import Product</h1></v-card-title>
    <template>
      <div class="container">
        <v-form v-on:submit="createPriceList($event)">
          <v-text-field
            label="Name"
            required
          ></v-text-field>
          <input type="file" multiple name="file" required/>
          <v-btn color="error" type="submit">Save</v-btn>
        </v-form>
        <form>

        </form>
      </div>
    </template>
    <v-data-table
      hide-headers
      :items="priceLists"
      :loading="loading"
      hide-actions
      class="elevation-10"
    >
      <v-progress-linear v-if="loading" slot="progress" color="blue" indeterminate></v-progress-linear>
      <template slot="items" slot-scope="props">
        <td>{{props.item.fileName}}</td>
        <td>{{props.item.products}}</td>
      </template>

    </v-data-table>
  </v-card>
</template>

<script>
  export default {
    name: 'ProductImport',
    data() {
      return {
        loading: true
      }
    },
    computed: {
      priceLists() {
        return this.$store.getters.priceLists
      },
      // loading() {
      //   return this.$store.getters.loading
      // }
    },
    methods: {
      createPriceList(e) {
        e.preventDefault();
        let name = e.target[0];
        let file = e.target[1];
        let formData = new FormData();
        debugger
        formData.append('file', file.files[0], file.files[0].name);
        formData.append('name', name.value);
        return this.$store.dispatch('createPriceList', formData);
      }
    }
  }
</script>

<style>

</style>
