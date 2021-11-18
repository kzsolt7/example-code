<template>
  <v-row>
    <div class="col-md-6">
      <v-form
          ref="form"
          v-model="formModel"
          lazy-validation
      >

        <v-text-field
            v-model="groupName"
            label="Group name"
            required
            :rules="groupRules"
        ></v-text-field>

        <v-select
            :items="activeItems"
            v-model="state"
            item-color="teal"
            label="Solo field"
            solo
        ></v-select>
        <v-btn color="teal" @click="saveGroup" dark>Update</v-btn>
        <v-btn style="margin-left: 20px" color="grey" @click="$router.go(-1)" dark>Cancel</v-btn>
      </v-form>
    </div>

    <div class="col-md-6" style="max-height: 90vh; overflow-y: scroll">

      <div v-for="user in users" v-bind:key="user.value">
        <v-checkbox
            :label="user.userName"
            color="teal"
            :value="user.userName"
            hide-details
            v-model="usersInGroup"
        ></v-checkbox>
      </div>
    </div>
  </v-row>

</template>

<script>
import {api} from "@/api";
import VueCookies from "vue-cookies";

export default {
  name: "EditNotificationGroup.vue",
  data() {
    return {
      id: '',
      groupName: '',
      state: 'Active',
      activeItems: ['Active', 'Inactive'],
      formModel: '',
      roleItems: this.$store.getters.getRoleItems,
      usersInGroup: [],
      groupRules: [
        v => !!v || 'Group name is required',
      ],
      users: [],
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    saveGroup() {
      if (this.groupName)
        api.put('/user/notification-group', {
          id: this.id,
          name: this.groupName,
          permissions: this.permissions,
          state: this.state,
          users: this.usersInGroup
        }).then(r => {
          if (r.status == 200) {
            VueCookies.set('success', 'update-success', "10s")
            this.$router.push("/notification")
          }
        });
    },

    init() {
      api.get("user/notification-group?id=" + this.$route.params.id).then(r => {
            this.groupName = r.data.name
            this.state = r.data.state
            this.id = r.data.id
            this.usersInGroup = r.data.users
          },
      )
      api.get('/user/all').then(r => this.users = r.data)
    },

  }
}
</script>

<style scoped>

</style>