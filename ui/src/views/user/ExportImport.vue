<template>
  <div>
    <transition name="fade">
      <v-alert v-show="isSuccess" type="success">
        {{ successMessage }}
      </v-alert>
    </transition>
    <transition name="fade">
      <v-alert v-show="isWarning" type="warning">
        {{ warningMessage }}
      </v-alert>
    </transition>

    <div class="row justify-center">

      <v-row>

        <v-col>
          <v-card class="col-md-5" height="230">
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

              <v-btn
                  :disabled="disableButton"
                  @click="importUsers"
                  color="teal"
                  :dark="!disableButton"
              >Load from file</v-btn>

            </v-card-text>
          </v-card>
        </v-col>

        <v-col>
          <v-card class="col-md-5" height="230">
            <v-card-title>Export</v-card-title>
            <v-card-text>
              <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate dolor magnam maxime mollitia non!
                Labore
                laudantium nisi officia ut! Labore
                laudantium nisi officia ut! Labore
                laudantium nisi officia asd</p>
              <v-btn color="teal" dark @click="exportUsers">Save to file</v-btn>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>

    </div>
  </div>
</template>

<script>
import {api} from "@/api";

export default {
  name: "ExportImport",
  data() {
    return {
      file: null,
      isSuccess: false,
      successMessage: 'Users successfully imported',
      isWarning: false,
      warningMessage: 'Problem occurred',
      disableButton: true
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
      ).then(() => {
        console.log('Users successfully imported');
        this.isSuccess = true;
        this.successMsgHandler();
      })

          .catch(() => {
            console.log('Koleka problem');
            this.isWarning = true;
            this.warningMsgHandler()
          });
    },
    successMsgHandler() {
      this.isWarning = false
      setTimeout(() => {
        this.isSuccess = false
        this.file = null
      }, 2000)
    },
    warningMsgHandler() {
      this.isSuccess = false;
      setTimeout(() => {
        this.isWarning = false
        this.file = null
      }, 2000)
    },

    handleFileUpload() {
      //this.file = this.$refs.file.files[0];


    }
  },
  watch: {
    file(value){
      if(value == null) {
        this.disableButton = true
      }else{
        this.disableButton = false
      }
    }
  }

}
</script>

<style scoped>

</style>