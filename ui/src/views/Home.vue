<template>
  <div>
    <v-container>
      <v-row>
        <v-col
            v-for="item in items"
            :key="item.name"
            cols="12"
            sm="4"
        >
          <v-card
              class="pa-2"
              outlined
              tile
              elevation="1"
              @click="$router.push(item.url)"
              :disabled="isXmanager"
          >
            <v-card-text>
              <div>{{ item.category }}</div>
              <p class="text-h5 text--primary">{{ item.name }}</p>
            </v-card-text>
            <v-card-actions>
<!--              <v-btn-->
<!--                  text-->
<!--                  color="deep-purple accent-4"-->
<!--                  @click="$router.push(item.url)"-->
<!--              >-->
<!--                Learn More-->
<!--              </v-btn>-->
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
<!--    <v-row>-->
<!--      <v-card-->
<!--          class="mx-auto"-->
<!--          width="344"-->
<!--          >-->
<!--        <v-card-text>-->
<!--         <div>Valami szöveg</div>-->
<!--          <p class="text-h5 text&#45;&#45;primary">Halo</p>-->
<!--        </v-card-text>-->
<!--        <v-card-actions>-->
<!--          <v-btn-->
<!--              text-->
<!--              color="deep-purple accent-4"-->
<!--          >-->
<!--            Learn More-->
<!--          </v-btn>-->
<!--        </v-card-actions>-->
<!--      </v-card>-->
<!--      <v-card-->
<!--          class="mx-auto"-->
<!--          width="344"-->
<!--      >-->
<!--        <v-card-text>-->
<!--          <div>Valami szöveg</div>-->
<!--          <p class="text-h5 text&#45;&#45;primary">Halo</p>-->
<!--        </v-card-text>-->
<!--        <v-card-actions>-->
<!--          <v-btn-->
<!--              text-->
<!--              color="deep-purple accent-4"-->
<!--          >-->
<!--            Learn More-->
<!--          </v-btn>-->
<!--        </v-card-actions>-->
<!--      </v-card>-->
<!--      <v-card-->
<!--          class="mx-auto"-->
<!--          width="344"-->
<!--      >-->
<!--        <v-card-text>-->
<!--          <div>Valami szöveg</div>-->
<!--          <p class="text-h5 text&#45;&#45;primary">Halo</p>-->
<!--        </v-card-text>-->
<!--        <v-card-actions>-->
<!--          <v-btn-->
<!--              text-->
<!--              color="deep-purple accent-4"-->
<!--          >-->
<!--            Learn More-->
<!--          </v-btn>-->
<!--        </v-card-actions>-->
<!--      </v-card>-->

<!--    </v-row>-->
  </div>
</template>

<script>
export default {
  name: 'Home',
  data(){
    return {
      isXmanager: !this.$store.getters.getAccess,
      items: [
        {category: 'Device management', name: 'Device list', url: '/device-list'},
        {category: 'User management', name: 'User list', url: '/user-list'},
        {category: 'Data analysis', name: 'Realtime monitoring', url: '/realtime-monitoring'},
        {category: 'Settings', name: 'Network management', url: '/network-management'},
      ]
    }
  },
  computed:{
    xManager(){
      return this.$store.getters.getAccess;
    }
  },
  watch: {
    xManager(value){
      this.isXmanager = !value;
    }
  },
  methods:{

  },
  components: {},
}
</script>
