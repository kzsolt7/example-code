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
            label="Add new password here (if it's empty, password won't be updated)"
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
            @change="groupSelectChanged"
        ></v-select>


        <v-select
            :items="activeItems"
            item-color="teal"
            v-model="state"
            label="Solo field"
            solo
        ></v-select>

        <v-btn color="teal" @click="saveUser" dark>Update</v-btn>
        <v-btn style="margin-left: 20px" color="grey" @click="$router.go(-1)" dark>Cancel</v-btn>

      </v-form>
    </div>

    <div class="col-md-6" style="position: absolute; top: 0; bottom: 0; right:0; overflow-y: scroll">
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
import VueCookies from "vue-cookies";

export default {
  name: "EditUser",
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
      id: '',
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
                //this.userPassword = r.data.password
                this.state = r.data.state
                this.groupValue = r.data.permissionGroups
                this.permissions = r.data.permissions
              }
          );
      api.get('user/permission-group/all').then(r => {
        this.permissionGroupsObject = r.data
        //console.log(r.data)
        for (let item in r.data) {
          this.groupItems.push(r.data[item].name)
        }
        this.groupSelectChanged()
      }).then(() => {
        this.groupSelectChanged()
      })
    },
    saveUser() {
      let permissionsToPost = this.permissions.filter(x => !this.permissionsFromGroups.includes(x))
      if (this.userName)
        api.put('/user', {
          id: this.id,
          userName: this.userName,
          permissions: permissionsToPost,
          permissionGroups: this.groupValue,
          password: this.userPassword,
          state: this.state,
          email: this.userEmail
        }).then(r => {
          if (r.status == 200) {
            VueCookies.set('success', 'update-success', "10s")
            this.$router.push("/user-list")
          }
        });
    },
    groupSelectChanged() {
      this.permissions = this.permissions.filter(x => !this.permissionsFromGroups.includes(x))
      this.permissionsFromGroups = []
      for (let item in this.groupValue) {
        for (let elem in this.permissionGroupsObject) {
          if (this.groupValue[item] == this.permissionGroupsObject[elem].name) {
            this.permissionsFromGroups.push.apply(this.permissionsFromGroups, this.permissionGroupsObject[elem].permissions)
          }
        }
      }
      for (let el in this.roleItems) {
        this.roleItems[el].disabled = false
        for (let item in this.permissionsFromGroups) {
          if (this.permissionsFromGroups[item] == this.roleItems[el].value) {
            this.roleItems[el].disabled = true
          }
        }
      }
      this.permissions.push.apply(this.permissions, this.permissionsFromGroups)
    }
  },
}
</script>

<style scoped>

</style>