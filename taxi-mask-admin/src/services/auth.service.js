import axios from 'axios';
import {SERVER_URL} from '../services/config-server';
const API_URL = `${SERVER_URL}/api/auth/`
import authHeader from './auth-header';
axios.defaults.withCredentials = true
const config = {
    headers: authHeader()
}

class AuthService {
  login(user) {
    return axios
      .post(API_URL + 'signin', {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }
        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post(API_URL + 'signup', {
      username: user.username,
      email: user.email,
      password: user.password
    },config);
  }
}

export default new AuthService();