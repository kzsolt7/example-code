<template>
  <v-navigation-drawer
      v-model="open"
      absolute
      temporary
  >
    <v-list-item>

      <v-list-item-content>
        <v-list-item-title>X manager</v-list-item-title>
      </v-list-item-content>
    </v-list-item>

    <v-divider></v-divider>

    <v-list dense>
      <v-list-item-group v-model="selectedItem" color="teal">

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

        <v-subheader>Data analysis</v-subheader>

        <v-list-item
            v-for="item in dataAnalysisItems"
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

        <v-subheader>Settings</v-subheader>

        <v-list-item
            v-for="item in settingsItems"
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
        { title: 'Notification groups', icon: 'mdi-forum', url: '/notification' },
        //{ title: 'Permission groups', icon: 'mdi-forum', url: '/permission' },
        { title: 'Add new user', icon: 'mdi-forum', url: '/add-new-user' },
        { title: 'Add new group', icon: 'mdi-forum', url: '/add-new-group' },
        { title: 'Export-import', icon: 'mdi-forum', url: '/export-import' },

      ],
      dataAnalysisItems: [
        { title: 'Realtime monitoring', icon: 'mdi-view-dashboard', url: '/realtime-monitoring' },
        { title: 'Clamp analysis', icon: 'mdi-forum', url: '/clamp-analysis' },

      ],

      settingsItems: [
        { title: 'Network management', icon: 'mdi-view-dashboard', url: '/network-management' },
        { title: 'System update', icon: 'mdi-forum', url: '/system-update' },

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