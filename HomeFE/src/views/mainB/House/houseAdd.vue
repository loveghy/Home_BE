<template>
  <div id="paper">
    <el-dialog title="房屋类型" :visible.sync="dialogFormVisible">
      <el-form :ref="temp" :model="temp" label-position="left" label-width="100px"
        style="width: 400px; margin-left:50px;">
        <el-form-item label="类型" prop="password">
          <el-input v-model="temp.type" type="text"></el-input>
          <el-button type="success" size="small" @click="submit()">提交</el-button>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>

    <el-header>
      <h3 style="font-family: Microsoft YaHei">房源发布</h3>
      <hr style="border: 0.5px solid #e6d2af">
    </el-header>
    <el-form ref="form" :model="form" label-width="100px" class="el-form-a">
      <el-row :gutter="24">
        <el-col :xs="24" :sm="24" :lg="12" style="width: 400px; margin-left:50px;">
          <el-form-item label="房源编号" prop="houseId">
            <el-input type="text" placeholder="请输入房源编号" v-model="form.houseId"></el-input>
          </el-form-item>
          <el-form-item label="发布人编号" prop="houseContact">
            <el-input v-model="form.houseContact" readonly="readonly"></el-input>
          </el-form-item>
          <el-form-item label="房东姓名" prop="houserName">
            <el-input type="text" placeholder="请输入房东姓名" v-model="form.houserName"></el-input>
          </el-form-item>
          <el-form-item label="发布时间" prop="starttime">
            <el-date-picker v-model="form.starttime" type="date" value-format="yyyy-MM-dd" placeholder="选择日期"
              style="width: 100%;" />
          </el-form-item>
          <el-form-item label="房源标题" prop="houseTitle">
            <el-select v-model="form.houseTitle" placeholder="请选择标题">
              <el-option v-for="item in optiones" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="房源状态" prop="houseState">
            <el-select v-model="form.houseState" placeholder="请选择房源状态">
              <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :lg="12" style="width: 400px; margin-left:50px;">
          <el-form-item label="租金/售价" prop="housePay">
            <el-input type="text" placeholder="请输入租金/售价" v-model="form.housePay"></el-input>
          </el-form-item>
          <el-form-item label="地址" prop="houseArea">
            <el-input type="textarea" placeholder="请输入地址" v-model="form.houseArea"></el-input>
          </el-form-item>
          <el-form-item label="详情描述" prop="houseContent">
            <el-select v-model="form.houseContent" placeholder="请选择房源类型">
              <el-option v-for="item in housetype" :key="item.type" :label="item.type" :value="item.type">
              </el-option>
            </el-select>
            <el-button type="success" size="small" id="el-button" @click="dialogFormVisible = true">添加房屋类型</el-button>
          </el-form-item>
          <el-form-item label="联系电话" prop="housePhone">
            <el-input type="text" v-model="form.housePhone" readonly="readonly">
            </el-input>
          </el-form-item>
          <el-form-item label="图片">
            <input type="file" @change="getFile($event)">
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-form-item>
          <el-button type="primary" @click="submitForm($event)">立即提交</el-button>
          <el-button type="warning" @click="resetForm('form')">重置</el-button>
        </el-form-item>
      </el-row>
    </el-form>

  </div>
</template>

<script>
  export default {
    name: 'houseBAdd',
    // 引用vue reload方法
    inject: ['reload'],
    data() {
      return {
        //表单变量
        form: {
          houseId: '',
          houseTitle: '',
          houseArea: '',
          housePay: '',
          houseContent: '',
          housePicture: '',
          houseState: '',
          houseContact: '',
          housePhone: '',
          houserName: '',
          starttime: '',
          file: '',
        },
        housetype: [],
        temp:{
            type:''
        },
        dialogFormVisible: false,
        options: [{
          value: '未租',
          label: '未租'
        }, {
          value: '未售',
          label: '未售'
        }],
        optiones: [{
          value: '房屋出租',
          label: '房屋出租'
        }, {
          value: '房屋出售',
          label: '房屋出售'
        }],
        staffList: [],
      }
    },
    created() {
      this.handleUserList(),
      this.handleHousetype()
    },
    methods: {
      getFile(event) {
        this.form.file = event.target.files[0];
        console.log(this.form.file);
      },
      handleHousetype(){
        this.$http.get('http://localhost:8945/housetype/All').then(res =>{
          this.housetype=res.body;
          console.log(this.housetype);
        })
      },
      handleUserList() {
        var id = sessionStorage.getItem("isLogin");
        this.form.houseContact = id;
        console.log("这是");
        console.log(id);
        this.$http.get('http://localhost:8945/staff/' + id).then(res => { //这是从本地请求的数据接口，
          this.staffList = res.body;
          console.log(res.data);
          this.form.housePhone = this.staffList.staffPhone;
        })
      },
      submitForm(event) {
        event.preventDefault();
        let formData = new FormData();
        for (let key in this.form) {
          formData.append(key, this.form[key]);
          console.log(formData.get(key));
        }
        if (this.form.houseId=="" || this.form.houseTitle == "" || this.form.houseArea == "" || this.form.housePay == "" || this.form.houseContent == "" ||
          this.form.houseState == "" || this.form.houseContact == "" || this.form.housePhone == "" || this.form.houserName == "" ||
           this.form.starttime == "" ) {
          this.$message.warning("请把信息填写完整！！")
        } else
        this.axios({
          method: "post",
          url: "http://localhost:8945/house/upload",
          headers: {
            "Content-Type": "multipart/form-data"
          },
          /* withCredentials: true, */
          data: formData
        }).then((response) => {
          console.log(response);
          this.$message.success("发布成功")
        }).catch((error) => {
          this.$message.error("发布失败")
          console.log("添加失败的原因：", error)
        })
      },
      submit(){
        let tempDate = new FormData();
        for (let key in this.temp) {
          tempDate.append(key, this.temp[key]);
        }
        this.axios({
          method: "post",
          url: "http://localhost:8945/housetype",
          headers: {
            "Content-Type": "multipart/form-data",
          },
          /* withCredentials: true, */
          data: tempDate
        }).then((response) => {
          console.log(response);
          this.$message.success("成功添加")
        }).catch((error) => {
          this.$message.error("添加失败")
          console.log("添加失败的原因：", error)
        })
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style scoped>
</style>
