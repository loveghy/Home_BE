<template>
  <div>
    <div class="deit">
      <div class="crumbs">
        <el-header>
          <h3 style="font-family: Microsoft YaHei">房源列表</h3>
          <hr style="border: 0.5px solid #e6cfaf">
        </el-header>
        <div class="cantainer">
          <el-form :inline="true" class="demo-form-inline" style="float: left;margin-top: 10px">
            <el-form-item>
              <el-input style="width: 300px;border:#999999;" v-model="search" placeholder="请输入搜索内容"></el-input> <!-- //自动查询 -->
            </el-form-item>
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
            <el-table-column label="房源编号" prop="houseId" min-width="140px" sortable align="center">
              <template slot-scope="{row,$index}">
                <span>{{row.houseId}}</span>
              </template>
            </el-table-column>
            <el-table-column label="发布人编号" prop="houseContact" min-width="120px" sortable align="center">
              <template slot-scope="{row,$index}">
                <span>{{row.houseContact}}</span>
              </template>
            </el-table-column>
            <el-table-column label="房东姓名" prop="houserName" min-width="100px" align="center">
              <template slot-scope="{row,$index}">
                <input class="edit-cell" v-if="showEdit[$index]" v-model="row.houserName">
                <span v-if="!showEdit[$index]">{{row.houserName}}</span>
              </template>
            </el-table-column>
            <el-table-column label="发布时间" prop="starttime" min-width="120px" align="center">
              <template slot-scope="{row,$index}">
                <span>{{row.starttime}}</span>
              </template>
            </el-table-column>
            <el-table-column label="联系电话" prop="housePhone" min-width="140px" align="center">
              <template slot-scope="{row,$index}">
                <input class="edit-cell" v-if="showEdit[$index]" v-model="row.housePhone">
                <span v-if="!showEdit[$index]">{{row.housePhone}}</span>
              </template>
            </el-table-column>
            <el-table-column label="房源标题" prop="houseTitle" min-width="150px" sortable align="center">
              <template slot-scope="{row,$index}">
                <span>{{row.houseTitle}}</span>
              </template>
            </el-table-column>
            <el-table-column label="房源状态" prop="houseState" min-width="120px" sortable align="center">
              <template slot-scope="{row,$index}">
                <el-select class="edit-cell" size="small" v-if="showEdit[$index]" v-model="row.houseState"
                  placeholder="请选择">
                  <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select>
                <span v-if="!showEdit[$index]&&row.houseState=='未租'">
                  <el-button type="success" size="small" plain disabled>{{row.houseState}}</el-button>
                </span>
                <span v-if="!showEdit[$index]&&row.houseState=='已租'">
                  <el-button type="danger" size="small" plain disabled>{{row.houseState}}</el-button>
                </span>
                <span v-if="!showEdit[$index]&&row.houseState=='未售'">
                  <el-button type="success" size="small" plain>{{row.houseState}}</el-button>
                </span>
                <span v-if="!showEdit[$index]&&row.houseState=='已售'">
                  <el-button type="danger" size="small" plain disabled>{{row.houseState}}</el-button>
                </span>
              </template>
            </el-table-column>
            <el-table-column label="房源类型" prop="houseContent" min-width="150px" sortable align="center">
              <template slot-scope="{row,$index}">
                <span>{{row.houseContent}}</span>
              </template>
            </el-table-column>
            <el-table-column label="图片" prop="housePicture" min-width="150px" sortable align="center">
              <template slot-scope="{row,$index}">
                <img :src="row.housePicture" style="width: 50px;height: 50px;" />
              </template>
            </el-table-column>
            <el-table-column label="价格" prop="housePay" min-width="130px" sortable align="center">
              <template slot-scope="{row,$index}">
                <label v-if="row.houseTitle=='房屋出租'">租金/月:</label>
                <label v-if="row.houseTitle=='房屋出售'">售价:</label>
                <input class="edit-cell" v-if="showEdit[$index]" v-model="row.housePay">
                <span v-if="!showEdit[$index]">￥{{row.housePay}}</span>
              </template>
            </el-table-column>
            <el-table-column label="地址" prop="houseArea" min-width="100px" align="center">
              <template slot-scope="{row,$index}">
                <el-popover trigger="hover" placement="top">
                  <input class="edit-cell" v-if="showEdit[$index]" v-model="row.houseArea">
                  <span v-if="!showEdit[$index]">{{row.houseArea}}</span>
                  <div slot="reference">
                    <span style="
                  display: inline-block;
                   overflow: hidden;
                   text-overflow: ellipsis;
                   white-space: nowrap;
                  ">{{row.houseArea}}</span>
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
                <el-button type="success" size="small" icon="el-icon-upload el-icon--right" @click="uploadOpen(row.houseId)">视频
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
    <el-dialog :visible.sync="dialogVisible">
        <!-- {{video.id}} -->
        <h3 style="margin-top: 0%;">编号{{video.id}}房屋视频上传</h3>
         <el-form ref="video" :model="video" label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
          <el-form-item>
            <input type="file" @change="getFile($event)">
          </el-form-item>
           <el-form-item>
             <el-button type="primary" @click="dialogVisible = false">取 消</el-button>
             <el-button type="primary" @click="uploadeVideo()">确 认</el-button>
           </el-form-item>
         </el-form>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: 'houseListG',
    // 引用vue reload方法
    inject: ['reload'],
    data() {
      return {
        dialogVisible:false,
        currentPage: 1, //初始页
        pagesize: 5, //    每页的数据
        userList: [], //定义数组
        multipleSelection: [], //定义复选款参数
        showEdit: [], //显示编辑框
        showBtn: [],
        list: [],
        video:{
          id:'',
          file:''
        },
        showBtnOrdinary: true,
        search: '',
        dialogFormVisible: false,
        options: [{
          value: '未租',
          label: '未租'
        }, {
          value: '未售',
          label: '未售'
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
        	var Id = sessionStorage.getItem("isLogin");
          console.log(Id)
        this.$http.get('http://localhost:8945/house/selecId/'+Id).then(res => { //这是从本地请求的数据接口，
          this.userList = res.body
          console.log(this.userList)
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
      //视屏上传页面打开
      uploadOpen(id){
        this.dialogVisible=true
        this.video.id=id
      },
      getFile(event) {
        this.video.file = event.target.files[0];
        console.log(this.video.file);
      },
      uploadeVideo(){
        let formData = new FormData();
        for (let key in this.video) {
          formData.append(key, this.video[key]);
          console.log(formData.get(key));
        }
        this.axios({
          method: "post",
          url: "http://localhost:8945/video/upload",
          headers: {
            "Content-Type": "multipart/form-data"
          },
          /* withCredentials: true, */
          data: formData
        }).then((response) => {
          console.log(response);
          this.$message.success("上传成功")
        }).catch((error) => {
          this.$message.error("上传失败")
          console.log("添加失败的原因：", error)
        })
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
            url: "http://localhost:8945/house",
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
          this.axios.delete('http://localhost:8945/house/' + row.houseId).then((response) => {
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
