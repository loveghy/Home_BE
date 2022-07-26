<template>
  <el-container v-if="role=='0'" style="background-color: #d0fcff;">
    <el-aside width="auto" @mouseenter.native="collapseOpen" @mouseleave.native="collapseClose">
      <el-menu :collapse="isCollapse" text-color="#fff" active-text-color="#ffd04b">
        <el-menu-item index="0" style="background-color: #303133;"><i class="el-icon-s-home"></i><router-link to="/mainB/fist">首页</router-link></el-menu-item>
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-folder-opened"></i>客户管理</template>
          <el-menu-item-group>
            <el-menu-item index="1-1">
              <i class="el-icon-folder-add"></i>
              <router-link to="/mainB/customAdd">客户创建</router-link>
            </el-menu-item>
            <el-menu-item index="1-2">
              <i class="el-icon-folder"></i>
              <router-link to="/mainB/customList">客户信息</router-link>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-office-building"></i>房源中心</template>
          <el-menu-item-group>
            <el-menu-item index="2-1">
              <i class="el-icon-folder-add"></i>
              <router-link to="/mainB/housBAdd">房源发布</router-link>
            </el-menu-item>
            <el-menu-item index="2-2">
              <i class="el-icon-tickets"></i>
              <router-link to="/mainB/houseBList">个人房源</router-link>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-s-data"></i>个人报表</template>
          <el-menu-item-group>
            <el-menu-item index="3-1">
              <i class="el-icon-s-data"></i>
              <router-link to="/mainB/Staffchart">个人房源</router-link>
            </el-menu-item>
<!--            <el-menu-item index="1-2">
              <i class="el-icon-s-data"></i>
              <router-link to="/defect/Add">个人财务</router-link>
            </el-menu-item> -->
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="4">
          <template slot="title"><i class="el-icon-user"></i>房主管理</template>
          <el-menu-item-group>
            <el-menu-item index="4-1">
              <i class="el-icon-s-data"></i>
              <router-link to="/mainB/houserAdd">房主录入</router-link>
            </el-menu-item>
            <el-menu-item index="4-2">
              <i class="el-icon-s-data"></i>
              <router-link to="/mainB/houserList">房主列表</router-link>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="5">
          <template slot="title"><i class="el-icon-box"></i>订单管理</template>
          <el-menu-item-group>
            <el-menu-item index="5-1">
              <i class="el-icon-shopping-cart-full"></i>
              <router-link to="/mainB/orderAddB">新订单</router-link>
            </el-menu-item>
            <el-menu-item index="5-2">
              <i class="el-icon-s-order"></i>
              <router-link to="/mainB/orderListB">销售订单</router-link>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header>
        <button @click="collapseStatus">
          <i class="el-icon-d-arrow-right" v-if="isCollapse==true"></i>
          <i class="el-icon-d-arrow-left" v-if="isCollapse==false"></i>
        </button>
        <span style="color: white;" >销售权限管理</span>
        <el-dropdown :hide-on-click="false" id="father">
          <span class="el-dropdown-link">
            <li class="el-icon-user"></li>
            {{$store.getters.getUser.name}}
            <i class="el-icon-caret-bottom"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item><router-link to="/mainB/fist" id="pp">返回首页</router-link></el-dropdown-item>
            <el-dropdown-item><router-link to="/mainB/mynewB" id="pp">个人中心</router-link></el-dropdown-item>
            <el-dropdown-item><router-link to="/logout" id="pp">退出登录</router-link></el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
      <!-- <el-footer>Footer</el-footer> -->
    </el-container>

  </el-container>
</template>

<script>
  export default {
    name: 'MainB',
    data() {
      return {
        role:sessionStorage.getItem('Type'),
        collapseBtnClick: true,
        isCollapse: false,
      }
    },
    methods: {
      collapseStatus() {
        this.collapseBtnClick = this.isCollapse;
        this.isCollapse = !this.isCollapse;
      },
      collapseOpen() {
        if (this.collapseBtnClick) return;
        this.isCollapse = false;
      },

      collapseClose() {
        if (this.collapseBtnClick) return;
        this.isCollapse = true;
      }
    }
  }
</script>

<style scoped>
  .el-header {
    position: relative;
    /* text-align: center; */
    box-shadow: 0 0 0.6rem gray;
    background-color: #55aaff;
    line-height: 2.5rem;
  }

/*  .el-footer {
    background-color: #2C3E50;
    color: #333;
    text-align: center;
    line-height: 60px;
  } */

 .el-aside {
   /* width: 20px; */
   min-height: 835px;
   background-color: #303133;
 }

  .el-menu {
    border-right: 0;
    background-color: #303133;
  }

 .el-menu-item-group {
   background-color: #303133;
   padding: 0px;
   margin: 0px;
 }

 .el-menu-item {
   background-color: #111d2a;
   padding: 0px;
   margin: 0px;
 }

  /* 去除router-link下划线*/
  a {
    text-decoration: none;
    color: white;
  }
  #pp{
    color: #243B55;
  }

    #father{
    width: 15%;
    font-size: 15px;
    font-weight: 600;
    position: absolute;
    margin-top: 10px;
    margin-left: 75%;
    color: #000;

    }

    .el-dropdown-link{
      font-size: larger;
      font-family: "arial, helvetica, sans-serif";
    }
/*  .el-main{
    padding: 0px;
    margin: 0px;
  } */
  /* .router-link-active {
  	text-decoration: none;
    color: yellow;
  } */
</style>
