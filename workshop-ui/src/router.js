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

router.beforeEach((to, from, next) => {
  console.log( window.$cookies );
  if( to.name === "login" || to.name === "404" || localStorage.getItem("token") ) {
    next();
  } else {
    next("login");
  }
});

export default router;