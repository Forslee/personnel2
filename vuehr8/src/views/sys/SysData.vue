<template>
    <!--<div>备份恢复数据库</div>-->
    <div v-if="this.uid=='123456'">


        <el-button @click="initdata" type="warning">备份数据库</el-button>
        <el-button @click="backdata" type="warning">恢复数据库</el-button>

    </div>
    <div v-else>
        权限不足
    </div>
</template>

<script>
    export default {
        name: "SalData",
        data(){
            return{
                uid:'',
            }
        },
        mounted(){
            this.getUser()
        },
        methods:{
            async initdata(){
                    const {data:res} =await this.$http.post('/backupdatabsase/backup');
                    if(res) {
                        this.$message.success("备份数据库成功")
                    }

            },
            async backdata(){
                const {data:res} =await this.$http.post('/backupdatabsase/restore?name='+'personnel.sql');
                if(res){
                    this.$message.success("恢复数据库成功")
                }
            },
            getUser(){
             this.uid= window.sessionStorage.getItem("opid");
            }
        }
    }
</script>

<style scoped>

</style>