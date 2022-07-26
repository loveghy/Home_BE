<template>
  <div>
    <div class="deit">
      <div class="crumbs">
        <el-header>
          <h3 style="font-family: Microsoft YaHei">员工信息</h3>
          <hr style="border: 0.5px solid #e6b89d">
        </el-header>
        <div class="cantainer">
          <el-form :inline="true" class="demo-form-inline" style="float: left;margin-top: 10px">
            <el-form-item>
              <el-input style="width: 300px;border:#999999;" v-model="search" placeholder="请输入搜索内容"></el-input> <!-- //自动查询 -->
            </el-form-item>
            <hr style="border: 0.5px solid #ded9e6">
          </el-form>

          <el-dialog title="修改密码" :visible.sync="dialogFormVisible">
            <el-form :ref="temp" :model="temp" label-position="left" label-width="100px"
              style="width: 400px; margin-left:50px;">
              <el-form-item label="员工姓名">
                <span>{{temp.staffName}}</span>
              </el-form-item>
              <el-form-item label="密码" prop="password">
                <el-input v-model="temp.password" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="updateDate()">确 定</el-button>
            </div>
          </el-dialog>

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
            <el-table-column label="员工编号" prop="staffId" min-width="140px" sortable align="center">
              <template slot-scope="{row,$index}">
                <span>{{row.staffId}}</span>
              </template>
            </el-table-column>
            <el-table-column label="角色" prop="staffRoles" min-width="80px" sortable align="center">
              <template slot-scope="{row,$index}">
                <span v-if="row.staffRoles=='1'">经理</span>
                <span v-if="row.staffRoles=='0'">销售</span>
                <span v-if="row.staffRoles=='2'" style="color: red;">已离职</span>
              </template>
            </el-table-column>
            <el-table-column label="员工姓名" prop="staffName" min-width="100px" align="center">
              <template slot-scope="{row,$index}">
                <input class="edit-cell" v-if="showEdit[$index]" v-model="row.staffName">
                <span v-if="!showEdit[$index]">{{row.staffName}}</span>
              </template>
            </el-table-column>
            <el-table-column label="性别" prop="staffSex" min-width="80px" align="center">
              <template slot-scope="{row,$index}">
                <input class="edit-cell" v-if="showEdit[$index]" v-model="row.staffSex">
                <span v-if="!showEdit[$index]">{{row.staffSex}}</span>
              </template>
            </el-table-column>
            <el-table-column label="电话号码" prop="staffPhone" min-width="140px" align="center">
              <template slot-scope="{row,$index}">
                <input class="edit-cell" v-if="showEdit[$index]" v-model="row.staffPhone">
                <span v-if="!showEdit[$index]">{{row.staffPhone}}</span>
              </template>
            </el-table-column>
            <el-table-column label="入职时间" prop="staffTime" min-width="150px" sortable align="center">
              <template slot-scope="{row,$index}">
                <span>{{row.staffTime}}</span>
              </template>
            </el-table-column>
            <el-table-column label="在职状态" prop="staffState" min-width="120px" sortable align="center">
              <template slot-scope="{row,$index}">
                <el-select class="edit-cell" size="small" v-if="showEdit[$index]" v-model="row.staffState"
                  placeholder="请选择">
                  <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select>
                <span v-if="!showEdit[$index]&&row.staffState=='离职'">
                  <el-button type="danger" size="small" plain>{{row.staffState}}</el-button>
                </span>
                <span v-if="!showEdit[$index]&&row.staffState=='在职'">
                  <el-button type="success" size="small" plain>{{row.staffState}}</el-button>
                </span>
              </template>
            </el-table-column>
            <el-table-column label="工资/月" prop="staffWages" min-width="100px" sortable align="center">
              <template slot-scope="{row,$index}">
                <input class="edit-cell" v-if="showEdit[$index]" v-model="row.staffWages">
                <span v-if="!showEdit[$index]">￥{{row.staffWages}}</span>
              </template>
            </el-table-column>
            <el-table-column label="备注" prop="staffRemarks" min-width="100px" align="center">
              <template slot-scope="{row,$index}">
                <el-popover trigger="hover" placement="top">
                  <input class="edit-cell" v-if="showEdit[$index]" v-model="row.staffRemarks">
                  <span v-if="!showEdit[$index]">{{row.staffRemarks}}</span>
                  <div slot="reference">
                  <span style="
                  display: inline-block;
                   overflow: hidden;
                   text-overflow: ellipsis;
                   white-space: nowrap;
                  ">{{row.staffRemarks}}</span>
                  </div>
                </el-popover>
              </template>
            </el-table-column>
            <el-table-column label="操作" fixed="right" min-width="350px" align="center">
              <template slot-scope="{row,$index}">
                <el-button type="warning" size="small" @click.native="handleUpdate($index, row)" v-if="showBtn[$index]">
                  更新
                </el-button>
                <el-button type="primary" size="small" @click.native="handleCancel($index, row)" v-if="showBtn[$index]">
                  取消
                </el-button>
                <el-button type="primary" size="small" icon="el-icon-edit" @click.native="handleEdit($index, row)"
                  v-if="!showBtn[$index]">编辑
                </el-button>
                <el-button type="danger" size="small" icon="el-icon-delete" @click="deleteData($index,row)">删除
                </el-button>
                <el-button type="info" size="small" icon="el-icon-edit" @click="handleopen($index,row)">密码
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
    name: 'staffList',
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
        temp: {
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
        },
        showBtnOrdinary: true,
        search: '',
        dialogFormVisible: false,
        options: [{
          value: '在职',
          label: '在职'
        }, {
          value: '离职',
          label: '离职'
        }]
      }
    },
    created() {
      this.handleUserList()
      /* this.handle() */
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
      //打开密码修改
      handleopen(index, row) {
        this.temp = Object.assign({}, row) // copy obj
        this.dialogFormVisible = true
      },
      updateDate() {
        let formData = new FormData();
        for (let key in this.temp) {
          formData.append(key, this.temp[key]);
          console.log(formData.get(key));
        }
        this.$confirm('确定修改密码?', '提示', {
          type: 'warning'
        }).then(() => {
          this.axios({
            method: "put",
            url: "http://localhost:8945/staff",
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
      /*      handle() {
              this.$http.get('http://localhost:8945/staff/All').then(res => { //这是从本地请求的数据接口，
              console.log(res.data)
                this.list = res.body
              })
            }, */

      /* 获取云端数据 */
      handleUserList() {
        this.$http.get('http://localhost:8945/staff/All').then(res => { //这是从本地请求的数据接口，
          this.userList = res.body
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
        if(row.staffState=="离职"){
          row.staffRoles="2"
        }
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
            url: "http://localhost:8945/staff",
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
          this.axios.delete('http://localhost:8945/staff/' + row.staffId).then((response) => {
            console.log("删除的结果：", response)
            //在方法中调用
            this.reload() //用于刷新当前页面 */
            if (response.data == true) {
              this.$message.success("删除成功")
            } else {
              alert("删除失败的原因：" + response.data.message)
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
