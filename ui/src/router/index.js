import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import UserList from "@/views/user/UserList";
import AddNewUser from "@/views/user/AddNewUser";
import NotificationGroups from "@/views/user/NotificationGroups";
import AddNewGroup from "@/views/user/AddNewGroup";
import PermissionGroups from "@/views/user/PermissionGroups";
import ExportImport from "@/views/user/ExportImport";
import EditUser from "@/views/user/EditUser";
import EditGroup from "@/views/user/EditGroup";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/user-list',
    name: 'User list',
    component: UserList
  },
  {
    path: '/add-new-user',
    name: 'Add new user',
    component: AddNewUser
  },
  {
    path:'/notification',
    name: 'Notification group',
    component: NotificationGroups
  },
  {
    path:'/permission',
    name: 'Permission group',
    component: PermissionGroups
  },
  {
    path: '/add-new-group',
    name: 'Add new group',
    component: AddNewGroup
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

export default router
