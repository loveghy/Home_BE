<template>
  <div class="personalCenter" style="background-color: white;">
    <el-container>
      <el-header>
        <h4 style="font-family: Microsoft YaHei">~~欢迎“{{tableData.staffName}}”进入销售员界面</h4>
      </el-header>
      <el-container>
        <el-aside width="400px">
          <el-col :span="12">
            <div class="sub-title"></div>
            <div class="demo-basic--circle">
              <div class="img">
                <el-avatar :size="100" src="../../static/logo.png" align="center"></el-avatar>
              </div>
            </div>
          </el-col>
          <div class="block">
            <span>用户ID: {{form.staffId}}</span>
          </div>
          <el-form ref="form" :model="form">
            <el-input v-model="form.staffId" type="text" readonly="readonly"></el-input>
            <el-input v-model="form.staffName" type="text"  readonly="readonly"></el-input>
            <el-input v-model="form.staffPhone" type="text"  readonly="readonly"></el-input>
            <el-input v-model="form.staffState" type="text" readonly="readonly"></el-input>
            <el-input v-model="form.staffWages" type="text"  readonly="readonly"></el-input>
            <el-input v-model="form.staffSex" type="text"  readonly="readonly"></el-input>
            <el-input v-model="form.staffTime" type="text" readonly="readonly" ></el-input>
          </el-form>
          <el-button-group style="float: right; padding: 3px 0" type="text">
            <!-- <el-button type="primary" size="medium" round>保存</el-button> -->
          </el-button-group>
        </el-aside>
        <el-container>
          <el-main>
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span style="float: left"><b>个人备注</b></span>
                <el-button-group style="float: right; padding: 3px 0" type="text">

                </el-button-group>
              </div>
              <div class="text item">
                   {{tableData.staffRemarks}}
              </div>
            </el-card>
            <el-card class="box-card">
              <div>
                <span style="float: left" shadow="hover"><b>公司说明</b></span>
                <br />
                <br />
                <span>公司名称：小型房产中介公司</span>
                <el-divider></el-divider>
                <span>法定人：宫洪彦</span>
                <el-divider></el-divider>
                <span>联系电话：15117578945</span>
                <el-divider></el-divider>
                <span>描述：该系统界面用于员工个人工作管理</span>
              </div>
            </el-card>
          </el-main>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>
<script>
  export default {
    name: 'mainBfist',
    data() {
      return {
        form: {
          staffId: '',
          staffName: '',
          staffSex: '',
          staffPhone: '',
          staffWages: '',
          staffRemarks: '',
          staffState: '',
          staffRoles: '0',
          staffTime: '',
          password: '',
        },
        circleUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
        sizeList: ['large'],
        tableData:[],
      }
    },
    created() {
      this.handleUserList()
    },
    methods: {
      handleUserList() {
      	var id = sessionStorage.getItem("isLogin");
      	console.log("这是");
      	console.log(id);
      	this.$http.get('http://localhost:8945/staff/'+id).then(res => { //这是从本地请求的数据接口，
      		this.tableData = res.body,
          this.form.staffId=this.tableData.staffId,
          this.form.staffName=this.tableData.staffName,
          this.form.staffPhone=this.tableData.staffPhone,
          this.form.staffState=this.tableData.staffState,
          this.form.staffWages=this.tableData.staffWages,
          this.form.staffSex=this.tableData.staffSex,
          this.form.staffTime=this.tableData.staffTime
      	})
      },

      handleEdit(index, row) {
        console.log(index, row)
      },
      handleDelete(index, row) {
        console.log(index, row)
      },
    },
  }
</script>
<style scoped>
    .el-header{
      display: flex; //先设置div里的内容显示同一行
      justify-content: flex-start; //左右布局
    line-height: 30px;
  }
   h3{
     margin-top: 20px;
   }
  .el-aside {
    border-radius: 30px;
    line-height: 44px;
    text-align: center;
    background-color: #d3dce6;
  }

  .el-main {
    line-height: 36px;
    /* text-align: center; */
    background-color: #e9eef3;
  }

  .demo-basic--circle {
    align: 'center';
    margin-top: 40px;
    margin-left: 150px;
  }

  .block {
    margin-top: 160px;
    font-weight: bold;
  }

  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: '';
  }

  .clearfix:after {
    clear: both;
  }
  a {
    text-decoration: none;
    color: greenyellow;
  }
  .box-card {
    width: 1000px;
    border-radius: 30px;
  }
</style>
