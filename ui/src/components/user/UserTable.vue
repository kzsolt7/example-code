<template>
  <v-data-table
      :headers="headers"
      :items="users"
      sort-by="permissionGroup"
      class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar
          flat
      >
        <v-toolbar-title>Users</v-toolbar-title>
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
export default {
  name: "UserTable",
  data: () => ({
    dialogDelete: false,
    headers: [
      {
        text: 'Username',
        align: 'start',
        sortable: false,
        value: 'name',
      },
      { text: 'Permission group', value: 'permissionGroup' },
      { text: 'State', value: 'state' },
      { text: 'Created', value: 'created' },
      { text: 'Actions', value: 'actions', sortable: false },
    ],
    users: [],
    defaultItem: {
      name: '',
      permissionGroup: '',
      state: '',
      created: '',
      protein: '',
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
          name: 'Lacika',
          permissionGroup: 'Technológus',
          state: 'Online',
          created: '2021-11-11',

        },
        {
          name: 'Béluka',
          permissionGroup: 'Admin',
          state: 'Offline',
          created: '2021-10-29',

        },

      ]
    },

    //id-t át kell adni paraméterként
    editItem (item) {
      this.editedIndex = this.users.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.$router.push("/edit-user")
    },

    deleteItem (item) {
      this.editedIndex = this.users.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },

    deleteItemConfirm () {
      this.users.splice(this.editedIndex, 1)
      this.closeDelete()
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