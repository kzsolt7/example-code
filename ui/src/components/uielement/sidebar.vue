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

        <v-list-item
            v-for="item in outOfMenuGroup"
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
      outOfMenuGroup: [
        { title: 'Home', icon: 'mdi-home', url: '/' },
      ],
      deviceManagementItems: [
        { title: 'Device list', icon: 'mdi-tablet-cellphone', url: '/device-list' },
        { title: 'Software update', icon: 'mdi-cellphone-arrow-down', url: '/about' },
      ],
      userManagementItems: [
        { title: 'User list', icon: 'mdi-account-search', url: '/user-list' },
        { title: 'Notification groups', icon: 'mdi-bell', url: '/notification' },
        { title: 'Permission groups', icon: 'mdi-account-key', url: '/permission' },
        { title: 'Add new user', icon: 'mdi-account-plus', url: '/add-new-user' },
        { title: 'Add new group', icon: 'mdi-account-group', url: '/add-new-group' },
        { title: 'Export-import', icon: 'mdi-database-export', url: '/export-import' },
      ],
      dataAnalysisItems: [
        { title: 'Realtime monitoring', icon: 'mdi-monitor-eye', url: '/realtime-monitoring' },
        { title: 'Clamp analysis', icon: 'mdi-poll', url: '/clamp-analysis' },
      ],
      settingsItems: [
        { title: 'Network management', icon: 'mdi-lan-connect', url: '/network-management' },
        { title: 'System update', icon: 'mdi-cellphone-arrow-down', url: '/system-update' },
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