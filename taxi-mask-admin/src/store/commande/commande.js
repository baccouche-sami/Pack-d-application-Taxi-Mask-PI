


import state from './CommandeState.js'
import mutations from './CommandeMutations.js'
import actions from './CommandeActions.js'
import getters from './CommandeGetters.js'

export default {
  isRegistered: false,
  namespaced: true,
  state: state,
  mutations: mutations,
  actions: actions,
  getters: getters
}

