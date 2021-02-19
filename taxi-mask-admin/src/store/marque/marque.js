


import state from './MarqueState.js'
import mutations from './MarqueMutations.js'
import actions from './MarqueActions.js'
import getters from './MarqueGetters.js'

export default {
  isRegistered: false,
  namespaced: true,
  state: state,
  mutations: mutations,
  actions: actions,
  getters: getters
}

