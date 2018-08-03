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
        <!--<td>{{props.item.file}}</td>-->
        <!--<td>{{props.item.identityField}}</td>-->
        <td>{{new Date(props.item.created).toLocaleString()}}</td>
        <td>{{new Date(props.item.updated).toLocaleString()}}</td>
        <td>
          <v-icon
            small
            class="mr-2"
            @click="editItem(props.item)"
          >
            edit
          </v-icon>
          <v-icon
            small
            @click="deleteItem(props.item)"
          >
            delete
          </v-icon>
        </td>
      </template>
    </v-data-table>
  </v-card>
</template>

<script>
  import {store} from '../../store'

  export default {
    name: 'ProductImportList',
    data() {
      return {
        // loading: true,
        headers: [
          {text: 'ID'},
          {text: 'Название'},
          // {text: 'Файл'},
          // {text: 'Признак уникальности'},
          {text: 'Дата создания'},
          {text: 'Дата загрузки'}
        ]
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
      }
    }
  }
</script>

<style>

</style>
