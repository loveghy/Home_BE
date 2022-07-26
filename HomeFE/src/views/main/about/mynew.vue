<template>
  <div class="my-container">
    <h2>我的信息</h2>
    <hr>
    <div>
      <ul>
        <el-row :gutter="32">
          <el-col :xs="24" :sm="24" :lg="12">
            <li>
              <label>姓名：</label>
              <span>{{userList.name}}</span>
            </li>
            <li>
              <label>账号：</label>
              <span>{{userList.username}}</span>
            </li>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="12">
            <li>
              <label>性别：</label>
              <span>{{userList.sex}}</span>
            </li>
            <li>
              <label>号码：</label>
              <span>{{userList.phone}}</span>
            </li>
          </el-col>
        </el-row>
      </ul>
      <div class="edit-button">
        <el-button type="primary" @click="dialogFormVisible=true">修改信息</el-button>
        <el-button type="primary" @click="dialogVisible=true" >修改密码</el-button>
      </div>
    </div>

    <el-dialog :visible.sync="dialogFormVisible" title="修改信息">
      <el-form ref="form" :model="form" label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name"/>
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="form.phone"/>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="form.sex">
            <el-radio label="男"/>
            <el-radio label="女"/>
          </el-radio-group>
        </el-form-item>
        <el-form-item>
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleUpdate">确 认</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog :visible.sync="dialogVisible" title="修改密码">
      <el-form ref="form" :model="form" label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="form.password"/>
        </el-form-item>
        <el-form-item label="确认密码" prop="fpassword">
          <el-input type="password" v-model="form.fpassword"/>
        </el-form-item>
        <el-form-item>
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="Update">确 认</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name:'mynew',
  inject: ['reload'],
   data(){
     return{
       dialogFormVisible:false,
       dialogVisible:false,
       form:{
         username:'',
         name:'',
         sex:'',
         phone:'',
         password:'',
         fpassword:''
       },
       userList:[],
     }
  },
  created() {
    this.handleUserList()
  },
 methods: {
   //获取个人信息
 	handleUserList() {
 		var id = sessionStorage.getItem("username");
 		console.log("这是");
 		console.log(id);
 		this.$http.get('http://localhost:8945/visitor/'+id).then(res => { //这是从本地请求的数据接口，
 			this.userList = res.body
      this.form.name=this.userList.name
      this.form.username=this.userList.username
      this.form.phone=this.userList.phone
      this.form.sex=this.userList.sex
      this.form.password=this.userList.password
       console.log(res.body);
 		})
 	},
  //点击更新
  handleUpdate() {
    let formData = new FormData();
    for (let key in this.form) {
      formData.append(key, this.form[key]);
      console.log(formData.get(key));
    }
    this.$confirm('确定修改当前数据?', '提示', {
      type: 'warning'
    }).then(() => {
      this.axios({
        method: "put",
        url: "http://localhost:8945/visitor",
        headers: {
          "Content-Type": "multipart/form-data"
        },
        /* withCredentials: true, */
        data: formData
      }).then((response) => {
        //在方法中调用
        this.reload() //用于刷新当前页面 */
        console.log(response);
        this.$message.success("修改成功");
      }).catch((error) => {
        console.log("更新失败的原因：", error)
      })
    }).catch((error) => {
      console.log("更新失败的原因：", error)
    })
  },

  //点击更新
  Update() {
    if(this.form.password!=this.form.fpassword){
      this.$message.error("两次密码不相同")
      return
    }
    let formData = new FormData();
    for (let key in this.form) {
      formData.append(key, this.form[key]);
      console.log(formData.get(key));
    }
    this.$confirm('确定修改当前数据?', '提示', {
      type: 'warning'
    }).then(() => {
      this.axios({
        method: "put",
        url: "http://localhost:8945/visitor",
        headers: {
          "Content-Type": "multipart/form-data"
        },
        /* withCredentials: true, */
        data: formData
      }).then((response) => {
        //在方法中调用
        this.reload() //用于刷新当前页面 */
        console.log(response);
        this.$message.success("修改成功");
      }).catch((error) => {
        console.log("更新失败的原因：", error)
      })
    }).catch((error) => {
      console.log("更新失败的原因：", error)
    })
  },
 }
 }

</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.my-container {
  padding: 32px;
  margin-bottom: 150px;
}
hr {
  border: 0.5px solid #dcdfe6;
}
h2 {
  font-family: Microsoft YaHei;
  text-align: center;
  font-weight: normal;
}
ul{
  margin-left: 20%;
  margin-top: 110px;
}
ul li {
  list-style: none;
  padding: 20px;
  font-size: 16px;
  color: #606266;
}
li > label:first-child {
  width: 100px;
  text-align: right;
  float: left;
}
li > span {
  margin-left: 15px;
}
.edit-button {
  margin-top: 100px;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}
</style>
