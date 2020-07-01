<template>
    <div style="">


        <!--面包屑导航区-->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>用户管理</el-breadcrumb-item>
            <el-breadcrumb-item>用户列表</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card style="margin-top: 10px">
        <div style="display: flex;justify-content: space-between;margin-top: 5px">
            <!--搜索框和按钮-->
                <div>
                    <el-input
                            placeholder="通过员工号搜索员工"
                            prefix-icon="el-icon-search"
                            style="width: 300px;margin-right: 10px"
                            clearable
                            @clear="getEmpList"
                            @keydown.enter.native="searchEmp"
                            v-model="pid">

                    </el-input>
                    <!--clearable  是否可清空；clear在点击由 clearable 属性生成的清空按钮时触发-->
                    <!--@clear 点击清空按钮时，加载个getEmpList函数，下面的表格中展示数据-->
                    <el-button icon="el-icon-search" type="primary" @click="searchEmp">搜索</el-button>

                </div>


                <!--导入数据、添加员工按钮-->
                <div>

                    <el-button type="success" icon="el-icon-plus" @click="addEmp">
                        添加用户
                    </el-button>
                </div>
        </div>
        <!--用户列表区域-->
        <div style="margin-top: 10px">
            <el-table :data="emps" stripe border style="width:100%" >

                <!--前面的选择框-->
                <el-table-column
                        type="selection"

                        width="55">
                </el-table-column>
                <!--员工数据表展示-->
                <el-table-column
                        prop="personnelname"
                        fixed
                        align="left"
                        label="姓名"
                        width="80">
                </el-table-column>
                <!--列是否固定在左侧或者右侧，true 表示固定在左侧-->
                <el-table-column
                        prop="personnelid"
                        label="工号"
                        align="left"
                        width="85">
                </el-table-column>
                <el-table-column
                        prop="personnelsex"
                        label="性别"
                        align="left"
                        width="80">
                </el-table-column>
                <el-table-column
                        prop="birthday"
                        width="100"
                        align="left"
                        :formatter="dateFormat"
                        label="出生日期">
                </el-table-column>
                <el-table-column
                        prop="id"
                        width="150"
                        align="left"
                        label="身份证号码">
                </el-table-column>


                <el-table-column
                        prop="eMail"
                        width="150"
                        align="left"
                        label="电子邮件">
                </el-table-column>
                <el-table-column
                        prop="phone"
                        width="100"
                        align="left"
                        label="电话号码">
                </el-table-column>

                <el-table-column
                        prop="department"
                        width="100"
                        align="left"
                        label="所属部门">
                </el-table-column>
                <el-table-column
                        prop="job"
                        width="100"
                        label="职位">
                </el-table-column>
                <el-table-column
                        prop="basissalary"
                        width="100"
                        label="基础工资">
                </el-table-column>

                

                <el-table-column
                        prop="beginworkdate"
                        width="100"
                        align="left"
                        :formatter="dateFormat"
                        label="入职日期">
                </el-table-column>
                <el-table-column
                        prop="workstate"
                        width="95"
                        align="left"
                        label="工作状态">
                </el-table-column>
                <el-table-column
                        prop="notworkdate"
                        width="100"
                        align="left"
                        :formatter="dateFormat"
                        label="离职日期">
                </el-table-column>
                <el-table-column
                        prop="workage"
                        width="95"
                        align="left"
                        label="工龄">
                </el-table-column>




                <!--对员工数据编辑、删除按钮-->
                <el-table-column
                        fixed="right"
                        width="150"
                        label="操作">
                    <template slot-scope="scope">
                        <el-button @click="editEmp(scope.row)" style="padding: 3px" rows="mini">编辑</el-button>
                        <el-button @click="deleteEmp(scope.row)" style="padding: 3px" rows="mini" type="danger">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            </div>
            <!--分页-->
            <div style="display: flex;justify-content: flex-end">
                <el-pagination
                        background
                        layout="total,sizes, prev, pager, next, jumper"
                        @size-change="sizeChange"
                        @current-change="currentChange"
                        :page-sizes="[1,2,5,10]"
                        :current-page="page"
                        :page-size="rows"
                        :total="total"></el-pagination>
            </div>
        </el-card>
            <!--添加员工数据对话框-->
            <el-dialog
                    title="添加员工"
                    :visible.sync="dialogVisible"
                    width="80%"
                    >
                <div>
                    <el-form :model="emp" :rules="rules" ref="empRef">
                        <!--第一行-->
                        <el-row>
                            <el-col :span="6">
                                <el-form-item label="姓名" prop="personnelname">
                                        <el-input rows="mini" style="width: 150px" prefix-icon="el-icon-edit" v-model="emp.personnelname" placeholder="请输入员工姓名"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item label="性别" prop="personnelsex">
                                    <el-radio-group v-model="emp.personnelsex">
                                        <el-radio label="男">男</el-radio>
                                        <el-radio label="女">女</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </el-col>

                            <el-col :span="6" >
                                <el-form-item label="出生日期：" prop="birthday" >
                                            <el-date-picker
                                                    v-model="emp.birthday"
                                                    type="date"
                                                    rows="mini"
                                                    value-format="yyyy-MM-dd"
                                                    style="width: 150px"
                                                    placeholder="出生日期">
                                            </el-date-picker>
                                </el-form-item>
                            </el-col>

                            <el-col :span="7">
                                <el-form-item label="身份证号码:" prop="id">
                                    <el-input rows="mini" style="width: 180px" prefix-icon="el-icon-edit"
                                              v-model="emp.id" placeholder="请输入身份证号码"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <!--第二行-->
                        <el-row>


                            <el-col :span="6">
                                <el-form-item label="工号:" prop="personnelid">
                                    <el-input rows="mini" style="width: 150px" prefix-icon="el-icon-edit"
                                              v-model="emp.personnelid" placeholder="工号" ></el-input>
                                </el-form-item>
                            </el-col>

                            <el-col :span="6">
                                <el-form-item label="电子邮箱:" prop="eMail">
                                    <el-input rows="mini" style="width: 150px" prefix-icon="el-icon-message"
                                              v-model="emp.eMail" placeholder="请输入电子邮箱"></el-input>
                                </el-form-item>
                            </el-col>

                            <el-col :span="6">
                                <el-form-item label="职位:" prop="job">
                                    <el-select v-model="emp.job" placeholder="职位" rows="mini" style="width: 150px;">
                                        <el-option
                                                v-for="item in jobs"
                                                :key="item.jobId"
                                                :label="item.jobName"
                                                :value="item.jobName">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="部门:" prop="job">
                                    <el-select v-model="emp.department" placeholder="部门" rows="mini" style="width: 150px;">
                                        <el-option
                                                v-for="item in departments"
                                                :key="item.departmentId"
                                                :label="item.departmentName"
                                                :value="item.departmentName">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>



                        </el-row>

                        <!--第三行-->
                        <el-row>
                            <el-col :span="6">
                                <el-form-item label="入职日期:" prop="beginWorkDate">
                                    <el-date-picker
                                            v-model="emp.beginworkdate"
                                            rows="mini"
                                            type="date"
                                            value-format="yyyy-MM-dd"
                                            style="width: 130px;"
                                            placeholder="入职日期">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="离职:" prop="notWorkDate">
                                    <el-date-picker
                                            v-model="emp.notworkdate"
                                            rows="mini"
                                            type="date"
                                            value-format="yyyy-MM-dd"
                                            style="width: 130px;"
                                            placeholder="离职日期">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>

                            <el-col :span="6">
                                <el-form-item label="工作状态" prop="workState">
                                    <el-radio-group v-model="emp.workstate">
                                        <el-radio label="在职">是</el-radio>
                                        <el-radio label="离职">否</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </el-col>

                            <el-col :span="6">
                                <el-form-item label="工龄" prop="workage">
                                    <el-input rows="mini" style="width: 150px" prefix-icon="el-icon-edit" v-model="emp.workage" placeholder="请输入员工工龄"></el-input>
                                </el-form-item>
                            </el-col>


                        </el-row>
                        <el-row>
                            <el-col :span="7">
                                <el-form-item label="电话号码:" prop="phone">
                                    <el-input rows="mini" style="width: 200px" prefix-icon="el-icon-phone"
                                              v-model="emp.phone" placeholder="电话号码"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="7">
                            <el-form-item label="基础工资:" prop="basissalary">
                                <el-input rows="mini" style="width: 200px" prefix-icon="el-icon-money"
                                          v-model="emp.basissalary" placeholder="请输入"></el-input>
                            </el-form-item>
                        </el-col>
                        </el-row>
                    </el-form>
                </div>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="submitEmp">确 定</el-button>
                 </span>
            </el-dialog>
            <!--编辑员工数据对话框-->
            <el-dialog
                    title="编辑员工"
                    :visible.sync="bjdialogVisible"
                    width="80%"
            >
                <div>
                    <el-form :model="editemp" :rules="rules" ref="empRef">
                        <!--第一行-->
                        <el-row>
                            <el-col :span="6">
                                <el-form-item label="姓名" prop="personnelname">
                                    <el-input rows="mini" style="width: 150px" prefix-icon="el-icon-edit" v-model="editemp.personnelname" placeholder="请输入员工姓名"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item label="性别" prop="personnelsex">
                                    <el-radio-group v-model="editemp.personnelsex">
                                        <el-radio label="男">男</el-radio>
                                        <el-radio label="女">女</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </el-col>

                            <el-col :span="6" >
                                <el-form-item label="出生日期：" prop="birthday" >
                                    <el-date-picker
                                            v-model="editemp.birthday"
                                            type="date"
                                            rows="mini"
                                            value-format="yyyy-MM-dd"
                                            style="width: 150px"
                                            placeholder="出生日期">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>

                            <el-col :span="7">
                                <el-form-item label="身份证号码:" prop="id">
                                    <el-input rows="mini" style="width: 180px" prefix-icon="el-icon-edit"
                                              v-model="editemp.id" placeholder="请输入身份证号码"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <!--第二行-->
                        <el-row>


                            <el-col :span="6">
                                <el-form-item label="工号:" prop="personnelid">
                                    <el-input rows="mini" style="width: 150px" prefix-icon="el-icon-edit"
                                              v-model="editemp.personnelid" placeholder="工号" ></el-input>
                                </el-form-item>
                            </el-col>

                            <el-col :span="6">
                                <el-form-item label="电子邮箱:" prop="eMail">
                                    <el-input rows="mini" style="width: 150px" prefix-icon="el-icon-message"
                                              v-model="editemp.eMail" placeholder="请输入电子邮箱"></el-input>
                                </el-form-item>
                            </el-col>

                            <el-col :span="6">
                                <el-form-item label="职位:" prop="job">
                                    <el-select v-model="editemp.job" placeholder="职位" rows="mini" style="width: 150px;">
                                        <el-option
                                                v-for="item in jobs"
                                                :key="item.jobId"
                                                :label="item.jobName"
                                                :value="item.jobName">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="部门:" prop="department">
                                    <el-select v-model="editemp.department" placeholder="部门" rows="mini" style="width: 150px;">
                                        <el-option
                                                v-for="item in departments"
                                                :key="item.departmentId"
                                                :label="item.departmentName"
                                                :value="item.departmentName">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>



                        </el-row>

                        <!--第三行-->
                        <el-row>
                            <el-col :span="6">
                                <el-form-item label="入职日期:" prop="beginWorkDate">
                                    <el-date-picker
                                            v-model="editemp.beginworkdate"
                                            rows="mini"
                                            type="date"
                                            value-format="yyyy-MM-dd"
                                            style="width: 130px;"
                                            placeholder="入职日期">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="离职:" prop="notWorkDate">
                                    <el-date-picker
                                            v-model="editemp.notworkdate"
                                            rows="mini"
                                            type="date"
                                            value-format="yyyy-MM-dd"
                                            style="width: 130px;"
                                            placeholder="离职日期">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>

                            <el-col :span="6">
                                <el-form-item label="工作状态" prop="workState">
                                    <el-radio-group v-model="editemp.workstate">
                                        <el-radio label="在职">是</el-radio>
                                        <el-radio label="离职">否</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </el-col>

                            <el-col :span="6">
                                <el-form-item label="工龄" prop="workage">
                                    <el-input rows="mini" style="width: 150px" prefix-icon="el-icon-edit" v-model="editemp.workage" placeholder="请输入员工工龄"></el-input>
                                </el-form-item>
                            </el-col>


                        </el-row>
                        <el-row>
                            <el-col :span="7">
                                <el-form-item label="电话号码:" prop="phone">
                                    <el-input rows="mini" style="width: 200px" prefix-icon="el-icon-phone"
                                              v-model="editemp.phone" placeholder="电话号码"></el-input>
                                </el-form-item>
                            </el-col><el-col :span="7">
                                <el-form-item label="基础工资:" prop="basissalary">
                                    <el-input rows="mini" style="width: 200px" prefix-icon="el-icon-money"
                                              v-model="editemp.basissalary" placeholder="基础工资"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </div>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="bjdialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="submitBjEmp">确 定</el-button>
                 </span>
            </el-dialog>
        </div>

