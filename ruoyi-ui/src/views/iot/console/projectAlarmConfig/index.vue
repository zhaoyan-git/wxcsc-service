<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="项目">
        <el-select v-model="queryParams.projectId"
                   placeholder="请选择项目"
                   @change="conditionProjectChange"
        >
          <el-option
            :label="item.name"
            :value="item.id"
            v-for="item in projectData"
            :key="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="所属结构物" prop="structureId">
        <el-select v-model="queryParams.structureId" placeholder="请选择所属结构物" clearable size="small"
                   @change="conditionProjectStructureChange">
          <el-option
            :label="item.name"
            :value="item.id"
            v-for="item in projectStructureData"
            :key="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['iot:projectAlarmConfig']"
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
          v-hasPermi="['iot:projectAlarmConfig']"
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
          v-hasPermi="['iot:projectAlarmConfig']"
        >删除
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="projectAlarmConfigList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="ID" align="center" prop="id"/>
      <el-table-column label="所属测点" align="center" prop="point">
        <template slot-scope="scope">
          <block>
            {{ scope.row.point.name }}
          </block>
        </template>
      </el-table-column>
      <el-table-column label="阈值" align="center" prop="threshold"/>
      <el-table-column label="计算方式" align="center" prop="computeType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.iot_compute_type" :value="scope.row.computeType"/>
        </template>
      </el-table-column>
      <el-table-column label="报警标题" align="center" prop="title"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['iot:projectAlarmConfig']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['iot:projectAlarmConfig']"
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

    <!-- 添加或修改报警配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="所属项目" prop="projectId">
          <el-select
            v-model="form.projectId"
            placeholder="请选择所属项目"
            @change="dialogFormProjectChange"
          >
            <el-option
              v-for="item in projectData"
              v-bind:key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="所属结构物" prop="structureId">
          <el-select v-model="form.structureId"
                     placeholder="请选择所属结构物"
                     @change="dialogFormProjectStructureChange"
          >
            <el-option
              v-for="item in formProjectStructureData"
              v-bind:key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="所属测点" prop="selectedPointId">
          <el-cascader
            v-model="form.selectedPointId"
            :options="optionsPointOrDeviceData"
            :props="{ expandTrigger: 'hover' }"></el-cascader>
        </el-form-item>
        <el-form-item label="阈值" prop="threshold">
          <el-input v-model="form.threshold" placeholder="请输入阈值"/>
        </el-form-item>
