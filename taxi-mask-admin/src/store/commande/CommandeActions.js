
import axios from "@/axios.js"
import authHeader from '../../services/auth-header';
const API_URL = 'http://localhost:8080';
export default {

  fetchDataListItems({ commit }) {
    return new Promise((resolve, reject) => {
      axios.get(API_URL +"/commandes", { headers: authHeader() })
        .then((response) => {
          commit('SET_COMMAND', response.data)
          resolve(response)
        })
        .catch((error) => { reject(error) })
    })
  },
  updateItem({ commit }, item) {
    console.log(item);
        return new Promise((resolve, reject) => {
      axios.put(API_URL +"/marques/"+item.id, item)
        .then((response) => {
          commit('UPDATE_MARQUE', response.data)
          resolve(response)
        })
        .catch((error) => { reject(error) })
    })
  }
}
