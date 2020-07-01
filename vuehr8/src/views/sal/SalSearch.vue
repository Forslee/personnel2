<template>

<!--工资表查询-->

    <div>
        <!--面包屑导航区域-->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>工资管理</el-breadcrumb-item>
            <el-breadcrumb-item>工资表查询</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card style="margin-top: 10px;width: 70%">
        <!--搜索框及按钮-->
        <div style="display: flex;">


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

            <!--工资表格-->
            <div style="margin-top: 10px">

                <el-table :data="sals" stripe border style="width: 80%">
                    <el-table-column
                            prop="personnelid"
                            align="left"
                            label="工号"
                            width="100">
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
                    </el-table-column> <el-table-column
                            prop="trafficsalary"

                            align="left"
                            label="交通补助"
                            width="90">
                    </el-table-column> <el-table-column
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
        name: "SalSearch",
        data(){
            return{
                sals:[],
                deps:'',
                Mvdepartment:[],
            }
        },
        mounted(){

            this.getDepartment()
        },
        methods:{
            /*按部门工资查询*/
            async salsSearch(){
                const {data:res} =await this.$http.post('Salary/findSalary_processing?department='+this.deps);
                this.sals=res.data;
            },
            async getDepartment(){


                var mm=window.sessionStorage.getItem("department")
                this.Mvdepartment= JSON.parse(mm) ;

            },
        }


    }
</script>

<style scoped>

</style>