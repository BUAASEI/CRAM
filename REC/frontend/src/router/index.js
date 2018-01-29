import Vue from 'vue'
import Router from 'vue-router'
import index from '@/Page/index'
import Stage from '@/Page/stage'
import Usage from '@/Page/usage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: index
    },
    {
      path: '/system/stage',
      name: 'stage',
      component: Stage
    },
    {
      path: '/system/stage/usage',
      name: 'usage',
      component: Usage
    }
  ]
})
