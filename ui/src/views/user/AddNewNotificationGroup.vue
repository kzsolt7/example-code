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
        <v-btn color="teal" @click="saveGroup" dark>Save</v-btn>
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
  name: "AddNewGroup.vue",
  data() {
    return {
      groupName: '',
      state: 'Active',
      activeItems: ['Active', 'Inactive'],
      formModel: '',
      roleItems: this.$store.getters.getRoleItems,
      groupRules: [
        v => !!v || 'Group name is required',
      ],

    }
  },
  methods:{
    saveGroup() {
      if (this.groupName)
        api.post('/user/notification-group', {
          name: this.groupName,
          permissions: this.permissions,
          state: this.state
        }).then(r => {
          if (r.status == 200) {
            this.$router.push("/notification/new-success")
          }
        });
    }
  }
}
</script>

<style scoped>

</style>