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
    getters: {
        getUsername: state => state.username,
        getRoles: state => state.roles,
        getAccess: state => state.hasAccess
    }
}