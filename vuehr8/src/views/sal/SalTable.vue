<template>
    <!--<div>工资表管理</div>-->

    <div>
        <!--面包屑导航区域-->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>工资管理</el-breadcrumb-item>
            <el-breadcrumb-item>工资表管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card style="margin-top: 10px;width: 70%">
            <div style="display: flex;justify-content: space-between">

                <div>
                    <el-input
                            placeholder="通过员工号搜索员工"
                            prefix-icon="el-icon-search"
                            style="width: 300px;margin-right: 10px"
                            clearable
                            @clear="initData"
                            v-model="Salid"
                            ></el-input>
                    <el-button icon="el-icon-search" type="primary" @click="getSalTable">搜索</el-button>
                </div>
                <div style="margin-left: 10px">
                    <el-select v-model="deps" placeholder="部门"  style="width: 150px;">
                        <el-option
                                v-for="item in Mvdepartment"
                                :key="item.departmentId"
                                :label="item.departmentName"
                                :value="item.departmentName">
                        </el-option>
                    </el-select>

                    <el-button icon="el-icon-search" type="primary" @click="salsSearch" style="margin-left: 10px">
                        搜索
                    </el-button>
                </div>

                <div style="margin-left: 70px">
                    <el-button type="primary" icon="el-icon-plus" @click="addSalsDialogVisible=true">
                        添加操作
                    </el-button>
                </div>
         </div>
            <div style="margin-top: 10px">


                <el-table :data="empSals" stripe border style="width: 100%" >

                    <el-table-column
                            prop="personnelid"
                            align="left"
                            label="工号"
                            width="90">
                    </el-table-column>
                    <el-table-column
                            prop="personnelname"

                            align="left"
                            label="姓名"
                            width="90">
                    </el-table-column>

                    <el-table-column
                            prop="department"

                            align="left"
                            label="部门"
                            width="90">
                    </el-table-column>

                    <el-table-column
                            prop="bonus"

                            align="left"
                            label="奖金"
                            width="90">
                    </el-table-column>

                    <el-table-column
                            prop="basissalary"

                            align="left"
                            label="基本工资"
                            width="90">
                    </el-table-column>
                    <el-table-column
                        prop="trafficsalary"

                        align="left"
                        label="交通补助"
                        width="90">
                </el-table-column>
                    <el-table-column
                        prop="allsalary"

                        align="left"
                        label="应发工资"
                        width="90">
                </el-table-column>

                    <el-table-column   label="操作" width="150">
                        <template slot-scope="scope">
                            <el-button @click="updateSals(scope.row)" style="padding: 3px"  >编辑
                            </el-button><el-button @click="deleteSals(scope.row)" style="padding: 3px" type="danger">删除
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>

            </div>
        </el-card>
        <!-- 添加用户的对话框 -->
        <el-dialog title="添加工资表" :visible.sync="addSalsDialogVisible" width="50%">
            <!-- 内容主体 -->
            <el-form
                    :model="addSalForms"

                    label-width="100px"
            >
                <el-form-item label="工号" prop="personnelid">
                    <el-input v-model="addSalForms.personnelid"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="personnelname">
                    <el-input v-model="addSalForms.personnelname"></el-input>
                </el-form-item>

                <el-form-item label="部门" prop="department">
                    <el-select v-model="addSalForms.department" placeholder="部门" size="mini" style="width: 150px;">
                        <el-option
                                v-for="item in Mvdepartment"
                                :key="item.departmentId"
                                :label="item.departmentName"
                                :value="item.departmentName">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="奖金" prop="bonus">

                    <el-input
                            placeholder="请输入"
                            v-model="addSalForms.bonus"
                            >
                    </el-input>

                </el-form-item>
                <el-form-item label="基本工资" prop="basissalary">
                    <el-input v-model="addSalForms.basissalary"></el-input>
                </el-form-item>
                <el-form-item label="交通补助" prop="trafficsalary">
                    <el-input v-model="addSalForms.trafficsalary"></el-input>
                </el-form-item>
                <el-form-item label="应发工资" prop="allsalary">
                    <el-input v-model="addSalForms.allsalary"></el-input>
                </el-form-item>



            </el-form>
            <span slot="footer" class="dialog-footer">
            <el-button @click="addSalsDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addSals">确 定</el-button>
          </span>
        </el-dialog>
        <!-- 编辑工资表的对话框 -->
        <el-dialog title="编辑工资表" :visible.sync="addSaDialogVisible" width="50%" >
            <!-- 内容主体 -->
            <el-form
                    :model="addSalForms"

                    label-width="100px"
            >
                <el-form-item label="工号" prop="personnelid">
                    <el-input v-model="addSalForms.personnelid"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="personnelname">
                    <el-input v-model="addSalForms.personnelname"></el-input>
                </el-form-item>

                <el-form-item label="部门" prop="department">
                    <el-select v-model="addSalForms.department" placeholder="部门" size="mini" style="width: 150px;">
                        <el-option
                                v-for="item in Mvdepartment"
                                :key="item.departmentId"
                                :label="item.departmentName"
                                :value="item.departmentName">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="奖金" prop="bonus">

                    <el-input
                            placeholder="请输入"
                            v-model="addSalForms.bonus"
                            >
                    </el-input>

                </el-form-item>
                <el-form-item label="基本工资" prop="basissalary">
                    <el-input v-model="addSalForms.basissalary"></el-input>
                </el-form-item>
                <el-form-item label="交通补助" prop="trafficsalary">
                    <el-input v-model="addSalForms.trafficsalary"></el-input>
                </el-form-item>
                <el-form-item label="应发工资" prop="allsalary">
                    <el-input v-model="addSalForms.allsalary"></el-input>
                </el-form-item>



            </el-form>
            <span slot="footer" class="dialog-footer">
            <el-button @click="addSalsDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="submitSal">确 定</el-button>
          </span>
        </el-dialog>

    </div>


