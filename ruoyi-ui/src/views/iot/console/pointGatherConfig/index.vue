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
      <el-form-item label="所属结构物" prop="projectStructureId">
        <el-select v-model="queryParams.projectStructureId" placeholder="请选择所属结构物" clearable size="small"
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
          v-hasPermi="['iot:pointGatherConfig']"
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
          v-hasPermi="['iot:pointGatherConfig']"
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
          v-hasPermi="['iot:pointGatherConfig']"
        >删除
        </el-button>
      </el-col>
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="warning"-->
      <!--          plain-->
      <!--          icon="el-icon-download"-->
      <!--          size="mini"-->
      <!--          :loading="exportLoading"-->
      <!--          @click="handleExport"-->
      <!--          v-hasPermi="['iot:pointGatherConfig']"-->
      <!--        >导出-->
      <!--        </el-button>-->
      <!--      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pointGatherConfigList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="周期" align="center" prop="id"/>
      <el-table-column label="所属测点" align="center" prop="pointId"/>
      <el-table-column label="计算方式" align="center" prop="calcType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.iot_gather_calc_type" :value="scope.row.calcType"/>
        </template>
      </el-table-column>
      <el-table-column label="周期" align="center" prop="cycle">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.iot_gather_cycle" :value="scope.row.cycle"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['iot:pointGatherConfig']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['iot:pointGatherConfig']"
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

    <!-- 添加或修改聚集配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="所属项目" prop="projectId">
          <el-select
            v-model="form.projectId"
            placeholder="请选择所属项目"
            @change="dialogFormGroupProjectChange"
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
                     @change="dialogFormGroupProjectStructureChange"
          >
            <el-option
              v-for="item in formGroupProjectStructureData"
              v-bind:key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="所属测点" prop="pointId">
          <el-cascader
            v-model="form.pointId"
            :options="optionsPointOrDeviceData"
            :props="{ expandTrigger: 'hover' }">
            <template slot-scope="{ node, data }">
              <span>{{ data.label }}</span>
              <el-badge v-if="null != data.alarmFlag" is-dot :class="'alarmFlag' + data.alarmFlag"
                        style="margin: 10px;">
              </el-badge>
            </template>
          </el-cascader>
        </el-form-item>

        <el-form-item label="计算方式" prop="calcType">
          <el-select v-model="form.calcType" placeholder="请选择计算方式">
            <el-option
              v-for="dict in dict.type.iot_gather_calc_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="周期" prop="cycle">
          <el-select v-model="form.cycle" placeholder="请选择周期">
            <el-option
              v-for="dict in dict.type.iot_gather_cycle"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
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
        listProjectPoint
    } from "@/api/iot/projectPoint";

    import {
        listPointGatherConfig,
        getPointGatherConfig,
        delPointGatherConfig,
        addPointGatherConfig,
        updatePointGatherConfig,
        exportPointGatherConfig
    } from "@/api/iot/pointGatherConfig";

    import {
        projectStructureTypeList,
        projectListBusiness,
        projectStructureListByProjectId,
        projectPointGroup,
        projectDeviceList
    } from "@/api/iot/console";

    export default {
        name: "PointGatherConfig",
        dicts: ['iot_gather_calc_type', 'iot_gather_cycle'],
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
                // 聚集配置表格数据
                pointGatherConfigList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                    pointId: null,
                    calcType: null,
                    cycle: null,
                },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                    pointId: [
                        {required: true, message: "所属测点不能为空", trigger: "blur"}
                    ],
                    calcType: [
                        {required: true, message: "计算方式不能为空", trigger: "change"}
                    ],
                    cycle: [
                        {required: true, message: "周期不能为空", trigger: "change"}
                    ],
                    projectId: [
                        {required: true, message: "所属项目不能为空", trigger: "change"}
                    ],
                    'structureId': [
                        {required: true, message: "所属结构物不能为空", trigger: "change"}
                    ]
                },
                projectData: [],
                projectStructureData: [],
                formGroupProjectStructureData: [],
                optionsPointOrDeviceData: []
            };
        },
        created() {
            this.getList();
        },
        methods: {
            /** 查询聚集配置列表 */
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

                if (null != this.queryParams.projectId && "" != this.queryParams.projectId) {
                    projectStructureListByProjectId({
                        projectId: this.queryParams.projectId,
                    }).then((response) => {
                        this.projectStructureData = response;
                    });
                }

                listPointGatherConfig(this.queryParams).then(response => {
                    this.pointGatherConfigList = response.rows;
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
                    structureId: null,
                    projectId: null,
                    pointId: null,
                    calcType: null,
                    cycle: null,
                    createTime: null,
                    updateTime: null
                };

                this.formGroupProjectStructureData = [];
                this.optionsPointOrDeviceData = [];

                this.resetForm("form");
            },
            /** 搜索按钮操作 */
            handleQuery() {
                this.queryParams.pageNum = 1;
                this.getList();
            },
            /** 重置按钮操作 */
            resetQuery() {
                this.queryParams = {
                    pageNum: 1,
                    pageSize: 10,
                    pointId: null,
                    calcType: null,
                    cycle: null,
                }
                this.resetForm("queryParams");
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
                this.title = "添加聚集配置";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getPointGatherConfig(id).then(response => {
                    this.form = response.data;

                    projectStructureListByProjectId({
                        projectId: this.form.projectId,
                    }).then((response) => {
                        this.formGroupProjectStructureData = response;
                        this.form.structureId = this.form.structureId
                    });

                    this.optionsPointOrDeviceData = this.getFormProjectPointData(this.form.structureId)

                    console.log(this.form)
                    console.log(this.formGroupProjectStructureData)

                    this.open = true;
                    this.title = "修改聚集配置";
                });
            },
            /** 提交按钮 */
            submitForm() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        this.form.pointId = this.form.pointId[1]

                        if (this.form.id != null) {
                            updatePointGatherConfig(this.form).then(response => {
                                this.$modal.msgSuccess("修改成功");
                                this.open = false;
                                this.getList();
                            });
                        } else {
                            addPointGatherConfig(this.form).then(response => {
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
                this.$modal.confirm('是否确认删除聚集配置编号为"' + ids + '"的数据项？').then(function () {
                    return delPointGatherConfig(ids);
                }).then(() => {
                    this.getList();
                    this.$modal.msgSuccess("删除成功");
                }).catch(() => {
                });
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$modal.confirm('是否确认导出所有聚集配置数据项？').then(() => {
                    this.exportLoading = true;
                    return exportPointGatherConfig(queryParams);
                }).then(response => {
                    this.$download.name(response.msg);
                    this.exportLoading = false;
                }).catch(() => {
                });
            },
            // 筛选项目
            conditionProjectChange() {
                if (null != this.queryParams.projectId) {
                    this.$emit('projectCurrent', this.queryParams.projectId);

                    projectStructureListByProjectId({
                        projectId: this.queryParams.projectId,
                    }).then((response) => {
                        this.projectStructureData = response;
                    });

                    this.getList();
                }
            },
            // 筛选结构物
            conditionProjectStructureChange() {
                if (null != this.queryParams.projectStructureId) {
                    projectPointGroup({
                        structureId: this.queryParams.projectStructureId,
                    }).then((response) => {
                        this.projectPointGroupData = response;
                        this.projectPointGroupData.unshift({
                            id: -1,
                            name: "未分组",
                            structureId: this.queryParams.projectStructureId
                        })
                    });

                    this.getList();
                }
            },
            dialogFormGroupProjectChange() {
                if (null != this.form.projectId) {
                    projectStructureListByProjectId({
                        projectId: this.form.projectId,
                    }).then((response) => {
                        this.formGroupProjectStructureData = response;
                        this.optionsPointOrDeviceData = [];
                        this.form.structureId = "";
                        this.form.pointId = "";
                    });
                }
            },
            dialogFormGroupProjectStructureChange() {
                if (null != this.form.structureId) {
                    this.optionsPointOrDeviceData = this.getFormProjectPointData(this.form.structureId)
                }

            },
            getFormProjectPointData(projectStructureId) {
                // 获取该项目测点及分组
                var projectPointData = [];

                listProjectPoint({
                    projectStructureId: projectStructureId
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
                                    alarmFlag: childrenItem.alarmFlag,
                                })
                            }
                        }

                        projectPointData.push({
                            value: item.id,
                            label: item.name,
                            children: children
                        })
                    }
                });

                return projectPointData;
            }
        }
    };
</script>
