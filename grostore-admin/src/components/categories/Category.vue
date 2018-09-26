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
                  <!--<input type="hidden" :v-model="editedItem.data.id">-->
                  <!--<v-flex xs12 sm12 md12>-->
                    <!--<v-text-field v-model="editedItem.data.parent.name" label="Parent Category"></v-text-field>-->
                  <!--</v-flex>-->
                  <v-flex xs12 sm12 md12>
                    <v-text-field v-model="editedItem.data.id" label="Item ID" readonly="readonly"></v-text-field>
                  </v-flex>
                  <v-flex xs12 sm12 md12>
                    <v-text-field v-model="editedItem.data.name" label="Item name"></v-text-field>
                  </v-flex>
                  <v-flex xs12 sm12 md12>
                    <v-text-field v-model="editedItem.data.description" label="Item description"></v-text-field>
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
      <v-flex md12 sm12 xs12>
        <v-card>
          <v-card-title>
            <v-flex d-flex>
              <sl-vue-tree v-model="categories" @nodedblclick='editItem' @select="selectItems" @drop="dropItem">
                <template slot="title" slot-scope="{ node }">
                  <span class="item-icon" style="float:right">
                    <i class="fa fa-link"></i>
                    <i class="fa fa-trash" v-if="node.data.id !== 1"></i>
                  </span>
                  {{ node.title }}
                </template>
              </sl-vue-tree>
            </v-flex>
          </v-card-title>
        </v-card>
        <ul>
          <li>Double click on category to edit</li>
          <li>Ctrl + Click to select many categories</li>
        </ul>
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
    name: 'Category',
    data() {
      return {
        dialog: false,
        editedIndex: -1,
        editedItem: {
          id: 0,
          title: null,
          children: [],
          data: {
            id: null,
            name: null,
            description: null,
            parent: {
              id: 1,
              name: "Root category"
            }
          }
        },
        defaultItem: {
          id: 0,
          title: null,
          children: [],
          data: {
            id: null,
            name: null,
            description: null,
            parent: {
              id: 1,
              name: "Root category"
            }
          }
        },
        selectedItems: []
      }
    },
    computed: {
      loading() {
        return store.getters.loading
      },
      categories: {
        get() {
          return store.getters.categories
        },
        set(value) {
          this.$store.commit('SET_CATEGORIES', value)
        }
      },
      formTitle() {
        return this.editedIndex === -1 ? 'New category' : 'Edit category'
      }
    },
    methods: {
      editItem(node, event) {
        if(node.data.id === 1) {
          // cannot edit root category
          return;
        }
        this.editedIndex = node.data.id || -1;
        this.editedItem = Object.assign({}, node);
        this.dialog = true;

      },
      selectItems(nodes, event) {
        this.selectedItems = nodes;
      },
      dropItem(dragging, position, event) {
        console.log("dragging");
        console.log(dragging[0].data.parent.name);
        console.log(dragging[0]);
        console.log("position node is");
        console.log(position.node);
        console.log(position.node.data.id);
        console.log("position type");
        console.log(position.placement);
        let parent = {};
        switch (position.placement) {
          case 'before': parent.id = position.node.data.parent.id; break;
          case 'after': parent.id = position.node.data.parent.id; break;
          case 'inside': parent.id = position.node.data.id; break;
        }
        dragging.forEach(d => {
          d.data.parent = parent;
        });
        console.log(dragging);
        this.editItem(dragging[0]);
        this.save();
      },
      close() {
        this.dialog = false;
      },
      save() {
        store.dispatch('saveCategory', this.editedItem.data);
        this.close();
      },
      delete(id) {
        console.log(id);
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
    font-size: initial;
    padding: 5px 10px;
    width: 100%;
  }

  .sl-vue-tree-toggle > span {
    width: 24px;
    text-align: center;
    display: inline-block;
    border-radius: 50%;
  }

  .sl-vue-tree-toggle > span:hover {
    background-color: #212121;
  }

  .sl-vue-tree-selected > .sl-vue-tree-node-item {
    background-color: #F44336
  }
</style>
