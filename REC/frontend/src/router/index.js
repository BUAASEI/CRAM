import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/Page/index'
import SysUser from '@/Page/sysUser'
import Home from '@/Page/home'
import ProManage from '@/Page/proManage'
import ProParticipate from '@/Page/proParticipate'
import ProAll from '@/Page/proAll'
import Project from '@/Page/project'
import Scenario from '@/Page/scenario'
import Activity from '@/Page/activity'
import Usecase from '@/Page/usecase'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: Index
    },
    {
      path: '/sysuser',
      name: 'sysuser',
      component: SysUser
    },
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/sysuser/proman',
      name: 'proman',
      component: ProManage
    },
    {
      path: '/sysuser/propar',
      name: 'propar',
      component: ProParticipate
    },
    {
      path: '/sysuser/proall',
      name: 'proall',
      component: ProAll
    },
    {
      path: '/project/home',
      name: 'project',
      component: Project
    },
    {
      path: '/project/scenario',
      name: 'scenario',
      component: Scenario
    },
    {
      path: '/project/scenario/activity',
      name: 'activity',
      component: Activity
    },
    {
      path: '/project/usecase',
      name: 'usecase',
      component: Usecase
    }
  ]
})
