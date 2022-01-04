<template>
  <div>
    <transition name="fade">
      <v-alert max-width="374" class="mx-auto" v-show="isWarning" type="warning">
        {{ warningMessage }}
      </v-alert>
    </transition>
    <v-card
        class="mx-auto pa-8"
        elevation="1"
        max-width="374"
    >
      <v-form @submit.prevent="login" class="mx-auto">
        <p class="text-h4 text--primary">
          Login
        </p>

        <v-text-field
            v-model="username"
            :rules="usernameRules"
            label="Username"
            required
        ></v-text-field>

        <v-text-field
            v-model="password"
            :rules="passwordRules"
            label="Password"
            type="password"
            required
        ></v-text-field>
        <v-btn type="submit" color="teal" dark>Login</v-btn>
      </v-form>
    </v-card>

  </div>
</template>

<script>
import {api} from "@/api";

export default {
  name: "Login",
  data() {
    return {
      username: '',
      password: '',
      usernameRules: [],
      passwordRules: [],
      isWarning: false,
      warningMessage: 'Incorrect username or password'

    }
  },
  methods: {
    login() {
      api.post(`authenticate?username=${this.username}&password=${this.password}`).then(r => {
        if (r.status === 200) {
          this.$cookies.set("access-token", r.headers.authorization, "1296000s");
          this.$cookies.set("refresh-token", r.headers.refresh, "2592000s");
          this.$cookies.set("username", r.headers.username, "1296000s");
          this.$store.commit("setUserName", r.headers.username)
          this.$router.push("/")
        }
      }, err => {
        if(err.response.status == 401) {
          this.incorrectWarning()
        }
      }).then(() => {
        api.get(`/user/byUserName?username=${this.username}`).then(r => {
          if (r.status == 200) {
            r.data.permissions.forEach(role => {
              if (role === "mng_HasAccessToX") {
                this.$store.commit("setAccess", true)
              }
            })
            this.init()
            this.$store.commit("setRoles", r.data.permissions)
            //console.log(this.$store.getters.getAccess)
          }
        })
      });

    },
    init() {
      this.$store.commit("setUserName", this.$cookies.get("username"))
      this.$store.dispatch('getAccess')
      this.$store.dispatch('getPermissions')
    },
    incorrectWarning() {
      this.isWarning = true;
      setTimeout(() => {
        this.isWarning = false;
      }, 3000)
    }
  }
}
</script>

<style scoped>

</style>