</template>

<script>
    export default {
        name: "SalTable",
        data(){
            return{
                empSals:[],
                Salid:'',
                addSalsDialogVisible:false,
                addSaDialogVisible:false,
                deps:'',
                Mvdepartment:[],

                addSalForms:{
                    personnelid:'',
                    personnelname:'',
                    department:'',
                    bonus:'',
                    basissalary:'',
                    trafficsalary:'',
                    allsalary:'',
                }

            }
        },
        mounted(){
            this.initData()
            this.getDepartment()
        },
        methods:{
            /*true*/
            async initData(){
                const {data:res} =await this.$http.post('Salary/findAll');
                this.empSals=res.data;

            },
            /* 查询true*/
            async getSalTable(){
                const {data:res} =await  this.$http.post('Salary/findone?personnelid='+this.Salid);
                var op = new Array(res)

                this.empSals= op;
            },
            /*true*/
            async deleteSals(data){

                const confirmResult = await this.$confirm(
                    '此操作将永久删除该【' + data.personnelname + '】, 是否继续?',
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
                const {data:res} = await this.$http.post('/Salary/delete?personnelid='+data.personnelid);

                this.initData();
            },
            /*true */
            async addSals(){
                const { data: res } = await this.$http.post('/Salary/save', this.addSalForms);
                // console.log(this.addUserForm)
                this.addSalsDialogVisible = false
                this.initData();
                this.initda()
            },
             getDepartment(){


                var mm=window.sessionStorage.getItem("department")
                this.Mvdepartment= JSON.parse(mm) ;

            },

            async updateSals(data){
                this.addSalForms=data;
                this.addSaDialogVisible=true
            },
            /*按部门工资查询*/
            async salsSearch(){
                const {data:res} =await this.$http.post('Salary/findsalarybydepartment?department='+this.deps);
                this.empSals=res.data;

            },
            async submitSal(){
                const {data:res} =await this.$http.post('Salary/update',this.addSalForms);
                this.addSaDialogVisible=false
                this.initData()
            },
            initda(){
                this.addSalForms={
                    personnelid:'',
                        personnelname:'',
                        department:'',
                        bonus:'',
                        basissalary:'',
                        trafficsalary:'',
                        allsalary:'',
                }
            }

        }
    }
</script>

<style scoped>

</style>