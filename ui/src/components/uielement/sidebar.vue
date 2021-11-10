<template>
  <v-navigation-drawer
      v-model="open"
      absolute
      temporary
  >
    <v-list-item>
      <v-list-item-avatar>
        <v-img src="https://randomuser.me/api/portraits/men/78.jpg"></v-img>
      </v-list-item-avatar>

      <v-list-item-content>
        <v-list-item-title>John Leider</v-list-item-title>
      </v-list-item-content>
    </v-list-item>

    <v-divider></v-divider>

    <v-list dense>
      <v-list-item-group v-model="selectedItem" color="primary">

        <v-list-item
            v-for="item in items"
            :key="item.title"
            link
            :to="item.url"
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
export default {
  name: "sidebar",
  props:{
    drawer: Boolean
  },
  data(){
    return {
      open: this.drawer,
      selectedItem: 0,
      items: [
        { title: 'Home', icon: 'mdi-view-dashboard', url: '/' },
        { title: 'About', icon: 'mdi-forum', url: '/about' },
      ],
    }
  },
  watch:{
    drawer(value){
      this.open = value;
    },
    open(value){
      if (value == false) {
        this.$emit('close-drawer');
      }
    }
  }
}
</script>

<style scoped>

</style>