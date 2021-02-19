
import {saveOrder, getOrders, saveFile, getOrdersComplete ,
    getStripeSession, deleteOrder,updateOrder} from '../services/order';
import {UPLOAD_URI,SERVER_URL} from '../services/config-server';

const initialState = {cardContent:0, listCard:[]} 
export const order = {
    namespaced: true,
    state: initialState,
    actions: {
        async save({commit},order) { 
            return await saveOrder(order)
        },
        async uploadDir({commit},) { 
            return `${SERVER_URL}/${UPLOAD_URI}/`
        },
        async stripeCheckoutSession({commit},) { 
            return await getStripeSession()
        },
        async saveFile(file) { 
            return await saveFile(file)
        },
        async delete(order) {
           return await deleteOrder(order)
        },
        async listNoPaye() {
           return await getOrders()
        },
        async listSuivi() {
           return await getOrdersComplete()
        },
        async cards({commit}){
            const res= await getOrders() 
            console.log('resss=='+res.data)
                if(res.status ===200){
                    console.log('cccccc')
                    const result = res.data
                    console.log(result.length)
                    commit('updateCard', result.length)
                }
             
        },
        async updateCommande(){
            return await updateOrder()
        }
    },
    mutations: {
        updateCard(state, payload){ 
            state.cardContent = payload 
        },
        updateListCard(state, payload){ 
            state.listCard = payload 
        }
    }
};
