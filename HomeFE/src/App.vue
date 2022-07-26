<template>
  <div id="app">
    <router-view v-if="isRouterAlive"></router-view>
  </div>
</template>

<script>
  export default {
    name: 'App',
    provide() {
    	return {
    		reload: this.reload
    	}
    },
    data() {
    	return {
    		isRouterAlive: true,
    	};
    },
    mounted() {
    	window.addEventListener('unload', this.saveState);//刷新判断
    },
    methods: {
    	saveState() {
    		sessionStorage.setItem('state', JSON.stringify(this.$store.state.user));//存储当前状态
    	},
      reload() {
      	this.isRouterAlive = false;
      	this.$nextTick(function() {
      		this.isRouterAlive = true;
      	})
      }
    },
  }
</script>
<style>
</style>
