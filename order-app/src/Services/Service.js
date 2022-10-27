import { Buffer } from "buffer";
import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

//const auth = localStorage.getItem('token');

class Service {

  addTransaction = (item, quantity) => {
    if (localStorage.getItem('transactions') === null) {
      localStorage.setItem('transactions', JSON.stringify([{ item, quantity }]));
    } else {
      const arr = JSON.parse(localStorage.getItem('transactions'));
      console.log(arr);

      const isIn = arr.some(elm => {
        if(elm.item.item.itemId===item.item.itemId){
          elm.quantity += quantity;
          return true;
        }else{
          return false;
        }
      });

      if (!isIn) {
        arr.push({ item, quantity });
      }
      localStorage.setItem('transactions', JSON.stringify(arr));
    }
  }

  calculateItems = () => {
    if (localStorage.getItem('transactions') !== null) {
      let data = JSON.parse(localStorage.getItem('transactions'));

      if (data !== null) {
        let res = data.reduce((sum, n) => sum = sum + n.quantity, 0);
        return res;
      }
    }
    return 0;
  }

  loginAdmin = async (adminName, password) => {
    const res = await fetch(BASE_URL + "/admin", {
      method: 'post',
      headers: new Headers({
        'Authorization': 'Basic ' + Buffer.from(adminName + ":" + password).toString('base64')
      })
    })
    if (!res.ok) {
      if ([401, 403].includes(res.status)) {
        return '';
      }
    }
    const token = await this.getToken(adminName, password);
    return token;
  }

  createUser = async (username, password) => {
    const res = await fetch(BASE_URL + '/user/create', {
      method: 'post',
      headers: { 'Content-Type': 'application/json', 'My-Custom-Header': 'foobar' },
      body: JSON.stringify({ username: username, password: password, active: true, roles: "ROLE_USER" })
    })
    if (!res.ok) {
      if ([401, 403].includes(res.status)) {
        return '';
      }
    }
    return await res.text();
  }

  getToken = async (username, password) => {
    const res = await fetch(BASE_URL + "/token", {
      method: 'post',
      headers: new Headers({
        'Authorization': 'Basic ' + Buffer.from(username + ":" + password).toString('base64')
      })
    })
    if (!res.ok) {
      if ([401, 403].includes(res.status)) {
        localStorage.removeItem('token');
        return '';
      }
    }

    let data = await res.text();
    return data;
  }

  getItems = async () => {
    let data = await axios.get(BASE_URL + "/item/all").then(data => data);
    return data.data;
  }

  getItemById = async (itemId) => {
    let data = await axios.get(BASE_URL + "/item/" + itemId).then(data => data);
    return data.data;
  }


}

export default new Service();