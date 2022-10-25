import { Buffer } from "buffer";

const BASE_URL = 'http://localhost:8080';
const auth = { headers: { 'Authorization': localStorage.getItem('token') } };

class Service {

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
        const token = await this.getToken(adminName,password);
        return token;
      }


}

export default new Service();