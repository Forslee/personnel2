<template>
    <!--<div>人事信息统计</div>-->
    <div>

        <!--面包屑导航区域-->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>人事管理</el-breadcrumb-item>
            <el-breadcrumb-item>人事信息</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card style="margin-top: 10px">
        <div style="display: flex;justify-content: space-between">
            <div>
                <el-select v-model="deps" placeholder="职位"  style="width: 150px;margin-right: 10px">
                    <el-option
                            v-for="item in jobs"
                            :key="item.jobId"
                            :label="item.jobName"
                            :value="item.jobName">
                    </el-option>
                </el-select>

                <el-button icon="el-icon-search" type="primary" @click="searchDep" >
                    搜索
                </el-button>
            </div>
            <div>
                <el-select v-model="sexVa" placeholder="请选择性别" style="margin-right: 10px">
                    <el-option
                            v-for="item in options"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
                <el-button icon="el-icon-search" type="primary" @click="searchSex" >
                    搜索
                </el-button>
            </div>
            <div>
                <el-input
                        placeholder="通过员工薪水搜索员工"
                        prefix-icon="el-icon-search"
                        style="width: 300px;margin-right: 10px"
                        v-model="sals"
                ></el-input>
                <el-button icon="el-icon-search" type="primary" @click="getemp">搜索</el-button>
            </div>
        </div>

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
                        width="90">
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
                        width="85">
                </el-table-column>
                <el-table-column
                        prop="birthday"
                        width="95"
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
                        width="180"
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
                        width="95"
                        align="left"
                        :formatter="dateFormat"
                        label="入职日期">
                </el-table-column>
                <el-table-column
                        prop="workstate"
                        width="95"
                        align="left"
                        label="在职状态">
                </el-table-column>
                <el-table-column
                        prop="notworkdate"
                        width="95"
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


            </el-table>
            </div>
        </el-card>
    </div>
</template>

<script>
    import * as fecha from "element-ui/lib/utils/date";
    export default {
        name: "SalSob",
        data(){
            return {
                 emps:[],
                deps:'',
                jobs:[],
                options: [{
                    value: '男',
                    label: '男'
                }, {
                    value: '女',
                    label: '女'
                }],
                sexVa:'',
                sals:''


            }
        },


        mounted(){
            this.getJob()
        },



        methods:{
             getJob(){

                var mm=window.sessionStorage.getItem("job")
                this.jobs= JSON.parse(mm) ;

            },
            async searchDep(){
                const {data:res} =await this.$http.post('personnel_information_statistics/findonebyjob?job='+this.deps);
                this.emps=res.data;
                this.sexVa='';this.sals='';
            },
            async searchSex(){
                const {data:res} =await this.$http.post('personnel_information_statistics/findonebysex?sex='+this.sexVa);
                this.emps=res.data;
                this.deps='';this.sals='';
            } ,
            async getemp(){
                const {data:res} =await this.$http.post('personnel_information_statistics/findonebysalary?salary='+this.sals);
                this.emps=res.data;
                this.deps='';this.sexVa='';
            },
            dateFormat(row, column, cellValue) {
                return cellValue ? fecha.format(new Date(cellValue), 'yyyy-MM-dd') : '';
            },
        }


    }
</script>

<style scoped>

</style>