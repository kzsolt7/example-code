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
          <template v-slot:activator="{  }">
            <v-btn
                color="primary"
                dark
                class="mb-2"
                @click="$router.push('/add-new-user')"
            >
              New user
            </v-btn>
          </template>
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
    <template v-slot:item.permissionGroups="{ item }">
      <div>{{ item.permissionGroups.join(", ") }}</div>
    </template>
    <template v-slot:item.created="{ item }">
      <p>{{ getTimestamp(item.id) }}</p>
    </template>
    <template v-slot:item.actions="{ item }">
      <v-icon
          small
          class="mr-2"
          @click="editItem(item)"
          :disabled="isAdmin(item)"
      >
        mdi-pencil
      </v-icon>
    </template>
    <template v-slot:no-data>
      <div>No data available</div>
    </template>
  </v-data-table>
</template>

<script>
import {api} from "@/api";

export default {
  name: "UserTable",
  data: () => ({
    dialogDelete: false,
    headers: [
      {
        text: 'Username',
        align: 'start',
        sortable: false,
        value: 'userName',
      },
      {text: 'Permission group', value: 'permissionGroups'},
      {text: 'State', value: 'state'},
      {text: 'Created', value: 'created'},
      {text: 'Actions', value: 'actions', sortable: false},
    ],
    users: [],
    defaultItem: {
      name: '',
      permissionGroups: '',
      state: '',
      created: '',
      protein: '',
    },

  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
    },
  },

  watch: {
    dialogDelete(val) {
      val || this.closeDelete()
    },
  },

  created() {
    this.initialize()
  },

  methods: {
    initialize() {
      this.getUsers();
    },
    getTimestamp(id) {
      let timestamp = id.toString().substring(0, 8);
      let date = new Date(parseInt(timestamp, 16) * 1000)
      return date.toLocaleDateString();
    },
    getUsers() {
      api.get("/user/all").then(r => this.users = r.data);
    },
    //id-t át kell adni paraméterként
    editItem(item) {
      if (item.userName != "ce") {
        this.$router.push("/edit-user/" + item.id)
      }
    },

    deleteItem(item) {
      this.editedIndex = this.users.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },

    deleteItemConfirm() {
      this.users.splice(this.editedIndex, 1)
      this.closeDelete()
    },


    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },
    isAdmin(item) {
      if (item.userName == "ce") {
        return true;
      } else {
        return false;
      }
    }
  },
}
</script>

<style scoped>

</style>