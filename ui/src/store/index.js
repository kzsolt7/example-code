import Vue from 'vue'
import Vuex from 'vuex'
import {permissionGroups} from "@/store/permissionGroups";
import {User} from "@/store/User";

Vue.use(Vuex)

export default new Vuex.Store({
  state: {

  },
  mutations: {
  },
  actions: {
  },
  modules: {
    permissionGroups,
    User
  }
})