</template>

<script>

    import * as fecha from "element-ui/lib/utils/date";
    export default {
        personnelName: "EmpBasic",
        data(){
            return{
                emps:[],
                editemp:{},
                total:0,
                page:1,
                rows:6,
                pid:'',
                dialogVisible:false,
                bjdialogVisible:false,
                totalpage:'',

                title:'',
                emp:'',
                jobs:[],
                departments:[],
                perid:'',
                rules: {
                    personnelname: [{required: true, message: '请输入用户名', trigger: 'blur'}],
                    personnelsex: [{required: true, message: '请输入性别', trigger: 'blur'}],
                    birthday: [{required: true, message: '请输入出生日期', trigger: 'blur'}],
                    id: [{required: true, message: '请输入身份证号码', trigger: 'blur'}, {
                        pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,
                        message: '身份证号码格式不正确',
                        trigger: 'blur'
                    }],
                    personnelid: [{required: true, message: '请输入工号', trigger: 'blur'}],

                    eMail: [{required: true, message: '请输入邮箱地址', trigger: 'blur'}, {
                        type: 'email',
                        message: '邮箱格式不正确',
                        trigger: 'blur'
                    }],
                    phone: [{required: true, message: '请输入电话号码', trigger: 'blur'}],

                    department: [{required: true, message: '请输入部门名称', trigger: 'blur'}],

                    job: [{required: true, message: '请输入职位', trigger: 'blur'}],

                    beginworkdate: [{required: true, message: '请输入入职日期', trigger: 'blur'}],
                    notWorkDate: [{required: false, message: '请输入离职日期', trigger: 'blur'}],
                    workState: [{required: true, message: '请输入工作状态', trigger: 'blur'}],
                    basissalary:[{required: true, message: '请输入基础工资', trigger: 'blur'}],

                    workage: [{required: true, message: '请输入工龄', trigger: 'blur'}],
                }
            }
        },

        mounted (){
            this.getEmpList();
            this.initData();
            this.getJob()
            this.getdep()
        },

        methods:{
            /*good*/
            async initData(){
              if(!window.sessionStorage.getItem('job')){
                 const {data: res} =  await this.$http.post('/job/findAll')
                  if(res){
                      window.sessionStorage.setItem("job", JSON.stringify(res.data));
                  }

                  //this.job=JSON.parse(window.sessionStorage.getItem("job"))
              }
                if(!window.sessionStorage.getItem('department')){
                    const {data: res} =  await this.$http.post('department/findAll')
                    if(res){
                        window.sessionStorage.setItem("department", JSON.stringify(res.data));
                    }
                }
            },

            /*加载员工列表 good*/
           async getEmpList(){

                    let url='/personnelinfo/findByPage?page='+this.page+'&rows='+this.rows;
              const {data: res} = await this.$http.post(url);

              this.emps=res.personnelinfos;
               this.totalpage=res.totalpage;
              this.total=res.totals;

            },

            /*搜索员工 true*/
            async searchEmp(){

                let url = '/personnelinfo/findone?personnelid='+this.pid;
              const {data: res} = await this.$http.post(url);
              var em=new Array(res)

                this.emps=em


            },
            /*加载空员工列表 good*/
            emptyEmp() {
                this.emp = {
                    personnelname: "",
                    personnelsex: "",
                    birthday: "",
                    id: "",
                    eMail: "",
                    phone: "",
                    department: null,
                    job: "",
                    beginworkdate: "",
                    workstate: "",
                    notworkdate: null,
                    workage: null,
                    basissalary:"",
                }

            },
            /*分页 当前页*/
            currentChange(currentPage){
                this.page = currentPage;
                this.getEmpList();
            },
            /*分页 当前页加载员工数,*/
            sizeChange(currentSize){
                this.rows =  currentSize;
                this.getEmpList();
            },


            /*添加员工方法 对话框true*/
            addEmp(){
                this.emptyEmp();
                this.title = '添加员工';
                //this.getMaxWordID();
                this.dialogVisible = true;

            },
            /*删除员工方法 good*/
           async deleteEmp(data){
                const confirmResult = await this.$confirm(
                    '此操作将永久删除该【'+data.personnelname+'】员工, 是否继续?',
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
                const {data:res} = await this.$http.post('/personnelinfo/delete?personnelid='+data.personnelid);
                this.getEmpList();
           },
            /*编辑员工对话框*/
            editEmp(data){



                this.editemp = data;
                /*弹框*/
                this.bjdialogVisible = true;

            },
            /*添加员工数据   --添加和修改--*/
            async submitEmp(){


                /*id来判端添加（有id更新）还是更新*/
               const {data: res}  = await this.$http.post('/personnelinfo/save',this.emp);

               if (res){
                   this.dialogVisible = false;
                   this.getEmpList();
               }


            },
            /*更新员工数据*/
            async submitBjEmp(){


                /*id来判端添加（有id更新）还是更新*/
               const {data: res}  = await this.$http.post('/personnelinfo/update',this.editemp);

               if (res){
                   this.bjdialogVisible = false;
                   this.getEmpList();
               }


            },
            getdep(){
                var mm=window.sessionStorage.getItem("department")
                this.departments= JSON.parse(mm) ;
            },
            getJob(){
                var mm=window.sessionStorage.getItem("job")
                this.jobs= JSON.parse(mm) ;
            },

            dateFormat(row, column, cellValue) {
                return cellValue ? fecha.format(new Date(cellValue), 'yyyy-MM-dd') : '';
            },


        }
    }
</script>

<style scoped>

</style>