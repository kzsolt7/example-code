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

        <v-subheader>Device management</v-subheader>

        <v-list-item
            v-for="item in deviceManagementItems"
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

        <v-subheader>User management</v-subheader>

        <v-list-item
            v-for="item in userManagementItems"
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
      deviceManagementItems: [
        { title: 'Device list', icon: 'mdi-view-dashboard', url: '/' },
        { title: 'Software update', icon: 'mdi-forum', url: '/about' },
      ],
      userManagementItems: [
        { title: 'User list', icon: 'mdi-view-dashboard', url: '/user-list' },
        { title: 'Notifcation group', icon: 'mdi-forum', url: '/notifictaion' },
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