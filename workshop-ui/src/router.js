import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

let router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [{
      path: '/login',
      name: 'login',
      component: () => import('./views/Login.vue')
    }, {
      path: '/',
      alias: '/home',
      name: 'home',
      component: () => import('./views/Home.vue')
    }, {
      path: '*',
      name: '404',
      component: () => import('./views/404.vue')
    }
  ]
});

// router.beforeEach((to, from, next) => {
//   if( to.name === "login" || to.name === "404" || localStorage.getItem("IsLogedIn") ) {
//     next();
//   } else {
//     next("login");
//   }
// });

export default router;
