import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/Page/index'
import SysUser from '@/Page/sysUser'
import Home from '@/Page/home'
import ProManage from '@/Page/proManage'
import ProParticipate from '@/Page/proParticipate'
import ProOther from '@/Page/proOther'
import Project from '@/Page/project'
import Role from '@/Page/role'
import Data from '@/Page/data'
import Problem from '@/Page/problem'
import Solution from '@/Page/solution'
import Historys from '@/Page/historys'
import Scenario from '@/Page/scenario'
import Activity from '@/Page/activity'
import Usecase from '@/Page/usecase'
import Evolution from '@/Page/evolution'

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
      path: '/sysuser/proother',
      name: 'proother',
      component: ProOther
    },
    {
      path: '/project/home',
      name: 'project',
      component: Project
    },
    {
      path: '/project/role',
      name: 'role',
      component: Role
    },
    {
      path: '/project/data',
      name: 'data',
      component: Data
    },
    {
      path: '/project/problem',
      name: 'problem',
      component: Problem
    },
    {
      path: '/project/solution',
      name: 'solution',
      component: Solution
    },
    {
      path: '/project/historys',
      name: 'historys',
      component: Historys
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
    },
    {
      path: '/evolution',
      name: 'evolution',
      component: Evolution
    }
  ]
})
