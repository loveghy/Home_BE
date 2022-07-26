<template>
  <div style="background-color: white; height: 100%;">
  <div class="dashboard-editor-container">
    <div class=" clearfix">
      <el-row :gutter="40" class="panel-group">
        <el-col :xs="24" :sm="24" :lg="8" class="info-container">
          <span style="font-size:20px">欢迎您经理</span><br>
          <span class="display_name">{{userList.staffName}}</span>
        </el-col>
        <el-col :xs="24" :sm="12" :lg="6" class="card-panel-col">
          <div class="card-panel">
           <div class="card-panel-icon-wrapper icon-money">
             <li class="el-icon-shopping-bag-1" style="font-size: 25px;"></li>
              <!-- <svg-icon icon-class="money" class-name="card-panel-icon" /> -->
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">收入额</div>
              ¥{{sales.toFixed(2)}}
             <!-- <count-to :start-val="0" :end-val="sales" :duration="3200" class="card-panel-num"/> -->
            </div>
          </div>
        </el-col>
        <el-col :xs="24" :sm="12" :lg="6" class="card-panel-col">
          <div class="card-panel">
           <div class="card-panel-icon-wrapper icon-shopping">
             <li class="el-icon-shopping-cart-full" style="font-size: 25px;"></li>
             <!-- <svg-icon icon-class="shopping" class-name="card-panel-icon" /> -->
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">交易单量</div>
              {{saleNum}}
             <!-- <count-to :start-val="0" :end-val="saleNum" :duration="1600" class="card-panel-num"/> -->
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
    <div>
      <img :src="emptyGif" class="emptyGif">
    </div>
  </div>
  </div>
</template>

<script>
export default {
  name: 'mainAfist',
  data() {
  	return {
      emptyGif: 'https://wpimg.wallstcn.com/0e03b7da-db9e-4819-ba10-9016ddfdaed3',
      sales: 0,
      saleNum: 0,
  		userList: [],
  	}

  },
  created() {
  	this.handleUserList()
    this.handlerOrder()
  },
  methods: {
  	handleUserList() {
  		var id = sessionStorage.getItem("isLogin");
  		console.log("这是");
  		console.log(id);
  		this.$http.get('http://localhost:8945/staff/'+id).then(res => { //这是从本地请求的数据接口，
  			this.userList = res.body
  		})
  	},
    handlerOrder(){
      this.$http.get('http://localhost:8945/record/All').then(res => { //这是从本地请求的数据接口，
      	console.log(res.data)
        res.data.map((item, index) => {
        if(item.recordState=="已支付"){
          this.saleNum++;
          this.sales+=item.recordPay;
        }
        })
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .emptyGif {
    display: block;
    width: 43%;
    margin: 0 auto;
  }

  .info-container {
    position: relative;
    margin-left: 30px;
    height: 150px;
    line-height: 200px;
    .display_name {
      font-size: 48px;
      line-height: 48px;
      color: #212121;
      position: absolute;
      top: 25px;
    }
  }

  .dashboard-editor-container {
    background-color: #e3e3e3;
    // min-height: 100vh;
    padding: 50px 60px 0px;
  }
  .panel-group {
    margin-top: 18px;
    .card-panel-col{
      margin-bottom: 32px;
    }
    .card-panel {
      height: 108px;
      cursor: pointer;
      font-size: 12px;
      position: relative;
      overflow: hidden;
      color: #666;
      background: #fff;
      box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
      border-color: rgba(0, 0, 0, .05);
      &:hover {
        .card-panel-icon-wrapper {
          color: #fff;
        }
        .icon-people {
          background: #40c9c6;
        }
        .icon-message {
          background: #36a3f7;
        }
        .icon-money {
          background: #f4516c;
        }
        .icon-shopping {
          background: #34bfa3
        }
      }
      .icon-people {
        color: #40c9c6;
      }
      .icon-message {
        color: #36a3f7;
      }
      .icon-money {
        color: #f4516c;
      }
      .icon-shopping {
        color: #34bfa3
      }
      .card-panel-icon-wrapper {
        float: left;
        margin: 14px 0 0 14px;
        padding: 16px;
        transition: all 0.38s ease-out;
        border-radius: 6px;
      }
      .card-panel-icon {
        float: left;
        font-size: 48px;
      }
      .card-panel-description {
        float: right;
        font-weight: bold;
        margin: 26px;
        margin-left: 0px;
        .card-panel-text {
          line-height: 18px;
          color: rgba(0, 0, 0, 0.45);
          font-size: 16px;
          margin-bottom: 12px;
        }
        .card-panel-num {
          font-size: 20px;
        }
      }
    }
  }
</style>

<style>
</style>
