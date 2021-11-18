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
            :rules="userRules"
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
            :rules="passwordRules"
        ></v-text-field>

        <v-select
            v-model="groupValue"
            :items="groupItems"
            item-color="teal"
            chips
            label="Permission groups"
            multiple
            solo
            @change="groupSelectChanged"
        ></v-select>


        <v-select
            :items="activeItems"
            item-color="teal"
            v-model="state"
            label="Solo field"
            solo
        ></v-select>

        <v-btn @click="saveUser" color="teal" dark>Save</v-btn>
        <v-btn style="margin-left: 20px" color="grey" @click="$router.go(-1)" dark>Cancel</v-btn>

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
            :disabled="roleItem.disabled"
        ></v-checkbox>
      </div>
    </div>
  </v-row>
</template>

<script>
import {api} from "@/api";
import VueCookies from 'vue-cookies';

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
      groupItems: [],
      groupValue: [],
      roleItems: this.$store.getters.getRoleItems,
      permissions: [],
      permissionsFromGroups: [],
      status: '',
      permissionGroupsObject: [],
      userRules: [
        v => !!v || 'Username is required',
      ],
      passwordRules: [
        v => !!v || 'Password is required',
      ],
    }
  },
  methods: {
    saveUser() {
      this.$refs.form.validate()
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
            VueCookies.set('success', 'new-success', "10s")
            this.$router.push("/user-list")
          }
        });

    },
    init() {
      api.get('user/permission-group/all').then(r => {
        this.permissionGroupsObject = r.data
        //console.log(r.data)
        for (let item in r.data) {
          this.groupItems.push(r.data[item].name)
        }
      })
    },
    groupSelectChanged() {
      this.permissions = []

      for (let item in this.groupValue) {
        for (let elem in this.permissionGroupsObject) {
          if (this.groupValue[item] == this.permissionGroupsObject[elem].name) {
            //console.log(this.groupValue[item])
            this.permissions.push.apply(this.permissions, this.permissionGroupsObject[elem].permissions)
          }
        }
      }
      this.permissionsFromGroups = this.permissions
      //console.log(this.permissionsFromGroups)
      for (let el in this.roleItems) {
        //console.log(this.permissionsFromGroups[item])
        this.roleItems[el].disabled=false
        for (let item in this.permissionsFromGroups ) {
          // console.log(this.permissionsFromGroups[item])
          // console.log(this.roleItems[el].value)
          if (this.permissionsFromGroups[item] == this.roleItems[el].value) {
            this.roleItems[el].disabled = true

            //console.log(this.roleItems)
          }
        }
      }
    }
  },
  mounted() {
    this.init()
  }
}
</script>

<style scoped>

</style>