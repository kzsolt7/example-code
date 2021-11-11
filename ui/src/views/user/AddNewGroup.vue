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
            :counter="10"
            label="Group name"
            required
        ></v-text-field>

        <v-select
            :items="activeItems"
            v-model="state"
            item-color="teal"
            label="Solo field"
            solo
        ></v-select>
        <v-btn color="teal" dark>Save</v-btn>
      </v-form>
    </div>

    <div class="col-md-6" style="position: absolute; top: 0; bottom: 0; right:0; overflow-y: scroll">

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
export default {
  name: "AddNewGroup.vue",
  data(){
    return{
      groupName:'',
      state: 'Active',
      activeItems: ['Active', 'Inactive'],
      formModel: '',
      roleItems: this.$store.getters.getRoleItems
     
    }
  }
}
</script>

<style scoped>

</style>