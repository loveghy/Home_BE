<template>
  <el-container style="width: 100%;background-color: #000128;min-height: 730px;">
<h3 class="input"><el-date-picker v-model="search" type="year" value-format="yyyy" placeholder="选择日期"/></h3>
<div id="main" style="width: 900px;height:500px;margin: auto;"></div>
  </el-container>
</template>

<script>
  export default {
    name:'finance',
    data(){
      return {
        search:'',
      }
    },
    mounted() {
      var echarts = require('echarts');

      var chartDom = document.getElementById('main');
      var myChart = echarts.init(chartDom, 'dark');
      var option;

      option = {
        title: {
          text: '公司月收入走向图'
        },
        xAxis: {
          type: 'category',
          data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月','八月', '九月', '十月', '十一月', '十二月']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [150000, 230000, 224000, 218000, 135000, 147000, 260000,200000,150000, 230000, 224000, 218000],
            type: 'line'
          }
        ]
      };

      option && myChart.setOption(option);
    }
  }
</script>

<style scoped>
  .input{
    position: absolute;
    width: 30%;
    margin-top: 50px;
    margin-left: 330px;
  }
</style>
