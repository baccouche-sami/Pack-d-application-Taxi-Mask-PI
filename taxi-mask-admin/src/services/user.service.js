import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8081/';

class UserService {
  async getPublicContent() {
    return await axios.get(API_URL + 'all');
  }

  async getUserBoard() {
    return await axios.get(API_URL + 'user', { headers: authHeader() });
  }



  async getAdminBoard() {
    return await axios.get(API_URL + 'admin', { headers: authHeader() });
  }
}

export default new UserService();
