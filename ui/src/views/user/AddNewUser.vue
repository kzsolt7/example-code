<template>
  <v-row>


    <div class="col-md-6">
      <v-form
          ref="form"
          v-model="formModel"
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

        <v-btn @click="saveUser" color="teal" dark>Save</v-btn>

      </v-form>
    </div>

    <div class="col-md-6" style="max-height: 90vh; overflow-y: scroll">
      <div v-for="roleItem in roleItems" v-bind:key="roleItem.value">
        <v-checkbox
            :label="roleItem.name"
            color="teal"
            :value="roleItem.value"
            hide-details
            v-model="permissions"
        ></v-checkbox>
      </div>
    </div>
  </v-row>
</template>

<script>
import {api} from "@/api";

export default {

  name: "AddNewUser",
  data() {
    return {
      userName: '',
      userEmail: '',
      userPassword: '',
      formModel: '',
      activeItems: ['Active', 'Inactive'],
      state: 'Active',
      //groupItems: ['Engineer', 'Operator'],
      groupItems: this.$store.getters.getPermissionGroups,
      groupValue: [],
      roleItems: this.$store.getters.getRoleItems,
      permissions: [],
      status: ''

    }
  },
  methods: {
    saveUser() {
      if (this.userName && this.userPassword)
        api.post('/user', {
          userName: this.userName,
          email: this.userEmail,
          password: this.userPassword,
          permissionGroups: this.groupValue,
          state: this.state,
          permissions: this.permissions
        }).then(r => {
          if (r.status == 200) {
            this.$router.push("/user-list/new-success")
          }
        });

    }
  }
}
</script>

<style scoped>

</style>