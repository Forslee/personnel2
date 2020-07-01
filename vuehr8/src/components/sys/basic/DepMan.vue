<template>
<div>
    <!--部门的删除-->
    <div  style="display: flex;">


        <el-button @click="addDialogVisible = true" type="primary" style="margin-right: 10px">添加</el-button>


    </div>
    <!--部门列表-->
        <div class="table1" style="margin-top: 10px">
            <el-table
                    :data="dep"
                    border
                    stripe
                    style="width: 40%">
                <el-table-column
                        type="selection"
                        width="55">

                </el-table-column>
                <el-table-column
                        prop="departmentId"
                        label="部门号"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="departmentName"
                        label="部门名"
                        width="150">
                </el-table-column>
                <el-table-column
                        label="操作">
                    <template slot-scope="scope">
                        <el-button size="small" type="danger" @click="deleteHandler(scope.row)">删除</el-button>
                    </template>
                </el-table-column>

            </el-table>
        </div>
        <!-- 添加用户的对话框 -->
        <el-dialog title="添加部门" :visible.sync="addDialogVisible" width="50%" >
            <!-- 内容主体 -->
            <el-form
                    :model="addUserForm"

                    label-width="100px"
            >
                <el-form-item label="编号" prop="departmentId">
                    <el-input v-model="addUserForm.departmentId"></el-input>
                </el-form-item>
                <el-form-item label="用户名" prop="departmentName">
                    <el-input v-model="addUserForm.departmentName"></el-input>
                </el-form-item>


            </el-form>
            <span slot="footer" class="dialog-footer">
            <el-button @click="addDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addDepartment">确 定</el-button>
          </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "DepMan",
        data(){
            return {
                dep:[],

                addUserForm: {

                    departmentName:'',
                    departmentId:''
                },
                // 添加用户对话框
                addDialogVisible: false,
            }
        },
        mounted(){
            this.initData()
        },
        methods:{
            /*true*/
           async initData(){

               const {data:res}=await this.$http.post('/department/findAll');
               if(!res.state) return false;
               this.dep = res.data;

               if(!window.sessionStorage.getItem('department')){

                   if(res){
                       window.sessionStorage.setItem("department", JSON.stringify(res.data));
                   }
               }

            },
            /*true*/
            async deleteHandler(data){

                const confirmResult = await this.$confirm(
                    '此操作将永久删除该【' + data.departmentName + '】部门, 是否继续?',
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
               const {data:res} = await this.$http.post('department/delete?departmentname='+data.departmentName);
                window.sessionStorage.removeItem("department")
                this.initData();
            },
            /*true*/
            async addDepartment(){
                const { data: res } = await this.$http.post('department/save', this.addUserForm);
               // console.log(this.addUserForm)
                this.addDialogVisible = false
                window.sessionStorage.removeItem("department")
                this.initData();
            }


        }

    }
</script>

<style scoped>



</style>