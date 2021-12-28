<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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

      <!--      <el-form-item label="监测因素" prop="monitorType">-->
      <!--        <el-select v-model="queryParams.monitorType" placeholder="请选择监测因素" clearable size="small">-->
      <!--          <el-option-->
      <!--            v-for="dict in dict.type.iot_monitor_type"-->
      <!--            :key="dict.value"-->
      <!--            :label="dict.label"-->
      <!--            :value="dict.value"-->
      <!--          />-->
      <!--        </el-select>-->
      <!--      </el-form-item>-->
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
          v-hasPermi="['iot:projectPoint']"
        >新增测点
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAddGroup"
          v-hasPermi="['iot:projectPoint']"
        >新增测点分组
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading"
              :data="projectPointList"
              default-expand-all
              row-key="id"
              @selection-change="handleSelectionChange"
              :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
      >
      <el-table-column label="分组名称" align="center" prop="name">
        <template slot-scope="scope">
          <block v-if="null == scope.row.pointGroupId">
            {{ scope.row.name }}
          </block>
        </template>
      </el-table-column>
      <el-table-column label="测点ID" align="center" prop="id">
        <template slot-scope="scope">
          <block v-if="null != scope.row.pointGroupId">
            {{ scope.row.id }}
          </block>
        </template>
      </el-table-column>
      <el-table-column label="测点名称" align="center" prop="name">
        <template slot-scope="scope">
          <block v-if="null != scope.row.pointGroupId">
            {{ scope.row.name }}
          </block>
        </template>
      </el-table-column>
      <el-table-column label="所属结构物" align="center" prop="projectStructureId">
        <template slot-scope="scope" v-if="null != scope.row.pointGroupId">
          <block v-if="item.id != scope.row.projectStructureId" v-for="item in projectStructureData">
            {{ item.name }}
          </block>
        </template>
      </el-table-column>
      <el-table-column label="警报标志" align="center" prop="alarmFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.iot_alarm" :value="scope.row.alarmFlag"/>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <block v-if="null != scope.row.pointGroupId">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['iot:projectPoint']"
            >修改
            </el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['iot:projectPoint']"
            >删除
            </el-button>
          </block>
          <block v-if="null == scope.row.pointGroupId && -1 != scope.row.id">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdateGroup(scope.row)"
              v-hasPermi="['iot:projectPoint']"
            >修改
            </el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDeleteGroup(scope.row)"
              v-hasPermi="['iot:projectPoint']"
            >删除
            </el-button>
          </block>
        </template>
      </el-table-column>


    </el-table>

    <!-- 添加或修改项目测点对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="140px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称"/>
        </el-form-item>
        <el-form-item label="图片地址">
          <imageUpload v-model="form.photoFile"/>
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="所属项目" prop="projectId">
              <el-select
                v-model="form.projectId"
                placeholder="请选择所属项目"
                @change="dialogFormProjectChange"
              >
                <el-option
                  v-for="item in projectData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属结构物" prop="projectStructureId">
              <el-select v-model="form.projectStructureId"
                         placeholder="请选择所属结构物"
                         @change="dialogFormProjectStructureChange"
              >
                <el-option
                  v-for="item in formProjectStructureData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="所属分组" prop="pointGroupId">
          <el-select v-model="form.pointGroupId" placeholder="请选择所属分组">
            <el-option
              v-for="item in formProjectPointGroupData"
              v-bind:key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="监测因素" prop="monitorType">
          <el-select v-model="form.monitorType" placeholder="请选择监测因素">
            <el-option
              v-for="dict in dict.type.iot_monitor_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <block v-if="1 == form.monitorType">

          <el-form-item label="选择数据来源" prop="monitorData.data_source_sensor">
            <el-cascader
              v-model="form.monitorData.data_source_sensor"
              :options="optionsDeviceData"
              :props="{ expandTrigger: 'hover' }"></el-cascader>
          </el-form-item>
          <el-row>
            <el-col :span="12">
              <el-form-item label="选择基准值数据来源" prop="monitorData.type">
                <el-select v-model="form.monitorData.type" placeholder="选择基准值数据来源">
                  <el-option
                    :key="1"
                    label="基准点"
                    :value="1"
                  ></el-option>
                  <el-option
                    :key="2"
                    label="初始值"
                    :value="2"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="选择基准点"
                            prop="monitorData.data_source_standard_sensor" v-if="1 == form.monitorData.type">
                <el-cascader
                  v-model="form.monitorData.data_source_standard_sensor"
                  :options="optionsDeviceData"
                  :props="{ expandTrigger: 'hover' }"></el-cascader>
              </el-form-item>

              <el-form-item label="初始值"
                            prop="monitorData.data_source_standard_value" v-if="2 == form.monitorData.type">
                <el-input v-model="form.monitorData.data_source_standard_value" type="input" placeholder="请输入初始值"/>
              </el-form-item>
            </el-col>
          </el-row>

        </block>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="titleGroup" :visible.sync="openGroup" append-to-body>
      <el-form ref="formGroup" :model="formGroup" :rules="rulesGroup" label-width="120px">
        <el-form-item label="分组名称" prop="name">
          <el-input v-model="formGroup.name" placeholder="请输入分组名称"/>
        </el-form-item>
        <el-form-item label="所属项目" prop="projectId">
          <el-select
            v-model="formGroup.projectId"
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
          <el-select v-model="formGroup.structureId"
                     placeholder="请选择所属结构物"
          >
            <el-option
              v-for="item in formGroupProjectStructureData"
              v-bind:key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormGroup">确 定</el-button>
        <el-button @click="cancelGroup">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
    import {
        listProjectPoint,
        getProjectPoint,
        delProjectPoint,
        addProjectPoint,
        updateProjectPoint,
        exportProjectPoint
    } from "@/api/iot/projectPoint";

    import {
        projectStructureTypeList,
        projectListBusiness,
        projectStructureListByProjectId,
        projectPointGroup,
        projectDeviceList
    } from "@/api/iot/console";

    import {
        listProjectPointGroup,
        getProjectPointGroup,
        delProjectPointGroup,
        addProjectPointGroup,
        updateProjectPointGroup,
        exportProjectPointGroup
    } from "@/api/iot/projectPointGroup";

    export default {
        name: "ProjectPoint",
        dicts: ['iot_alarm', 'iot_monitor_type'],
        props: {
            isShow: {
                type: Boolean,
                default: false,
            },
            projectId: {
                type: Number,
                default: null
            }
        },
        watch: {
            isShow: function (newObject, oldObject) {
                if (this.isShow) {
                    this.getList();
                }
            },
            projectId: function (newObject, oldObject) {
                this.queryParams.projectId = this.projectId;
                if (this.projectId) {

                    projectStructureListByProjectId({
                        projectId: this.queryParams.projectId,
                    }).then((response) => {
                        this.projectStructureData = response;
                    });

                    this.getList()
                } else {
                    this.queryParams.projectStructureId = null
                    this.getList()
                }
            },
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
                // 项目测点表格数据
                projectPointList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                    name: null,
                    projectStructureId: null,
                    pointGroupId: null,
                    alarmFlag: null,
                    monitorType: null,
                    monitorData: null,
                },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                    'name': [
                        {required: true, message: "名称不能为空", trigger: "blur"}
                    ],
                    'projectStructureId': [
                        {required: true, message: "所属结构物不能为空", trigger: "change"}
                    ],
                    'projectId': [
                        {required: true, message: "所属项目不能为空", trigger: "change"}
                    ],
                    'monitorType': [
                        {required: true, message: "监测因素不能为空", trigger: "change"}
                    ],
                    'monitorData.data_source_sensor': [
                        {required: true, message: "数据来源不能为空", trigger: "change"}
                    ],
                    'monitorData.type': [
                        {required: true, message: "基准值数据来源不能为空", trigger: "change"}
                    ],
                    'monitorData.data_source_standard_sensor': [
                        {required: true, message: "基准点不能为空", trigger: "change"}
                    ],
                    'monitorData.data_source_standard_value': [
                        {required: true, message: "初始值不能为空", trigger: "change"}
                    ]
                },
                projectData: [],
                projectStructureData: [],
                projectPointGroupData: [],
                formProjectStructureData: [],
                formProjectPointGroupData: [],
                openGroup: false,
                // 表单参数
                formGroup: {},
                // 表单校验
                rulesGroup: {
                    name: [
                        {required: true, message: "分组名称不能为空", trigger: "blur"}
                    ],
                    'formGroup.projectId': [
                        {required: true, message: "所属项目不能为空", trigger: "blur"}
                    ],
                    'formGroup.structureId': [
                        {required: true, message: "所属结构物不能为空", trigger: "blur"}
                    ]
                },
                titleGroup: "",
                formGroupProjectStructureData: [],
                optionsDeviceData: []
            };
        },
        created() {
            this.getList();
        },
        methods: {
            /** 查询项目测点列表 */
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

                listProjectPoint(this.queryParams).then(response => {
                    this.projectPointList = response.rows;
                    this.total = response.total;
                    this.loading = false;
                });


                projectStructureListByProjectId({
                    projectId: this.queryParams.projectId,
                }).then((response) => {
                    this.projectStructureData = response;
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
                    projectId: null,
                    id: null,
                    name: null,
                    photoFile: null,
                    projectStructureId: null,
                    pointGroupId: null,
                    alarmFlag: null,
                    monitorType: null,
                    delFlag: null,
                    monitorData: {
                        // data_source_sensor: null,
                        // type: null,
                        // data_source_standard_sensor: null,
                        // data_source_standard_value: null
                    },
                    createBy: null,
                    createTime: null,
                    updateBy: null,
                    updateTime: null,
                };

                this.formProjectStructureData = this.projectStructureData;
                this.formProjectPointGroupData = this.projectPointGroupData;
                this.optionsDeviceData = []
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
                    threshold: null,
                    computeType: null,
                    title: null,
                    content: null,
                };
                this.resetForm("queryParams");
                this.$emit('projectCurrent', null);
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

                this.form.projectId = this.queryParams.projectId
                this.form.projectStructureId = this.queryParams.projectStructureId

                // 获取设备列表
                this.getDeviceAndSensor(this.queryParams.projectId)

                this.open = true;
                this.title = "添加项目测点";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getProjectPoint(id).then(response => {
                    this.form = response.data;

                    for (var i = 0; i < this.projectStructureData.length; i++) {
                        var item = this.projectStructureData[i];

                        if (item.id == this.form.projectStructureId) {
                            this.form.projectId = item.projectId
                        }
                    }

                    // 获取设备及传感器
                    var projectDeviceData = [];

                    projectDeviceList({
                        projectId: this.form.projectId
                    }).then(res => {
                        for (var i = 0; i < res.length; i++) {
                            var item = res[i];
                            var children = [];

                            if (null != item.wxcxcDeviceSensorList) {
                                for (var j = 0; j < item.wxcxcDeviceSensorList.length; j++) {
                                    var sensorItem = item.wxcxcDeviceSensorList[j]
                                    children.push({
                                        value: sensorItem.id,
                                        label: sensorItem.name,
                                    })
                                }
                            }

                            projectDeviceData.push({
                                value: item.id,
                                label: item.name,
                                children: children
                            })
                        }

                        this.optionsDeviceData = projectDeviceData

                        // 数据回填
                        if (1 == response.data.monitorType && null != response.data.monitorData && "" != response.data.monitorData) {
                            var resMonitorData = JSON.parse(response.data.monitorData)
                            var monitorData = {}

                            monitorData.data_source_sensor = this.getProjectDeviceDataIdBySensorId(resMonitorData.data_source_sensor)
                            monitorData.type = resMonitorData.type
                            if (1 == monitorData.type) {
                                monitorData.data_source_standard_sensor = this.getProjectDeviceDataIdBySensorId(resMonitorData.data_source_standard_sensor)
                            } else if (2 == monitorData.type) {
                                monitorData.data_source_standard_value = resMonitorData.data_source_standard_value
                            }

                            this.form.monitorData = monitorData
                        }

                        if (null == response.data.monitorData || "" == response.data.monitorData) {
                            this.form.monitorData = {}
                        }

                        console.log(this.form)

                        this.open = true;
                        this.title = "修改项目测点";
                    });


                });
            },
            /** 提交按钮 */
            submitForm() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        var formData = JSON.parse(JSON.stringify(this.form))
                        // 处理检测因素
                        var monitorData = {};

                        if (1 == formData.monitorType) {
                            monitorData.data_source_sensor = formData.monitorData.data_source_sensor[1]
                            monitorData.type = formData.monitorData.type
                            if (1 == formData.monitorData.type) {
                                monitorData.data_source_standard_sensor = formData.monitorData.data_source_standard_sensor[1]
                            } else if (2 == formData.monitorData.type) {
                                monitorData.data_source_standard_value = formData.monitorData.data_source_standard_value
                            }
                        }

                        formData.monitorData = monitorData;

                        if (this.form.id != null) {
                            updateProjectPoint(formData).then(response => {
                                this.$modal.msgSuccess("修改成功");
                                this.open = false;
                                this.getList();
                            });
                        } else {
                            addProjectPoint(formData).then(response => {
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
                this.$modal.confirm('是否确认删除项目测点编号为"' + ids + '"的数据项？').then(function () {
                    return delProjectPoint(ids);
                }).then(() => {
                    this.getList();
                    this.$modal.msgSuccess("删除成功");
                }).catch(() => {
                });
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$modal.confirm('是否确认导出所有项目测点数据项？').then(() => {
                    this.exportLoading = true;
                    return exportProjectPoint(queryParams);
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
            // 测点dialog 项目变化
            dialogFormProjectChange() {
                if (null != this.form.projectId) {
                    projectStructureListByProjectId({
                        projectId: this.form.projectId,
                    }).then((response) => {
                        this.formProjectStructureData = response;
                        this.form.projectStructureId = null;
                        this.form.pointGroupId = null;
                    });

                    // 获取设备列表
                    this.getDeviceAndSensor(this.form.projectId)
                }
            },
            // 测点dialog 结构物变化
            dialogFormProjectStructureChange() {
                if (null != this.form.projectStructureId) {
                    // 获取测点分组
                    projectPointGroup({
                        structureId: this.form.projectStructureId,
                    }).then((response) => {
                        this.formProjectPointGroupData = response;
                        this.projectPointGroupData.unshift({
                            id: -1,
                            name: "未分组",
                            structureId: this.queryParams.projectStructureId
                        })
                        this.form.pointGroupId = null;
                    });

                }
            },
            // 取消按钮
            cancelGroup() {
                this.openGroup = false;
                this.resetGroup();
            },
            // 表单重置
            resetGroup() {
                this.formGroup = {
                    id: null,
                    name: null,
                    structureId: null
                };

                this.formGroupProjectStructureData = this.projectStructureData
                this.resetForm("formGroup");
            },
            /** 新增按钮操作 */
            handleAddGroup() {
                this.resetGroup();
                this.openGroup = true;
                this.titleGroup = "添加测点分组";
            },
            /** 修改按钮操作 */
            handleUpdateGroup(row) {
                this.resetGroup();
                const id = row.id || this.ids
                getProjectPointGroup(id).then(response => {
                    this.formGroup = response.data;

                    for (var i = 0; i < this.projectStructureData.length; i++) {
                        var item = this.projectStructureData[i];

                        if (item.id == this.formGroup.structureId) {
                            this.formGroup.projectId = item.projectId
                        }
                    }

                    this.openGroup = true;
                    this.titleGroup = "修改测点分组";
                });
            },
            /** 提交按钮 */
            submitFormGroup() {
                this.$refs["formGroup"].validate(valid => {
                    if (valid) {
                        if (this.formGroup.id != null) {
                            updateProjectPointGroup(this.formGroup).then(response => {
                                this.$modal.msgSuccess("修改成功");
                                this.openGroup = false;
                                this.getList();
                            });
                        } else {
                            addProjectPointGroup(this.formGroup).then(response => {
                                this.$modal.msgSuccess("新增成功");
                                this.openGroup = false;
                                this.getList();
                            });
                        }
                    }
                });
            },
            /** 删除按钮操作 */
            handleDeleteGroup(row) {
                const ids = row.id || this.ids;
                this.$modal.confirm('是否确认删除测点分组编号为"' + ids + '"的数据项？').then(function () {
                    return delProjectPointGroup(ids);
                }).then(() => {
                    this.getList();
                    this.$modal.msgSuccess("删除成功");
                }).catch(() => {
                });
            },
            dialogFormGroupProjectChange() {
                if (null != this.formGroup.projectId) {
                    projectStructureListByProjectId({
                        projectId: this.formGroup.projectId,
                    }).then((response) => {
                        this.formGroupProjectStructureData = response;
                        this.formGroup.structureId = null;
                    });
                }
            },
            getProjectDeviceDataIdBySensorId(sensorId) {
                for (var i = 0; i < this.optionsDeviceData.length; i++) {
                    var item = this.optionsDeviceData[i]

                    for (var j = 0; j < item.children.length; j++) {
                        var childrenItem = item.children[j]
                        console.log(childrenItem.value)

                        if (sensorId == childrenItem.value) {
                            return [item.value, childrenItem.value]
                        }
                    }
                }

                return null
            },
            getDeviceAndSensor(projectId) {
                // 获取设备及传感器
                var projectDeviceData = [];

                projectDeviceList({
                    projectId: projectId
                }).then(response => {
                    for (var i = 0; i < response.length; i++) {
                        var item = response[i];
                        var children = [];

                        if (null != item.wxcxcDeviceSensorList) {
                            for (var j = 0; j < item.wxcxcDeviceSensorList.length; j++) {
                                var sensorItem = item.wxcxcDeviceSensorList[j]
                                children.push({
                                    value: sensorItem.id,
                                    label: sensorItem.name,
                                })
                            }
                        }

                        projectDeviceData.push({
                            value: item.id,
                            label: item.name,
                            children: children
                        })
                    }

                    this.optionsDeviceData = projectDeviceData
                });
            }


        }
    };
</script>
