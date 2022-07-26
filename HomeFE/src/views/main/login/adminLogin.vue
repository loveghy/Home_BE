<template>
  <div class="container">
    <div class="loginBox">
      <h2>login</h2>
    	<el-form ref="form" :model="form">
          <div class="item" prop="name">
            <input type="text"  v-model="form.name"  required></input>
            <label for="">userName</label>
          </div>
          <div class="item" prop="password">
    			<input :type="passwordType"  v-model="form.password" name="password" auto-complete="on" required></input>
          <label for="">password</label>
    			<span class="show-pwd" @click="showPwd">
    				<i class="el-icon-view"></i>
    			</span>
          </div>

        <el-form-item >
             <el-radio v-model="form.staffRoles" label="1" >经理</el-radio>
             <el-radio v-model="form.staffRoles" label="0" >普通员工</el-radio>
        </el-form-item>
        <el-form-item >

         <!-- prevent解决提交多次问题 -->
         <Vcode :show="isShow" @success="success" @close="close" />
    			<button class="btn" @click.prevent="submit">SUBMIT
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          </button>
    			<button class="btn" @click="resetForm('form')">RESET
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          </button>
          <router-link to="/First">
            <button class="btn">REFIRST
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            </button>
          </router-link>
          </el-form-item>
    	</el-form>
    </div>
  </div>
</template>

<script>
  import Vcode from "vue-puzzle-vcode";
	export default {
		name: 'admin',
    inject: ['reload'],
		data() {
			return {
        isShow: false,
				form: {
					name: '',
					password: '',
          staffRoles:'0'
				},
				passwordType: 'password',
			}
		},
   mounted() {
      //触发事件禁止回退
        history.pushState(null, null, document.URL);

         window.addEventListener("popstate",function(e) {

              history.pushState(null, null, document.URL);

          }, false);
    },
    components: {
      Vcode,
    },
		methods: {
      submit() {
        if(this.form.name==''||this.form.password==''){
          this.$message.warning('用户或密码不能为空')
          return
        }
        this.isShow = true;
      },
      // 用户通过了验证
      success(msg) {
        this.isShow = false; // 通过验证后，需要手动隐藏模态框
        const that = this;
        var userName = this.form.name;
        var userPassword = this.form.password;
        var userType = this.form.staffRoles;
        this.axios.post('http://localhost:8945/staff/login', {
        	staffId: userName,
        	password: userPassword,
          staffRoles: userType,
        	headers: {
        		'Content-Type': 'application/x-www-form-urlencoded'
        	} //跨域
        }).then(function(dat) {
        	console.log(Response);
        	if (dat.data == '0'){
            that.$message.error('用户不存在')
          }
        	else if (dat.data == '1'){
            that.$message.error("登录失败，账号或密码错误")
          }
          else if(dat.data == '-2'){
              that.$message.error("角色选择错误")
          }
        	else if (dat.data == '2') {
        		//当前窗体跳转
        		window.sessionStorage.setItem('isLogin',userName); /*记录设置状态 */
            that.$message.success("~~~欢迎来到经理权限界面");
            window.sessionStorage.setItem('Type',userType);
        	 that.$store.dispatch("asyncUpdateUser",{name:userName});
           that.$router.push({name:'MainA',params:{name:userName}});
        		/* window.location.href = 'http://localhost:8099/maina' */
        	}
          else if(dat.data == '3'){
            window.sessionStorage.setItem('isLogin',userName); /*记录设置状态 */
            that.$message.success("~~~欢迎来到普通员工权限界面");
            window.sessionStorage.setItem('Type',userType);
            that.$store.dispatch("asyncUpdateUser",{name:userName});/* 随登录的改变而改变当前登录信息 */
            that.$router.push({name:'MainB',params:{name:userName}});
          }
        }).catch((error) => {
        	console.log("失败的原因：", error)
        })
      },
      // 用户点击遮罩层，应该关闭模态框
      close() {
        this.isShow = false;
      },
			showPwd() {
			  if (this.passwordType === 'password') {
			    this.passwordType = ''
			  } else {
			    this.passwordType = 'password'
			  }
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			}
		}
	}
