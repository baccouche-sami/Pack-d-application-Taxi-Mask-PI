import axios from 'axios';
import authHeader from './auth-header';
import {SERVER_URL,PAYMENT} from './config-server';
const API_URL =  `${SERVER_URL}/${PAYMENT}/`

class PaymentService {
  async getStripePublicKey() {
    console.log(API_URL)
    return await axios.get(API_URL + 'stripe-public-key',{ headers: authHeader() });
  }

  async chargeCard(charge) {
    return await axios.post(API_URL + 'charge-card',charge, { headers: authHeader() });
  }
 
}

export default new PaymentService();