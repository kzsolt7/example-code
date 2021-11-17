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
        console.log(r.headers.username)
        this.$cookies.set("access-token", r.headers.authorization, "1d")
        this.$cookies.set("refresh-token", r.headers.refresh, "10d")
      });
    }
  }
}
</script>

<style scoped>

</style>