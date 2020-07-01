<template>

    <!--职位的删除-->
    <div>
        <div style="display: flex">

            <el-button type="primary" prefix-icon="el-icon-plus" @click="addDialogVisible = true">添加</el-button>
         </div>

            <div  style="margin-top: 10px">
                <el-table
                        :data="job"
                        border
                        stripe
                        style="width: 40%">
                    <el-table-column
                            type="selection"
                            width="55">

                    </el-table-column>
                    <el-table-column
                            prop="jobId"
                            label="职位号"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="jobName"
                            label="职位名"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            label="操作">
                        <template slot-scope="scope">
                            <el-button size="small" type="danger" @click="deleteJob(scope.row)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>


        <!-- 添加用户的对话框 -->
        <el-dialog title="添加职位" :visible.sync="addDialogVisible" width="50%" >
            <!-- 内容主体 -->
            <el-form
                    :model="addJobForm"

                    label-width="100px"
            >
                <el-form-item label="职位号" prop="jobId">
                    <el-input v-model="addJobForm.jobId"></el-input>
                </el-form-item>
                <el-form-item label="职位名" prop="jobName">
                    <el-input v-model="addJobForm.jobName"></el-input>
                </el-form-item>


            </el-form>
            <span slot="footer" class="dialog-footer">
            <el-button @click="addDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addJob">确 定</el-button>
          </span>
        </el-dialog>

    </div>
</template>


<script>
    export default {
        name: "PosMan",
        data() {
            return {
                job: [],
                dialogVisible: false,
                pos:{
                    id:'',
                    name:''
                },
                ps:{
                    name:''
                },
                addDialogVisible:false,
                addJobForm: {
                    jobName:'',
                    jobId:''
                },
            }
        },
        //加载数据
        mounted() {
            this.initData();
        },
        methods: {

            /*初始化加载 good*/
            async initData() {
                const {data: res} = await this.$http.post('/job/findAll');
                    this.job = res.data;
                if(!window.sessionStorage.getItem('job')){

                    if(res){
                        window.sessionStorage.setItem("job", JSON.stringify(res.data));
                    }

                    //this.job=JSON.parse(window.sessionStorage.getItem("job"))
                }

            },

            /*添加职位*/
            async addJob(){

                const {data: res} = await this.$http.post('/job/save',this.addJobForm);
                this.addDialogVisible = false
                window.sessionStorage.removeItem("job")
                this.initData();

            },

            /*删除职位操作 good*/
            async deleteJob(data){
                const confirmResult = await this.$confirm(
                    '此操作将永久删除该【' + data.jobName + '】职位, 是否继续?',
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
                const { data: res } = await this.$http.post('job/delete?jobname=' + data.jobName)
                window.sessionStorage.removeItem("job")
                this.initData();

            }


        }
    }









</script>

<style scoped>

    .table1{
        margin-top: 10px;
    }
</style>