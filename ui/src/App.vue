<template>
  <v-app>
    <v-app-bar
        app
        color="white"
        dense
        elevation="1"
    >
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>

      <v-toolbar-title class="mr-16">{{ this.$route.name }}</v-toolbar-title>

      <v-toolbar-title class="ml-16">{{ time }}</v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-heart</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-dots-vertical</v-icon>
      </v-btn>
    </v-app-bar>

    <sidebar :drawer="drawer" @close-drawer="drawer = false"></sidebar>

    <v-main style="position: absolute; top:48px; bottom: 48px; left:20px; right: 20px;">
      <router-view/>
    </v-main>
    <v-footer padless absolute color="white">
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
