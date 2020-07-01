<template>
    <!--<div>操作员管理</div>-->

        <div v-if="this.uid=='123456'">

            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>系统管理</el-breadcrumb-item>
                <el-breadcrumb-item>操作员管理</el-breadcrumb-item>
            </el-breadcrumb>

            <!-- 卡片视图区域 -->
            <el-card style="margin-top: 10px">

                <!--搜索 添加-->
                <div style="display: flex;justify-content: space-between">
                    <div>
                        <el-input
                                placeholder="通过员工号搜索员工"
                                prefix-icon="el-icon-search"
                                style="width: 300px;margin-right: 10px"
                                clearable
                                @clear="initData"
                                v-model="Opid"
                        ></el-input>
                        <el-button icon="el-icon-search" type="primary" @click="getOpTable">搜索</el-button>
                    </div>
                    <div>
                        <el-button type="primary" icon="el-icon-plus" @click="addDialogVisible=true">
                            添加操作员
                        </el-button>
                    </div>
                </div>


                <!-- 用户列表区域 -->
                <el-table :data="emps" border stripe style="margin-top: 10px">
                    <!--前面的选择框-->
                    <el-table-column
                            type="selection"
                            width="55">
                    </el-table-column>

                    <el-table-column label="账号" prop="opid" align="left"> </el-table-column>
                    <el-table-column label="密码" prop="oppassword" align="left"></el-table-column>
                    <el-table-column label="操作员" prop="opname" align="left"></el-table-column>
                    <el-table-column label="电话" prop="opphone" align="left"></el-table-column>
                    <el-table-column label="备注" prop="remark" align="left"></el-table-column>
                    <el-table-column label="权限" prop="permission" align="left"></el-table-column>

                    <el-table-column
                            label="操作">
                        <template slot-scope="scope">

                           <el-tooltip effect="dark" content="修改权限" :enterable="false" placement="top">
                                <el-button
                                        @click="editEmp(scope.row)"
                                        icon="el-icon-setting"
                                        type="warning"
                                        size="small">

                                </el-button>
                            </el-tooltip>


                            <el-button size="small" type="danger" @click="deleteOp(scope.row)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>


            </el-card>
            <!-- 添加用户的对话框 -->
            <el-dialog title="添加操作员" :visible.sync="addDialogVisible" width="50%" >
                <!-- 内容主体 -->
                <el-form
                        :model="addOpForm"

                        label-width="100px"
                >
                    <el-form-item label="账号" prop="opid">
                        <el-input v-model="addOpForm.opid"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="oppassword">
                        <el-input v-model="addOpForm.oppassword"></el-input>
                    </el-form-item>
                    <el-form-item label="操作员姓名" prop="opname">
                        <el-input v-model="addOpForm.opname"></el-input>
                    </el-form-item>
                    <el-form-item label="电话" prop="opphone">
                        <el-input v-model="addOpForm.opphone"></el-input>
                    </el-form-item>
                    <el-form-item label="备注" prop="remark">
                        <el-input v-model="addOpForm.remark"></el-input>
                    </el-form-item>
                    <el-form-item label="权限" prop="permission">
                        <el-input v-model="addOpForm.permission"></el-input>
                    </el-form-item>


                </el-form>
                <span slot="footer" class="dialog-footer">
            <el-button @click="addDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addOp">确 定</el-button>
          </span>
            </el-dialog>


        </div>

        <div v-else>您的权限不足</div>




</template>

<script>



    export default {
        name: "SalSob",
        data() {
            return {
                 emps:[],
                 Opid:'',
                 pid:'',
                uid:'',
                addDialogVisible:false,


                addOpForm:{
                    opid:'',
                    opname:'',
                    oppassword:'',
                    opphone:'',
                    remark:'',
                    permission:''
                }

            }
        },
        mounted() {
            this.initData();
            this.getUser()
        },
        methods: {

            /*true 查找*/
            async getOpTable(){
                const {data:res} =await  this.$http.post('/op/findone?id='+this.Opid);
                var op = new Array(res)
                this.emps=op

            },
            /*true 初始化*/
            async initData(){
                const {data:res} =await this.$http.post('/op/findAll');
                this.emps=res.data;


            },
            /*false 权限问题*/
            async deleteOp(data){
                const confirmResult = await this.$confirm(
                    '此操作将永久删除该【' + data.opname + data.opid+'】操作员, 是否继续?',
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
                console.log( typeof data.opid)
                const {data:res} = await this.$http.post('/op/delete?opid='+data.opid);

                this.initData();
            },
            /*true 添加*/
            async addOp(){
1
                const { data: res } = await this.$http.post('/op/save', this.addOpForm);
                // console.log(this.addUserForm)
                this.addDialogVisible = false
                this.initData();

            },
            /*管理员权限*/
            getUser(){
                this.uid= window.sessionStorage.getItem("opid");
            },

           /*修改操作员权限*/
            async editEmp(data){

                const {data:res} =await this.$http.post('/op/update?opid='+ data.opid);
                console.log(this.addOpForm)

                this.initData()
            }
        }

    }
</script>

<style scoped>

</style>