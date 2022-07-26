<template>
  <div class="register-container">
    <!-- 注册内容 -->
    <div class="register">
      <h3>用户登录
        <span class="go">没有账号？去
          <router-link class="login" to="/main/Register">注册</router-link>
        </span>
      </h3>
      <el-form :model="DataForm" :rules="rules" ref="DataForm" label-width="100px">
        <el-form-item label="账号" prop="username">
          <el-input v-model="DataForm.username" placeholder="请输入用户名(字母或数字)" :maxlength="6" show-word-limit clearable
            prefix-icon='el-icon-user-solid' :style="{width: '50%'}"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="DataForm.password" placeholder="请输入密码" show-password :maxlength="16" :style="{width: '50%'}"></el-input>
        </el-form-item>
      </el-form>
      <!--           <div class="controls">
                <input name="m1" type="checkbox" :checked="agree">
                <span>同意协议并注册《房产中介管理系统用户协议》</span>
            </div> -->
      <div class="btn">
        <Vcode :show="isShow" @success="success" @close="close" />
        <button @click="submit">登录</button>
      </div>
    </div>

  </div>
</template>

<script>
  import Vcode from "vue-puzzle-vcode";
  export default {
    name: 'userlogin',
    inject: ['reload'],
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输出密码'));
        } else {
          if (this.DataForm.ralePassword !== '') {
            this.$refs.DataForm.validateField('ralePassword');
          }
          callback();
        }
      };
      return {
        isShow: false, // 验证码模态框是否出现
        changePwdDialog: false, //批改明码弹框
        agree: true,
        DataForm: {
          password: '',
          username: '',
        },
        rules: {
          username: [{
            required: true,
            message: '请输入用户名',
            trigger: 'blur'
          }],
          password: [{
              required: true,
              message: '请输入密码',
              trigger: 'blur'
            },
            {
              min: 6,
              max: 16,
              message: '长度在 6 到 16 个字符',
              trigger: 'blur'
            },
            {
              validator: validatePass,
              trigger: 'blur'
            }
          ]
        }
      }
    },
    components: {
      Vcode
    },
    methods: {
      submit() {
        if(this.DataForm.username==""||this.DataForm.password==""){
          this.$message.error("请填写完整")
          return
        }
        this.isShow = true;
      },
      // 用户通过了验证
      success(msg) {
        this.isShow = false; // 通过验证后，需要手动隐藏模态框
        let tempDate = new FormData();
        for (let key in this.DataForm) {
          tempDate.append(key, this.DataForm[key]);
        }
        var that = this
        this.axios({
          method:"post",
          url:"http://localhost:8945/visitor/login",
          data:tempDate
        }).then(res => {
          console.log("asda"+res.data)
          if(res.data=="0"){
            this.$message.error("账号不存在")
          }else if(res.data=="1"){
            this.$message.error("密码错误")
          }else if(res.data=="2"&&sessionStorage.getItem("username")==null){
            this.reload();
            sessionStorage.setItem("username",this.DataForm.username);
            that.$router.push({name:'Main',params:{name:this.DataForm.username}});
            that.$message.success("欢迎用户"+sessionStorage.getItem("username")+"登录")
          }else if(sessionStorage.getItem("username")==this.DataForm.username){
            that.$message.error("该账号已登录,请选择其他账号哦")
          }
        })
      },
      // 用户点击遮罩层，应该关闭模态框
      close() {
        this.isShow = false;
      }
    }

  }
</script>

<style lang="less" scoped>
  .register-container {
    .register {
      width: 550px;
      height: 300px;
      border: 1px solid rgb(223, 223, 223);
      margin: 100px auto;

      h3 {
        background: #ececec;
        margin: 0;
        padding: 6px 15px;
        color: #333;
        border-bottom: 1px solid #dfdfdf;
        font-size: 20.04px;
        line-height: 30.06px;

        span {
          font-size: 14px;
          float: right;

          a {
            color: #e1251b;
          }
        }
      }

      div:nth-of-type(1) {
        margin-top: 15px;
      }

      .content {
        margin-bottom: 18px;
        position: relative;

        label {
          text-align: right;
          display: inline-block;
        }

        img {
          vertical-align: sub;
        }
      }

      .controls {
        text-align: center;
        position: relative;

        input {
          vertical-align: middle;
        }

        .error-msg {
          position: absolute;
          top: 100%;
          left: 495px;
          color: red;
        }
      }

      .btn {
        text-align: center;
        margin: 17px 0 0 55px;

        button {
          outline: none;
          width: 270px;
          height: 36px;
          background: #e1251b;
          color: #fff !important;
          display: inline-block;
          font-size: 16px;
        }
      }
    }

    .copyright {
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
    }
  }
</style>
