<template>
    <!--<</div>div>初始化数据库-->


    <div v-if="this.uid=='123456'">
        <el-button @click="initdata" type="warning">初始化数据库</el-button>
    </div>

    <div v-else>您的权限不足</div>


</template>

<script>
    export default {
        name: "SalSob",
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
                const confirmResult = await this.$confirm(
                    '此操作将初始化数据库, 是否继续?',
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
                    return this.$message.info('已取消初始化')
                }
                const {data:res} =await this.$http.post('/initializedb/clear');
                if(res){
                    this.$message.success("初始化成功")
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