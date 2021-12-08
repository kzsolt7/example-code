<template>
  <div>
    <transition name="fade">
      <v-alert v-show="isWarning" type="warning">
        {{ warningMessage }}
      </v-alert>
    </transition>
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
              :rules="rules"
              @keyup="delRules"
          ></v-text-field>

          <v-select
              :items="activeItems"
              v-model="state"
              item-color="teal"
              label="Solo field"
              solo
          ></v-select>
          <v-btn color="teal" @click="saveGroup" dark>Save</v-btn>
          <v-btn style="margin-left: 20px" color="grey" @click="$router.push('/notification')" dark>Cancel</v-btn>
        </v-form>
      </div>

      <div class="col-md-3">
        <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
            @keyup="searchInUsers(search)"
        ></v-text-field>
        <v-card class="mt-1" elevation="1">
          <v-card-text>
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
          </v-card-text>

        </v-card>
      </div>
      <div class="col-md-3">

        <v-card class="mt-1" elevation="0">
          <v-card-text>
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
          </v-card-text>

        </v-card>
      </div>
    </v-row>
  </div>
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
      //roleItems: this.$store.getters.getRoleItems,
      users: '',
      usersToDisplay: '',
      groupRules: [
        //v => !!v || 'Group name is required',
      ],
      usersInGroup: [],
      search: '',
      unTicked: [],
      ticked: [],
      isWarning: false,
      warningMessage: "Group already exists!",
      groupNameRuleBool: false


    }
  },
  mounted() {
    this.init()
  },
  computed: {

    rules() {
      const rules = []

      if (this.groupNameRuleBool) {
        const rule =
            v => (!!v && v) === "cica" || 'Group name already in use'
        rules.push(rule)
      }
      if (!this.groupNameRuleBool) {
        const rule = v => !!v || 'Group name is required'
        rules.push(rule)

      }

      return rules
    },
  },
  methods: {
    groupExists() {
      this.isWarning = true;
      setTimeout(() =>
              this.isWarning = false
          , 2000)
    },
    validateField() {
      this.$refs.form.validate()
    },
    delRules() {
      if (this.groupNameRuleBool === true) {
        this.groupNameRuleBool = false;
        setTimeout(() =>
                this.$refs.form.validate()
            , 200)
      }

    },
    saveGroup() {
      this.$refs.form.validate()
      if (this.groupName)
        api.post('/user/notification-group', {
          name: this.groupName,
          permissions: this.permissions,
          state: this.state,
          users: this.usersInGroup
        }).then(r => {
          if (r.status == 200) {
            VueCookies.set('success', 'new-success', "2s")
            this.$router.push("/notification")
          }
        }, err => {
          if (err.response.status == 409) {
            this.groupExists()
            this.groupNameRuleBool = true;
            setTimeout(() =>
                    this.$refs.form.validate()
                , 200)
          }
        });
    },
    init() {
      api.get('/user/all').then(r => {
        this.users = r.data
        this.usersToDisplay = r.data
        this.clear();
      })
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
      for (let item in this.usersToDisplay) {
        let b = false
        for (let user in this.usersInGroup) {
          if (this.usersInGroup[user] === this.usersToDisplay[item].userName) {
            sorted[i] = this.usersToDisplay[item]
            ticked[i] = this.usersToDisplay[item]

            i++
            b = true
            break
          }

        }
        this.ticked = ticked
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
  watch: {
    groupNameRuleBool: 'validateField'
  },
}
</script>

<style scoped>


</style>