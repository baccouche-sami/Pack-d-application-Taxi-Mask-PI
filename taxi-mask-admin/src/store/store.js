


import Vue from 'vue'
import Vuex from 'vuex'

import state from "./state"
import getters from "./getters"
import mutations from "./mutations"
import actions from "./actions"
import { auth } from './auth.module';

Vue.use(Vuex)


export default new Vuex.Store({
  modules: {
    auth,
  },
    getters,
    mutations,
    state,
    actions,
    strict: process.env.NODE_ENV !== 'production'
})
