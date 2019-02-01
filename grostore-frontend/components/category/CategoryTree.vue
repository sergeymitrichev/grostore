<template>
  <v-navigation-drawer
    v-model="drawer"
    fixed
    app
    temporary
    flat
  >
    <v-list 
      two-line
      class="warning">
      <v-list-tile 
        color="white">
        <v-list-tile-action>
          <v-icon 
            warning
            color="white"
            @click="close">search</v-icon>
        </v-list-tile-action>
        <v-list-tile-content>
          <v-text-field 
            class="text--white"
            color="white"
            label="Поиск по сайту"
          />
        </v-list-tile-content>
        <v-list-tile-action>
          <v-icon 
            color="white"
            @click="close">close</v-icon>
        </v-list-tile-action>
      </v-list-tile>
    </v-list>
    <v-list>  
      <template
        v-for="category in tree"
      >
        <v-list-group 
          v-if="category.children.length > 0"
          :key="'group' + category.id"
        >
          <v-list-tile slot="activator">
            <v-list-tile-avatar>
              <img :src="category.data.imageUrl">
            </v-list-tile-avatar>
            <v-list-tile-content>
              <v-list-tile-title>{{ category.data.name }}</v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
          <v-list-tile
            v-for="children in category.children"
            :key="children.data.id"
            :to="'/shop' + children.data.hgu"
          >
            <v-list-tile-avatar>
              <img :src="children.data.imageUrl">
            </v-list-tile-avatar>
            <v-list-tile-action>
              {{ children.data.name }}
            </v-list-tile-action>
          </v-list-tile>
        </v-list-group>
        <v-list-tile
          v-else
          :key="'tile' + category.id"
          :to="'/shop' + category.data.hgu"
        >
          <v-list-tile-avatar>
            <img :src="category.data.imageUrl">
          </v-list-tile-avatar>
          <v-list-tile-action>
            {{ category.data.name }}
          </v-list-tile-action>
        </v-list-tile>
      </template>
    </v-list>
  </v-navigation-drawer>
</template>
<script>
import { mapGetters } from 'vuex'

export default {
  props: {
    drawer: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    ...mapGetters({
      tree: 'categories/getTree'
    })
  },
  methods: {
    close() {
      this.drawer = false
    }
  }
}
</script>
<style>
</style>
