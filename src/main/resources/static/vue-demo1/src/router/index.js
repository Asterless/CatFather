import { createRouter, createWebHistory } from 'vue-router'

// 导入组件
import Bai from '@/components/maodie/Bai.vue'
import Beside from '@/components/maodie/Beside.vue'
import Gushen from '@/components/maodie/Gushen.vue'
import Junshi from '@/components/maodie/Junshi.vue'
import Putong from '@/components/maodie/Putong.vue'
import Grow from '@/components/maodie/Grow.vue'
import Little from '@/components/maodie/Little.vue'
import BESIDES1 from '@/components/history/BESIDES1.vue'
import HISTORY from '@/components/history/HISTORY.vue'
import BESIDES2 from '@/components/history/BESIDES2.vue'
import Cat from '@/components/music/Cat.vue'
import List from '@/components/music/List.vue'
import { compile } from 'vue'
const routes = [
  {
    path:'/list',
    name:'list',
    component:List
  },
  {
path:'/cat',
name:'cat',
component:Cat
  },
  {
path:'/besides1',
name:'besides1',
component:BESIDES1
  },
  {
path:'/besides2',
name:'besides2',
component:BESIDES2
  },
  {
path:'/history',
name:'history',
component:HISTORY
  },
  {
    path: '/bai',
    name: 'bai',
    component: Bai
  },
  {
    path: '/beside',
    name: 'beside',
    component: Beside
  },
  {
    path: '/gushen',
    name: 'gushen',
    component: Gushen
  },
  {
    path: '/junshi',
    name: 'junshi',
    component: Junshi
  },
  {
    path: '/putong',
    name: 'putong',
    component: Putong
  },
  {
    path: '/grow',
    name: 'grow',
    component: Grow
  },
  {
    path: '/little',
    name: 'little',
    component: Little
  },
  // 可以添加一个默认路由
  {
    path: '/',
    redirect: '/putong' // 默认重定向到普通耄耋
  },
  //404
  {
    path: '/:pathMatch(.*)*',
    component: () => import('@/views/NotFound.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
