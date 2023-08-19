import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import CreateCourse from '../components/CreateCourse.vue'
import Course from '../views/Course.vue'
import Content from '../views/Content.vue'
import AddModule from '../components/AddModule.vue'
import Homework from '../views/Homework.vue'
import Grade from '../views/Grade.vue'
import Progress from'../views/Progress.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/add-course",
      name: "add-course",
      component: CreateCourse,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/course/:id',
      name: 'course',
      component: Course,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/course/:id/add-module',
      name: 'add-module',
      component: AddModule,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/course/:id/:currid/:pageNum',
      name: 'content',
      component: Content,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/course/:id/:currid/homework/:homeworkId',
      name: 'homework',
      component: Homework,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/course/:id/gradehomework',
      name: 'grade-homework',
      component: Grade,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/course/:id/progress',
      name: 'progress',
      component: Progress,
      meta: {
        requiresAuth: true
      }
    },
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
