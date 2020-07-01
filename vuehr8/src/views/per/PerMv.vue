<template>
    <!--<div>员工调动</div>-->


    <div>
        <!--面包屑导航区域-->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>人事管理</el-breadcrumb-item>
            <el-breadcrumb-item>员工调动</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card style="width: 80%;margin-top: 10px">
        <div style="display: flex;justify-content: space-between">

            <div>
                <el-input
                        placeholder="通过员工名搜索员工"
                        prefix-icon="el-icon-search"
                        style="width: 300px;margin-right: 10px"
                        clearable
                        @clear="initMvData"
                        v-model="Mvid"
                ></el-input>
                <el-button icon="el-icon-search" type="primary" @click="getMvTable">搜索</el-button>
            </div>

            <div>

                <el-button type="primary"  @click="addMvDialogVisible=true">
                    <i icon="el-icon-plus" aria-hidden="true"></i>
                    添加员工移动
                </el-button>

            </div>
        </div>

        <div style="margin-top: 10px">


                <el-table :data="Mvs" stripe border style="width:100%" >

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
                            prop="personnelid"
                            align="left"
                            label="员工编号"
                            width="90"
                            fixed>

                    </el-table-column>

                    <el-table-column
                            prop="removedate"
                            align="left"
                            label="调动日期"
                            :formatter="dateFormat"
                            width="100"
                            fixed>

                    </el-table-column>

                    <el-table-column
                            prop="afterremovedepartment"
                            align="left"
                            label="调后部门"
                            width="90"
                            fixed>

                    </el-table-column>

                    <el-table-column
                            prop="afterremovejob"
                            align="left"
                            label="调后职位"
                            width="100"
                            fixed>

                    </el-table-column>
                    <el-table-column
                            prop="removereason"
                            align="left"
                            label="调动原因"
                            width="90"
                            fixed>

                    </el-table-column>
                    <el-table-column
                            prop="remark"
                            align="left"
                            label="备注"
                            width="150"
                            fixed>

                    </el-table-column>

                    <el-table-column
                            label="操作" width="170">
                        <template slot-scope="scope">
                            <!--<el-button size="small"  @click="updateMv(scope.row)">编辑</el-button>-->
                            <el-button size="small" type="danger" @click="deleteMv(scope.row)">删除</el-button>
                        </template>
                    </el-table-column>

                </el-table>
        </div>
        </el-card>
        <!-- 添加用户的对话框 -->
        <el-dialog title="添加员工移动" :visible.sync="addMvDialogVisible" width="30%" >
            <!-- 内容主体 -->
            <el-form
                    :model="addMvForm"

                    label-width="70px"
            >
                <el-form-item label="员工编号" prop="personnelid" >
                    <el-input v-model="addMvForm.personnelid"></el-input>
                </el-form-item>
                <el-form-item label="调动日期" prop="removedate">

                    <el-date-picker
                            v-model="addMvForm.removedate"
                            type="date"
                            rows="mini"
                            value-format="yyyy-MM-dd"

                            placeholder="调动日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="调后部门" prop="afterremovedepartment">

                    <el-select v-model="addMvForm.afterremovedepartment" placeholder="部门" size="mini" style="width: 150px;">
                        <el-option
                                v-for="item in Mvdepartment"
                                :key="item.departmentId"
                                :label="item.departmentName"
                                :value="item.departmentName">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="调后职位" prop="afterremovejob">

                    <el-select v-model="addMvForm.afterremovejob" placeholder="职位" size="mini" style="width: 150px;">
                        <el-option
                                v-for="item in Mvjob"
                                :key="item.jobId"
                                :label="item.jobName"
                                :value="item.jobName">
                        </el-option>
                    </el-select>

                </el-form-item>
                <el-form-item label="调动原因" prop="removereason" >
                    <el-input v-model="addMvForm.removereason"></el-input>
                </el-form-item>
                <el-form-item label="备注" prop="remark" >
                    <el-input v-model="addMvForm.remark"></el-input>
                </el-form-item>
                <el-form-item label="操作员" prop="op" >

                    <el-input
                            placeholder="请输入"
                            v-model="addMvForm.op"
                            >
                    </el-input>

                </el-form-item>



            </el-form>
            <span slot="footer" class="dialog-footer">
            <el-button @click="addMvDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addMv">确 定</el-button>
          </span>
        </el-dialog>
    <!-- 编辑用户的对话框 -->
        <el-dialog title="编辑员工移动" :visible.sync="addMDialogVisible" width="30%">
            <!-- 内容主体 -->
            <el-form
                    :model="editMvForm"

                    label-width="70px"
            >
                <el-form-item label="员工编号" prop="personnelid" >
                    <el-input v-model="editMvForm.personnelid"></el-input>
                </el-form-item>
                <el-form-item label="调动日期" prop="removedate">

                    <el-date-picker
                            v-model="editMvForm.removedate"
                            type="date"
                            rows="mini"
                            value-format="yyyy-MM-dd"

                            placeholder="调动日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="调后部门" prop="afterremovedepartment">

                    <el-select v-model="editMvForm.afterremovedepartment" placeholder="部门" size="mini" style="width: 150px;">
                        <el-option
                                v-for="item in Mvdepartment"
                                :key="item.departmentId"
                                :label="item.departmentName"
                                :value="item.departmentName">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="调后职位" prop="afterremovejob">

                    <el-select v-model="editMvForm.afterremovejob" placeholder="职位" size="mini" style="width: 150px;">
                        <el-option
                                v-for="item in Mvjob"
                                :key="item.jobId"
                                :label="item.jobName"
                                :value="item.jobName">
                        </el-option>
                    </el-select>

                </el-form-item>
                <el-form-item label="调动原因" prop="removereason" >
                    <el-input v-model="editMvForm.removereason"></el-input>
                </el-form-item>
                <el-form-item label="备注" prop="remark" >
                    <el-input v-model="editMvForm.remark"></el-input>
                </el-form-item>
                <el-form-item label="操作员" prop="op" >

                    <el-input
                            placeholder="请输入"
                            v-model="editMvForm.op"
                            >
                    </el-input>

                </el-form-item>



            </el-form>
            <span slot="footer" class="dialog-footer">
            <el-button @click="addMDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="submitMv">确 定</el-button>
          </span>
        </el-dialog>

    </div>





