<template>
  <v-data-table
      :headers="headers"
      :items="groupsData"
      sort-by="permissionGroup"
      class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar
          flat
      >
        <v-toolbar-title>{{ headerText }}</v-toolbar-title>
        <v-divider
            class="mx-4"
            inset
            vertical
        ></v-divider>
        <v-spacer></v-spacer>
        <v-dialog v-model="dialogDelete" max-width="500px">
          <v-card>
            <v-card-title class="text-h5">Are you sure you want to delete this item?</v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="closeDelete">Cancel</v-btn>
              <v-btn color="blue darken-1" text @click="deleteItemConfirm">OK</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template v-slot:item.actions="{ item }">
      <v-icon
          small
          class="mr-2"
          @click="editItem(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
          small
          @click="deleteItem(item)"
      >
        mdi-delete
      </v-icon>
    </template>
    <template v-slot:no-data>
      <v-btn
          color="teal"
          @click="initialize"
      >
        Reset
      </v-btn>
    </template>
  </v-data-table>
</template>

<script>
import {api} from "@/api";
export default {
  name: "GroupTable",
  props: {
    headerText: String,
    headers: Array,
    groupsData: Array
  },
  data: () => ({
    dialogDelete: false,

    defaultItem: {
      name: '',
      state: '',
      created: '',
      protein: '',
      tempId: '',
    },
  }),

  computed: {
    formTitle () {
      return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
    },
  },

  watch: {
    dialogDelete (val) {
      val || this.closeDelete()
    },
  },

  created () {
    this.initialize()
  },

  methods: {
    initialize () {
      this.users = [
        {
          name: 'Engineer',
          state: 'Active',
          created: '2021-11-11',

        },
        {
          name: 'Operator',
          state: 'Inactive',
          created: '2021-10-29',

        },
        {
          name: 'Machine adjuster',
          state: 'Active',
          created: '2021-09-23',

        },

      ]
    },
    editItem (item) {
      this.$router.push("/edit-permission-group/" + item.id)
    },

    deleteItem (item) {
      this.editedIndex = this.users.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
      this.tempId=item.id



    },

    deleteItemConfirm () {
      this.users.splice(this.editedIndex, 1)


      if(this.$route.name == "Permission group"){
        api.delete("user/permission-group?id=" + this.tempId)
      }
      if(this.$route.name == "Notification group"){
        api.delete("user/notification-group?id=" + this.tempId)
      }
      this.closeDelete()
      this.$emit("delete-item")

    },


    closeDelete () {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },
  },
}
</script>

<style scoped>

</style>