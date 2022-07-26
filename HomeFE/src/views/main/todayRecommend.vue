<template lang="html">
  <div class="mod-albums">
    <hr style="border: 0.5px solid #dceaff">
    <div class="filter-container" style="margin-bottom: 20px;margin-left: 23%;margin-top: 20px;border-radius: 10px;">
      <label style="font-style: normal;font-size: larger;">搜索栏&nbsp;&nbsp;&nbsp;&nbsp;</label>
      <el-date-picker v-model="search" type="month" value-format="yyyy-MM" placeholder="选择日期" />
      <el-select v-model="searcha" placeholder="房源状态" clearable class="filter-item" style="width: 110px">
        <el-option v-for="item in state" :key="item" :label="item" :value="item" />
      </el-select>
      <el-select v-model="searchb" placeholder="房屋类型" clearable class="filter-item" style="width: 110px">
        <el-option v-for="item in housetype" :key="item.type" :label="item.type" :value="item.type" />
      </el-select>
      <!--      <el-select v-model="searchb" placeholder="房屋地址" clearable class="filter-item" style="width: 110px">
        <el-option v-for="item in todayRecommend" :key="item.houseArea" :label="item.houseArea" :value="item.houseArea" />
      </el-select> -->
      <el-input type="text" placeholder="输入搜索内容" v-model="searchc" style="width: 200px;"></el-input>
    </div>
    <hr style="border: 0.5px solid #dceaff">
    <el-row style="background-color: #dceaff;padding-bottom: 40px;padding-right: 80px;border-radius: 10px;">
      <div style="position: fixed ; left: 18px; top: 135px;">
        <p>
          <li class="el-icon-loading" style="font-size: 25px;color: #0055ff;font-weight: 600;"></li>关注微信
        </p>
        <img width="100px" height="100px" src="../../../build/weixin.png" />
      </div>
      <!-- slice(0,3)限定输出的数据 -->
      <el-col :span="6" v-for="(item, index) in tableList.slice((currentPage-1)*pagesize,currentPage*pagesize)"
        :key="index" :offset="2">
        <el-card :body-style="{ padding: '0px' }" style="width: 400px; margin-top: 25px;">
          <router-link :to="{path:'/main/mainDetail/'+item.houseId}" class="rout" style="height: 270px;display: block;">
            <img style="height: 270px;  margin: 4px;display: block;" :src="item.housePicture" title="点击查看该房屋详细介绍"
              class="image">
          </router-link>
          <div style="padding: 14px;">
            <span v-if="item.houseTitle=='房屋出租'">标题：<nobr style="color: #ff5500;font-weight: 550;">{{ item.houseTitle}}
              </nobr>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<nobr
                style="color: #0f1a63;font-weight: 400;font-style: normal;font-size: 17px;">
                租金：<em>{{(item.housePay/10000).toFixed(2)}}</em>&nbsp;万元/月</nobr></span>
            <span v-if="item.houseTitle=='房屋出售'">标题：<nobr style="color: #ff5500;font-weight: 550;">{{ item.houseTitle}}
              </nobr>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<nobr
                style="color: #0f1a63;font-weight: 400;font-style: normal;font-size: 17px;">
                售价：<em>{{(item.housePay/10000).toFixed(2)}}</em>&nbsp;万元</nobr></span>
            <p>地址：<nobr title="到这里去">
                <li class="el-icon-location"></li>
                <router-link :to="{path:'/main/baidu/'+item.houseArea}" style="font-size: small;color: #1e42c5;">
                  {{ item.houseArea}}
                </router-link>
              </nobr>
            </p>
            <hr style="border: 0.5px solid #3a4655">
            <div class="bottom clearfix">
              <el-button type="text" class="button" style="font-size: large;">
                <router-link :to="{path:'/main/mainDetail/'+item.houseId}" style="text-decoration:none;color: #0055ff;"
                  title="点击到房源详情页面">详情</router-link>
              </el-button>
              <!-- <el-button type="text" class="button" @click="handle(item)" style="font-size: large;">详情</el-button> -->
              <el-button type="text" class="button" @click="say(item)">
                <li class="el-icon-s-comment" style="font-size: large;">
                  <nobr style="font-size: large;">评论</nobr>
                </li>&nbsp;&nbsp;&nbsp;&nbsp;
              </el-button>
              <span><i class="el-icon-mobile-phone" style="font-size: larger;color: #0055ff;"></i>
                <nobr style="color: #ff5500;font-weight: 550;">{{ item.housePhone}}</nobr>
              </span>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <hr style="border: 0.5px solid #dceaff">
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
      :page-sizes="[6,12, 18, 24, 30]" :page-size="pagesize" layout="total, sizes, prev, pager, next, jumper"
      :total="tableList.length">
      <!-- //这是显示总共有多少数据， -->
      <!-- //显示当前行的条数 -->
    </el-pagination>
    <hr style="border: 0.5px solid #dceaff">
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
        <el-button @click="dialogVisible = false" size="small">取 消</el-button>
        <el-button type="primary" size="small" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 留言评论 -->
    <el-dialog :visible.sync="dialogsay" width="30%">
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
          <el-button type="primary" @click="dialogsay = false" size="small">关闭</el-button>
        </el-form>
        <ul style="list-style-type:none;margin-left: -40px;">
          <p style="color: #ff3eef;font-family: FZYaoti;font-size: 15px;">
            关于“编号{{message.houseId}}”的房源留言回复（共{{sayMessage.length}}条留言 回复）：</p>
          <li v-for="item in sayMessage.slice(0,m)" style="border: 1px solid #999;background-color: #eee;font-family: 方正姚体_GBK;">
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
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "todayRecommend",
    // 引用vue reload方法
    inject: ['reload'],
    data() {
      return {
        m:2,//设置显示数据
        x:0,//标记是否展开
        tf: sessionStorage.getItem("username"), //存储登录状态
        currentPage: 1, //初始页
        pagesize: 6, //    每页的数据
        todayRecommend: [],
        housetype: [],
        //留言回显
        sayMessage: [],
        /*        searchtime: '',
                searchstate: '',
                searchtype:'', */
        message: {
          messageRemarks: '',
          houseId: '',
          userId: ''
        }, //留言提交
        search: '',
        searcha: '',
        searchb: '',
        searchc: '',
        state: ['未租', '未售', '已租', '已售'],
        houseRen: [],
        houseSale: [],
        houseTime: [],
        temp: {},
        dialogVisible: false,
        dialogsay: false
      }
    },
    props: {
      title: {
        type: String,
        default: "房源榜单"
      },
      type: {
        type: String,
        default: "1"
      }
    },
    computed: {
      // 模糊搜索
      tableList: function() {
        var search = null;
        if (this.search && search == null) {
          search = this.search;
        }
        if (this.searcha && search == null) {
          search = this.searcha;
        }
        if (this.searchb && search == null) {
          search = this.searchb;
        }
        if (this.searchc && search == null) {
          search = this.searchc;
        }
        if (search) {
          // filter() 方法创建一个新的数组，新数组中的元素是通过检查指定数组中符合条件的所有元素。
          // 注意： filter() 不会对空数组进行检测。
          // 注意： filter() 不会改变原始数组。
          return this.todayRecommend.filter(data => {
            // some() 方法用于检测数组中的元素是否满足指定条件;
            // some() 方法会依次执行数组的每个元素：
            // 如果有一个元素满足条件，则表达式返回true , 剩余的元素不会再执行检测;
            // 如果没有满足条件的元素，则返回false。
            // 注意： some() 不会对空数组进行检测。
            // 注意： some() 不会改变原始数组。
            return Object.keys(data).some(key => {
              // indexOf() 返回某个指定的字符在某个字符串中首次出现的位置，如果没有找到就返回-1；
              // 该方法对大小写敏感！所以之前需要toLowerCase()方法将所有查询到内容变为小写。
              return String(data[key]).toLowerCase().indexOf(search) > -1
            })
          })
        }
        search = null
        this.search = null
        this.searcha = null
        this.searchb = null
        this.searchc = null
        return this.todayRecommend;
      }
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
      say(item) {
        if (this.tf == null) {
          this.$message.error("你没有该权限，请先去登录")
        } else {
          this.dialogsay = true
          this.message.houseId = item.houseId
          this.message.userId = this.tf
        }
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
      },
      submitmessage() {
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
      /* 留言删除操作 */
      deleteData(id) {
        //this.tableData.splice(index, 1)
        this.$confirm('确定删除该留言?', '提示', {
          type: 'warning'
        }).then(() => {
          /* var params = new URLSearchParams()
          params.append("id", row.stockId) */
          this.axios.delete('http://localhost:8945/message/' + id).then((response) => {
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
            console.log("删除的结果：", response)
            //在方法中调用
            /* this.reload() */ //用于刷新当前页面 */
            if (response.data == true) {
              this.$message.success("删除成功")
            } else {
              this.$message.error("删除失败的原因：" + response.data.message)
            }
          }).catch((error) => {
            console.log("删除失败的原因：", error)
          })
        }).catch((error) => {
          console.log("删除失败的原因：", error)
        })
      },
      //详情
      handle(item) {
        this.temp = Object.assign({}, item) // copy obj
        this.dialogVisible = true
      },
      // 初始页currentPage、初始每页数据数pagesize和数据data
      handleSizeChange: function(size) {
        this.pagesize = size;
        console.log(this.pagesize) //每页下拉显示数据
      },
      handleCurrentChange: function(currentPage) {
        this.currentPage = currentPage;
        console.log(this.currentPage) //点击第几页
      }
    },
    mounted() {
      this.$http.get('http://localhost:8945/housetype/All').then(res => {
        this.housetype = res.body;
      });
      this.$http.get('http://localhost:8945/house/All/').then(res => { //这是从本地请求的数据接口，
        this.todayRecommend = res.body
        /*   console.log(this.todayRecommend[0].houseTitle) */
      });
    }
  }
