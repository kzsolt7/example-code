<template>
  <div class="row justify-center">


    <v-card class="col-md-5 mr-1">
      <v-card-title>Import</v-card-title>
      <v-card-text>
        <v-file-input
            color="teal"
            counter
            label="Load from file"
            placeholder="Select your file"
            prepend-icon="mdi-paperclip"
            outlined
            :show-size="1000"
            v-model="file"
        >
          <template v-slot:selection="{ index, text }">
            <v-chip
                v-if="index < 2"
                color="teal"
                dark
                label
                small
            >
              {{ text }}
            </v-chip>

            <span
                v-else-if="index === 2"
                class="text-overline grey--text text--darken-3 mx-2"
            >
        +{{ files.length - 2 }} File(s)
      </span>
          </template>
        </v-file-input>

        <v-btn @click="importUsers" color="teal" dark>Load from file</v-btn>

      </v-card-text>
    </v-card>

    <v-card class="col-md-5 ml-1">
      <v-card-title>Export</v-card-title>
      <v-card-text>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate dolor magnam maxime mollitia non! Labore
          laudantium nisi officia ut! At beatae consequuntur ea fugit laudantium </p>
        <v-btn color="teal" dark @click="exportUsers">Save to file</v-btn>
      </v-card-text>
    </v-card>

  </div>
</template>

<script>
import {api} from "@/api";

export default {
  name: "ExportImport",
  data() {
    return {
      file: null,
    }
  },
  methods: {
    exportUsers() {
      api.get('user/export', {method: 'GET', responseType: 'blob'}).then(response => {
        let fileName = response.headers["content-disposition"].toString().split("=")[1];
        const url = window.URL
            .createObjectURL(new Blob([response.data]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', fileName);
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
      });
    },

    importUsers() {
      let formData = new FormData();
      formData.append('file', this.file);


      // You should have a server side REST API
      api.post('user/import',
          formData, {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          }
      ).then(function () {
        console.log('Users successfully exported');
      })
          .catch(function () {
            console.log('Users successfully imported');
          });
    },
    handleFileUpload() {
      //this.file = this.$refs.file.files[0];


    }
  }

}
</script>

<style scoped>

</style>