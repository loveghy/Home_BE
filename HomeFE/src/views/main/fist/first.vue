<template>
  <div class="first">
    <el-carousel :interval="8000" style="height: 430px;" arrow="always">
      <el-carousel-item v-for="item in imagebox" :key="item.id">
        <h3><img :src="item.idView" alt="图片不存在" class="image"></h3>
      </el-carousel-item>
    </el-carousel>
    <hr style="border: 0.5px solid #dcdfe6">
    <span style="margin-left: 35%; text-align: center; font-family: FZYaoti;font-size: 30px;color: #475669;">小型房产中介公司管理系统</span>
    <hr style="border: 0.5px solid #dcdfe6">
    <el-input type="text" @keyup.native.enter="btn" style="width: 65%; height:60px; margin-left: 15%;margin-top: 30px;"
      placeholder="如:出租/出售" v-model="search"></el-input>
    <el-button type="success" @click.prevent="btn">搜索</el-button>

    <el-row style="margin-right: 6%;margin-top: 30px;">
      <!-- slice(0,3)限定输出的数据 -->
      <el-col :span="5" v-for="(item, index) in searchData.slice(0,4)" :key="index" :offset="1">
        <el-card :body-style="{ padding: '0px' }" style="width: 300px;">
          <img style="height: 200px; margin: 5px;" :src="item.housePicture" class="image">
          <div style="padding: 14px;">
            <span v-if="item.houseTitle=='房屋出租'">标题：{{ item.houseTitle}}
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;租金/月：¥{{item.housePay}}</span>
            <span v-if="item.houseTitle=='房屋出售'">标题：{{ item.houseTitle}}
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;售价：¥{{item.housePay}}</span>
            <span>地址：{{ item.houseArea}}</span>
            <div class="bottom clearfix">
              <el-button type="text" class="button" @click="handle(item)">详情</el-button>
              <span><i class="el-icon-mobile-phone"></i>：{{ item.housePhone}}</span>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-dialog title="详情描述" :visible.sync="dialogVisible" width="40%">
      <span>
        <el-descriptions title="房屋信息">
          <el-descriptions-item label="房源编号">{{temp.houseId}}</el-descriptions-item>
          <el-descriptions-item label="户型">{{temp.houseContent}}</el-descriptions-item>
          <el-descriptions-item label="当前状态">{{temp.houseState}}</el-descriptions-item>
          <el-descriptions-item label="备注">
            <el-tag size="small">中介</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="联系地址">毕节市-七星关区-贵州工程应用技术学院</el-descriptions-item>
        </el-descriptions>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>


    <!-- 底部 -->
<!--    <div class="copyright">
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
        <div class="address">地址：贵州省毕节市七星关区碧阳街道贵州工程应用技术学院信息工程学院</div>
        <div class="beian">联系电话：15117578945</div>
    </div> -->
  </div>
</template>

<script>
  export default {
    name: 'First',
    data() {
      return {
        /*  currentDate: new Date(), */
        HouseList: [],
        searchData: [],
        temp: {},
        search: '',
        dialogVisible: false,
        imagebox: [{
            id: 0,
            idView: require('../../../../image/1.png')
          },
/*          {
            id: 1,
            idView: require('../../../../image/2.png')
          }, */
          {
            id: 2,
            idView: require('../../../../image/3.png')
          },
          {
            id: 3,
            idView: require('../../../../image/4.png')
          },
          {
            id: 4,
            idView: require('../../../../image/5.png')
          }
        ],
        // 浏览器宽度
        screenWidth: 0
      }
    },
    created() {
      this.handleHouseList()
    },
    methods: {
      handle(item) {
        this.temp = Object.assign({}, item) // copy obj
        this.dialogVisible = true

      },
      //点击搜索
      btn: function() {

        var search = this.search;
        if (search) {
          this.searchData = this.HouseList.filter(function(product) {
            console.log(product)
            return Object.keys(product).some(function(key) {
              console.log(key)
              return String(product[key]).toLowerCase().indexOf(search) > -1
            })
          })
        }

      },
      /*  changef(){
           for(let Key in this.HouseList){
            Key.housePicture=require(Key.housePicture);
          }
        }, */

      handleHouseList() {
        this.$http.get('http://localhost:8945/house/All').then(res => { //这是从本地请求的数据接口，
          /*   let host="http://localhost:8945/";
             	res.body=res.body.map((item,index)=>{
                 item.housePicture=host+item.housePicture;
                 return item
               }) */
          /* for(let Key in this.HouseList){
            Key.housePicture=require(Key.housePicture);
          } */
          this.HouseList = res.body
          console.log(this.HouseList)
          /* console.log(this.HouseList.housePicture) */
        })
      },
      setSize: function() {
        // 通过浏览器宽度(图片宽度)计算高度
        this.bannerHeight = 295 / 1920 * this.screenWidth;
      },
    },
    mounted() {
      // 首次加载时,需要调用一次
      this.screenWidth = window.innerWidth;
      this.setSize();
      // 窗口大小发生改变时,调用一次
      window.onresize = () => {
        this.screenWidth = window.innerWidth;
        this.setSize();
      }
    }
  }
</script>

<style scoped>
  /*   .el-card{
      height: 300px;
      width: 300px;
    } */

  /*  #el-row{
      margin-left: 15%;
    }
 */
  .first {
    min-height: 680px;
    /*     background: url(../../../../image/fb.jpg);
     background-repeat: no-repeat;
     background-size: cover; */
  }

  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  /* 上面为图片区 */
/*  .el-carousel {
  height: 470px;
  } */

  .el-carousel__item,
  h3 {
    color: #475669;
    font-size: 14px;
    opacity: 8.5;
    height: 430px;
    /* border: 0.125rem solid yellow; */
    background-repeat: repeat;
    margin: 0;
    /*    -webkit-transition: -webkit-transform 0.2s ease-in-out;
    transition: -webkit-transform 0.2s ease-in-out;
    transition: transform 0.2s ease-in-out;
    transition: transform 0.2s ease-in-out, -webkit-transform 0.2s ease-in-out; */
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #7693bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #9db9e6;
  }

  /deep/ .el-carousel__item.is-animating {
    transform: translateZ(0) !important;
  }

  /deep/ .el-carousel__indicator--horizontal .el-carousel__button {
    width: 10px;
    height: 10px;
    background: transparent;
    border: 1px solid #ffffff;
    border-radius: 50%;
    opacity: 0.5;
  }

  /deep/ .el-carousel__indicator--horizontal.is-active .el-carousel__button {
    width: 10px;
    height: 10px;
    background: #ffffff;
    border-radius: 50%;
    opacity: 1;
  }

  /*设置图片宽度和浏览器宽度一致*/
  img {
    width: 100%;
    height: 430px;
  }

/*  .copyright {
      width: 1200px;
      margin: 0 auto;
      text-align: center;
      line-height: 24px;

      ul {
          li {

              display: inline-block;
              border-right: 1px solid #e4e4e4;
              padding: 0 20px;
              margin: 15px 0;
          }
      }
  } */
</style>
