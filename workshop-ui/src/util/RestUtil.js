import Axios from "axios"
import router from "../router"

const RestUtil = Axios.create({
    withCredentials: true
});


RestUtil.interceptors.response.use((response) => {
    return response;
  },
  (error) => {
    if( error.response.status === 401 ) {
      router.push('/login');
    }
    return Promise.reject(error);
  }
);


export default RestUtil;
