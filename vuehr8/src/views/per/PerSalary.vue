<template>
    <!--<div>员工调薪</div>-->

    <div>
        <!--面包屑导航区域-->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>人事管理</el-breadcrumb-item>
            <el-breadcrumb-item>员工调薪</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card style="margin-top: 10px;width: 80%">
            <div style="display: flex;justify-content: space-between">

                <div>
                    <el-input
                            placeholder="通过员工号搜索员工"
                            prefix-icon="el-icon-search"
                            style="width: 300px;margin-right: 10px"
                            clearable
                            @clear="initSaleData"
                            v-model="Sid"
                    ></el-input>
                    <el-button icon="el-icon-search" type="primary" @click="getSalTable">搜索</el-button>
                </div>
                <div>
                    <el-button type="primary"  @click="addSalDialogVisible=true">
                        <i icon="el-icon-plus" aria-hidden="true"></i>
                        添加员工薪水
                    </el-button>
                </div>
                </div>


            <div style="margin-top: 10px">


                    <el-table :data="empSas" stripe border style="width:100%" >

                        <!--前面的选择框-->
                        <el-table-column
                                type="selection"
                                width="55">
                        </el-table-column>

                        <el-table-column
                                prop="no"
                                align="left"
                                label="编号"
                                width="90"
                                fixed>

                        </el-table-column><el-table-column
                                prop="personnelid"
                                align="left"
                                label="员工编号"
                                width="90"
                                fixed>

                        </el-table-column>

                        <el-table-column
                                prop="adjustsalarydate"
                                align="left"
                                label="调薪日期"
                                :formatter="dateFormat"
                                width="100"
                                fixed>

                        </el-table-column>

                        <el-table-column
                                prop="beforeadjustsalary"
                                align="left"
                                label="调前薪资"
                                width="90"
                                fixed>

                        </el-table-column>

                        <el-table-column
                                prop="afteradjustsalary"
                                align="left"
                                label="调后薪资"
                                width="90"
                                fixed>

                        </el-table-column>
                        <el-table-column
                                prop="adjustsalaryreason"
                                align="left"
                                label="调薪原因"
                                width="90"
                                fixed>

                        </el-table-column>
                        <el-table-column
                                prop="remark"
                                align="left"
                                label="备注"
                                width="120"
                                fixed>

                        </el-table-column>
                        <el-table-column
                                prop="op"
                                align="left"
                                label="操作员"
                                width="90"
                                fixed>

                        </el-table-column>

                        <el-table-column
                                label="操作" width="100">
                            <template slot-scope="scope">
                                <el-button size="small" type="danger" @click="deleteSal(scope.row)">删除</el-button>
                            </template>
                        </el-table-column>


                    </el-table>


            </div>
        </el-card>
        <!-- 添加用户的对话框 -->
        <el-dialog title="添加员工薪水" :visible.sync="addSalDialogVisible" width="50%" >
            <!-- 内容主体 -->
            <el-form
                    :model="addSalForm"

                    label-width="100px"
            >
                <el-form-item label="员工编号" prop="personnelid">
                    <el-input v-model="addSalForm.personnelid"></el-input>
                </el-form-item>
                <el-form-item label="调薪日期" prop="adjustsalarydate">
                    <el-date-picker
                            v-model="addSalForm.adjustsalarydate"
                            type="date"
                            rows="mini"
                            value-format="yyyy-MM-dd"

                            placeholder="调薪日期">
                    </el-date-picker>
                </el-form-item>
               <!-- <el-form-item label="调前薪资" prop="beforeadjustsalary">
                    <el-input v-model="addSalForm.beforeadjustsalary"></el-input>
                </el-form-item>-->
                <el-form-item label="调后薪资" prop="afteradjustsalary">
                    <el-input v-model="addSalForm.afteradjustsalary"></el-input>
                </el-form-item>
                <el-form-item label="调薪原因" prop="adjustsalaryreason">
                    <el-input v-model="addSalForm.adjustsalaryreason"></el-input>
                </el-form-item>

                <el-form-item label="操作员" prop="op">

                    <el-input
                            placeholder="请输入"
                            v-model="addSalForm.op"
                            >
                    </el-input>

                </el-form-item>
                <el-form-item label="备注" prop="remark">
                    <el-input v-model="addSalForm.remark"></el-input>
                </el-form-item>



            </el-form>
            <span slot="footer" class="dialog-footer">
            <el-button @click="addSalDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addSal">确 定</el-button>
          </span>
        </el-dialog>

    </div>







</template>

<script>

    import * as fecha from "element-ui/lib/utils/date";
    export default {
        name: "PerSalary",
       data(){
            return{
                Sid:'',
                empSas:[],
                pid:'',
                addSalDialogVisible:false,
                addSalForm:{
                    personnelid:'',
                    adjustsalarydate:'',
                    beforeadjustsalary:'',
                    afteradjustsalary:'',
                    adjustsalaryreason:'',
                    remark:'',
                    op:''
                }
            }
       },
        mounted(){
            this.initSaleData();
            /*this.getOpInSal();*/
        },
        methods:{
            /*所有 true*/
            async initSaleData(){
                const {data:res} =await this.$http.post('adjustsalary/findAll');
                this.empSas=res.data;

            },
            /*查找一个 true*/
            async getSalTable(){
                
                const {data:res} =await  this.$http.post('adjustsalary/findbyid?id='+this.Sid);
                console.log(res)
                this.empSas=res.data;
            },
            /*删除 true*/
            async deleteSal(data){
                const confirmResult = await this.$confirm(
                    '此操作将永久删除, 是否继续?',
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
                const {data:res} = await this.$http.post('/adjustsalary/delete?personnelid='+data.personnelid);
                this.initSaleData();
            },
            /*添加 true*/
            async addSal(){
                const { data: res } = await this.$http.post('/adjustsalary/update', this.addSalForm);
               this.addSalDialogVisible=false
                this.initSaleData()

            },
            /*/!*获取操作员 true*!/
            getOpInSal(){
                var op= window.sessionStorage.getItem("opid")

                this.addSalForm.op=op
            }*/
            dateFormat(row, column, cellValue) {
                return cellValue ? fecha.format(new Date(cellValue), 'yyyy-MM-dd') : '';
            },
        },
    }
</script>

<style scoped>

</style>