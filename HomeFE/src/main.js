// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import VueRouter from 'vue-router'
import router from './router'//引入路由,实现路径配置，<npm install vue-router>
//router使用
import ElementUI from 'element-ui'//引入element-ui<npm i element-ui -S>
import 'element-ui/lib/theme-chalk/index.css'//element-ui框架样式引入
import axios from 'axios' //npm install axios
/* import Vuex from 'vuex'//npm install vuex,可用于状态存储 */
import  VueResource  from 'vue-resource'//获取后端数据引入<npm install vue-resource --save>
import Vuex from 'vuex'
import store from './store'//状态保存引用store文件夹
import JsonExcel from 'vue-json-excel'//npm install vue-json-excel
import * as echarts from 'echarts';
//验证码
import Vcode from "vue-puzzle-vcode";
import Video from 'video.js'
import 'video.js/dist/video-js.css'
//视屏播放
import BaiduMap from 'vue-baidu-map'
Vue.use(BaiduMap, {
  ak: 'DD279b2a90afdf0ae7a3796787a0742enSxiPohfziUaCuONe4ViUP2N'  //这个地方是官方提供的ak密钥
})
//百度地图注册



Vue.component('downloadExcel', JsonExcel);

//挂载Vuex
Vue.use(Vuex)

Vue.use(ElementUI);
Vue.use(VueRouter);
/* Vue.use(Vuex); */
Vue.use(VueResource);
//需要挂载到Vue原型上
Vue.prototype.$echarts = echarts;
Vue.prototype.axios = axios;
Vue.config.productionTip = false

 router.beforeEach((to, from, next) => {
	let isLogin = sessionStorage.getItem('isLogin'); //获取设置的登录状态
  let Type = sessionStorage.getItem('Type');
	console.log(isLogin );
  console.log(Type);
	if (to.path == '/logout') {
		//注销登录
		sessionStorage.clear(); //清空登录数据
		// 跳转到登录页面
		next({
			path: '/main/adminLogin'
		});
	} else if (to.path == '/main/adminLogin') {
		//判断是否为空，不为空跳转到首页
		if (isLogin != null) {
      if(Type=='1')
      {
        next({
        	path: '/MainA'
        });
      }else
      next({
      	path: '/MainB'
      });
		}
	}
   	next();
})
//登录设置拦截，只有登录才能使用
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
