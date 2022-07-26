<template>
  <div>
    <el-row :gutter="10">
      <hr style="border: 0.5px solid #dceaff">
      <div style="position: fixed ; left: 18px; top: 135px;">
      <p><li class="el-icon-loading" style="font-size: 25px;color: #0055ff;font-weight: 600;"></li>关注微信</p>
      <img width="100px" height="100px" src="../../../build/weixin.png"/>
      </div>
      <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1" class="el-col1"><div class="grid-content bg-purple"><img style="height: 100%;width: 100%;" :src="house.housePicture" title="小型房产中介公司房源"></div></el-col>
      <el-col :xs="4" :sm="6" :md="8" :lg="9" :xl="11" class="el-col2">
      <div class="grid-content bg-purple-light">
      <span>
          </br>
          <span style="margin-left: 2%;"><nobr>《编号{{house.houseId}}》房源详细情况：</nobr></span>
          </br>
          </br>
          <dd style="margin-left: 8%;">
             <em><nobr>编号：</nobr>{{house.houseId}}</em></br>
             </br>
              <em><nobr>类型：</nobr>{{house.houseTitle}}</em></br>
              </br>
              <em><nobr>户型：</nobr>{{house.houseContent}}</em></br>
              </br>
              <em><nobr>当前状态：</nobr>{{house.houseState}}</em></br>
              </br>
              <em v-if="house.houseTitle=='房屋出售'"><nobr>价格：</nobr>{{(house.housePay/10000).toFixed(2)}}万元</em>
              <em v-if="house.houseTitle=='房屋出租'"><nobr>价格：</nobr>{{house.housePay.toFixed(2)}}元/月</em></br>
              </br>
              <em><nobr>联系电话：</nobr>{{house.housePhone}}</em></br>
              </br>
              <em><nobr>地址：</nobr>{{house.houseArea}}</em></br>
              </br>
          </dd>
      </span>
      </div>
      </el-col>
    </el-row>
    <el-row style="margin-top: 10px;margin-bottom: 30px;">
      <hr style="border: 0.5px solid #dceaff">
      <p v-if="videoSrc!=null"><nobr>视频介绍</nobr></p>
      <p v-if="videoSrc==null"><nobr>暂无该视频资源</nobr></p>
      <hr style="border: 0.5px solid #dceaff">
      <video :preload="preload" style="margin-top: 5px;background-color: #000000;" :height="height" :width="width" align="center"
      :poster="videoImg"  :controls="controls" :autoplay="autoplay" :src="video.url" alt="还未上传视屏">
        您的浏览器不支持此种视频格式
      </video>
      <hr style="border: 0.5px solid #dceaff">
    </el-row>
    <el-row style="margin-top: 10px;margin-bottom: 120px;">
      <hr style="border: 0.5px solid #dceaff">
  <span>
    <el-form class="demo-form-inline" :model="message">
      <h3>评论留言</h3>
      <el-form-item>
        <el-input v-model="message.messageRemarks" style="width: 400px;font-size: large;" type="textarea"
          placeholder="内容"></el-input>
      </el-form-item>
      <!--        <el-form-item>
      <el-button type="primary" @click="onSubmit">查询</el-button>
    </el-form-item> -->
      <el-button type="success" size="small" @click="submitmessage()">发表留言</el-button>
    </el-form>
    <ul style="list-style-type:none;margin-left: -40px;">
      <p style="color: #ff3eef;font-family: FZYaoti;font-size: 15px;width: 55%;">
        关于“编号{{message.houseId}}”的房源留言回复（共{{sayMessage.length}}条留言 回复）：</p>
      <li v-for="item in sayMessage.slice(0,m)" style="border: 1px solid #999;background-color: #eee;font-family: 方正姚体_GBK;width: 55%;">
        <P class="badge" style="color: #0055ff;">"我"：&nbsp;&nbsp;<span
            style="font-size: smaller;color: #000000;">{{item.messageRemarks}}</span>；
          <el-button type="text" @click="deleteData(item.messageId)" size="small"
            style="margin-left: 50px;color: #55ffff;">删除</el-button>
        </p>
        <p style="color: #0055ff;" v-if="item.remessage!=null">"中介公司"&nbsp;回复：&nbsp;&nbsp;<span
            style="font-size: smaller;color: #000000;">{{item.remessage}}</span>；</p>
        <p style="color: #0055ff;" v-if="item.remessage==null">"中介公司"&nbsp;回复：&nbsp;&nbsp;<span
            style="font-size: smaller;color: #ff5500;">暂无回复</span>；</p>
      </li>
      <div class="zkmore">
      	<p class="sm_open" id="sm_open" @click="sm_open()" v-if="m==2">展开更多  <i class="el-icon-arrow-down"></i></p>
      	<p class="sm_close" id="sm_close" @click="sm_close()" v-if="x==1">收&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;起<i class="el-icon-arrow-up"></i></p>
      </div>
    </ul>
  </span>
      <hr style="border: 0.5px solid #dceaff">
    </el-row>
  </div>
