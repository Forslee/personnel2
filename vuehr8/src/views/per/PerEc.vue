<template>
  <!--  <div>员工奖惩</div>-->

    <div>

        <!--面包屑导航区域-->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>人事管理</el-breadcrumb-item>
            <el-breadcrumb-item>员工奖惩</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card style="width: 75%;margin-top: 10px">
        <div style="display: flex;justify-content: space-between">

            <div>
                <el-input
                        placeholder="通过员工号搜索/删除员工"
                        prefix-icon="el-icon-search"
                        style="width: 300px;margin-right: 10px"
                        clearable
                        @clear="initEcData"
                        v-model="eid"
                ></el-input>
                <el-button icon="el-icon-search" type="primary" @click="getSalTable" style="margin-right: 10px">搜索</el-button>
                <!--<el-tooltip effect="dark" content="按员工号" :enterable="false" placement="top">
                    <el-button icon="el-icon-delete" type="warning" @click="deleteEc">删除</el-button>
                </el-tooltip>-->
            </div>

            <div>

                <el-button type="primary"  @click="addEcDialogVisible=true">
                    <i icon="el-icon-plus" aria-hidden="true"></i>
                    添加奖惩
                </el-button>

            </div>
        </div>

        <div style="margin-top: 10px">


                    <el-table :data="ecs" stripe border width="100%" >

                        <!--前面的选择框-->
                        <el-table-column
                                type="selection"
                                width="55">
                        </el-table-column>

                        <el-table-column
                                prop="no"
                                align="left"
                                label="编号"
                                width="90"
                                fixed>

                        </el-table-column>

                        <el-table-column
                                prop="personnelid"
                                align="left"
                                label="员工编号"
                                width="90"
                                fixed>

                        </el-table-column>

                        <el-table-column
                                prop="encorpunishdate"
                                align="left"
                                label="奖罚日期"
                                :formatter="dateFormat"
                                width="100"
                                >

                        </el-table-column>

                        <el-table-column
                                prop="encorpunishreason"
                                align="left"
                                label="奖罚原因"
                                width="90"
                                >

                        </el-table-column>
                        <el-table-column
                                prop="encorpunishpoint"
                                align="left"
                                label="奖罚分"
                                width="90"
                                >

                        </el-table-column>
                        <el-table-column
                                prop="type"
                                align="left"
                                label="类型"
                                width="90"
                                >

                        </el-table-column>
                        <el-table-column
                                prop="remark"
                                align="left"
                                label="备注"
                                width="130"
                               >

                        </el-table-column>

                        <el-table-column
                                label="操作" width="150">
                            <template slot-scope="scope">
                                <!--后端接口没写-->
                               <!-- <el-button size="small"  @click="updateEc(scope.row)">编辑</el-button>-->

                                <el-button  size="small" type="danger" @click="deleteEcbyno(scope.row)">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
            </div>
        </el-card>
        <div>

        <!-- 添加用户的对话框 -->
        <el-dialog title="添加奖惩" :visible.sync="addEcDialogVisible" width="40%" >
            <!-- 内容主体 -->
            <el-form
                    :model="addEcForm"

                    label-width="100px"
            >
                <el-form-item label="员工编号" prop="personnelid" style="width: 80%">
                    <el-input v-model="addEcForm.personnelid"></el-input>
                </el-form-item>
                <el-form-item label="奖罚原因" prop="encorpunishreason"style="width: 80%">
                    <el-input v-model="addEcForm.encorpunishreason"></el-input>
                </el-form-item>
                <el-form-item label="奖罚日期" prop="removedate">

                    <el-date-picker
                            v-model="addEcForm.encorpunishdate"
                            type="date"
                            rows="mini"
                            value-format="yyyy-MM-dd"

                            placeholder="奖罚日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="奖罚分" prop="encorpunishpoint"style="width: 80%">
                    <el-input v-model="addEcForm.encorpunishpoint"></el-input>
                </el-form-item>
                <el-form-item label="类型" prop="type">

                    <el-select v-model="addEcForm.type" placeholder="请选择">
                        <el-option
                                v-for="item in options"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="操作员" prop="op" style="width: 80%">

                        <el-input
                                placeholder="请输入"
                                v-model="addEcForm.op"
                                >
                        </el-input>

                </el-form-item>
                <el-form-item label="备注" prop="remark" style="width: 80%">
                    <el-input v-model="addEcForm.remark"></el-input>
                </el-form-item>



            </el-form>
            <span slot="footer" class="dialog-footer">
            <el-button @click="addEcDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addEc">确 定</el-button>
          </span>
        </el-dialog>
            <!-- 添加用户的对话框 -->
        <el-dialog title="修改奖惩" :visible.sync="addEDialogVisible" width="40%" >
            <!-- 内容主体 -->
            <el-form
                    :model="editEcForm"

                    label-width="100px"
            >
                <el-form-item label="员工编号" prop="personnelid" style="width: 80%">
                    <el-input v-model="editEcForm.personnelid"></el-input>
                </el-form-item>
                <el-form-item label="奖罚原因" prop="encorpunishreason"style="width: 80%">
                    <el-input v-model="editEcForm.encorpunishreason"></el-input>
                </el-form-item>
                <el-form-item label="奖罚分" prop="encorpunishpoint"style="width: 80%">
                    <el-input v-model="editEcForm.encorpunishpoint"></el-input>
                </el-form-item>
                <el-form-item label="类型" prop="type">

                    <el-select v-model="editEcForm.type" placeholder="请选择">
                        <el-option
                                v-for="item in options"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="操作员" prop="op" style="width: 80%">

                        <el-input
                                placeholder="请输入"
                                v-model="editEcForm.op"
                                >
                        </el-input>

                </el-form-item>
                <el-form-item label="备注" prop="remark" style="width: 80%">
                    <el-input v-model="editEcForm.remark"></el-input>
                </el-form-item>



            </el-form>
            <span slot="footer" class="dialog-footer">
            <el-button @click="addEDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="submitEc">确 定</el-button>
          </span>
        </el-dialog>
        </div>

    </div>
