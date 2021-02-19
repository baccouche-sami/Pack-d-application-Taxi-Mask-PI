


export default {

  SET_COMMAND(state, commandes) {
    state.commandes = commandes
  },


  UPDATE_MARQUE(state, marque) {
    const marqueIndex = state.commandes.findIndex((p) => p.id == marque.id)
    Object.assign(state.commandes[marqueIndex], marque)
  },

  REMOVE_MARQUE(state, itemId) {
    const ItemIndex = state.commandes.findIndex((p) => p.id == itemId)
    state.commandes.splice(ItemIndex, 1)
},
}
