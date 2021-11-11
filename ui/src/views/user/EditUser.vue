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
          :counter="10"
          label="Username"
          required
      ></v-text-field>

      <v-text-field
          v-model="userEmail"
          :counter="10"
          label="Email"
          required
      ></v-text-field>

      <v-text-field
          v-model="userPassword"
          :counter="10"
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

      <v-btn color="teal" dark>Update</v-btn>

    </v-form>
  </div>

    <div  class="col-md-6" style="position: absolute; top: 0; bottom: 0; right:0; overflow-y: scroll">
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
  name: "AddNewUser",
  data(){
    return{
      userName:'',
      userEmail:'',
      userPassword:'',
      formModel: '',
      activeItems: ['Active', 'Inactive'],
      state: 'Active',
      //groupItems: ['Engineer', 'Operator'],
      groupItems: this.$store.getters.getPermissionGroups,
      groupValue: [],
      roleItems: this.$store.getters.getRoleItems

    }
  }
}
</script>

<style scoped>

</style>