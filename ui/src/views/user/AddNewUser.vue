<template>
  <div class="col-md-6 mx-auto">
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
          chips
          label="Groups"
          multiple
          solo
      ></v-select>

      <v-select
          v-model="roleValue"
          :items="roleItems"
          chips
          label="Roles"
          multiple
          solo
      ></v-select>

      <v-select
          :items="activeItems"
          v-model="state"
          label="Solo field"
          solo
      ></v-select>

      <v-btn color="teal" dark>Save</v-btn>

    </v-form>
  </div>
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
      groupItems: ['Engineer', 'Operator'],
      groupValue: [],
      roleItems: ['Admin', 'User'],
      roleValue: [],
    }
  }
}
</script>

<style scoped>

</style>