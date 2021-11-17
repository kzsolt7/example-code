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
import EditGroup from "@/views/user/EditGroup";
import AddNewNotificationGroup from "@/views/user/AddNewNotificationGroup";
import EditPermissionGroup from "@/views/user/EditPermissionGroup";
import EditNotificationGroup from "@/views/user/EditNotificationGroup";
import Login from "@/views/Login";

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
        component: UserList
    },
    {
        path: '/add-new-user',
        name: 'Add new user',
        component: AddNewUser
    },
    {
        path: '/notification/:status?',
        name: 'Notification group',
        component: NotificationGroups
    },
    {
        path: '/permission/:status?',
        name: 'Permission group',
        component: PermissionGroups
    },
    {
        path: '/edit-permission-group/:id',
        name: 'Edit permission group',
        component: EditPermissionGroup
    },
    {
        path: '/edit-notification-group/:id',
        name: 'Edit notification group',
        component: EditNotificationGroup
    },
    {
        path: '/add-new-permission-group',
        name: 'Add new permission group',
        component: AddNewPermissionGroup
    }, {
        path: '/add-new-notification-group',
        name: 'Add new notification group',
        component: AddNewNotificationGroup
    },
    {
        path: '/export-import',
        name: 'Export import',
        component: ExportImport
    },
    {
        path: '/about',
        name: 'About',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    },
    {
        path: '/edit-user/:id',
        name: 'Edit user',
        component: EditUser
    },
    {
        path: '/edit-group',
        name: 'Edit group',
        component: EditGroup
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (!window.$cookies.get("access-token")) {
            router.push('login')
        }
    }
    next();
})

export default router
