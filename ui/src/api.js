import axios from 'axios'
import router from './router'

export const api = axios.create({
    baseURL: `/api`,
    timeout: 10000
});

api.interceptors.request.use(
    config => {
        const token = getCookie("access-token");

        if (token) {
            config.headers.common.Authorization = `${token}`;
        }

        return config;
    },
    error => Promise.reject(error)
)

api.interceptors.response.use(
    (response) => {

        if (response.data.accessToken) {
            localStorage.setItem('access_token', response.data.accessToken);
        }
        return Promise.resolve(response);
    },
    error => {
        //TODO handle handle 401
        if(error.response.status === 401){
            if (getCookie("refresh-token")) {
                api.post('/refreshtoken?refreshtoken=' + getCookie("refresh-token")).then(r => {
                    if (r.status === 200) {
                        setCookie("access-token", r.headers.authorization, "1296000");
                        setCookie("refresh-token", r.headers.refresh, "2592000");
                        setCookie("username", r.headers.username, "1296000");

                    }
                })

            }else{
                router.push('/login');
            }

            if (getCookie("refresh-token")){
                return api.request(error.config)
            }
        }
        if(error.response.status == 409){
            return Promise.reject(error);
        }
        // if(error.response.status == 403){
        //     router.push('/login')
        // }

       return  Promise.reject(error);
    }
)

function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function setCookie(cname, cvalue, exsecs) {
    const d = new Date();
    d.setTime(d.getTime() + (exsecs *  1000));
    let expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}