</script>
<style scoped>
  * {
  	margin: 0;
  	padding: 0;
  }

  a {
  	text-decoration: none;
  }
   .container{
      height: 100vh;
/*     min-height: 50rem; */
      background: linear-gradient(#55aaff, #55ffff,#55aaff,#55ffff);
/*     background-image: url(../../../../image/login.jpg);

     background-repeat: no-repeat;
     background-size: cover; */
    /* background-image: linear-gradient(to right, #fbc2eb, #a6c1ee); */
     display: flex;
     justify-content: center;
     align-items: center;
     font-size: 16px;
     color: #03e9f4;
   }
  input,
  button {
  	background: transparent;
  	border: 0;
  	outline: none;
  }

  .loginBox {
  	width: 450px;
  	height: 400px;
  	background-color: rgba(33, 33, 120, 0.6);
  	margin: 100px auto;
  	border-radius: 10px;
  	box-shadow: 0 15px 25px 0 rgba(0, 0, 0, .6);
  	padding: 40px;
  	box-sizing: border-box;
  }
	/* scoped标记style只在当前页生效 */
/* 	.el-form-a {
		width: 500px;
		margin: 150px auto;
		border: 1px solid grey;
		padding: 20px;
		border-radius: 10px;
		box-shadow: 0 0 20px #808080;
	} */
   h2 {
   	text-align: center;
   	color: aliceblue;
   	margin-bottom: 30px;
   	font-family: 'Courier New', Courier, monospace;
   }

   .item {
   	height: 45px;
   	border-bottom: 1px solid #fff;
   	margin-bottom: 40px;
   	position: relative;
   }

   .item input {
   	width: 100%;
   	height: 100%;
   	color: #fff;
   	padding-top: 20px;
   	box-sizing: border-box;
   }

   .item input:focus+label,
   .item input:valid+label {
   	top: 0px;
   	font-size: 2px;
   }

   .item label {
   	position: absolute;
   	left: 0;
   	top: 12px;
   	transition: all 0.5s linear;
   }

   .btn {
   	padding: 10px 20px;
   	margin-top: 30px;
   	color: #03e9f4;
   	position: relative;
   	overflow: hidden;
   	text-transform: uppercase;
   	letter-spacing: 2px;
   	left: 7%;
   }

   .btn:hover {
   	border-radius: 5px;
   	color: #fff;
   	background: #03e9f4;
   	box-shadow: 0 0 5px 0 #03e9f4,
   		0 0 25px 0 #03e9f4,
   		0 0 50px 0 #03e9f4,
   		0 0 100px 0 #03e9f4;
   	transition: all 1s linear;
   }

   .btn>span {
   	position: absolute;
   }

   .btn>span:nth-child(1) {
   	width: 100%;
   	height: 2px;
   	background: -webkit-linear-gradient(left, transparent, #03e9f4);
   	left: -100%;
   	top: 0px;
   	animation: line1 1s linear infinite;
   }

   @keyframes line1 {

   	50%,
   	100% {
   		left: 100%;
   	}
   }

   .btn>span:nth-child(2) {
   	width: 2px;
   	height: 100%;
   	background: -webkit-linear-gradient(top, transparent, #03e9f4);
   	right: 0px;
   	top: -100%;
   	animation: line2 1s 0.25s linear infinite;
   }

   @keyframes line2 {

   	50%,
   	100% {
   		top: 100%;
   	}
   }

   .btn>span:nth-child(3) {
   	width: 100%;
   	height: 2px;
   	background: -webkit-linear-gradient(left, #03e9f4, transparent);
   	left: 100%;
   	bottom: 0px;
   	animation: line3 1s 0.75s linear infinite;
   }

   @keyframes line3 {

   	50%,
   	100% {
   		left: -100%;
   	}
   }

   .btn>span:nth-child(4) {
   	width: 2px;
   	height: 100%;
   	background: -webkit-linear-gradient(top, transparent, #03e9f4);
   	left: 0px;
   	top: 100%;
   	animation: line4 1s 1s linear infinite;
   }

   @keyframes line4 {

   	50%,
   	100% {
   		top: -100%;
   	}
   }

   .el-radio{
     margin-left:1.5625rem;
     color: white;
   }

	.show-pwd {
		position: absolute;
		/* background-color: #333333; */
		right: 7px;
		top: 23px;
		font-size: 20px;
		color: $dark_gray;
		cursor: pointer;
		user-select: none;
	}
</style>
