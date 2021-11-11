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
     
    }
  }
}
</script>

<style scoped>

</style>