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
            :disabled="true"
        ></v-text-field>

        <v-select
            :items="activeItems"
            v-model="state"
            item-color="teal"
            label="Solo field"
            solo
        ></v-select>
        <v-btn color="teal" @click="saveGroup" dark>Update</v-btn>
        <v-btn style="margin-left: 20px" color="grey" @click="$router.push('/permission')" dark>Cancel</v-btn>
      </v-form>
    </div>

    <div class="col-md-6 mb-16">

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
  name: "EditPermissionGroup.vue",
  data() {
    return {
      id: '',
      groupName: '',
      state: 'Active',
      activeItems: ['Active', 'Inactive'],
      formModel: '',
      roleItems: this.$store.getters.getRoleItems,
      permissions: [],
      groupRules: [
        v => !!v || 'Group name is required',
      ],
    }
  },
  mounted() {
    this.init()
  },
  computed:{
    getRoleItems() {
      return this.$store.getters.getRoleItems;
    }
  },
  watch:{
    getRoleItems(value) {
      this.roleItems = value
    }
  },
  methods: {
    saveGroup() {
      if (this.groupName)
        api.put('/user/permission-group', {
          id: this.id,
          name: this.groupName,
          permissions: this.permissions,
          state: this.state
        }).then(r => {
          if (r.status == 200) {
            VueCookies.set('success', 'update-success', "2s")
            this.$router.push("/permission")
          }
        });
    },

    init() {
      api.get("user/permission-group?id=" + this.$route.params.id).then(r => {
        this.groupName = r.data.name
        this.state = r.data.state
        this.permissions = r.data.permissions
        this.id = r.data.id
      })
    }
  }
}
</script>

<style scoped>

</style>