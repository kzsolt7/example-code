import {api} from "@/api";

export const User = {
    state: {
        username: '',
        roles: [],
        hasAccess: false
    },
    mutations: {
        setUserName(state, username) {
            state.username = username;
        },
        setRoles(state, roles) {
            state.roles = roles;
        },
        logout(state) {
            state.username = '';
            state.roles = [];
            state.hasAccess = false;
        },
        setAccess(state, access){
            state.hasAccess = access;
        }
    },
    actions: {
        getAccess(context) {
            api.get(`/user/byUserName?username=${context.getters.getUsername}`).then(r => {
                if(r.status == 200) {
                    r.data.permissions.forEach(role => {
                        if(role == "mng_HasAccessToX"){
                            context.commit("setAccess", true)
                        }
                    })
                    context.commit("setRoles", r.data.permissions)
                    //console.log(this.$store.getters.getAccess)
                }
            })
        }
    },
    getters: {
        getUsername: state => state.username,
        getRoles: state => state.roles,
        getAccess: state => state.hasAccess
    }
}