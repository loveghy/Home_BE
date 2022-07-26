const user = {
	//全局state对象，用于保存所有组件公共数据
	state: sessionStorage.getItem('state') ? JSON.parse(sessionStorage.getItem('state')) : {
		user: {
			name: '',
		}
	},

	//监听state对象最新状态
	getters: {
		getUser(state) {
			return state.user;
		}
	},


	//唯一一个可以修改state值的方法(同步执行阻塞的方法)
	mutations: {
		updateUser(state, user) {
			state.user = user;
		}
	},

   //解决同步执行
	actions: {
		asyncUpdateUser(context, user) {
			context.commit("updateUser", user);
		}
	},
}

export default user;
