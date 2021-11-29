<template>
  <div>
    <transition name="fade">
      <v-alert v-show="isSuccess" type="success">
        {{ successMessage }}
      </v-alert>
    </transition>

    <group-table
      :headers="headers"
      header-text="Notification groups"
      :groups-data="groupsData"
      @delete-item="getData"
    ></group-table>
  </div>
</template>

<script>
import GroupTable from "@/components/user/GroupTable";
import {api} from "@/api";

export default {
  name: "NotificationGroups",
  components: {
    GroupTable
  },
  data() {
    return {
      headers: [
        {
          text: 'Group name',
          align: 'start',
          sortable: false,
          value: 'name',
        },
        {text: 'State', value: 'state'},
        {text: 'Created', value: 'created'},
        {text: 'Actions', value: 'actions', sortable: false},
      ],
      groupsData: [],
      successMessage: '',
      isSuccess: false
    }
  },
  created() {
    setTimeout(() =>
        this.isSuccess = false, 3000)
  },
  mounted() {
    this.getData()
    this.init()
  },
  methods: {
    getData() {
      api.get('/user/notification-group/all').then(r => this.groupsData = r.data)
    },
    init() {
      if (this.$cookies.get("success") == "new-success") {
        this.successMessage = "Group successfully created."
        this.isSuccess = true
      }
      if(this.$cookies.get("success") == "update-success"){
        this.successMessage = "Group successfully updated."
        this.isSuccess = true;
      }
    },
  }
}
</script>

<style scoped>

</style>