</template>

<script>

    import * as fecha from "element-ui/lib/utils/date";

    export default {
        name: "PerEc",

        data(){
            return{
                eid:'',
                name:'',
                ecs:[],
                addEcDialogVisible:false,
                addEDialogVisible:false,
                addEcForm:{
                    personnelid:'',
                    encorpunishreason:'',
                    encorpunishpoint:'',
                    encorpunishdate:'',
                    op:'',
                    remark:'',
                    type:''
                },
                editEcForm:{},
                oop:'',
                options: [{
                    value: '001',
                    label: '奖励'
                }, {
                    value: '002',
                    label: '处罚'
                }, ],
            }
        },

        mounted(){
            this.initEcData()
            /*this.getOpInEc()*/
        },

        methods:{

            /*true查找*/
           async getSalTable(){
                const {data:res} =await  this.$http.post('/personnelencourageorpunish/findbyid?id='+this.eid);
                 var op = new Array(res)

                this.ecs= res.data;
            },
            /*true*/
            async initEcData(){
               const {data:res} =await this.$http.post('/personnelencourageorpunish/findAll');
                this.ecs=res.data;

            },
            async deleteEcbyno(data){
                const confirmResult = await this.$confirm(
                    '此操作将永久删除, 是否继续?',
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

                const {data:res} = await this.$http.post('/personnelencourageorpunish/deletebyno?no='+ data.no);
                console.log(typeof parseInt(data.no) )
                this.initEcData();
            },async deleteEc(){
                const confirmResult = await this.$confirm(
                    '此操作将永久删除, 是否继续?',
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
                const {data:res} = await this.$http.post('/personnelencourageorpunish/delete?personnelid='+this.eid);
                this.initEcData();
            },
            /*false*/
            async addEc(){
                const { data: res } = await this.$http.post('/personnelencourageorpunish/add', this.addEcForm);
                // console.log(this.addUserForm)
                this.addEcDialogVisible = false
                this.initEcData();
            },
            /*/!*获取操作员*!/
            getOpInEc(){
               var op= window.sessionStorage.getItem("opid")
                this.oop=op
                this.addEcForm.op=op
            },*/
            updateEc(data){
                this.editEcForm=data;
                this.addEDialogVisible=true
            },
            async submitEc(){
                const {data:res} =await this.$http.post('personnelencourageorpunish/update',this.editEcForm);
                this.addEDialogVisible=false
                this.initEcData()
            },
            dateFormat(row, column, cellValue) {
                return cellValue ? fecha.format(new Date(cellValue), 'yyyy-MM-dd') : '';
            },
        },
    }
</script>

<style scoped>

</style>