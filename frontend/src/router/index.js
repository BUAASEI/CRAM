import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/Page/index'
import SysUser from '@/Page/sysUser'
import Home from '@/Page/home'
import Manager from '@/Page/manager'
import Project from '@/Page/project'
import Role from '@/Page/projectRole'
import Data from '@/Page/projectData'
import Problem from '@/Page/projectProblem'
import Solution from '@/Page/projectSolution'
import Historys from '@/Page/projectHistorys'
import Business from '@/Page/business'
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
      path: '/manager',
      name: 'manager',
      component: Manager
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
      path: '/project/business',
      name: 'business',
      component: Business
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
