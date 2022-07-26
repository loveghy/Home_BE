<template>
  <div id="paper">
    <el-header>
      <h3 style="font-family: Microsoft YaHei">客户创建</h3>
      <hr style="border: 0.5px solid #ffb786">
    </el-header>
    <el-form ref="form" :model="form" :rules="rules" label-width="100px" class="el-form-a">
      <el-row :gutter="24">
        <el-col :xs="24" :sm="24" :lg="12" style="width: 400px; margin-left:50px;">
          <el-form-item label="客户身份证号" prop="customId">
            <el-input type="text" placeholder="请输入客户编号" onkeyup="this.value=this.value.replace(/[^\X0-9]/g, '')" v-model="form.customId"></el-input>
          </el-form-item>
          <el-form-item label="客户姓名" prop="customName">
            <el-input type="text" placeholder="请输入姓名" v-model="form.customName"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="customSex">
            <el-radio-group v-model="form.customSex">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="电话号码" prop="customPhone">
            <el-input type="number" placeholder="请输入电话号码" v-model="form.customPhone"></el-input>
          </el-form-item>
          <el-form-item label="备注" prop="customRemarks">
            <el-input type="textarea" v-model="form.customRemarks"></el-input>
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
  export default {
    name: 'staffAdd',
    data() {
      var checkIdentitytionId = (rule, value, callback) => {
        if (!value) {
          return callback(new Error("身份证号不能为空"));
        }
        if (!/(^\d{15}$)|(^\d{17}(\d|X|x)$)/.test(value)) {
          callback(new Error("你输入的身份证长度或格式错误"));
        }
        //身份证城市
        var aCity = {
          11: "北京",
          12: "天津",
          13: "河北",
          14: "山西",
          15: "内蒙古",
          21: "辽宁",
          22: "吉林",
          23: "黑龙江",
          31: "上海",
          32: "江苏",
          33: "浙江",
          34: "安徽",
          35: "福建",
          36: "江西",
          37: "山东",
          41: "河南",
          42: "湖北",
          43: "湖南",
          44: "广东",
          45: "广西",
          46: "海南",
          50: "重庆",
          51: "四川",
          52: "贵州",
          53: "云南",
          54: "西藏",
          61: "陕西",
          62: "甘肃",
          63: "青海",
          64: "宁夏",
          65: "新疆",
          71: "台湾",
          81: "香港",
          82: "澳门",
          91: "国外"
        };
        if (!aCity[parseInt(value.substr(0, 2))]) {
          callback(new Error("你的身份证地区非法"));
        }
        // 出生日期验证
        var sBirthday = (
            value.substr(6, 4) +
            "-" +
            Number(value.substr(10, 2)) +
            "-" +
            Number(value.substr(12, 2))
          ).replace(/-/g, "/"),
          d = new Date(sBirthday);
        if (
          sBirthday !=
          d.getFullYear() + "/" + (d.getMonth() + 1) + "/" + d.getDate()
        ) {
          callback(new Error("身份证上的出生日期非法"));
        }

        // 身份证号码校验
        var sum = 0,
          weights = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2],
          codes = "10X98765432";
        for (var i = 0; i < value.length - 1; i++) {
          sum += value[i] * weights[i];
        }
        var last = codes[sum % 11]; //计算出来的最后一位身份证号码
        if (value[value.length - 1] != last) {
          callback(new Error("你输入的身份证号非法"));
        }
        callback();
      };
      return {
        //表单变量
        form: {
          customId: '',
          customName: '',
          customSex: '',
          customRemarks: '',
          customPhone: '',
        },
        rules:{
          customPhone: [{
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
          ],
          customId: [{
            validator: checkIdentitytionId,
            trigger: "blur"
          }]
        }
      }
    },

    methods: {
      submit() {
        let formData = new FormData();
        for (let key in this.form) {
          formData.append(key, this.form[key]);
          console.log(formData.get(key));
        }
        if (this.form.customId == "" || this.form.customName == "" || this.form.customPhone == "" || this.form
          .customRemarks == "" || this.form.customSex == "") {
          this.$message.warning("请把信息填写完整！！")
        } else
          this.axios({
            method: "post",
            url: "http://localhost:8945/custom",
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
