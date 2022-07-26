<template>
  <el-container id="el">
    <el-header>
      <h4 style="font-family: STXingkai;font-size: 25px;color: white;margin-top: 20px;">~~~欢迎看房</h4>
      <el-menu :default-active="activeIndex2" class="el-menu-demo" mode="horizontal"  @select="handleSelect"
        background-color="#55aaff" text-color="#fff" active-text-color="yellow">
        <el-menu-item index="1" class="font-big">
          <router-link to="/First"><i class="el-icon-s-home" style="color: white;font-size: 25px;margin-top: -10px;"></i>首页</router-link>
        </el-menu-item>
        <el-menu-item index="2" class="font-big">
          <router-link to="/main/todayRecommend">房源推荐</router-link>
        </el-menu-item>
        <el-menu-item index="3" class="font-big">
          <router-link to="/main/adminLogin">后台登录</router-link>
        </el-menu-item>
        <el-menu-item index="4" class="font-big">
          <router-link to="/main/baidu/贵州工程应用技术学院">搜索地址</router-link>
        </el-menu-item>
<!--        <el-menu-item index="5" class="font-big">
            <el-popover
              placement="top-start"
              title="标题"
              width="200"
              trigger="manual"
              content="这是"
              v-model="visible">
              <el-button slot="reference" type="text" @click="visible!=visible">手动激活</el-button>
            </el-popover>
        </el-menu-item> -->
      </el-menu>
      <span style="color: red;margin-left: 30px;margin-top: 20px;font-size: smaller;"v-if="username=='no'">没有账号？去<router-link to="/main/Register" style="color: #55ffff;font-size: 15px;">注册</router-link></span>
      <!-- <span style="color: #e9f4ea;margin-top: 20px;margin-left: 35%; text-align: center;"><li style="font-size: 22px;color: white;" class="el-icon-mobile-phone"></li> Mobil:15117578945</span> -->
      <el-dropdown :hide-on-click="false" id="father">
        <span class="el-dropdown-link" v-if="username=='no'">
          <li class="el-icon-user"></li>
          <span style="color: red;font-size: smaller;">未登录？去</span>
          <router-link to="/main/userlogin" style="color: #06fff7;font-size: 15px;">登录</router-link>
          <i class="el-icon-caret-bottom"></i>
        </span>
        <span class="el-dropdown-link" v-else="username!='no'">
          <li class="el-icon-user"></li>
          {{username}}
          <i class="el-icon-caret-bottom"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <router-link to="/">返回首页</router-link>
            </el-dropdown-item>
            <el-dropdown-item>
              <router-link to="/main/mynew">个人中心</router-link>
            </el-dropdown-item>
            <el-dropdown-item>
              <el-button type="text" @click="logout()">退出登录</el-button>
            </el-dropdown-item>
            <el-dropdown-item>
              <router-link to="/main/userlogin">其他账号登录</router-link>
            </el-dropdown-item>
          </el-dropdown-menu>
        </span>
      </el-dropdown>
    </el-header>
    <el-container>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
    <!-- 底部 -->

    <div class="copyright">
      <hr style="border: 0.5px solid #dcdfe6;width: 600px;">
        <ul>
            <li>关于我们</li>
            <li>联系我们</li>
            <li>联系客服</li>
            <li>商家入驻</li>
            <li>营销中心</li>
            <li>手机尚品汇</li>
            <li>销售联盟</li>
            <li>尚品汇社区</li>
        </ul>
        <div class="address">地址：<router-link title="点击去这里" to="/main/baidu/贵州省毕节市七星关区碧阳街道贵州工程应用技术学院信息工程学院"  style="font-size: small;color: #1e42c5;">贵州省毕节市七星关区碧阳街道贵州工程应用技术学院信息工程学院</router-link></div>
        <div class="beian">联系电话：15117578945</div>
    </div>
  </el-container>
</template>

<script>
  export default {
    name: 'Main',
    // 引用vue reload方法
    inject: ['reload'],
   data() {
      return {
        visible: false,
        username:'',
        activeIndex: '1',
        activeIndex2: '1',
        mydata: [],
        dialogVisible: false,
        nowIndex: -100,
      };
    },
   mounted() {
     if(sessionStorage.getItem("username")){
       this.username=sessionStorage.getItem("username");
     }else{
       this.username="no"
     }
   },
    methods: {
      logout(){
        sessionStorage.clear();
        this.reload();
      },
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style scoped lang="less">
  #father {
    position: absolute;
    margin-top: 20px;
    margin-left: 87%;
    color: white;

  }

  #el{
    /* background-color: #03E9F4; */
    /* background-image: url(../../image/bk.jpg); */
    background-repeat: no-repeat;
    background-size: cover;
  }


  .el-aside {
    min-height: 18.75rem;
    width: 16rem;
    background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
    /* background-image: url(../../image/bg.png);
    background-size: cover; */
    border: 0.125rem solid #55aaff;
  }

  h2 {
    text-align: center;
  }

  #messageboardM {
    width: 16rem;
    margin-left: 1rem;
    border-radius: 10px;
    background-image: linear-gradient(to right, #7A88FF, #7AFFAF);
    box-shadow: 0 0 5px #074A99;
  }

  #messageboard {
    width: 14.625rem;
    margin-top: 1.875rem;
    margin-left: 0.625rem;
  }

  .el-header {
    /* width: 1600px; */
    /* margin-left: 15%; */
    background-color: #55aaff;
    border-radius: 5px;
    box-shadow: 2px 3px 4px gray;
    display: flex; //先设置div里的内容显示同一行
    justify-content: flex-start; //左右布局
    /* flex-wrap: wrap; //换行 */
    /* align-content: flex-start; //紧揍排列 */
    /* color: #333; */
   /* text-align: center;
    height: 100px; */
  }

  h4 {
    margin-top: 10px;
    width: 17.5rem;
    font-style: italic;
    letter-spacing: 5px;
    color: whitesmoke;
    font-size: 1.25rem;
  }

  .el-main {
    min-height: 630px;
    padding: 0rem;
    /*    background-image: url(../../image/bm.png);
    background-repeat: no-repeat;
    background-size: cover; */
    /* min-height: 45rem; */
    /* background-color: #E9EEF3; */
    color: #333;
    /*   line-height: 160px; */
  }

  .el-menu-demo {
    margin-left: 0rem;
  }

  a {
    text-decoration: none;
  }

  #p1 {
    width: 6.25rem;
  }

  .font-big:active {
    font-size: 110%;
  }

  .copyright {
     background-color: #7ec3ff;
      font-size: small;
      width: 100%;
      margin: 0 auto;
      text-align: center;
      height: 130px;
      border-radius: 10px;
      color: #333;
      ul {
          li {
               //先设置div里的内容显示同一行
              /* float: left; */
              display: inline-block;
              border-right: 1px solid #e4e4e4;
              padding: 0 20px;
              margin: 15px 0;
          }
      }
  }
</style>
