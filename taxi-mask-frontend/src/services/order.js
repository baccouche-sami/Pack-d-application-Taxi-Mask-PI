import axios from 'axios' 
import authHeader from './auth-header'
import {SERVER_URL, ORDER_URL, CARD_LIST} from './config-server'

 

export async function saveOrder(order) { 
    const config = {
        headers: authHeader()
    }
    console.log(order)
    const result = axios.post(`${SERVER_URL}/${ORDER_URL}`, order, config) 
    return result
}
export async function deleteOrder(order) { 
    const config = {
        headers: authHeader()
    }
    console.log(order.id)
   const result = axios.get(`${SERVER_URL}/${ORDER_URL}/del/${order.id}`, config) 
    return result
}
export async function saveFile(file) { 
    const config = {
        headers: authHeader()
    }
   const result = axios.post(`${SERVER_URL}/${ORDER_URL}`, file, config) 
    return result
}

export async function getOrders(){ 
    const config = {
        headers: authHeader()
    }
    const result = await axios.get(`${SERVER_URL}/${ORDER_URL}`, config)
    return result
}
export async function getOrdersComplete(){ 
    const config = {
        headers: authHeader()
    }
    const result = await axios.get(`${SERVER_URL}/${ORDER_URL}/order-complete`, config)
    return result
}
 
export async function getStripeSession() {
    const config = {
        headers: authHeader()
    }
    const result = await axios.get(`${SERVER_URL}/${ORDER_URL}/checkoutsession`, config)
    return result
}
export async function updateOrder() { 
    const config = {
        headers: authHeader()
    }
    let cards = localStorage.getItem(CARD_LIST)
    if(cards!==null && cards!=='undefined' && cards!==''){ 
        const result= await axios.get(`${SERVER_URL}/${ORDER_URL}/update-commande/${cards}`, config)
        if(result.status ===200){
            localStorage.setItem(CARD_LIST,"")
        }
        console.log(result)
        return result
    }
    return null
}
