<template>
  <v-container grid-list-md>
    <v-layout row wrap>
      <v-flex md12>
        <h1>Categories</h1>
        <v-card>
          <v-card-title>
            <v-flex
              xs6 sm4 md-2 d-flex
              v-for="parent in selectedPath"
              v-bind:key="parent.id"
            >
              <v-select
                :items="parent.child"
                item-text="name"
                item-value=""
                solo
                label="Select category"
                v-on:change="changePath($event)"
              ></v-select>

              <span class="path-divider">/</span>
            </v-flex>
          </v-card-title>
          <v-card-text>
            <v-text-field v-model="selectedCategory.name" label="Category name"></v-text-field>
            <input type="hidden" :v-model="selectedCategory.id">
            <p v-if="selectedCategory.parent !== undefined">Parent category: {{selectedCategory.parent}}</p>
            <p v-if="selectedCategory.child !== undefined">Child categories: {{selectedCategory.child.length}}</p>
            <v-chip close
                    v-for="(children, index) in selectedCategory.child"
                    v-bind:key="children.id"
            >{{children.name}}</v-chip>
            <v-chip @click><v-avatar color="red"><v-icon color="white">fa-plus</v-icon></v-avatar>Add new</v-chip>
          </v-card-text>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import {store} from '../../store'

  export default {
    name: "Category",
    computed: {
      selectedPath() {
        return store.getters.selectedPath
      },
      allCategories() {
        return store.getters.categories
      }
    },
    data() {
      return {
        selectedCategory: store.getters.selectedPath[0]
      }
    },
    methods: {
      changePath(event) {
        console.log(event);
        if (event) {
          this.selectedCategory = event;
          return store.dispatch('changeSelectedPath', event)
        }
      }
    }
  }
</script>

<style scoped>
  .path-divider {
    height: 100%;
    padding-left: 10px;
    font-size: 26px;
    padding-top: 2px;
  }
</style>
