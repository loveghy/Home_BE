<template>
  <div style="margin-bottom: 50px;">
    <hr style="border: 0.5px solid #dceaff">
    <h3 style="text-align: center;" v-if="video.id!=null">编号为{{video.id}}的房屋视频介绍</h3>
    <h3 style="text-align: center;color: red;" v-if="video.id==null">编号为{{id}}的房屋无该资源，店家未上传</h3>
   <hr style="border: 0.5px solid #dceaff">
    <video :preload="preload" style="margin-top: 5px;background-color: #000000;" :height="height" :width="width" align="center"
      :controls="controls" :autoplay="autoplay" :src="video.url" alt="还未上传视屏">
      您的浏览器不支持此种视频格式
    </video>
    <hr style="border: 0.5px solid #dcdfe6;width: 100%;">
  </div>

</template>

<script>
  export default {
    name: 'detail',
    props: ['id'],
    data() {
      return {
        video: [],
        videoSrc: '../../../../static/upload/video/100007.mp4',
        videoImg: '../../../../static/logo.jpg',
        playStatus: '',
        muteStatus: '',
        isMute: true,
        isPlay: false,
        width: '100%', // 设置视频播放器的显示宽度（以像素为单位）
        height: '650', // 设置视频播放器的显示高度（以像素为单位）
        preload: 'auto', //  建议浏览器是否应在<video>加载元素后立即开始下载视频数据。
        controls: true, // 确定播放器是否具有用户可以与之交互的控件。没有控件，启动视频播放的唯一方法是使用autoplay属性或通过Player API。
        autoplay: ''
      }
      // 自动播放属性,muted:静音播放
      // autoplay: 'muted',
    },
    created() {
      // console.log(this.id),
      this.handleVideo()
    },
    methods: {
      handleVideo() {
        var id = this.id;
        console.log("这是");
        console.log(id);
        this.$http.get('http://localhost:8945/video/' + id).then(res => { //这是从本地请求的数据接口，
          this.video = res.body
          this.videoSrc = this.video.url
        })
      }
    }
  }
</script>

<style>
</style>
