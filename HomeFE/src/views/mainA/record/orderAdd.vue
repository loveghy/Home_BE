<template>
  <div id="paper">
    <el-header>
      <h3 style="font-family: Microsoft YaHei">创建订单</h3>
      <hr style="border: 0.5px solid #ffb786">
    </el-header>
    <el-form ref="form" :model="form" :rules="rules" label-width="100px" class="el-form-a">
      <el-row :gutter="24">
        <el-col :xs="24" :sm="24" :lg="12" style="width: 400px; margin-left:50px;">
          <el-form-item label="账单类型" prop="recordType">
            <el-radio-group v-model="form.recordType">
              <el-radio label="租房"></el-radio>
              <el-radio label="售房"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="房源编号" prop="houseId">
            <el-input type="text" placeholder="房源编号" v-model="form.houseId"></el-input>
          </el-form-item>
          <el-form-item label="客户编号" prop="recordContact">
            <el-input type="text" placeholder="客户编号" onkeyup="this.value=this.value.replace(/[^\X0-9]/g, '')" v-model="form.recordContact"></el-input>
          </el-form-item>
          <el-form-item label="创建时间" prop="recordStart">
            <el-date-picker v-model="form.recordStart" type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
              placeholder="选择创建日期" style="width: 100%;" />
          </el-form-item>
          <el-form-item label="结束时间" prop="recordEnd">
            <el-date-picker v-model="form.recordEnd" type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
              placeholder="选择结束日期" style="width: 100%;" />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :lg="12" style="width: 400px; margin-left:50px;">
          <el-form-item label="押金" prop="deposit" v-if="form.recordType=='租房'">
            <el-input type="number" placeholder="押金" v-model="form.deposit"></el-input>
          </el-form-item>
          <!--          <el-form-item label="中介费" prop="agencyFee">
            <el-input type="text" placeholder="中介费" v-model="form.agencyFee"></el-input>
          </el-form-item> -->
          <el-form-item label="个人编号" prop="agencyFee">
            <el-input type="text" placeholder="个人编号" v-model="form.staffId" readonly="readonly"></el-input>
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
    name: 'orderAdd',
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
          recordId: '', //账单编号
          houseId: '', //房源编号
          recordState: '未支付',
          recordPay: '', //租金总价或房价
          recordStart: '',
          recordEnd: '',
          recordContact: '', //交易人
          deposit: 0, //押金
          agencyFee: 0, //中介费
          staffId: sessionStorage.getItem("isLogin"), //员工编号
          recordType: '租房' //账单类型
        },
        rules: {
          recordContact: [{
            validator: checkIdentitytionId,
            trigger: "blur"
          }]
        }
      }
    },
    methods: {
      // 获取当前日期的方法
      getProjectNum() {
        const projectTime = new Date() // 当前中国标准时间
        const Year = projectTime.getFullYear() // 获取当前年份 支持IE和火狐浏览器.
        const Month = projectTime.getMonth() + 1 // 获取中国区月份
        const Day = projectTime.getDate() // 获取几号
        var CurrentDate = Year
        if (Month >= 10) { // 判断月份和几号是否大于10或者小于10
          CurrentDate += Month
        } else {
          CurrentDate += '0' + Month
        }
        if (Day >= 10) {
          CurrentDate += Day
        } else {
          CurrentDate += '0' + Day
        }
        return CurrentDate
      },
      submit() {
        // 调用获取当前日期的方法加四位随机数  赋值表单中的项目编号
        this.form.recordId = this.getProjectNum() + Math.floor(Math.random() *
          10000) // 如果是6位或者8位随机数，相应的 *1000000或者 *100000000就行了
        alert(this.form.recordId)
        let formData = new FormData();
        for (let key in this.form) {
          formData.append(key, this.form[key]);
          console.log(formData.get(key));
        }
        this.axios({
          method: "post",
          url: "http://localhost:8945/record",
          headers: {
            "Content-Type": "multipart/form-data"
          },
          /* withCredentials: true, */
          data: formData
        }).then((response) => {
          console.log(response);
          this.$message.success("创建成功")
        }).catch((error) => {
          this.$message.error("创建失败")
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
