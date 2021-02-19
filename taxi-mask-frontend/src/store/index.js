import Vue from 'vue';
import Vuex from 'vuex';

import { auth } from './auth.module';
import {order} from './order.module';
import {payment} from './payment.module';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    order,
    payment
  }
});