import Axios from "axios"

const RestUtil = Axios.create({
    withCredentials: true
});


RestUtil.interceptors.response.use((response) => {
    return response;
  },
  (error) => {
    if( error.response.status === 401 ) {
      console.log("yes !");
    }
    return Promise.reject(error);
  }
);


export default RestUtil;
