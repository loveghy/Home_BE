<template>
  <el-container v-if="role=='1'" style="background-color: #d0fcff;">
    <el-aside width='auto' @mouseenter.native="collapseOpen" @mouseleave.native="collapseClose">
      <el-menu :collapse="isCollapse" text-color="#fff" active-text-color="#ffd04b">
         <el-menu-item style="background-color: #303133;">
           <i class="el-icon-s-home"></i>
           <router-link to="/mainA/fist">首页</router-link>
         </el-menu-item>
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-folder-opened"></i>客户管理</template>
          <el-menu-item-group>
            <el-menu-item index="1-1">
              <i class="el-icon-user"></i>
              <router-link to="/mainA/custom">客户创建</router-link>
            </el-menu-item>
            <el-menu-item index="1-2">
              <i class="el-icon-user-solid"></i>
              <router-link to="/mainA/customList">客户信息</router-link>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-box"></i>销售管理</template>
          <el-menu-item-group>
            <el-menu-item index="2-1">
              <i class="el-icon-shopping-cart-full"></i>
              <router-link to="/mainA/orderAdd">新订单</router-link>
            </el-menu-item>
            <el-menu-item index="2-2">
              <i class="el-icon-s-order"></i>
              <router-link to="/mainA/orderList">销售订单</router-link>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>

        <el-submenu index="3">
          <template slot="title"><i class="el-icon-reading"></i>留言管理</template>
          <el-menu-item-group>
            <el-menu-item index="3-1">
              <i class="el-icon-document-remove"></i>
              <router-link to="/mainA/message">留言列表</router-link>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>

        <el-submenu index="4">
          <template slot="title"><i class="el-icon-user"></i>员工管理</template>
          <el-menu-item-group>
            <el-menu-item index="4-1">
              <i class="el-icon-user-solid"></i>
              <router-link to="/mainA/staffAdd">添加员工</router-link>
            </el-menu-item>
            <el-menu-item index="4-2">
              <i class="el-icon-folder-add"></i>
              <router-link to="/mainA/staffList">员工信息</router-link>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>

        <el-submenu index="5">
          <template slot="title"><i class="el-icon-office-building"></i>房源管理</template>
          <el-menu-item-group>
            <el-menu-item index="5-1">
              <i class="el-icon-folder-add"></i>
              <router-link to="/mainA/houseAdd">发布房源</router-link>
            </el-menu-item>
            <el-menu-item index="5-2">
              <i class="el-icon-notebook-2"></i>
              <router-link to="/mainA/houseList">房源列表</router-link>
            </el-menu-item>
            <el-menu-item index="5-3">
              <i class="el-icon-notebook-2"></i>
              <router-link to="/mainA/houseListG">个人房源</router-link>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>

        <el-submenu index="6">
          <template slot="title"><i class="el-icon-s-data"></i>报表中心</template>
          <el-menu-item-group>
            <el-menu-item index="6-1">
              <i class="el-icon-s-data"></i>
              <router-link to="/mainA/perchart">公司房源报表</router-link>
            </el-menu-item>
            <el-menu-item index="6-2">
              <i class="el-icon-s-data"></i>
              <router-link to="/mainA/finance">公司财务报表</router-link>
            </el-menu-item>
            <el-menu-item index="6-3">
              <i class="el-icon-s-data"></i>
              <router-link to="/mainA/Onlychart">个人房源报表</router-link>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="7">
          <template slot="title"><i class="el-icon-s-check"></i>房主管理</template>
          <el-menu-item-group>
            <el-menu-item index="7-1">
              <i class="el-icon-s-data"></i>
              <router-link to="/mainA/houserAdd">房主录入</router-link>
            </el-menu-item>
            <el-menu-item index="7-2">
              <i class="el-icon-s-data"></i>
              <router-link to="/mainA/houserList">房主列表</router-link>
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
        <span style="color: white;" >经理权限管理</span>
        <el-dropdown :hide-on-click="false" id="father">
          <span class="el-dropdown-link">
            <li class="el-icon-user"></li>
            {{$store.getters.getUser.name}}
            <i class="el-icon-caret-bottom"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <router-link to="/mainA/fist" id="pp">返回首页</router-link>
            </el-dropdown-item>
            <el-dropdown-item>
              <router-link to="/mainA/mynewA" id="pp">个人中心</router-link>
            </el-dropdown-item>
            <el-dropdown-item>
              <router-link to="/logout" id="pp">退出登录</router-link>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  export default {
    name: 'MainA',
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
    line-height: 2.5rem;
    box-shadow: 0 0 0.6rem gray;
    background-color: #55aaff;
  }

/* .el-main {
    padding: 0;
  } */

  /*  .el-footer {
    border: 1px solid #99A9BF;
    background-color: #99A9BF;
    line-height: 2.5rem;
  } */

  .el-aside {
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

  #pp {
    color: #243B55;
  }

  #father {
    width: 15%;
    font-size: 15px;
    font-weight: 600;
    position: absolute;
    margin-top: 10px;
    margin-left: 75%;
    color: #000;

  }

  .el-dropdown-link {
    font-size: larger;
    font-family: "arial, helvetica, sans-serif";
  }

  /* .router-link-active {
  	text-decoration: none;
    color: yellow;
  } */
</style>
