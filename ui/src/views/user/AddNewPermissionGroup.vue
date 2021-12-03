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
          ></v-checkbox>
        </div>
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
      roleItems: this.$store.getters.getRoleItems,
      permissions: [],
      formRules: [
        // v => !!v || 'Group name is required',
      ],
      isWarning: false,
      warningMessage: "Group already exists!",
      groupNameRuleBool: false
    }
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
    saveGroup() {
      if (this.groupName)
        api.post('/user/permission-group', {
          name: this.groupName,
          permissions: this.permissions,
          state: this.state
        }).then(r => {
          if (r.status == 200) {
            VueCookies.set('success', 'new-success', "2s")
            this.$router.push("/permission")
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
      if (this.groupNameRuleBool == true) {
        this.groupNameRuleBool = false;
        setTimeout(() =>
                this.$refs.form.validate()
            , 200)
      }

    },
  },
  watch: {
    groupNameRuleBool: 'validateField'
  },
}
</script>

<style scoped>

</style>