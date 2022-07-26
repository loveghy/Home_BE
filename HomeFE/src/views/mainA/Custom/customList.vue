<template>
  <div>
    <div class="deit">
      <div class="crumbs">
        <el-header>
          <h3 style="font-family: Microsoft YaHei">客户信息</h3>
          <hr style="border: 0.5px solid #ffb786">
        </el-header>
        <div class="cantainer">
          <el-form :inline="true" class="demo-form-inline" style="float: left;padding-bottom: 10px;">
              <el-input style="width: 300px;margin-top: 10px;border:#999999;" v-model="search" placeholder="请输入关键字搜索"></el-input> <!-- //自动查询 -->
           <!-- 	<input type="text" name="" id="" placeholder="搜索" v-model="search"/> -->
              	<!-- <button @click.prevent="btn">搜索</button> -->
            <el-button type="primary">
            	<download-excel class="export-btn" :data="tableList" :fields="jsonFields" type="xls" header="库存列表" name="库存列表.xls">
            		<i class="el-icon-download"> 导 出</i>
            	</download-excel>
            </el-button>
            <hr style="border: 0.5px solid #ded9e6">
          </el-form>
          <el-table style="width: 100%;" border header-align="center"
            :data="tableList.slice((currentPage-1)*pagesize,currentPage*pagesize)"
            @selection-change="handleSelectionChange">
            <!-- //对数据请求的处理，最为重要的一句话 -->
            <el-table-column type="selection" width="55">
            </el-table-column>
            <!-- 						<el-table-column label="排序" type="index" width="80" header-align="center">
						</el-table-column> -->
            <el-table-column  label="排序"  min-width="80px"  align="center">
              <template slot-scope="{row,$index}">
                <span>{{$index + 1}}</span>
              </template>
            </el-table-column>
            <el-table-column label="客户编号" prop="customId"  min-width="100px" align="center">
              <template slot-scope="{row,$index}">
                <span>{{row.customId}}</span>
              </template>
            </el-table-column>
            <el-table-column label="客户姓名" prop="customName" min-width="100px" align="center">
              <template slot-scope="{row,$index}">
                <input class="edit-cell" v-if="showEdit[$index]" v-model="row.customName">
                <span v-if="!showEdit[$index]">{{row.customName}}</span>
              </template>
            </el-table-column>
            <el-table-column label="性别" prop="customSex" min-width="88px" align="center">
              <template slot-scope="{row,$index}">
                <el-radio-group v-if="showEdit[$index]" v-model="row.customSex">
                  <el-radio label="男"></el-radio>
                  <el-radio label="女"></el-radio>
                </el-radio-group>
                <span v-if="!showEdit[$index]">{{row.customSex}}</span>
              </template>
            </el-table-column>
            <el-table-column label="电话号码" prop="customPhone" min-width="100px" align="center">
              <template slot-scope="{row,$index}">
                <input class="edit-cell" v-if="showEdit[$index]" v-model="row.customPhone">
                <span v-if="!showEdit[$index]">{{row.customPhone}}</span>
              </template>
            </el-table-column>
            <el-table-column label="备注" prop="customRemarks" min-width="150px" align="center">
              <template slot-scope="{row,$index}">
                <el-popover trigger="hover" placement="top">
                  <input class="edit-cell" v-if="showEdit[$index]" v-model="row.customRemarks">
                  <span v-if="!showEdit[$index]">{{row.customRemarks}}</span>
                  <div slot="reference">
                  <span style="
                  display: inline-block;
                   overflow: hidden;
                   text-overflow: ellipsis;
                   white-space: nowrap;
                  ">{{row.customRemarks}}</span>
                  </div>
                </el-popover>
              </template>
            </el-table-column>
            <el-table-column label="操作" fixed="right" min-width="200px" align="center">
              <template slot-scope="{row,$index}">
                <el-button type="warning" size="small" @click.native="handleUpdate($index, row)" v-if="showBtn[$index]">更新
                </el-button>
                <el-button type="primary" size="small" @click.native="handleCancel($index, row)" v-if="showBtn[$index]">取消
                </el-button>
                <el-button type="primary" size="small" icon="el-icon-edit" @click.native="handleEdit($index, row)"
                  v-if="!showBtn[$index]">编辑
                </el-button>
                <el-button type="danger" size="small" icon="el-icon-delete" @click="deleteData($index,row)">删除</el-button>
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
    name: 'customList',
    // 引用vue reload方法
    inject: ['reload'],
    data() {
      return {
        currentPage: 1, //初始页
        pagesize: 5, //    每页的数据
        userList: [], //定义数组
        searchData: [],
        multipleSelection: [], //定义复选款参数
        showEdit: [], //显示编辑框
        showBtn: [],
        showBtnOrdinary: true,
        search: '',
        jsonFields: { //导出Excel表格的表头设置
        	'客户编码': 'customId',
        	'客户姓名': 'customName',
        	'客户性别': 'customSex',
        	'客户电话': 'customPhone',
          '客户备注': 'customRemarks',
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
/*      btn:function(){

        		var search = this.search;
      			if (search) {
      			 this.searchData =  this.userList.filter(function(product) {
      			 	console.log(product)
      				return Object.keys(product).some(function(key) {
      					console.log(key)
      			  	return String(product[key]).toLowerCase().indexOf(search) > -1
      			})
      			})
      			}

        	}, */

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
        this.$http.get('http://localhost:8945/custom/All').then(res => { //这是从本地请求的数据接口，
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
            url: "http://localhost:8945/custom",
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
          this.axios.delete('http://localhost:8945/custom/' + row.customId).then((response) => {
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
