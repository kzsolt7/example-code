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

    <div class="col-md-3" style="max-height: 90vh; overflow-y: scroll">
      <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
          @keyup="searchInUsers(search)"
      ></v-text-field>
      <div v-for="user in unTicked" v-bind:key="user.id">
        <v-checkbox
            :label="user.userName"
            color="teal"
            :value="user.userName"
            hide-details
            v-model="usersInGroup"
            @click="clear"
        ></v-checkbox>
      </div>
    </div> <div class="col-md-3" style="max-height: 90vh; overflow-y: scroll">

    <div v-for="user in ticked" v-bind:key="user.id">
      <v-checkbox
          :label="user.userName"
          color="teal"
          :value="user.userName"
          hide-details
          v-model="usersInGroup"
          @click="clear"
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
      //roleItems: this.$store.getters.getRoleItems,
      users: '',
      usersToDisplay: '',
      groupRules: [
        v => !!v || 'Group name is required',
      ],
      usersInGroup: [],
      search: '',
      unTicked: [],
      ticked :[]
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
      api.get('/user/all').then(r => {
            this.users = r.data
            this.usersToDisplay = r.data
            this.clear()
          }
      )
    },
    searchInUsers(text) {
      this.unTicked = this.users.filter(item => item.userName.toUpperCase().includes(text.toUpperCase()))
    },
    clear() {
      this.search = ''
      this.searchInUsers('')
      let sorted = {}
      let unTicked = {}
      let i = 0
      let j = 0
      let ticked = {}
      //console.log(this.usersToDisplay)
      for (let item in this.usersToDisplay) {
        let b = false
        for (let user in this.usersInGroup) {
          if (this.usersInGroup[user] === this.usersToDisplay[item].userName) {
            sorted[i] = this.usersToDisplay[item]
            ticked[i] = this.usersToDisplay[item]

            i++
            //console.log(this.usersToDisplay[item].userName)
            b = true
            break
          }

        }
        this.ticked=ticked
        if (!b) {
          unTicked[j] = this.usersToDisplay[item]
          j++
        }
      }

      for (let item in unTicked) {
        //console.log(unTicked[item])
        sorted[i] = unTicked[item]
        i++
      }
      //console.log(sorted)
      this.usersToDisplay = {}
      this.usersToDisplay = sorted
      this.unTicked = unTicked
    }
  },


}
</script>

<style scoped>

</style>