</script>



<style scoped>
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

  /* 上面图片测试 */
  .el-pagination {
    text-align: center;
    background-color: #dceaff;
    border: 1px solid #dceaff;
    /* width: 500px; */
    border-radius: 10px;
    color: #000000;
    margin: auto;
    margin-top: 10px;
    margin-bottom: 30px;
  }

  .mod-albums {
    background-color: #fff;
    padding: 10px 17px;
  }

  .mod-albums .gallery {
    overflow: hidden;
    margin: 0 -5px;
  }

  .mod-albums .gallery .card {
    width: 25%;
    float: left;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    padding: 0 5px 10px;
  }

  .mod-albums .gallery .card .album {
    position: relative;
  }

  .mod-albums .gallery .card img {
    width: 100%;
    height: auto;
    border: 1px solid #eee;
  }

  .mod-albums .gallery .card .name {
    font-size: 12px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    margin-top: 4px;
    line-height: 14px;
    max-height: 28px;
    margin-bottom: 2px;
  }

  em {
    font-size: 20px;
    font-weight: 600;
    color: #ff5500;
  }

  /* 动态 */
  .rout {
    height: 270px;
  }
  .rout img {
    display: block;
    transition: all 1.5s ease-out;
    transform: scale(1);
  }

  .rout img {
    border: 0;
  }

  .rout img:hover {
    transform: scale(1.05);
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