</template>

<script>

    import * as fecha from "element-ui/lib/utils/date";
    export default {
        name: "PerMv",
        data(){
            return {
                Mvs:[],
                Mvid:'',
                addMvForm:{
                    no:'',
                    personnelid:'',
                    removedate:'',
                    afterremovedepartment:'',
                    afterremovejob:'',
                    removereason:'',
                    remark:'',
                    op:''
                },
                editMvForm:{},
                addMvDialogVisible:false,
                addMDialogVisible:false,
                Mvjob:[],
                Mvdepartment:[],


            }
        },
        mounted(){
            this.initMvData();
            this.getJob()
            this.getDepartment()
           /* this.getOpInMv()*/
        },
        methods:{
            /*查询所有 true*/
            async initMvData(){
                const {data:res} =await this.$http.post('/personnelremove/findAll');


                this.Mvs= res.data;

            },
            /*查询一个*/
            async getMvTable(){
                const {data:res} =await  this.$http.post('/personnelremove/findbyid?id='+this.Mvid);
                /*alert(typeof res)*/
                var op = new Array(res)
                /*alert(typeof op)*/
                this.Mvs= res.data;
            },
            /*true 添加*/
            async addMv(){
                const {data:res} =await this.$http.post('/personnelremove/update',this.addMvForm);
                this.addMvDialogVisible=false
                this.initMvData()
            },

            async getJob(){
                var mm=window.sessionStorage.getItem("job")
                this.Mvjob= JSON.parse(mm) ;

            },
            async getDepartment(){
                var mm=window.sessionStorage.getItem("department")
                this.Mvdepartment= JSON.parse(mm) ;

            },
           /* getOpInMv(){
                var op= window.sessionStorage.getItem("opid")
                this.addMvForm.op=op
            },*/
            async deleteMv(data){
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
               /* alert(data.no)*/
                const {data:res} = await this.$http.post('/personnelremove/deletebyno?no='+data.no);

                this.initMvData();
            },
            updateMv(data){
                this.editMvForm=data
                this.addMDialogVisible=true

            },
            async submitMv(){
                const {data:res} =await this.$http.post('personnelremove/update',this.editMvForm);
                this.addMDialogVisible=false
                this.initMvData()
            },
            dateFormat(row, column, cellValue) {
                return cellValue ? fecha.format(new Date(cellValue), 'yyyy-MM-dd') : '';
            },
        },
    }
</script>

<style scoped>

</style>