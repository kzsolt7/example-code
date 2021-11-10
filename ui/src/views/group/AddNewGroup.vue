<template>
  <div class="col-md-6 mx-auto">
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
          label="Solo field"
          solo
      ></v-select>



      <v-btn color="teal" dark>Save</v-btn>

    </v-form>
  </div>
</template>

<script>
export default {
  name: "AddNewGroup.vue",
  data(){
    return{
      groupName:'',
      activeItems: ['Active', 'Inactive'],
      formModel: ''
     
    }
  }
}
</script>

<style scoped>

</style>