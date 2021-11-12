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

        <v-btn color="teal" dark @click="saveUser">Save</v-btn>

      </v-form>
    </div>

    <div class="col-md-6" style="max-height: 90vh; overflow-y: scroll">
      <div v-for="roleItem in roleItems" v-bind:key="roleItem.value">
        <v-checkbox
            :label="roleItem.name"
            color="teal"
            :value="roleItem.value"
            hide-details
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
      roleItems: this.$store.getters.getRoleItems

    }
  },
  methods: {
    saveUser() {
      api.post('/user', {
        userName: this.userName,
        email: this.userEmail,
        password: this.userPassword,
        groups: this.groupValue,
        state: this.state
      });
    }
  }
}
</script>

<style scoped>

</style>