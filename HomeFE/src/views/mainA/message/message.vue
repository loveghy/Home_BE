<template>
  <div>
    <div class="deit">
      <div class="crumbs">
        <el-header>
          <h3 style="font-family: Microsoft YaHei">留言列表</h3>
          <hr style="border: 0.5px solid #e6c590">
        </el-header>
        <div class="cantainer">
          <el-form :inline="true" class="demo-form-inline" style="float: left;margin-top: 10px">
            <el-form-item>
              <el-input style="width: 300px;border:#818181;" v-model="search"
                placeholder="请输入关键词搜索留言"></el-input> <!-- //自动查询 -->
            </el-form-item>
            <!--            <el-button type="primary">
            	<download-excel class="export-btn" :data="tableList" :fields="jsonFields" type="xls" header="库存列表" name="库存列表.xls">
            		<i class="el-icon-download"> 导 出</i>
            	</download-excel>
            </el-button> -->
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
            <el-table-column label="排序" min-width="80px" align="center">
              <template slot-scope="{row,$index}">
                <span>{{$index + 1}}</span>
              </template>
            </el-table-column>
            </el-table-column>
            <el-table-column label="状态" prop="remessage" min-width="70px" align="center">
              <template slot-scope="{row,$index}">
                <span v-if="row.remessage==null" style="color: red;">未回复</span>
                <span v-if="row.remessage!=null" style="color: greenyellow;">已回复</span>
              </template>
            </el-table-column>
            <el-table-column label="留言者" prop="userId" min-width="100px" align="center">
              <template slot-scope="{row,$index}">
                <el-tooltip placement="top">
                  <div slot="content" v-if="row.userId==vister.username">
                    姓名：{{vister.name}}<br />性别：{{vister.sex}}<br />电话号码：{{vister.phone}}</div>
                  <div slot="content" v-if="row.userId!=vister.username">请点击，获取留言者信息</div>
                  <el-button type="text" @click="handlevister(row.userId)">{{row.userId}}</el-button>
                </el-tooltip>
              </template>
            </el-table-column>
            <el-table-column label="房源编号" prop="houseId" min-width="100px" align="center">
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
            <el-table-column label="留言内容" prop="messageRemarks" min-width="200px" align="center">
              <template slot-scope="{row,$index}">
                <el-popover trigger="hover" placement="top">
                  <span>{{row.messageRemarks}}</span>
                  <div slot="reference">
                    <span style="
                  display: inline-block;
                   overflow: hidden;
                   text-overflow: ellipsis;
                   white-space: nowrap;
                  ">{{row.messageRemarks}}</span>
                  </div>
                </el-popover>
              </template>
            </el-table-column>
            <el-table-column label="操作" fixed="right" min-width="150px" align="center">
              <template slot-scope="{row,$index}">
                <el-button type="danger" size="small" icon="el-icon-delete" @click="deleteData($index,row)">删除
                </el-button>
                <el-button type="success" size="small" icon="el-icon-edit" @click="say($index,row)">回复
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

    <el-dialog title="回复" :visible.sync="dialogFormVisible">
      <el-form :ref="temp" :model="temp" label-position="left" label-width="100px"
        style="width: 400px; margin-left:50px;">
        <el-form-item label="回复者">
          <span>{{temp.manger}}</span>
        </el-form-item>
        <el-form-item label="内容" prop="remessage">
          <el-input v-model="temp.remessage" autocomplete="off" type="textarea"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="dialogFormVisible = false" size="small">取 消</el-button>
          <el-button type="primary" size="small" @click="updateDate()">回复</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: 'message',
    // 引用vue reload方法
    inject: ['reload'],
    data() {
      return {
        dialogFormVisible:false,
        currentPage: 1, //初始页
        pagesize: 5, //    每页的数据
        userList: [], //定义数组
        vister: [], //存储游客信息
        house: [], //存储房屋信息
        multipleSelection: [], //定义复选款参数
        showEdit: [], //显示编辑框
        showBtn: [],
        showBtnOrdinary: true,
        search: '',
        //定义留言对象
        temp: {
          messageRemarks: '',
          houseId: '',
          userId: '',
          manger:'',
          remessage:''
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
      /* 获取留言信息*/
      handleUserList() {
        this.$http.get('http://localhost:8945/message/All').then(res => { //这是从本地请求的数据接口，
          this.userList = res.body
        })
      },
      /* 获取会员信息*/
      handlevister(Id) {
        console.log(Id)
        this.$http.get('http://localhost:8945/visitor/' + Id).then(res => { //这是从本地请求的数据接口，
          this.vister = res.body
          console.log(this.vister)
        })
      },
      /* 获取房屋信息*/
      handlehouse(Id) {
        console.log(Id)
        this.$http.get('http://localhost:8945/house/' + Id).then(res => { //这是从本地请求的数据接口，
          this.house = res.body
          console.log(this.house)
        })
      },
      //打开回复框
      say(index,row){
        this.temp = Object.assign({}, row); // copy obj
        this.temp.manger=sessionStorage.getItem("isLogin");
        this.dialogFormVisible=true;
      },
      //提交回复
      updateDate() {
        let formData = new FormData();
        for (let key in this.temp) {
          formData.append(key, this.temp[key]);
          console.log(formData.get(key));
        }
        this.$confirm('确定回复?', '提示', {
          type: 'warning'
        }).then(() => {
          this.axios({
            method: "put",
            url: "http://localhost:8945/message",
            headers: {
              "Content-Type": "multipart/form-data"
            },
            /* withCredentials: true, */
            data: formData
          }).then((response) => {
            //在方法中调用
            this.reload() //用于刷新当前页面 */
            console.log(response);
            this.$message.success("回复成功");
          }).catch((error) => {
            console.log("更新失败的原因：", error)
          })
        }).catch((error) => {
          console.log("更新失败的原因：", error)
        })
      },
      /* 复选框定义 */
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      /* 删除操作 */
      deleteData(index, row) {
        //this.tableData.splice(index, 1)
        this.$confirm('确定删除该数据?', '提示', {
          type: 'warning'
        }).then(() => {
          /* var params = new URLSearchParams()
          params.append("id", row.stockId) */
          this.axios.delete('http://localhost:8945/message/' + row.messageId).then((response) => {
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
