<template><!--
    <div>月末处理</div>-->
    <!--对该月该部门员工工资进行增删改查的功能。-->
    <div>

        <!--面包屑导航区域-->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>工资管理</el-breadcrumb-item>
            <el-breadcrumb-item>月末处理</el-breadcrumb-item>
        </el-breadcrumb>

        <el-card style="margin-top: 10px;width: 70%">
        <!--搜索框及按钮-->
        <div style="display: flex; justify-content: space-between">

            <div>
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
            <div>
                <el-input
                        placeholder="通过员工号"
                        prefix-icon="el-icon-search"
                        style="width: 300px;margin-right: 10px;margin-left: 10px"
                        clearable
                        @clear="initData"
                        v-model="Salid"

                ></el-input>
                <el-button icon="el-icon-search" type="primary" @click="initSalMonth">月末处理</el-button>
            </div>


        </div>


            <!--工资表格-->
            <div style="margin-top: 10px">

                <el-table :data="sals" stripe border style="width: 80%">
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


                </el-table>

            </div>
        </el-card>






    </div>







</template>

<script>
    export default {
        name: "SalMonth",

        data(){
            return{
                deps:'',
                Mvdepartment:[],
                sals:[],
                Salid:'',
            }
        },
        mounted(){
            this.getDepartment()
            this.initData()
        },
        methods:{
            async initData(){
                const {data:res} =await this.$http.post('Salary/findAll');
                this.empSals=res.data;

            },
            async getDepartment(){


                var mm=window.sessionStorage.getItem("department")
                this.Mvdepartment= JSON.parse(mm) ;

            },
            /*按部门工资查询*/
            async salsSearch(){
                const {data:res} =await this.$http.post('Salary/findsalary_no_processing?department='+this.deps);
                this.sals=res.data;
            },
            async initSalMonth(){
                const {data:res} =await this.$http.post('Salary/end_month_processing?personnelid='+this.Salid);
                this.sals=res.data;
            },

        }
    }
</script>

<style scoped>

</style>