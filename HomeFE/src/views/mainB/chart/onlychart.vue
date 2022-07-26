<template>
  <el-container style="width: 100%;background-color: #000128;min-height: 730px;">
 <div id="main" style="width: 900px;height:500px;margin: auto;"></div>
  </el-container>
</template>

<script>
  export default {
    name: 'Staffchart',
    data() {
      return {
        houselist: [],
        len: 0,
        total: 0,
      }
    },
    mounted() {
      this.echartsInit()
    },
    methods: {
      //初始化echarts
      echartsInit() {
        this.$http.get('http://localhost:8945/house/All/').then(res => { //这是从本地请求的数据接口，
          this.houselist = res.body
          /* console.log(this.houselist.length) */
          let per = 0;
          let norental = 0;
          let nosale = 0;
          let rental = 0;
          let sale = 0;
          let total = 0;
          /* 			 for(let i in this.multipleSelection){
          				  total +=this.multipleSelection[i].bookPrice*this.multipleSelection[i].waibuyCount;
          			  } */
          this.houselist.map((item, index) => {
            if (item.houseContact == sessionStorage.getItem('isLogin')) {
              per++
              if (item.houseState == '未租')
                norental++
              if (item.houseState == '未售')
                nosale++
              if (item.houseState == '已租')
                rental++
              if (item.houseState == '已售')
                sale++
              if (item.houseState == '已租' || item.houseState == '已售') {
                this.total += item.housePay
              }
            }
          })
          console.log(total)
          //柱形图
          //因为初始化echarts 的时候，需要指定的容器 id='main'
          var echarts = require('echarts');
          var myChart = echarts.init(document.getElementById('main'),'dark');
          // 指定图表的配置项和数据
          var option = {
            title: {
              text: '个人房源报表'
            },
            tooltip: {},
            legend: {
              data: ['房源数量'],
                  itemStyle: {
                    color: '#a90000'
                  }
            },
            xAxis: {
              data: ["房源总量", "未售", "已售", "未租", "已租"]
            },
            yAxis: {},
            series: [{
              name: '房源数量',
              type: 'bar',
              data: [{
                  value: per,
                  itemStyle: {
                    color: '#a90000'
                  }
                },
                {
                  value: nosale,
                  itemStyle: {
                    color: '#a90000'
                  }
                },
                {
                  value: sale,
                  itemStyle: {
                    color: '#a90000'
                  }
                },
                {
                  value: norental,
                  itemStyle: {
                    color: '#a90000'
                  }
                },
                {
                  value: rental,
                  itemStyle: {
                    color: '#a90000'
                  }
                }
              ]
            }]
          };

          // 使用刚指定的配置项和数据显示图表。
          myChart.setOption(option);
        })
      },

    }
  }
</script>

<style>
</style>
