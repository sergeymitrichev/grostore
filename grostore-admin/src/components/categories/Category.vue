<template>
  <v-container grid-list-md>
    <v-layout row wrap>
      <v-flex md12>
        <h1>Categories</h1>
        <v-dialog v-model="dialog" max-width="500px">
          <v-btn slot="activator" color="success" dark @click="editedItem = defaultItem; editedIndex = -1" class="mb-2">
            New Category
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
                    <v-text-field v-model="editedItem.title" label="Item name"></v-text-field>
                  </v-flex>
                  <v-flex xs12 sm6 md4>
                    <v-text-field v-model="editedItem.description" label="Item description"></v-text-field>
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
        <v-card>
          <v-card-title>
            <v-flex d-flex>
              <sl-vue-tree v-model="categories" @nodeclick='editItem'>
                <template slot="title" slot-scope="{ node }">
                  <span class="item-icon">
                    <i class="fa fa-file" v-if="node.isLeaf"></i>
                    <i class="fa fa-folder" v-if="!node.isLeaf"></i>
                  </span>
                  {{ node.title }}
                </template>
              </sl-vue-tree>
            </v-flex>
          </v-card-title>
        </v-card>
      </v-flex>
    </v-layout>

  </v-container>
</template>

<script>
  import {store} from '../../store'
  import SlVueTree from "sl-vue-tree";
  import 'sl-vue-tree/dist/sl-vue-tree-minimal.css'

  export default {
    components: {SlVueTree},
    name: "Category",
    data() {
      return {
        dialog: false,
        editedIndex: -1,
        editedItem: {
          id: null,
          title: '',
          parent: {},
          children: []
        },
        defaultItem: {
          id: null,
          title: '',
          parent: {},
          children: []
        }
      }
    },
    computed: {
      categories: {
        get() {
          return store.getters.categories
        },
        set(value) {
          // this.$store.commit('setCategories', value)
        }
      },
      formTitle() {
        return this.editedIndex === -1 ? 'New category' : 'Edit category'
      }
    },
    methods: {
      editItem(node, event) {
        console.log(node);
        console.log(event);
        this.editedIndex = this.categories.indexOf(node);
        this.editedItem = Object.assign({}, node);
        this.dialog = true;
      },
      close() {
        this.dialog = false;
      },
      save() {
        this.close();
      }
    },
    created() {
      Promise.all([
        store.dispatch('initCategories')
      ]).then();
    }
  }
</script>

<style>
  .sl-vue-tree-title {
    cursor: pointer;
  }
</style>
