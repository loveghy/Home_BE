<template>
  <div>
    <div class="deit">
      <div class="crumbs">
        <el-header>
          <h3 style="font-family: Microsoft YaHei">订单列表</h3>
          <hr style="border: 0.5px solid #ffb786">
        </el-header>
        <div class="cantainer">
          <el-form :inline="true" class="demo-form-inline" style="float: left;margin-top: 15px;">
            <el-form-item>
              <el-input style="width: 300px;border:#999999;" v-model="search" placeholder="请输入搜索内容">
              </el-input> <!-- //自动查询 -->
            </el-form-item>
            <el-button type="primary">
            	<download-excel class="export-btn" :data="tableList" :fields="jsonFields" type="xls" header="订单列表" name="订单列表.xls">
            		<i class="el-icon-download"> 导 出</i>
            	</download-excel>
            </el-button>
            <hr style="border: 0.5px solid #ded9e6">
          </el-form>

          <el-table style="width: 100%;" border header-align="center"
            :data="tableList.slice((currentPage-1)*pagesize,currentPage*pagesize)"
            @selection-change="handleSelectionChange" :default-sort="{prop: 'date', order: 'descending'}">
            <!-- //对数据请求的处理，最为重要的一句话 -->
            <el-table-column type="selection" width="55" align="center">
            </el-table-column>
            <!-- 						<el-table-column label="排序" type="index" width="80" header-align="center">
						</el-table-column> -->
            <el-table-column label="排序" min-width="100px" align="center">
              <template slot-scope="{row,$index}">
                <span>{{$index + 1}}</span>
              </template>
            </el-table-column>
            <el-table-column label="订单编号" prop="recordId" min-width="140px" sortable align="center">
            </el-table-column>
            <el-table-column label="房源编号" prop="houseId" min-width="120px" sortable align="center">
           <template slot-scope="{row,$index}">
             <el-tooltip placement="top">
               <div slot="content" v-if="row.houseId==house.houseId">
                 房东：{{house.houserName}}<br />状态：{{house.houseState}}<br />联系电话：{{house.housePhone}}<br />价格：{{house.housePay}}元<br />类型：{{house.houseContent}}
               </div>
               <div slot="content" v-if="row.houseId!=house.houseId">请点击，获取房屋信息</div>
               <el-button type="text" @click="handlehouse(row.houseId)">{{row.houseId}}</el-button>
             </el-tooltip>
           </template>
            </el-table-column>
            <el-table-column label="客户编号" prop="recordContact" min-width="140px" align="center">
            <template slot-scope="{row,$index}">
             <el-tooltip placement="top">
               <div slot="content" v-if="row.recordContact==custom.customId">
                 姓名：{{custom.customName}}<br />性别：{{custom.customSex}}<br />电话号码：{{custom.customPhone}}</div>
               <div slot="content" v-if="row.recordContact!=custom.customId">请点击，获取留言者信息</div>
               <el-button type="text" @click="handlecustom(row.recordContact)">{{row.recordContact}}</el-button>
             </el-tooltip>
            </template>
            </el-table-column>
            <el-table-column label="员工编号" prop="staffId" min-width="140px" align="center">
            <template slot-scope="{row,$index}">
             <el-tooltip placement="top">
               <div slot="content" v-if="row.staffId==staff.staffId">
                 姓名：{{staff.staffName}}<br />性别：{{staff.staffSex}}<br />电话号码：{{staff.staffPhone}}</div>
               <div slot="content" v-if="row.staffId!=staff.staffId">请点击，获取留言者信息</div>
               <el-button type="text" @click="handlestaff(row.staffId)">{{row.staffId}}</el-button>
             </el-tooltip>
            </template>
            </el-table-column>
            <el-table-column label="交易金额" prop="recordPay" min-width="100px" align="center">
           <template slot-scope="{row,$index}">
             <span>¥{{row.recordPay}}</span>
           </template>
            </el-table-column>
            <el-table-column label="创建时间" prop="recordStart" min-width="120px" align="center">
            </el-table-column>
            <el-table-column label="押金" prop="deposit" min-width="150px" sortable align="center">
            <template slot-scope="{row,$index}">
              <span>¥{{row.deposit}}</span>
            </template>
            </el-table-column>
            <el-table-column label="中介费" prop="agencyFee" min-width="120px" sortable align="center">
           <template slot-scope="{row,$index}">
             <span>¥{{row.agencyFee}}</span>
           </template>
            </el-table-column>
            <el-table-column label="订单类型" prop="recordType" min-width="150px" sortable align="center">
            </el-table-column>
            <el-table-column label="订单状态" prop="recordState" min-width="150px" sortable align="center">
              <template slot-scope="{row,$index}">
                <el-select class="edit-cell" size="small" v-if="showEdit[$index]" v-model="row.recordState"
                  placeholder="请选择">
                  <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select>
                <span v-if="!showEdit[$index]&&row.recordState=='未支付'">
                  <el-button type="danger" size="small" plain >{{row.recordState}}</el-button>
                </span>
                <span v-if="!showEdit[$index]&&row.recordState=='已支付'">
                  <el-button type="success" size="small" plain>{{row.recordState}}</el-button>
                </span>
              </template>
            </el-table-column>
            <el-table-column label="操作" fixed="right" min-width="300px" align="center">
              <template slot-scope="{row,$index}">
                <el-button type="warning" size="small" @click.native="handleUpdate($index, row)" v-if="showBtn[$index]">
                  更新
                </el-button>
                <el-button type="primary" size="small" @click.native="handleCancel($index, row)" v-if="showBtn[$index]">
                  取消
                </el-button>
                <el-button type="primary" size="small" icon="el-icon-edit" @click.native="handleEdit($index, row)"
                  v-if="!showBtn[$index]&&row.recordState=='未支付'">编辑
                </el-button>
                <el-button type="primary" size="small" icon="el-icon-edit" @click.native="handleEdit($index, row)"
                  v-if="!showBtn[$index]&&row.recordState=='已支付'" disabled>编辑
                </el-button>
                <el-button type="danger" size="small" icon="el-icon-delete" @click="deleteData($index,row)">删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
            :current-page="currentPage" :page-sizes="[5, 10, 15, 20]" :page-size="pagesize"
            layout="total, sizes, prev, pager, next, jumper" :total="userList.length">
            <!-- //这是显示总共有多少数据， -->
            <!-- //显示当前行的条数 -->
          </el-pagination>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'orderList',
    // 引用vue reload方法
    inject: ['reload'],
    data() {
      return {
        currentPage: 1, //初始页
        pagesize: 5, //    每页的数据
        userList: [], //定义数组
        multipleSelection: [], //定义复选款参数
        showEdit: [], //显示编辑框
        showBtn: [],
        list: [],
        showBtnOrdinary: true,
        search: '',
        dialogFormVisible: false,
        options: [{
          value: '未支付',
          label: '未支付'
        }, {
          value: '已支付',
          label: '已支付'
        }],
        staff:[],//员工信息
        custom:[],//客户编号
        house:[],//房屋信息
        jsonFields: { //导出Excel表格的表头设置
        	'订单编号': 'recordId',
          '销售员':'staffId',
          '客户':'recordContact',
        	'交易金额': 'recordPay',
        	'创建时间': 'recordStart',
        	'订单状态': 'recordState',
          '订单类型': 'recordType',
        },
      }
    },
    created() {
      this.handleUserList()
    },
    computed: {
      // 模糊搜索
      tableList: function() {
        const search = this.search
        if (search) {
          // filter() 方法创建一个新的数组，新数组中的元素是通过检查指定数组中符合条件的所有元素。
          // 注意： filter() 不会对空数组进行检测。
          // 注意： filter() 不会改变原始数组。
          return this.userList.filter(data => {
            // some() 方法用于检测数组中的元素是否满足指定条件;
            // some() 方法会依次执行数组的每个元素：
            // 如果有一个元素满足条件，则表达式返回true , 剩余的元素不会再执行检测;
            // 如果没有满足条件的元素，则返回false。
            // 注意： some() 不会对空数组进行检测。
            // 注意： some() 不会改变原始数组。
            return Object.keys(data).some(key => {
              // indexOf() 返回某个指定的字符在某个字符串中首次出现的位置，如果没有找到就返回-1；
              // 该方法对大小写敏感！所以之前需要toLowerCase()方法将所有查询到内容变为小写。
              return String(data[key]).toLowerCase().indexOf(search) > -1
            })
          })
        }
        return this.userList;
      }
    },
    methods: {
      // 初始页currentPage、初始每页数据数pagesize和数据data
      handleSizeChange: function(size) {
        this.pagesize = size;
        console.log(this.pagesize) //每页下拉显示数据
      },
      handleCurrentChange: function(currentPage) {
        this.currentPage = currentPage;
        console.log(this.currentPage) //点击第几页
      },
      /* 获取云端数据 */
      handleUserList() {
        this.$http.get('http://localhost:8945/record/All').then(res => { //这是从本地请求的数据接口，
          this.userList = res.body
          console.log(this.userList)
        })
      },
      /* 获取员工信息*/
      handlestaff(Id) {
        this.$http.get('http://localhost:8945/staff/' + Id).then(res => { //这是从本地请求的数据接口，
          this.staff = res.body
        })
        },
        /* 获取客户信息*/
        handlecustom(Id) {
          this.$http.get('http://localhost:8945/custom/' + Id).then(res => { //这是从本地请求的数据接口，
            this.custom = res.body
          })
          },
          /* 获取房屋信息*/
          handlehouse(Id) {
            this.$http.get('http://localhost:8945/house/' + Id).then(res => { //这是从本地请求的数据接口，
              this.house = res.body
            })
          },
      /* 复选框定义 */
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      //点击编辑
      handleEdit(index, row) {
        this.showEdit[index] = true;
        this.showBtn[index] = true;
        this.$set(this.showEdit, row, true)
        this.$set(this.showBtn, row, true)
      },
      //取消编辑
      handleCancel(index, row) {
        this.reload() //重新加载
      },
      //点击更新
      handleUpdate(index, row) {
        let formData = new FormData();
        for (let key in row) {
          formData.append(key, row[key]);
          console.log(formData.get(key));
        }
        this.$confirm('确定修改当前数据?', '提示', {
          type: 'warning'
        }).then(() => {
          this.axios({
            method: "put",
            url: "http://localhost:8945/record",
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
      /* 删除操作 */
      deleteData(index, row) {
        //this.tableData.splice(index, 1)
        this.$confirm('确定删除该数据?', '提示', {
          type: 'warning'
        }).then(() => {
          /* var params = new URLSearchParams()
          params.append("id", row.stockId) */
          this.axios.delete('http://localhost:8945/record/' + row.recordId).then((response) => {
            console.log("删除的结果：", response)
            //在方法中调用
            this.reload() //用于刷新当前页面 */
            if (response.data == true) {
              this.$message.success("删除成功")
            } else {
              this.$message.error("删除失败的原因：" + response.data.message)
            }
          }).catch((error) => {
            console.log("删除失败的原因：", error)
          })
        }).catch((error) => {
          console.log("删除失败的原因：", error)
        })
      }

    }
  }
</script>

<style scoped>
  .cantainer{
    padding-left: 20px;
    background-color: white;
  }
</style>
