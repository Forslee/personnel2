<template>
    <div>
        <el-form :rules="rules" :model="loginForm" ref="loginForm" class="loginContainer">
            <!--model：表单里面的数据对象-->
            <h3 class="loginTitle">系统登录</h3>
            <el-form-item prop="userid">
                <el-input type="text" v-model="loginForm.userid" auto-complete="off" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="text" v-model="loginForm.password" auto-complete="off" placeholder="请输入密码"></el-input>
            </el-form-item>
            <!--图片-->
            <el-form-item >
                <el-col :span="12">
                    <!--输入图片验证码-->
                    <el-form-item prop="captcha">
                        <el-input type="test" v-model="loginForm.captcha" auto-complete="off" placeholder="验证码, 单击图片刷新"
                                  style="width: 100%;">
                        </el-input>
                    </el-form-item>
                </el-col>
                <el-col class="line" :span="1">&nbsp;</el-col>
                <el-col :span="11">
                    <!--图片-->
                    <el-form-item>
                        <img style="width: 90%;margin-left: 10px" class="pointer" :src="loginForm.src" @click="refreshCaptcha">
                    </el-form-item>
                </el-col>
            </el-form-item>
            <el-checkbox v-model="checked" class="loginRemember"></el-checkbox>
            <el-button type="primary" style="width: 100%" @click="submitLogin">登录</el-button>
        </el-form>
    </div>
    <!--rules：表单验证-->
</template>

<script>

    import {postKeyValueRequest} from "../utils/api";
    import {postRequest} from "../utils/api";

    export default {
        name: "Login",

        data(){
            return{
                loginForm:{
                    userid:'12345',
                    password:'12345',
                    captcha:'',
                    src:''
                },
                checked:false,
                rules:{
                    userid:[{required:true,message:'请输入用户名',trigger:'blur'}],
                    password:[{required:true,message:'请输入密码',trigger:'blur'},{min:3,message:'密码太短',trigger:'blur'}]
                }
            }
        },
        methods:{
            submitLogin(){
                this.$refs.loginForm.validate(async (valid) => {
                    if (!valid) return false;

                    if(window.sessionStorage.getItem("opid")){
                       return this.$message.warning('请退出后再进行登录')
                    }

                   const {data: res}= await this.$http.post('/Login',this.loginForm);

                    if (res.state == false) return this.$message.error('登录失败');
                    this.$message.success('登录成功')
                    window.sessionStorage.setItem('opid', res.userid)


                    // 2、通过编程式导航跳转到后台主页, 路由地址为：/home
                    this.$router.push('/home')


                });
            },
             refreshCaptcha(){
               this.$http.get('/Captcha.jpg',{
                   responseType: 'arraybuffer',

               })
                   .then(response => {
                       return 'data:image/png;base64,' + btoa(
                           new Uint8Array(response.data)
                               .reduce((data, byte) => data + String.fromCharCode(byte), '')
                       );
                   }).then(data => {
                   this.loginForm.src = data
               })
                console.log(this.loginForm.src)
            },

        },
        mounted(){
            this.refreshCaptcha()
        }
    }
</script>

<style >
    .loginContainer{
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }
    .loginTitle{
        margin: 15px auto 20px auto;
        text-align: center;
        color: #505458;
    }
    .loginRemember{
        text-align: left;
        margin: 0px 0px 15px 0px;
    }
</style>