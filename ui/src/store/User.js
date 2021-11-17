export const User = {
    state: {
        username: '',
        roles: []
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
        },
    },
    getters: {
        getUsername: state => state.username,
        getRoles: state => state.roles
    }
}