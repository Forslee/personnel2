<template>
    <el-container class="homeContainer">
        <!--头部区域-->
        <el-header class="homeHeader">
            <div>
            <span>人事管理</span>
            </div>
            <el-dropdown class="userInfo" @command="commandHandler">
                  <span class="el-dropdown-link" style="cursor: pointer" >
                    设置
                  </span>
                <el-dropdown-menu slot="dropdown">
                   <!-- <el-dropdown-item command="userinfo">个人中心</el-dropdown-item>-->
                    <el-dropdown-item command="logout" divided>注销登录</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </el-header>
        <!--页面主题区域-->
        <el-container>
            <!--侧边栏-->
            <el-aside class="homeAside" width="200px">

                <!--侧边栏菜单区域-->
                <el-menu unique-opened @select="menuClick"  background-color="#333744" text-color="#fff" active-text-color="#ffdo4b" >
                    <!--一级菜单-->
                    <el-submenu :index="item.meta.index" v-for="(item,indexc) in this.$router.options.routes" v-if="!item.hidden" :key="indexc" >
                        <!--一级菜单模板-->
                        <template slot="title" >
                            <!--一级菜单图标-->
                            <i :class="item.meta.icon" style="margin-right: 5px"></i>
                            <!--一级菜单文本-->
                            <span>{{item.name}}</span>
                        </template>
                        <!--二级菜单-->
                        <el-menu-item   :index="child.path" v-for="(child,indec) in item.children" :key="indec">

                            <!--二级菜单模板-->
                            <template slot="title" >
                                <!--二级菜单图标-->
                                    <!--<i  :class="child.meta.icon"></i>-->
                                <!--二级菜单文本-->
                                <span>{{child.name}}</span>
                            </template>


                        </el-menu-item>
                    </el-submenu>

                </el-menu>

            </el-aside>

            <!--右侧内容主题-->
            <el-main class="homeMain">
                <router-view class="homeRouterView"/>
            </el-main>
        </el-container>


    </el-container>
</template>

<script>
    export default {
        name: "Home",
        //用户信息
        data(){
            return{

            }
        },

        methods:{
            menuClick(index){
                console.log(index);
                this.$router.push(index)

            },
            commandHandler(cmd){
                if(cmd=="logout"){
                    this.$confirm('此操作将注销登录, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                       // this.getRequest("/logout");
                        window.sessionStorage.removeItem("opid");
                        window.sessionStorage.removeItem("department");
                        window.sessionStorage.removeItem("job");


                        this.$router.replace("/");
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消操作'
                        });
                    });
                }
            }
        }
    }
</script>

<style scoped>
    .homeRouterView{
        margin-top: 10px;
    }
    .homeContainer{
        height: 100vh;
    }
    .homeHeader{
        background-color: #373d41;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 0px 15px;
        font-size: 20px;
        font-family: 宋体;
        color: #fff;
    }
    .homeAside{
        background-color: #333744;
    }
    .homeMain{
        background-color: #eaedf1;
    }
    .userInfo{
        font-size: 20px;
        font-family: 宋体;
        color: #fff;
    }
    .upMenu{

    }
</style>