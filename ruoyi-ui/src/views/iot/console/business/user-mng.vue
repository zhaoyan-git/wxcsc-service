<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <!--      <el-form-item>-->
      <!--        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>-->
      <!--        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
      <!--      </el-form-item>-->
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['iot:console']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['iot:console']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['iot:console']"
        >删除
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="businessMemberList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="ID" align="center" prop="id"/>
      <el-table-column label="企业id" align="center" prop="businessId"/>
      <el-table-column label="用户名称" align="center" prop="user.userName"/>
      <el-table-column label="用户昵称" align="center" prop="user.nickName"/>
      <el-table-column label="用户角色" align="center" prop="user.roles">
        <template slot-scope="scope">
          {{scope.row.user.roles[0].roleName}}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['iot:console']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-key"
            @click="handleResetPwd(scope.row)"
            v-hasPermi="['iot:console']"
            v-if="name != scope.row.user.userName"
          >重置密码
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['iot:console']"
            v-if="name != scope.row.user.userName"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改企业人员对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="用户昵称" prop="user.nickName">
              <el-input v-model="form.user.nickName" placeholder="请输入用户昵称" maxlength="30"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="手机号码" prop="user.phonenumber">
              <el-input v-model="form.user.phonenumber" placeholder="请输入手机号码" maxlength="11"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="用户邮箱" prop="user.email">
              <el-input v-model="form.user.email" placeholder="请输入邮箱" maxlength="50"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item v-if="form.user.userId == undefined" label="用户名称" prop="user.userName">
              <el-input v-model="form.user.userName" placeholder="请输入用户名称" maxlength="30"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item v-if="form.user.userId == undefined" label="用户密码" prop="user.password">
              <el-input v-model="form.user.password" placeholder="请输入用户密码" type="password" maxlength="20"
                        show-password/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="用户性别">
              <el-select v-model="form.user.sex" placeholder="请选择">
                <el-option
                  v-for="dict in dict.type.sys_user_sex"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="name != form.user.userName">
            <el-form-item label="账号状态">
              <el-radio-group v-model="form.user.status">
                <el-radio
                  v-for="dict in dict.type.sys_normal_disable"
                  :key="dict.value"
                  :label="dict.value"
                >{{dict.label}}
                </el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" v-if="name != form.user.userName">
            <el-form-item label="用户角色" prop="user.roleIds">
              <el-select v-model="form.user.roleIds" placeholder="请选择" multiple :multiple-limit="1"
                         @change="roleIdsChange">
                <el-option
                  v-for="dict in dict.type.iot_business_member_role"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="项目权限" prop="businessMemberRoleList" v-if="hasMngRole">
              <el-select v-model="form.projectIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in projectData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="用户备注">
              <el-input v-model="form.user.remark" type="textarea" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
    import {
        getBusinessMemberDto,
        delBusinessMemberDto,
        addBusinessMemberDto,
        updateBusinessMemberDto,
        resetUserPwd
    } from "@/api/iot/businessMember";

    import {
        consoleListBusinessMemberDto,
        getListByBusinessId
    } from "@/api/iot/console";

    import {mapGetters} from "vuex";

    export default {
        name: "BusinessMember",
        computed: {
            ...mapGetters(["roles", "name"]),
        },
        data() {
            return {
                // 遮罩层
                loading: true,
                // 导出遮罩层
                exportLoading: false,
                // 选中数组
                ids: [],
                // 非单个禁用
                single: true,
                // 非多个禁用
                multiple: true,
                // 显示搜索条件
                showSearch: true,
                // 总条数
                total: 0,
                // 企业人员表格数据
                businessMemberList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    businessId: 0,
                    pageNum: 1,
                    pageSize: 10,
                },
                // 表单参数
                form: {user: {}},
                // 表单校验
                rules: {
                    'user.roleIds': [
                        {required: true, message: "角色不能为空", trigger: "blur"}
                    ],
                    'user.userName': [
                        {required: true, message: "用户名称不能为空", trigger: "blur"},
                        {min: 2, max: 20, message: '用户名称长度必须介于 2 和 20 之间', trigger: 'blur'}
                    ],
                    'user.nickName': [
                        {required: true, message: "用户昵称不能为空", trigger: "blur"}
                    ],
                    'user.password': [
                        {required: true, message: "用户密码不能为空", trigger: "blur"},
                        {min: 5, max: 20, message: '用户密码长度必须介于 5 和 20 之间', trigger: 'blur'}
                    ],
                    'user.email': [
                        {
                            type: "email",
                            message: "'请输入正确的邮箱地址",
                            trigger: ["blur", "change"]
                        }
                    ],
                    'user.phonenumber': [
                        {
                            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
                            message: "请输入正确的手机号码",
                            trigger: "blur"
                        }
                    ]
                },
                projectData: [],
                hasMngRole: false
            };
        },
        dicts: ['sys_normal_disable', 'sys_user_sex', 'iot_business_member_role'],
        created() {
            this.getList();

            // 获取项目列表
            getListByBusinessId().then(response => {
                this.projectData = response;
            });
        },
        methods: {
            /** 查询企业人员列表 */
            getList() {
                this.loading = true;
                consoleListBusinessMemberDto(this.queryParams).then(response => {
                    this.businessMemberList = response.rows;
                    this.total = response.total;
                    this.loading = false;
                });
            },
            // 取消按钮
            cancel() {
                this.open = false;
                this.reset();
            },
            // 表单重置
            reset() {
                this.form = {
                    id: null,
                    businessId: this.queryParams.businessId,
                    user: {
                        userId: undefined,
                        deptId: undefined,
                        userName: undefined,
                        nickName: undefined,
                        password: undefined,
                        phonenumber: undefined,
                        email: undefined,
                        sex: '2',
                        status: "0",
                        remark: undefined,
                        postIds: [],
                        roleIds: []
                    }
                };
                this.resetForm("form");
            },
            /** 搜索按钮操作 */
            handleQuery() {
                this.queryParams.pageNum = 1;
                this.getList();
            },
            /** 重置按钮操作 */
            resetQuery() {
                this.resetForm("queryForm");
                this.handleQuery();
            },
            // 多选框选中数据
            handleSelectionChange(selection) {
                this.ids = selection.map(item => item.id)
                this.single = selection.length !== 1
                this.multiple = !selection.length
            },
            /** 新增按钮操作 */
            handleAdd() {
                this.reset();
                this.open = true;
                this.hasMngRole = false;
                this.title = "添加企业人员";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getBusinessMemberDto(id).then(response => {
                    this.form = response.data;
                    if (this.form.user.roles[0]) {
                        this.form.user.roleIds = [];
                        this.form.user.roleIds.push(this.form.user.roles[0].roleId.toString());
                    }

                    this.roleIdsChange()

                    this.form.projectIds = [];

                    if (null != this.form.businessMemberRoleList && 0 < this.form.businessMemberRoleList.length) {
                        for (var i = 0; i < this.form.businessMemberRoleList.length; i++) {
                            var item = this.form.businessMemberRoleList[i]

                            this.form.projectIds.push({
                                id: item.id
                            })
                        }
                    }

                    this.open = true;
                    this.title = "修改企业人员";
                });
            },
            /** 提交按钮 */
            submitForm() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        this.form.businessMemberRoleList = []

                        if (null != this.form.projectIds && 0 < this.form.projectIds.length) {
                            for (var i = 0; i < this.form.projectIds.length; i++) {
                                var item = this.form.projectIds[i]

                                this.form.businessMemberRoleList.push({
                                    projectId: item
                                })
                            }
                        }

                        if (this.form.id != null) {
                            updateBusinessMemberDto(this.form).then(response => {
                                this.$modal.msgSuccess("修改成功");
                                this.open = false;
                                this.getList();
                            });
                        } else {
                            addBusinessMemberDto(this.form).then(response => {
                                this.$modal.msgSuccess("新增成功");
                                this.open = false;
                                this.getList();
                            });
                        }
                    }
                });
            },
            /** 删除按钮操作 */
            handleDelete(row) {
                const ids = row.id || this.ids;
                this.$modal.confirm('是否确认删除企业人员编号为"' + ids + '"的数据项？').then(function () {
                    return delBusinessMemberDto(ids);
                }).then(() => {
                    this.getList();
                    this.$modal.msgSuccess("删除成功");
                }).catch(() => {
                });
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$modal.confirm('是否确认导出所有企业人员数据项？').then(() => {
                    this.exportLoading = true;
                    return exportBusinessMember(queryParams);
                }).then(response => {
                    this.$download.name(response.msg);
                    this.exportLoading = false;
                }).catch(() => {
                });
            },
            /** 重置密码按钮操作 */
            handleResetPwd(row) {
                this.$prompt('请输入"' + row.user.userName + '"的新密码', "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    closeOnClickModal: false,
                    inputPattern: /^.{5,20}$/,
                    inputErrorMessage: "用户密码长度必须介于 5 和 20 之间",
                }).then(({value}) => {
                    resetUserPwd(row.user.userId, value).then(response => {
                        this.$modal.msgSuccess("修改成功，新密码是：" + value);
                    });
                }).catch(() => {
                });
            },
            roleIdsChange() {
                if (0 < this.form.user.roleIds.length && 5 == this.form.user.roleIds[0]) {
                    this.hasMngRole = true;
                } else {
                    this.hasMngRole = false;
                }
            }
        }
    };
</script>
