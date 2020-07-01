<template>
   <!-- <div>操作日志管理</div>-->

    <div v-if="this.opid=='123456'">

        <!--面包屑导航区域-->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>系统管理</el-breadcrumb-item>
            <el-breadcrumb-item>日志管理</el-breadcrumb-item>
        </el-breadcrumb>

        <el-card style="margin-top: 10px">
            <div style="display: flex;justify-content: space-between">
                <div>
                    <el-input
                            placeholder="请输入员工号搜索/删除"
                            prefix-icon="el-icon-search"
                            style="width: 300px;margin-right: 10px"
                            clearable
                            @clear="initData"
                            v-model="oid"
                    ></el-input>
                    <el-button icon="el-icon-search" type="primary" @click="getLogTable">搜索</el-button>
                    <!--<el-tooltip effect="dark" content="按员工号" :enterable="false" placement="top">
                    <el-button icon="el-icon-delete" type="warning" @click="deleteLogTable">删除</el-button>
                    </el-tooltip>-->
                </div>

                <div>
                    <el-button type="primary" icon="el-icon-plus" @click="addDialogVisible=true">
                        添加操作日志
                    </el-button>
                </div>
            </div>
        <div style="margin-top: 10px">

                <el-table :data="logs" stripe border style="width:60%" >

                    <!--前面的选择框-->
                    <el-table-column
                            type="selection"
                            width="55">
                    </el-table-column>

                    <el-table-column
                            prop="no"
                            align="left"
                            label="序号"
                            width="90"
                            fixed>

                    </el-table-column>

                    <el-table-column
                            prop="op"
                            align="left"
                            label="操作员"
                            width="150"
                            fixed>

                    </el-table-column>

                    <el-table-column
                            prop="adddate"
                            align="left"
                            label="添加日期"
                            :formatter="dateFormat"
                            width="150"
                            fixed>

                    </el-table-column>

                    <el-table-column
                            prop="event"
                            align="left"
                            label="事件内容"
                            width="150"
                            fixed>

                    </el-table-column>


                    <el-table-column fixed="right" width="140" label="操作">
                        <template slot-scope="scope">
                            <el-button @click="editSysLog(scope.row)" style="padding: 3px">编辑</el-button>
                            <el-button @click="deleteLog(scope.row)" style="padding: 3px" type="danger">删除
                            </el-button>
                        </template>
                    </el-table-column>


                </el-table>



        </div>
        </el-card>
        <!-- 添加日志的对话框 -->
        <el-dialog title="添加日志" :visible.sync="addDialogVisible" width="50%" >
            <!-- 内容主体 -->
            <el-form
                    :model="addLogForm"

                    label-width="100px"
            >
                <el-form-item label="操作员" prop="op">
                    <el-input v-model="addLogForm.op"></el-input>
                </el-form-item>
                <el-form-item label="添加日期：" prop="adddate" >
                    <el-date-picker
                            v-model="addLogForm.adddate"
                            type="date"
                            rows="mini"
                            value-format="yyyy-MM-dd"

                            placeholder="操作日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="事件内容" prop="event">
                    <el-input v-model="addLogForm.event"></el-input>
                </el-form-item>


            </el-form>
            <span slot="footer" class="dialog-footer">
            <el-button @click="addDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addLog">确 定</el-button>
          </span>
        </el-dialog>
        <!--编辑用户的对话框-->
        <el-dialog title="修改日志" :visible.sync="adddDialogVisible" width="50%" >
            <!-- 内容主体 -->
            <el-form
                    :model="editLogForm"

                    label-width="100px"
            >
                <el-form-item label="操作员" prop="op">
                    <el-input v-model="editLogForm.op"></el-input>
                </el-form-item>
                <el-form-item label="添加日期：" prop="adddate" >
                    <el-date-picker
                            v-model="editLogForm.adddate"
                            type="date"
                            rows="mini"
                            value-format="yyyy-MM-dd"

                            placeholder="操作日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="事件内容" prop="event">
                    <el-input v-model="editLogForm.event"></el-input>
                </el-form-item>


            </el-form>
            <span slot="footer" class="dialog-footer">
            <el-button @click="adddDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="submitBjEmp">确 定</el-button>
          </span>
        </el-dialog>
    </div>

    <div v-else>您的权限不足</div>
</template>

<script>

    import * as fecha from "element-ui/lib/utils/date";

    export default {
        name: "SysLog",
        data() {
            return {
                opid: '',
                oid:'',
                logs: [],
                addDialogVisible: false,
                adddDialogVisible: false,
                addLogForm: {

                    op: '',
                    adddate: '',
                    event: '',
                },
                uid:'',
                editLogForm:{},
            }
        },
        mounted(){
            this.initData()
            this.getUser()
        },
      methods:{

            /*true 查询日志*/
               async getLogTable(){
                   const {data:res} =await  this.$http.post('/operate/findbyopid?opid='+this.oid);
                   var op = new Array(res)
                   this.logs=op
                },
                /*添加日志*/
          async addLog(){

              const { data: res } = await this.$http.post('/operate/save', this.addLogForm);
              // console.log(this.addUserForm)
              this.addDialogVisible = false
              this.initData();

          },
                /*初始化展示*/
          async initData(){

              const {data:res}=await this.$http.post('/operate/findall');
              if(!res.state) return false;
              this.logs = res.data;

          },
          /*按序号删除日志*/
          async deleteLog(data){

              const confirmResult = await this.$confirm(
                  '此操作将永久删除该日志, 是否继续?',
                  '提示',
                  {
                      confirmButtonText: '确定',
                      cancelButtonText: '取消',
                      type: 'warning'
                  }
              ).catch(err => err)
              // 点击确定 返回值为：confirm
              // 点击取消 返回值为： cancel
              if (confirmResult !== 'confirm') {
                  return this.$message.info('已取消删除')
              }
              const {data:res} = await this.$http.post('/operate/deletebyno?no='+data.no);
              this.initData();
          },
          /*按员工编号删除*/
          async deleteLogTable(){

              const confirmResult = await this.$confirm(
                  '此操作将永久删除该操作员日志, 是否继续?',
                  '提示',
                  {
                      confirmButtonText: '确定',
                      cancelButtonText: '取消',
                      type: 'warning'
                  }
              ).catch(err => err)
              // 点击确定 返回值为：confirm
              // 点击取消 返回值为： cancel
              if (confirmResult !== 'confirm') {
                  return this.$message.info('已取消删除')
              }
              const {data:res} = await this.$http.post('/operate/delete?opid='+this.oid);
              this.initData();
          },
          /*编辑员工对话框*/
          editSysLog(data){
              this.editLogForm=data;
              this.adddDialogVisible=true
          },
          /*编辑员工操作*/
          async submitBjEmp(){


              /*id来判端添加（有id更新）还是更新*/
              const {data: res}  = await this.$http.post('/operate/update',this.editLogForm);

              if (res){
                  this.adddDialogVisible = false;
                  this.initData();
              }


          },

          getUser(){
              this.opid= window.sessionStorage.getItem("opid");
          },
          initDa(){
              this.addLogForm={
                  op: '',
                  adddate: '',
                  event: '',
              }
          },
          dateFormat(row, column, cellValue) {
              return cellValue ? fecha.format(new Date(cellValue), 'yyyy-MM-dd') : '';
          },
        }


    }
</script>

<style scoped>

</style>