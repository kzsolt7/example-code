<template>
  <v-app>
    <v-app-bar
        app
        color="white"
        dense
        elevation="1"
    >
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>

      <v-toolbar-title class="mr-16">{{ time }}</v-toolbar-title>
      <v-toolbar-title class="ml-16">{{ this.$route.name }}</v-toolbar-title>


      <v-spacer></v-spacer>

      <v-menu left bottom>
        <template v-slot:activator="{ on, attrs }">
          <v-btn icon v-bind="attrs" v-on="on">
            <v-icon>mdi-account-circle-outline</v-icon>
          </v-btn>
        </template>

        <v-list>
          <v-list-item @click="() => {}">
            <v-list-item-title>Logout</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>

    </v-app-bar>

    <sidebar :drawer="drawer" @close-drawer="drawer = false"></sidebar>

    <v-main style="position: absolute; top:48px; bottom: 48px; left:20px; right: 20px;">
      <router-view/>
    </v-main>
    <v-footer padless fixed color="white">
      <v-col
          class="text-center"
          cols="12"
      >
        {{ new Date().getFullYear() }} — <strong>Cavity eye</strong>
      </v-col>
    </v-footer>

  </v-app>
</template>

<script>
import sidebar from "@/components/uielement/sidebar";

export default {
  name: 'App',
  components: {
    sidebar
  },
  data: () => ({
    drawer: false,
    time: new Date().toLocaleTimeString()
  }),
  mounted() {
    setInterval(() => {
      this.createTimer()
    }, 1000);
  },
  methods:{
    createTimer() {
      const d = new Date();
      this.time = d.toLocaleTimeString();
    }
  }
};
</script>
