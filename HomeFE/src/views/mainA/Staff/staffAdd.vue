<template>
  <div id="paper">
    <el-header>
      <h3 style="font-family: Microsoft YaHei">添加员工</h3>
      <hr style="border: 0.5px solid #e6ab8d">
    </el-header>
    <el-form ref="form" :model="form" :rules="rules" label-width="100px" class="el-form-a">
      <el-row :gutter="24">
        <el-col :xs="24" :sm="24" :lg="12" style="width: 400px; margin-left:50px;">
          <el-form-item label="员工编号" prop="staffId">
            <el-input type="text" placeholder="请输入员工编号" :maxlength="11" show-word-limit clearable
              v-model="form.staffId"></el-input>
          </el-form-item>
          <el-form-item label="员工姓名" prop="staffName">
            <el-input type="text" placeholder="请输入员工姓名" v-model="form.staffName"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="staffSex">
            <el-radio-group v-model="form.staffSex">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="电话号码" prop="staffPhone">
            <el-input type="text" placeholder="请输入电话号码" v-model="form.staffPhone"></el-input>
          </el-form-item>
          <el-form-item label="入职日期" prop="staffTime">
            <el-date-picker v-model="form.staffTime" type="date" value-format="yyyy-MM-dd" placeholder="选择日期"
              style="width: 100%;" />
          </el-form-item>
          <el-form-item label="在职状态" prop="staffState">
            <el-radio-group v-model="form.staffState">
              <el-radio label="在职"></el-radio>
              <el-radio label="离职"></el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :lg="12" style="width: 400px; margin-left:50px;">
          <el-form-item label="角色" prop="staffRoles">
            <el-select v-model="form.staffRoles" placeholder="请选择">
              <el-option v-for="item in statusOptions" :key="item.key" :label="item.display_name" :value="item.key" />
            </el-select>
          </el-form-item>
          <el-form-item label="工资/月" prop="staffWages">
            <el-input type="text" placeholder="请输入底薪" v-model="form.staffWages"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="text" placeholder="请输入密码,长度在 6 到 12个字符" v-model="form.password"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="passwordConfirm">
            <el-input type="text" v-model="form.passwordConfirm" auto-complete="off" placeholder="确认密码">
            </el-input>
          </el-form-item>
          <el-form-item label="备注" prop="staffRemarks">
            <el-input type="textarea" v-model="form.staffRemarks"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-form-item>
          <el-button type="primary" @click="submit()">立即提交</el-button>
          <el-button type="primary" @click="resetForm('form')">重置</el-button>
        </el-form-item>
      </el-row>
    </el-form>

  </div>
</template>

<script>
  const statusOptions = [{
      key: '1',
      display_name: '经理'
    },
    {
      key: '0',
      display_name: '普通员工'
    }
  ]

  export default {
    name: 'staffAdd',
    data() {
      return {
        //表单变量
        form: {
          staffId: '',
          staffName: '',
          staffSex: '',
          staffPhone: '',
          staffWages: '',
          staffRemarks: '',
          staffState: '',
          staffRoles: '0',
          staffTime: '',
          password: '',
          passwordConfirm: ''
        },
        statusOptions,
        rules: {
          password: [{
              required: true,
              message: '密码不能为空',
              trigger: 'blur'
            },
            {
              min: 6,
              max: 12,
              message: '长度在 6 到 12个字符',
              trigger: 'blur'
            }
          ],
          passwordConfirm: [{
            required: true,
            message: '密码不能为空',
            trigger: 'blur'
          }],
          staffPhone: [{
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

    methods: {
      submit() {
        if (this.form.password !== this.form.passwordConfirm) {
          this.$message({
            message: '两次输入的密码不一致',
            type: 'error'
          })
          return
        }
        if (this.registerForm.password.length < 6 || this.registerForm.password.length > 12) {
          this.$message.warning("密码在6~12位之间")
          return
        }
        let formData = new FormData();
        for (let key in this.form) {
          formData.append(key, this.form[key]);
          console.log(formData.get(key));
        }
        if (this.form.staffId == "" || this.form.staffName == "" || this.form.staffPhone == "" || this.form
          .staffRemarks == "" || this.form.staffRoles == "" ||
          this.form.staffSex == "" || this.form.staffState == "" || this.form.staffTime == "") {
          this.$message.warning("请把信息填写完整！！")
        } else
          this.axios({
            method: "post",
            url: "http://localhost:8945/staff",
            headers: {
              "Content-Type": "multipart/form-data"
            },
            /* withCredentials: true, */
            data: formData
          }).then((response) => {
            console.log(response);
            this.$message.success("添加成功")
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
