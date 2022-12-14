<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="所属项目" prop="projectId">
        <el-select v-model="queryParams.projectId" placeholder="请选择">
          <el-option
            v-for="dict in projectList"
            :key="dict.id"
            :label="dict.name"
            :value="dict.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否已读" prop="readFlag">
        <el-select v-model="queryParams.readFlag" placeholder="请选择">
          <el-option
            v-for="dict in dict.type.iot_read_flag"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="projectAlarmRecordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="ID" align="center" prop="id"/>
      <el-table-column label="标题" align="center" prop="title"/>
      <el-table-column label="内容" align="center" prop="content"/>
      <el-table-column label="所属项目" align="center" prop="projectId">
        <template slot-scope="scope">
          <span
            v-for="item in projectList"
            :key="item.id"
            :value="item.id"
            v-if="item.id == scope.row.projectId"
          >
            {{ item.name }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="是否已读" align="center" prop="readFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.iot_read_flag" :value="scope.row.readFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleLook(scope.row)"
            v-hasPermi="['iot:console']"
          >查看
          </el-button>
          <!--          <el-button-->
          <!--            size="mini"-->
          <!--            type="text"-->
          <!--            icon="el-icon-edit"-->
          <!--            @click="handleUpdate(scope.row)"-->
          <!--            v-hasPermi="['iot:projectAlarmRecord:edit']"-->
          <!--          >修改-->
          <!--          </el-button>-->
          <!--          <el-button-->
          <!--            size="mini"-->
          <!--            type="text"-->
          <!--            icon="el-icon-delete"-->
          <!--            @click="handleDelete(scope.row)"-->
          <!--            v-hasPermi="['iot:projectAlarmRecord:remove']"-->
          <!--          >删除-->
          <!--          </el-button>-->
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

    <!-- 添加或修改报警记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" :readonly="true"/>
        </el-form-item>
        <el-form-item label="内容">
          <el-input v-model="form.content" type="textarea" :readonly="true"/>
        </el-form-item>
        <el-form-item label="所属项目" prop="projectId">
          <span
            v-for="item in projectList"
            :key="item.id"
            :value="item.id"
            v-if="item.id == form.projectId"

          >
            {{ item.name }}
          </span>
        </el-form-item>
        <el-form-item label="是否已读" prop="readFlag">
          <dict-tag :options="dict.type.iot_read_flag" :value="form.readFlag"/>
        </el-form-item>
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
        listProjectAlarmRecord,
        getProjectAlarmRecord,
        delProjectAlarmRecord,
        addProjectAlarmRecord,
        updateProjectAlarmRecord,
        exportProjectAlarmRecord
    } from "@/api/iot/projectAlarmRecord";

    import {projectAlarmRecordListByUser, projectListByUserRole, getProjectAlarmRecordInfo} from "@/api/iot/console";

    export default {
        name: "ProjectAlarmRecord",
        dicts: ['iot_read_flag'],
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
                // 报警记录表格数据
                projectAlarmRecordList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                    title: null,
                    content: null,
                    projectId: null,
                    readFlag: null,
                },
                // 表单参数
                form: {},
                // 表单校验
                rules: {},
                projectList: []
            };
        },
        created() {
            this.getList();
        },
        methods: {
            /** 查询报警记录列表 */
            getList() {
                this.loading = true;

                projectListByUserRole().then((response) => {
                    this.projectList = response;
                });


                projectAlarmRecordListByUser(this.queryParams).then(response => {
                    this.projectAlarmRecordList = response.rows;
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
                    title: null,
                    content: null,
                    projectId: null,
                    readFlag: null,
                    createTime: null,
                    updateTime: null
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
                this.title = "添加报警记录";
            },
            handleLook(row) {
                this.reset();
                const id = row.id || this.ids
                getProjectAlarmRecordInfo(id).then(response => {
                    this.form = response.data;
                    this.getList();
                    this.open = true;
                    this.title = "查看报警记录";
                });
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getProjectAlarmRecord(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改报警记录";
                });
            },
            /** 提交按钮 */
            submitForm() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != null) {
                            updateProjectAlarmRecord(this.form).then(response => {
                                this.$modal.msgSuccess("修改成功");
                                this.open = false;
                                this.getList();
                            });
                        } else {
                            addProjectAlarmRecord(this.form).then(response => {
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
                this.$modal.confirm('是否确认删除报警记录编号为"' + ids + '"的数据项？').then(function () {
                    return delProjectAlarmRecord(ids);
                }).then(() => {
                    this.getList();
                    this.$modal.msgSuccess("删除成功");
                }).catch(() => {
                });
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$modal.confirm('是否确认导出所有报警记录数据项？').then(() => {
                    this.exportLoading = true;
                    return exportProjectAlarmRecord(queryParams);
                }).then(response => {
                    this.$download.name(response.msg);
                    this.exportLoading = false;
                }).catch(() => {
                });
            }
        }
    };
</script>
