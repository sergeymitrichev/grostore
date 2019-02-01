<template>
  <div>
    <category-tree :drawer="categoryTree.drawer"/>
    <v-container 
      mt-5
      grid-list-md 
      text-xs-center>
      <v-layout 
        row 
        wrap>
        <v-flex xs3>
          <v-card 
            color="primary">
            <v-card-text class="px-0">Logo</v-card-text>
          </v-card>
        </v-flex>
        <v-flex xs6>
          <v-card              
            color="primary">
            <v-card-text class="px-0">Menu</v-card-text>
          </v-card>
        </v-flex>
        <v-flex xs3>
          <v-card              
            color="primary">
            <v-card-text class="px-0">Phone</v-card-text>
          </v-card>
        </v-flex>
        <v-flex xs12>
          <v-card 
            dark 
            color="primary">
            <v-card-text class="px-0">Content</v-card-text>
            <v-btn 
              flat
              large
              round 
              color="success"
              @click.stop="showCategoryTree"
            >Каталог продуктов</v-btn>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
    <nuxt/>
  </div>
</template>
<script>
import CategoryTree from '~/components/category/CategoryTree'
import { mapGetters } from 'vuex'

export default {
  components: {
    CategoryTree
  },
  data() {
    return {
      categoryTree: {
        drawer: false
      }
    }
  },
  methods: {
    showCategoryTree() {
      this.categoryTree.drawer = null
      this.categoryTree.drawer = true
    }
  }
}
</script>

<style>
</style>
