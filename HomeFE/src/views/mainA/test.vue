<template>
  <div id="box" ref="box">
    <div class="marquee-box" ref="marquee" @mouseover="menter" @mouseleave="mleave">
      <p ref="cmdlist" id="pWidth">
      <ul class="list">
        <li v-for="(item, index) in ulList" :key="item.id" :class="!index && play ? 'toUp' : ''">
          {{ item.msg }}
        </li>
      </ul>
      </p>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'test',
    data() {
      return {
        ulList: [{
            msg: '这是第一条信息'
          },
          {
            msg: '这是第二条信息'
          },
          {
            msg: '这是第三条信息'
          },
          {
            msg: '这是第四条信息'
          },
          {
            msg: '这是第五条信息'
          },
          {
            msg: '这是第六条信息'
          },
          {
            msg: '这是第七条信息'
          },
          {
            msg: '这是第八条信息'
          },
          {
            msg: '这是第九条信息'
          },
          {
            msg: '这是第十条信息'
          }
        ],
        play: false,
        value: 0,
        timer: '', //计时器
        pwidth: 0, //公告文本的宽度
        windowWidth: document.documentElement.clientWidth, // 设备屏幕的宽度
      }
    },

    mounted() {
      let element = this.$refs.cmdlist;
      this.pwidth = document.defaultView.getComputedStyle(element, '').width.split('px');
      this.timer = setInterval(this.clickCommend, 20);
    },
    watch: {
      value(newValue, oldValue) {
        let allWidth = parseInt(this.windowWidth) + parseInt(this.pwidth[0]);
        if (newValue <= -allWidth) {
          this.$refs.cmdlist.style.marginLeft = this.windowWidth + "px";
          this.value = 0;
        }
      },
    },
    methods: {
      clickCommend(e) {
        let _this = this;
        this.$nextTick(() => {
          this.value -= 1;
          this.$refs.cmdlist.style.marginLeft = _this.windowWidth + this.value + "px";
        });
      },
      menter() {
        clearInterval(this.timer);
      },
      mleave() {
        this.timer = setInterval(this.clickCommend, 20);
      },
    },
    beforeDestroy() {
      clearInterval(this.timer);
    }
  }
</script>
<style scoped>
  #box {
    width: 100%;
    height: 50px;
    margin-top: 50px;
    position: relative;
  }

  .marquee-box {
    position: relative;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: #f8f8f8;
  }

  #pWidth {
    width: 100%;
    height: 50px;
    padding: 0;
    margin: 0;
    line-height: 50px;
    display: block;
    word-break: keep-all;
    white-space: nowrap;
    overflow: hidden;
    font-family: 微软雅黑;
    fontSize: 14px;
    background-color: #f8f8f8
  }

  ::-webkit-scrollbar {
    width: 0 !important;
  }

  ::-webkit-scrollbar {
    width: 0 !important;
    height: 0;
  }
</style>
