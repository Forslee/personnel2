<template>
  <!--  <div>员工积分统计</div>-->
<div>
    <!--面包屑导航区域-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>人事管理</el-breadcrumb-item>
        <el-breadcrumb-item>积分</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card style="margin-top: 10px;width: 40%">
    <div>
        <el-select v-model="deps" placeholder="部门"  style="width: 150px;margin-right: 10px;">
            <el-option
                    v-for="item in Mvdepartment"
                    :key="item.departmentId"
                    :label="item.departmentName"
                    :value="item.departmentName">
            </el-option>
        </el-select>

        <el-button icon="el-icon-search" type="primary" @click="getScoretable" >
            搜索
        </el-button>
    </div>



    <div style="margin-top: 10px">


            <el-table :data="poi" stripe border style="width:60%" >

                <!--前面的选择框-->
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>

                <el-table-column
                        prop="userId"
                        align="left"
                        label="员工ID"
                        width="110"
                        fixed>

                </el-table-column>

                <el-table-column
                        prop="point"
                        align="left"
                        label="积分"
                        width="120"
                        fixed>

                </el-table-column>
            </el-table>
    </div>
    </el-card>

    </div>



</template>

<script>
    export default {
        name: "SalSob",
        data() {
            return {
               poi:[],
                deps:'',
                Mvdepartment:[],
            }
        },
        mounted(){
          this.getDepartment()
        },

        methods:{

        /*根据部门找员工积分*/
            async getScoretable(){
                const {data:res} =await  this.$http.post('/integration_statistics/findintegrationbydepartment?department='+this.deps);
                console.log(res.data)
                this.poi=res.data
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