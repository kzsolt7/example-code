<template>
  <v-row>
    <div class="col-md-6">
      <v-form
          ref="form"
          v-model="formModel"
      >

        <v-text-field
            v-model="groupName"
            label="Group name"
            required
            :rules="formRules"
        ></v-text-field>

        <v-select
            :items="activeItems"
            v-model="state"
            item-color="teal"
            label="Solo field"
            solo
        ></v-select>
        <v-btn color="teal" @click="saveGroup" dark>Save</v-btn>
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
        ></v-checkbox>
      </div>
    </div>
  </v-row>

</template>

<script>
import {api} from "@/api";
import VueCookies from "vue-cookies";

export default {
  name: "AddNewGroup.vue",
  data() {
    return {
      groupName: '',
      state: 'Active',
      activeItems: ['Active', 'Inactive'],
      formModel: '',
      roleItems: this.$store.getters.getRoleItems,
      permissions: [],
      formRules: [
        v => !!v || 'Group name is required',
      ],
    }
  },
  methods: {
    saveGroup() {
      if (this.groupName)
      api.post('/user/permission-group', {
        name: this.groupName,
        permissions: this.permissions,
        state: this.state
          }).then(r => {
        if (r.status == 200) {
          VueCookies.set('success' , 'new-success', "10s")
          this.$router.push("/permission")
        }
      });
    }
  }
}
</script>

<style scoped>

</style>