<!--        <el-form-item label="报警等级" prop="threshold">-->
<!--          <el-select v-model="form.alarmLevel" placeholder="请选择报警等级">-->
<!--            <el-option-->
<!--              :key="'1'"-->
<!--              label="一级告警"-->
<!--              :value="'1'"-->
<!--            ></el-option>-->
<!--            <el-option-->
<!--              :key="'2'"-->
<!--              label="二级告警"-->
<!--              :value="'2'"-->
<!--            ></el-option>-->
<!--            <el-option-->
<!--              :key="'3'"-->
<!--              label="三级告警"-->
<!--              :value="'3'"-->
<!--            ></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
        <el-form-item label="计算方式" prop="computeType">
          <el-select v-model="form.computeType" placeholder="请选择计算方式">
            <el-option
              v-for="dict in dict.type.iot_compute_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="报警标题" prop="title">
          <el-input v-model="form.title" type="input" placeholder="请输入报警标题"/>
        </el-form-item>
        <el-form-item label="报警内容">
          <el-input v-model="form.content" type="textarea" placeholder="请输入报警内容"/>
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
        listProjectAlarmConfig,
        getProjectAlarmConfig,
        delProjectAlarmConfig,
        addProjectAlarmConfig,
        updateProjectAlarmConfig,
        exportProjectAlarmConfig
    } from "@/api/iot/projectAlarmConfig";

    import {
        projectStructureTypeList,
        projectListBusiness,
        projectStructureListByProjectId,
        projectPointGroup
    } from "@/api/iot/console";

    import {
        listProjectPoint
    } from "@/api/iot/projectPoint";

    export default {
        name: "ProjectAlarmConfig",
        dicts: ['iot_compute_type', 'iot_alarm'],
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
                // 报警配置表格数据
                projectAlarmConfigList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                    pointId: null,
                    threshold: null,
                    computeType: null,
                    title: null,
                    content: null,
                },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                    selectedPointId: [
                        {required: true, message: "所属测点不能为空", trigger: "blur"}
                    ],
                    threshold: [
                        {required: true, message: "阈值不能为空", trigger: "blur"}
                    ],
                    computeType: [
                        {required: true, message: "计算方式不能为空", trigger: "change"}
                    ],
                    title: [
                        {required: true, message: "报警标题不能为空", trigger: "blur"}
                    ],
                    projectId: [
                        {required: true, message: "所属项目不能为空", trigger: "blur"}
                    ],
                    structureId: [
                        {required: true, message: "所属结构物不能为空", trigger: "blur"}
                    ],
                    alarmLevel: [
                        {required: true, message: "告警等级不能为空", trigger: "blur"}
                    ],

                },
                projectData: [],
                projectStructureData: [],
                formProjectStructureData: [],
                optionsPointOrDeviceData: []
            };
        },
        created() {
            this.getList();
        },
        methods: {
            /** 查询报警配置列表 */
            getList() {
                this.loading = true;

                projectListBusiness().then((response) => {
                    this.projectData = response;
                    this.projectDataDict = []
                    for (var i = 0; i < this.projectData.length; i++) {
                        var item = this.projectData[i];
                        this.projectDataDict.push({
                            label: item.name,
                            value: item.id,
                            raw: {
                                listClass: 'default'
                            }
                        });
                    }
                });

                listProjectAlarmConfig(this.queryParams).then(response => {
                    this.projectAlarmConfigList = response.rows;
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
                    pointId: null,
                    threshold: null,
                    computeType: null,
                    title: null,
                    content: null,
                    createTime: null,
                    updateTime: null,
                    selectedPointId: null,
                    projectId: null,
                    structureId: null,
                    alarmLevel: null
                };

                this.formProjectStructureData = this.projectStructureData;
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
                this.title = "添加报警配置";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                var that = this

                this.reset();
                const id = row.id || this.ids
                getProjectAlarmConfig(id).then(response => {
                    this.form = response.data;
                    // 回显 所属结构物 选项
                    projectStructureListByProjectId({
                        projectId: this.form.projectId,
                    }).then((response) => {
                        this.formProjectStructureData = response;
                    });

                    // 回显 所属测点 选项
                    this.optionsPointOrDeviceData = [];
                    this.form.selectedPointId = [this.form.point.pointGroupId, this.form.point.id]

                    listProjectPoint({
                        projectStructureId: this.form.structureId
                    }).then(response => {
                        for (var i = 0; i < response.rows.length; i++) {
                            var item = response.rows[i];
                            var children = [];

                            if (null != item.children) {
                                for (var j = 0; j < item.children.length; j++) {
                                    var childrenItem = item.children[j]
                                    children.push({
                                        value: childrenItem.id,
                                        label: childrenItem.name,
                                    })
                                }
                            }

                            that.optionsPointOrDeviceData.push({
                                value: item.id,
                                label: item.name,
                                children: children
                            })

                            this.form.pointId = this.getProjectPointDataIdByPointId(this.form.pointId)
                        }
                    });


                    this.open = true;
                    this.title = "修改报警配置";
                });
            },
            /** 提交按钮 */
            submitForm() {
                this.form.pointId = this.form.selectedPointId[1]
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != null) {
                            updateProjectAlarmConfig(this.form).then(response => {
                                this.$modal.msgSuccess("修改成功");
                                this.open = false;
                                this.getList();
                            });
                        } else {
                            addProjectAlarmConfig(this.form).then(response => {
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
                this.$modal.confirm('是否确认删除报警配置编号为"' + ids + '"的数据项？').then(function () {
                    return delProjectAlarmConfig(ids);
                }).then(() => {
                    this.getList();
                    this.$modal.msgSuccess("删除成功");
                }).catch(() => {
                });
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$modal.confirm('是否确认导出所有报警配置数据项？').then(() => {
                    this.exportLoading = true;
                    return exportProjectAlarmConfig(queryParams);
                }).then(response => {
                    this.$download.name(response.msg);
                    this.exportLoading = false;
                }).catch(() => {
                });
            },
            conditionProjectChange() {
                if (null != this.queryParams.projectId) {
                    this.$emit('projectCurrent', this.queryParams.projectId);
                    this.projectStructureData = [];

                    projectStructureListByProjectId({
                        projectId: this.queryParams.projectId,
                    }).then((response) => {
                        this.projectStructureData = response;
                    });

                    this.getList();
                }
            },
            conditionProjectStructureChange() {
            },
            dialogFormProjectChange() {
                if (null != this.form.projectId) {
                    projectStructureListByProjectId({
                        projectId: this.form.projectId,
                    }).then((response) => {
                        this.formProjectStructureData = response;

                        this.form.structureId = "";
                        this.form.selectedPointId = ""
                        this.optionsPointOrDeviceData = [];
                    });
                }
            },
            dialogFormProjectStructureChange() {
                var that = this
                this.optionsPointOrDeviceData = [];

                listProjectPoint({
                    projectStructureId: this.form.structureId
                }).then(response => {
                    for (var i = 0; i < response.rows.length; i++) {
                        var item = response.rows[i];
                        var children = [];

                        if (null != item.children) {
                            for (var j = 0; j < item.children.length; j++) {
                                var childrenItem = item.children[j]
                                children.push({
                                    value: childrenItem.id,
                                    label: childrenItem.name,
                                })
                            }
                        }

                        that.optionsPointOrDeviceData.push({
                            value: item.id,
                            label: item.name,
                            children: children
                        })
                    }
                });
            },
            getProjectPointDataIdByPointId(pointId) {
                for (var i = 0; i < this.optionsPointOrDeviceData.length; i++) {
                    var item = this.optionsPointOrDeviceData[i]

                    for (var j = 0; j < item.children.length; j++) {
                        var childrenItem = item.children[j]

                        if (pointId == childrenItem.value) {
                            return [item.value, childrenItem.value]
                        }
                    }

                }
            },
        }
    };
</script>
