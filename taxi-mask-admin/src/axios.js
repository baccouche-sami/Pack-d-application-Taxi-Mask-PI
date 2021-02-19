// axios
import axios from 'axios'

const domain = "http://localhost:8080/"

axios.defaults.withCredentials = true;

export default axios.create({
  domain
  // You can add your headers here
})
