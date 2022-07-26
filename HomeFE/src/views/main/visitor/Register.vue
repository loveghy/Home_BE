<template>
  <div class="register-container">
    <!-- 注册内容 -->
    <div class="register">
      <h3>注册新用户
        <span class="go">已有账号，去
          <router-link class="login" to="/main/userlogin">登陆</router-link>
        </span>
      </h3>
      <el-form :model="DataForm" :rules="rules" ref="DataForm" label-width="100px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="DataForm.username" placeholder="请输入用户名(字母或数字)" :maxlength="6" show-word-limit clearable
            prefix-icon='el-icon-user-solid' :style="{width: '50%'}"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="DataForm.name" placeholder="请输入姓名" :style="{width: '50%'}"></el-input>
        </el-form-item>
        <el-form-item>
          <el-radio v-model="DataForm.sex" label="男">男</el-radio>
          <el-radio v-model="DataForm.sex" label="女">女</el-radio>
        </el-form-item>
        <el-form-item label="手机" prop="phone">
          <el-input v-model="DataForm.phone" placeholder="请输入手机,11位数字" show-word-limit :maxlength="11" clearable
            :style="{width: '50%'}" prefix-icon='el-icon-mobile-phone'></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="password">
          <el-input v-model="DataForm.password" placeholder="请输入密码" show-password :maxlength="16"
            :style="{width: '50%'}"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="ralePassword">
          <el-input v-model="DataForm.ralePassword" show-password :maxlength="16" :style="{width: '50%'}"></el-input>
        </el-form-item>
      </el-form>
      <div class="controls">
        <input name="m1" type="checkbox" :checked="agree">
        <span>同意协议《房产中介公司管理系统用户协议》</span>
      </div>
      <div class="btn">
        <Vcode :show="isShow" @success="success" @close="close" />
        <button @click="submit">完成注册</button>
      </div>
    </div>

  </div>
</template>

<script>
  import Vcode from "vue-puzzle-vcode";
  export default {
    name: 'Register',
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
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输出密码'));
        } else if (value !== this.DataForm.password) {
          callback(new Error('两次输出密码不统一!'));
        } else {
          callback();
        }
      };
      return {
        isShow: false, // 验证码模态框是否出现
        changePwdDialog: false, //批改明码弹框
        agree:true,
        DataForm: {
          name: '',
          sex: '男',
          password: '',
          ralePassword: '',
          username: '',
          phone: '',
        },
        rules: {
          phone: [{
            required: true,
            validator: this.checkPhone,
            trigger: 'blur',
          }],
          username: [{
            required: true,
            message: '请输入用户名',
            trigger: 'blur'
          }],
          name: [{
            required: true,
            message: '请输入姓名',
            trigger: 'blur'
          }],
          password: [{
              required: true,
              message: '请输出明码',
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
          ],
          ralePassword: [{
              required: true,
              message: '请确认明码',
              trigger: 'blur'
            },
            {
              min: 6,
              max: 16,
              message: '长度在 6 到 16 个字符',
              trigger: 'blur'
            },
            {
              validator: validatePass2,
              trigger: 'blur',
              required: true
            }
          ],
          phone: [{
              required: true,
              message: "请输入手机号码",
              trigger: "blur"
            },
            {
              validator: function(rule, value, callback) {
                if (/^1[34578]\d{9}$/.test(value) == false) {
                  callback(new Error("手机号格式错误"));
                } else {
                  callback();
                }
              },
              trigger: "blur"
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
        if (this.DataForm.username == null || this.DataForm.name == null || this.DataForm.password == null || this
          .DataForm.phone == "" || this.DataForm.password == "") {
          this.$message.error("信息未填写完整")
          return
        }

        if (this.DataForm.phone.length != 11) {
          this.$message.warning("号码格式不对,请输入十一位")
          return
        }

        if (this.DataForm.password.length < 6 || this.DataForm.password.length > 12) {
          this.$message.warning("密码在6~12位之间")
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
          method: "post",
          url: "http://localhost:8945/visitor",
          data: tempDate
        }).then(res => {
          /* console.log("asda"+res.data.username) */
          if (res.data.username != null) {
            that.$message.success("~~恭喜你成为新用户")
          } else {
            that.$message.error("用户已存在")
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
      height: 600px;
      border: 1px solid rgb(223, 223, 223);
      margin: 30px auto;

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
