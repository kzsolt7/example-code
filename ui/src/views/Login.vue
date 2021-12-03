<template>
  <div>
    <v-form class="col-md-4 mx-auto">
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
      <v-btn color="teal" dark @click="login">Login</v-btn>
    </v-form>
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
      passwordRules: []

    }
  },
  methods: {
    login() {
      api.post(`authenticate?username=${this.username}&password=${this.password}`).then(r => {
        if (r.status === 200) {
          this.$cookies.set("access-token", r.headers.authorization, "3600s");
          this.$cookies.set("refresh-token", r.headers.refresh, "7200s");
          this.$cookies.set("username", r.headers.username, "7200s");
          this.$store.commit("setUserName", r.headers.username)
          this.$router.push("/")
        }
      }).then(() => {
        api.get(`/user/byUserName?username=${this.username}`).then(r => {
          if(r.status == 200) {
            r.data.permissions.forEach(role => {
              if(role == "mng_HasAccessToX"){
                this.$store.commit("setAccess", true)
              }

            })
            this.$store.commit("setRoles", r.data.permissions)
            //console.log(this.$store.getters.getAccess)
          }
        })
      });

    }
  }
}
</script>

<style scoped>

</style>