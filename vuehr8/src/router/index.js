import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'

import EmpBasic from '../views/emp/EmpBasic.vue'

import SalSearch from '../views/sal/SalSearch.vue'
import SalMonth from '../views/sal/SalMonth.vue'
import SalTable from '../views/sal/SalTable.vue'

import SysBasic from '../views/sys/SysBasic.vue'
import SysData from '../views/sys/SysData.vue'
import SysHr from '../views/sys/SysHr.vue'
import SysInit from '../views/sys/SysInit.vue'
import SysLog from '../views/sys/SysLog.vue'

import PerEc from '../views/per/PerEc.vue'
import PerMv from '../views/per/PerMv.vue'
import PerSalary from '../views/per/PerSalary.vue'

import StaPers from '../views/sta/StaPers.vue'
import StaScore from '../views/sta/StaScore.vue'



//0.如果使用模块化机制编程，导入Vue和VueRouter，要调用 Vue.use(VueRouter)
Vue.use(VueRouter)
// 2. 定义路由
// 每个路由应该映射一个组件。 其中"component" 可以是
// 通过 Vue.extend() 创建的组件构造器，
// 或者，只是一个组件配置对象。
// 我们晚点再讨论嵌套路由。
      const routes = [
      {
        path: '/',
        name: 'Login',
        component: Login,
        hidden:true
      }, {
          path: '/home',
          name: 'Home',
          component: Home,
          hidden:true,
      }, {
          path: '/home',
          name: '员工资料',
          component: Home,
              meta:{
                index:'1',
                 icon:'fa fa-user-circle-o'
              },

          children:[
              {
                  path: '/empBasic',
                  name: '员工基本资料',
                  component: EmpBasic,
                  meta:{
                      icon:'fa fa-user-circle-o'
                  }
              },
          ]
      },{
              path: '/home',
              name: '人事管理',
              component: Home,
              meta:{
                index:'2',
                  icon:'fa fa-address-card-o'
              },
              children:[
                  {
                      path: '/perEc',
                      name: '员工奖惩',
                      component: PerEc
                  },{
                      path: '/perMv',
                      name: '员工移动',
                      component: PerMv
                  },{
                      path: '/perSalary',
                      name: '员工调薪',
                      component: PerSalary
                  },
              ]


       },{
              path: '/home',
              name: '工资管理',
              component: Home,
              meta:{
                index:'3',
                  icon:'fa fa-money'
              },
              children:[
                  {
                      path: '/salMonth',
                      name: '月末处理',
                      component: SalMonth,
                  },{
                      path: '/salSearch',
                      name: '工资表查询',
                      component: SalSearch,
                  },{
                      path: '/salTable',
                      name: '工资表管理',
                      component: SalTable,
                  },
              ]


       },{
              path: '/home',
              name: '人事信息统计',
              component: Home,
              meta:{
                index:'4',
                  icon:'fa fa-bar-chart'
              },
              children:[
                  {
                      path: '/staPers',
                      name: '人事信息',
                      component: StaPers,
                  },{
                      path: '/staScore',
                      name: '积分',
                      component: StaScore,
                  },
              ]


       },{
              path: '/home',
              name: '系统管理',
              component: Home,
              meta:{
                  index:'5',
                  icon:'fa fa-windows'
              },
              children:[
                  {
                      path: '/sysHr',
                      name: '操作员管理',
                      component: SysHr
                  },{
                      path: '/sysBasic',
                      name: '基础信息设置',
                      component: SysBasic
                  },{
                      path: '/sysData',
                      name: '备份恢复数据库',
                      component: SysData
                  },{
                      path: '/sysInit',
                      name: '系统初始化',
                      component: SysInit
                  },{
                      path: '/sysLog',
                      name: '操作日志管理',
                      component: SysLog
                  },
              ]

        }


]
// 3. 创建 router 实例，然后传 `routes` 配置
// 你还可以传别的配置参数, 不过先这么简单着吧。
const router = new VueRouter({
  routes
})

//挂在路由导航守卫, 看后端，后来再加

/*
router.beforeEach(to,from,next)=>{
    //获取token
    const tokenStr = window.sessionStorage.getItem('token')
    if(!tokenStr){
        return next('/login')
    }
    next()
}
*/


export default router
