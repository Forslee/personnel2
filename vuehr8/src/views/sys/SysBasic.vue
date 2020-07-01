<template>

    <div v-if="this.uid=='123456'">
        <!--面包屑导航区域-->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>系统管理</el-breadcrumb-item>
            <el-breadcrumb-item>基本信息设置</el-breadcrumb-item>
        </el-breadcrumb>

        <el-tabs v-model="activeName" type="card" class="el-tabs-s" style="margin-top: 10px">
            <el-tab-pane label="部门管理" name="first"><DepMan></DepMan></el-tab-pane>
            <el-tab-pane label="职位管理" name="second"><PosMan></PosMan></el-tab-pane>
        </el-tabs>

    </div>
    <div v-else>
        您的权限不足
    </div>
</template>

<script>
    import DepMan from '../../components/sys/basic/DepMan.vue'
    import EcMan from '../../components/sys/basic/EcMan.vue'
    import JobLevelMan from '../../components/sys/basic/JobLevelMan.vue'
    import PosMan from '../../components/sys/basic/PosMan.vue'
    export default {
        name: "SalSob",
        data(){
            return{
                activeName:'second',
                uid:''
            }
        },
        components:{
            DepMan,

            PosMan

        },
        mounted(){
            this.getUser()
        },
        methods:{
            getUser(){
                this.uid= window.sessionStorage.getItem("opid");
            }
        }
    }
</script>

<style scoped>

</style>