</template>

<script>
  export default {
    name:'mainDetail',
    props: ['id'],
    data(){
      return {
        m:2,//设置显示数据
        x:0,//标记是否展开
        tf: sessionStorage.getItem("username"), //存储登录状态
        house:[],
        //视屏播放
        video: [],
        videoSrc: '',
        videoImg: '',
        playStatus: '',
        muteStatus: '',
        isMute: true,
        isPlay: false,
        width: '90%', // 设置视频播放器的显示宽度（以像素为单位）
        height: '600', // 设置视频播放器的显示高度（以像素为单位）
        preload: 'auto', //  建议浏览器是否应在<video>加载元素后立即开始下载视频数据。
        controls: true, // 确定播放器是否具有用户可以与之交互的控件。没有控件，启动视频播放的唯一方法是使用autoplay属性或通过Player API。
        autoplay: '',
        //留言回显
        sayMessage: [],
        //留言区
        message: {
          messageRemarks: '',
          houseId: '',
          userId: ''
        }, //留言提交
      }
    },
    created() {
      // console.log(this.id),
      this.handleHouse()
      this.handleVideo()
      this.axios.post('http://localhost:8945/message/insert', {
        houseId: this.id,
        userId: this.tf,
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        } //跨域
      }).then(res => {
        console.log(res.data)
        this.sayMessage = res.data
        console.log(res.data)
      });
    },
    methods: {
      //设置展开的数据
      sm_open(){
        this.m=this.message.length;
        this.x=1;
      },
      //设置收起
      sm_close(){
        this.m=2;
        this.x=0;
      },
      submitmessage() {
        if(this.tf==null){
          this.$message.warning("请先去登录")
          return ;
        }
        this.message.houseId=this.id;
        this.message.userId=this.tf;
        let formData = new FormData();
        for (let key in this.message) {
          formData.append(key, this.message[key]);
          console.log(formData.get(key));
        }

        this.axios({
          method: "post",
          url: "http://localhost:8945/message",
          data: formData
        }).then((response) => {
          this.axios.post('http://localhost:8945/message/insert', {
            houseId: this.message.houseId,
            userId: this.tf,
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            } //跨域
          }).then(res => {
            console.log(res.data)
            this.sayMessage = res.data
            console.log(res.data)
          });
          console.log(response);
          this.$message.success("欢迎下次评论");
          /* this.reload() */
          /* this.dialogsay=false; */
        }).catch((error) => {
          this.$message.error("留言失败")
          console.log("添加失败的原因：", error)
        })
      },

      handleHouse() {
        var id = this.id;
        this.$http.get('http://localhost:8945/house/' + id).then(res => { //这是从本地请求的数据接口，
          this.house = res.body
          this.videoImg=this.house.housePicture
        })
      },
      handleVideo() {
        var id = this.id;
        console.log("这是");
        console.log(id);
        this.$http.get('http://localhost:8945/video/' + id).then(res => { //这是从本地请求的数据接口，
          this.video = res.body
          this.videoSrc = this.video.url
        })
      }
  },
  }
</script>

<style scoped>
   .el-row{
     top: 50px;
     left: 10%;
     width: 90%;
   }
    .el-col {
      border-radius: 6px;
    }
    .el-col1{
       width: 30%;
    }
    .el-col2{
      margin-left: 1%;
      width: 60%;
    }
    .bg-purple {
      background: #ff0000;
      height: 400px;
    }
    .bg-purple-light {
      background: #fffecd;
      height: 400px;
    }
    .grid-content {
      border-radius: 4px;
      min-height: 36px;
    }
    em{
      font-size: 19px;
      font-weight: 600;
      color: #ff5500;
    }
    nobr{
      font-size: 19px;
      font-weight: 600;
      color: #612000;
    }
    /* 展开收起 */
    .zkmore{
    	border-top: 1px solid #e4e7ed;
    	padding-top: 5px;
    }
    .zkmore p.sm_open,.zkmore p.sm_close{
    	position:relative;
    	cursor: pointer;
    }
</style>
