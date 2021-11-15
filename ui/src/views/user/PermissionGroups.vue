<template>
  <group-table
      :headers="headers"
      header-text="Permission groups"
      :groups-data="groupsData"
      @delete-item="getData"
  ></group-table>
</template>

<script>
import GroupTable from "@/components/user/GroupTable";
import {api} from "@/api";
export default {
  name: "PermissionGroups",
  components:{
    GroupTable
  },
  data(){
    return{
      headers: [
        {
          text: 'Group name',
          align: 'start',
          sortable: false,
          value: 'name',
        },
        { text: 'State', value: 'state' },
        { text: 'Created', value: 'created' },
        { text: 'Actions', value: 'actions', sortable: false },
      ],
      groupsData:[]
    }
  },
  mounted() {
  this.getData()
    },
  methods:{
    getData(){
      api.get('/user/permission-group/all').then(r => this.groupsData = r.data)
    }
  }
}
</script>

<style scoped>

</style>