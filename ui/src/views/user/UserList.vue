<template>
  <div>
    <transition name="fade">
    <v-alert v-show="isSuccess" type="success">
      {{successMessage}}
    </v-alert>
    </transition>
    <user-table></user-table>
  </div>
</template>

<script>
import UserTable from "@/components/user/UserTable";
//import VueCookies from 'vue-cookies';
export default {
  name: "UserList",
  components: {
    UserTable
  },
  data() {
    return {
      isSuccess: false,
      successMessage: "jsjs"
    }
  },
  mounted() {
    this.init()

  },
  created() {
    setTimeout(() =>
        this.isSuccess = false, 3000)
  },
  methods: {
    init() {
      if (this.$cookies.get("success") == "new-success") {
        this.successMessage = "User successfully added."
        this.isSuccess = true
      }
      if(this.$cookies.get("success") == "update-success"){
        this.successMessage = "User successfully updated."
        this.isSuccess = true;
      }
    },
  }
}
</script>

<style scoped>

</style>