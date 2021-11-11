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
      //roleItems: ['Can start measure', 'Set reference'],
      roleItems:[
        {name: "Set first sampling, reference, limit", value: "setFirstSampRefLimit"},
        {name: "Set ref default", value: "setRefDefault"},
        {name: "Import reference by hand", value: "importRefByHand"},
        {name: "Set sampling time", value: "setSampTime"},
        {name: "Create, edit, reference", value:"createEditRef"},
        {name: "Edit Pfill, Tcool", value:"editPfillTcool"},
        {name: "Edit evaluation value settings", value:"editEvaluationValueSettings"},
        {name: "Edit limits", value:"editLimits"},
        {name: "Turn sensor on/off", value:"turnSensorsOnOff"},
        {name: "Enable/disable cycle stop", value:"enableDisableCycleStop"},
        {name: "Edit cycle stop condition", value:"editCycleStopCond"},
        {name: "Enable/disable switchover", value:"enableDisableSwitchover"},
        {name: "Edit switchover condition settings", value:"editSwitchoverCondSettings"},
        {name: "Enable/disable partial select", value:"enableDisablePartialSelect"},
        {name: "Edit partial select", value:"editPartialSelect"},
        {name: "Edit selector signal length", value:"editSelectorSignalLength"},
        {name: "Enable/disable surveillance", value:"enableDisableSurveillance"},
        {name: "Edit mold manager settings", value:"editMoldManagerSettings"},
        {name: "Manage users", value:"manageUsers"},
        {name: "Edit/set IMM", value:"editSetIMM"},
        {name: "Update software", value:"updateSoftware"},
        {name: "Edit network settings", value:"editNetworkSettings"},
        {name: "Edit device default settings", value:"editDeviceDefaultSettings"},
      ],
      roleValue: [],
    }
  }
}
</script>

<style scoped>

</style>