import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import UserList from "@/views/user/UserList";
import AddNewUser from "@/views/user/AddNewUser";
import NotificationGroups from "@/views/user/NotificationGroups";
import AddNewPermissionGroup from "@/views/user/AddNewPermissionGroup";
import PermissionGroups from "@/views/user/PermissionGroups";
import ExportImport from "@/views/user/ExportImport";
import EditUser from "@/views/user/EditUser";
import AddNewNotificationGroup from "@/views/user/AddNewNotificationGroup";
import EditPermissionGroup from "@/views/user/EditPermissionGroup";
import EditNotificationGroup from "@/views/user/EditNotificationGroup";
import Login from "@/views/Login";
import {api} from "@/api";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
        meta: {requiresAuth: true}
    },
    {
        path: '/login',
        name: 'login',
        component: Login
    },
    {
        path: '/user-list/:status?',
        name: 'User list',
        component: UserList,
        meta: {requiresAuth: true}

    },
    {
        path: '/add-new-user',
        name: 'Add new user',
        component: AddNewUser,
        meta: {requiresAuth: true}

    },
    {
        path: '/notification/:status?',
        name: 'Notification group',
        component: NotificationGroups,
        meta: {requiresAuth: true}

    },
    {
        path: '/permission/:status?',
        name: 'Permission group',
        component: PermissionGroups,
        meta: {requiresAuth: true}

    },
    {
        path: '/edit-permission-group/:id',
        name: 'Edit permission group',
        component: EditPermissionGroup,
        meta: {requiresAuth: true}

    },
    {
        path: '/edit-notification-group/:id',
        name: 'Edit notification group',
        component: EditNotificationGroup,
        meta: {requiresAuth: true}

    },
    {
        path: '/add-new-permission-group',
        name: 'Add new permission group',
        component: AddNewPermissionGroup,
        meta: {requiresAuth: true}

    }, {
        path: '/add-new-notification-group',
        name: 'Add new notification group',
        component: AddNewNotificationGroup,
        meta: {requiresAuth: true}

    },
    {
        path: '/export-import',
        name: 'Export import',
        component: ExportImport,
        meta: {requiresAuth: true}

    },
    {
        path: '/about',
        name: 'About',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/About.vue'),
        meta: {requiresAuth: true}

    },
    {
        path: '/edit-user/:id',
        name: 'Edit user',
        component: EditUser,
        meta: {requiresAuth: true}

    },

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (!window.$cookies.get("access-token") && window.$cookies.get("refresh-token")) {

            api.post('/refreshtoken?refreshtoken=' + window.$cookies.get("refresh-token")).then(r => {
                if (r.status === 200) {
                    setCookie("access-token", r.headers.authorization, "3600");
                    setCookie("refresh-token", r.headers.refresh, "7200");
                    setCookie("username", r.headers.username, "7200");
                }

            })

        }

        if (!window.$cookies.get("access-token")) {
            router.push('login')
        }
    }
    next();
})

function setCookie(cname, cvalue, exsecs) {
    const d = new Date();
    d.setTime(d.getTime() + (exsecs *  1000));
    let expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

export default router
