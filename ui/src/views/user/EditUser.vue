<template>
  <v-row>


    <div class="col-md-6">
      <v-form
          ref="form"
          v-model="formModel"
          lazy-validation
      >

        <v-text-field
            v-model="userName"
            label="Username"
            required
        ></v-text-field>

        <v-text-field
            v-model="userEmail"
            label="Email"
            required
        ></v-text-field>

        <v-text-field
            v-model="userPassword"
            label="Password"
            type="password"
            required
        ></v-text-field>

        <v-select
            v-model="groupValue"
            :items="groupItems"
            item-color="teal"
            chips
            label="Groups"
            multiple
            solo
        ></v-select>


        <v-select
            :items="activeItems"
            item-color="teal"
            v-model="state"
            label="Solo field"
            solo
        ></v-select>

        <v-btn color="teal" @click="saveUser" dark>Update</v-btn>

      </v-form>
    </div>

    <div class="col-md-6" style="position: absolute; top: 0; bottom: 0; right:0; overflow-y: scroll">
      <div v-for="roleItem in roleItems" v-bind:key="roleItem.value">
        <v-checkbox
            :label="roleItem.name"
            color="teal"
            :value="roleItem.value"
            hide-details
            v-model="roleValues"
        ></v-checkbox>
      </div>
    </div>
  </v-row>
</template>

<script>
import {api} from "@/api";

export default {
  name: "EditUser",
  data() {
    return {
      userName: '',
      userEmail: '',
      userPassword: '',
      formModel: '',
      activeItems: ['Active', 'Inactive'],
      state: '',
      //groupItems: ['Engineer', 'Operator'],
      groupItems: this.$store.getters.getPermissionGroups,
      groupValue: [''],
      roleItems: this.$store.getters.getRoleItems,
      roleValues: [],
      id: ""
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      api.get("user?id=" + this.$route.params.id)
          .then(r => {
                this.id = r.data.id
                this.userName = r.data.userName
                this.userEmail = r.data.email
                this.userPassword = r.data.password
                this.state = r.data.state
                this.groupValue = r.data.permissionGroups
                this.roleValues = r.data.permissions
              }
          );
    },
    saveUser() {
      if (this.userName && this.userPassword)
        api.put('/user', {
          id: this.id,
          userName: this.userName,
          permissions: this.roleValues,
          permissionGroups: this.groupValue,
          password: this.userPassword,
          state: this.state,
          email: this.userEmail
      }).then(r => {
          if (r.status == 200) {

            this.$router.push("/user-list/update-success")
          }
        });
    },
  }
}
</script>

<style scoped>

</style>