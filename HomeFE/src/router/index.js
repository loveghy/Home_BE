import Vue from 'vue'
import Router from 'vue-router'
import Main from '../views/main'
import MainA from '../views/mainA'
import MainB from '../views/mainB'
import First from '../views/main/fist/first'
import Test from '../views/mainA/test'
import todayRecommend from '../views/main/todayRecommend'
import staffAdd from '../views/mainA/Staff/staffAdd'
import staffList from '../views/mainA/Staff/staffList'
import mainAfist from '../views/mainA/fist/mainAfist'
import mainBfist from '../views/mainB/fist/mainBfist'
import adminLogin from '../views/main/login/adminLogin'
import NotFound from '../views/NotFound'
import custom from '../views/mainA/Custom/customAdd'
import customList from '../views/mainA/Custom/customList'
import houseAdd from '../views/mainA/House/houseAdd'
import message from '../views/mainA/message/message'
import housBAdd from '../views/mainB/House/houseAdd'
import houseList from '../views/mainA/House/houseList'
import houseBList from '../views/mainB/House/houseList'
import houseListG from '../views/mainA/House/houseListG'
import Perchart from '../views/mainA/chart/perchart'
import Onlychart from '../views/mainA/chart/onlychart'
import Staffchart from '../views/mainB/chart/onlychart'
import Register from '../views/main/visitor/Register'
import userlogin from '../views/main/visitor/userlogin'
import mynew from '../views/main/about/mynew'
import mynewA from '../views/mainA/about/mynew'
import mynewB from '../views/mainB/about/mynew'
import orderAdd from '../views/mainA/record/orderAdd'
import orderList from '../views/mainA/record/orderList'
import orderAddB from '../views/mainB/record/orderAdd'
import orderListB from '../views/mainB/record/orderList'
import houserAdd from '../views/mainA/houser/houserAdd'
import houserList from '../views/mainA/houser/houserList'
import detail from '../views/main/detail'
import finance from '../views/mainA/chart/financeMonth'
import baidu from '../views/main/map/baidu'
import mainDetail from '../views/main/mainDetail'

Vue.use(Router);


export default new Router({
  mode: 'history', //去除#标志
  routes: [{
      path: '/main/adminLogin',
      name: 'adminLogin',
      component: adminLogin,
    },
    {
      path: '/',
      name: 'Main',
      component: Main,
      redirect: '/First',
      children: [{
          path: '/First',
          name: 'First',
          component: First,
        },
        {
          path: '/main/todayRecommend',
          name: 'todayRecommend',
          component: todayRecommend,
        },
        {
          path: '/main/Register',
          name: 'Register',
          component: Register,
        },
        {
          path: '/main/userlogin',
          name: 'userlogin',
          component: userlogin,
        },
        {
          path: '/main/mynew',
          name: 'mynew',
          component: mynew,
        },
        {
          path: '/main/detail/:id',
          name: 'detail',
          component: detail,
          props:true
        },
        {
          path: '/main/baidu/:id',
          name: 'baidu',
          component: baidu,
          props:true
        },
        {
          path: '/main/mainDetail/:id',
          name: 'mainDetail',
          component: mainDetail,
          props:true
        }
      ]
    },
    {
      path: '/MainA',
      name: 'MainA',
      component: MainA,
      redirect: '/mainA/fist',
      children: [{
          path: '/mainA/fist',
          name: 'mainAfist',
          component: mainAfist,
        },
        {
          path: '/Test',
          name: 'Test',
          component: Test,
        },
        {
          path: '/mainA/staffAdd',
          name: 'staffAdd',
          component: staffAdd,
        },
        {
          path: '/mainA/staffList',
          name: 'staffList',
          component: staffList,
        },
        {
          path: '/mainA/custom',
          name: 'custom',
          component: custom,
        },
        {
          path: '/mainA/customList',
          name: 'customList',
          component: customList,
        },
        {
          path: '/mainA/houseAdd',
          name: 'houseAdd',
          component: houseAdd,
        },
        {
          path: '/mainA/houseList',
          name: 'houseList',
          component: houseList,
        },
        {
          path: '/mainA/houseListG',
          name: 'houseListG',
          component: houseListG,
        },
        {
          path: '/mainA/message',
          name: 'message',
          component: message,
        },
        {
          path: '/mainA/perchart',
          name: 'perchart',
          component: Perchart,
        },
        {
          path: '/mainA/Onlychart',
          name: 'Onlychart',
          component: Onlychart,
        },
        {
          path: '/mainA/mynewA',
          name: 'mynewA',
          component: mynewA,
        },
        {
          path: '/mainA/orderAdd',
          name: 'orderAdd',
          component: orderAdd,
        },
        {
          path: '/mainA/orderList',
          name: 'orderList',
          component: orderList,
        },
        {
          path: '/mainA/houserAdd',
          name: 'houserAdd',
          component: houserAdd,
        },
        {
          path: '/mainA/houserList',
          name: 'houserList',
          component: houserList,
        },
        {
          path: '/mainA/finance',
          name: 'finance',
          component: finance,
        },
      ]
    },
    {
      path: '/MainB',
      name: 'MainB',
      component: MainB,
      redirect: '/mainB/fist',
      children: [{
        path: '/mainB/fist',
        name: 'mainBfist',
        component: mainBfist,
      },
      {
        path: '/mainB/customAdd',
        name: 'customB',
        component: custom,
      },
      {
        path: '/mainB/customList',
        name: 'customListB',
        component: customList,
      },
      {
        path: '/mainB/housBAdd',
        name: 'housBAdd',
        component: housBAdd,
      },
      {
        path: '/mainB/houseBList',
        name: 'houseBList',
        component: houseBList,
      },
      {
        path: '/mainB/Staffchart',
        name: 'Staffchart',
        component: Staffchart,
      },
      {
        path: '/mainB/mynewB',
        name: 'mynewB',
        component: mynewB,
      },
      {
        path: '/mainB/orderAddB',
        name: 'orderAddB',
        component: orderAddB,
      },
      {
        path: '/mainB/orderListB',
        name: 'orderListB',
        component: orderListB,
      },
      {
        path: '/mainB/houserAdd',
        name: 'houserAddB',
        component: houserAdd,
      },
      {
        path: '/mainB/houserList',
        name: 'houserListB',
        component: houserList,
      },
      ]
    },
    {
      path: '*', //为所有未出现的网页进行路由（router）进行网页定位
      component: NotFound
    },
  ]
})
