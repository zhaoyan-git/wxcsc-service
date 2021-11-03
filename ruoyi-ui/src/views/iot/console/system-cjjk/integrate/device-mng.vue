<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="所属项目" prop="projectId">
        <el-select v-model="queryParams.projectId" placeholder="请选择所属项目" clearable size="small"
                   @change="conditionProjectChange">
          <el-option
            :label="item.name"
            :value="item.id"
            v-for="item in projectData"
            :key="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="选择网关" prop="gatewayId">
        <el-select v-model="queryParams.gatewayId" placeholder="请选择网关ID" clearable size="small">
          <el-option
            :label="item.name"
            :value="item.id"
            v-for="item in deviceGatewayData"
            :key="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="设备名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入设备名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['iot:device']"
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
          v-hasPermi="['iot:device']"
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
          v-hasPermi="['iot:device']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['iot:device:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="deviceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="105" align="center"/>
      <el-table-column label="设备ID" align="center" prop="id"/>
      <el-table-column label="所属项目" align="center" prop="projectId"/>
      <el-table-column label="设备名称" align="center" prop="name"/>
      <el-table-column label="网关" align="center" prop="gatewayId"/>
      <el-table-column label="是否在线" align="center" prop="onlineFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.iot_device_status" :value="scope.row.onlineFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="是否报警" align="center" prop="alarmFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.iot_alarm" :value="scope.row.alarmFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['iot:device']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['iot:device']"
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

    <!-- 添加或修改设备表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="80%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="所属项目" prop="projectId">
          <el-select v-model="form.projectId" placeholder="请选择所属项目" @change="dialogFormProjectChange">
            <el-option
              :label="item.name"
              :value="item.id"
              v-for="item in projectData"
              :key="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
<!--        <el-form-item label="选择产品" prop="projectId">-->
<!--          <el-select v-model="form.projectId" placeholder="请选择产品" @change="dialogFormProjectChange">-->
<!--            <el-option-->
<!--              :label="item.name"-->
<!--              :value="item.id"-->
<!--              v-for="item in projectData"-->
<!--              :key="item.id"-->
<!--            ></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
        <el-form-item label="设备名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入设备名称"/>
        </el-form-item>
        <el-form-item label="选择网关" prop="gatewayId">
          <el-select v-model="form.gatewayId" placeholder="请选择网关ID">
            <el-option
              :label="item.name"
              :value="item.id"
              v-for="item in formDeviceGatewayData"
              :key="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="设备图片">
          <imageUpload v-model="form.photoFile"/>
        </el-form-item>
        <el-form-item label="厂商" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入厂商"/>
        </el-form-item>
        <el-form-item label="型号" prop="modelNumber">
          <el-input v-model="form.modelNumber" placeholder="请输入型号"/>
        </el-form-item>
        <el-form-item label="设备说明" prop="detail">
          <el-input v-model="form.detail" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="设备纬度" prop="positionLat">
          <el-input v-model="form.positionLat" placeholder="请输入设备纬度"/>
        </el-form-item>
        <el-form-item label="设备经度" prop="positionLng">
          <el-input v-model="form.positionLng" placeholder="请输入设备经度"/>
        </el-form-item>
        <el-divider content-position="center">设备传感器信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddWxcxcDeviceSensor">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteWxcxcDeviceSensor">删除
            </el-button>
          </el-col>
        </el-row>
        <el-table :data="wxcxcDeviceSensorList" :row-class-name="rowWxcxcDeviceSensorIndex"
                  @selection-change="handleWxcxcDeviceSensorSelectionChange" ref="wxcxcDeviceSensor">
          <el-table-column type="selection" width="50" align="center"/>
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="传感器名称" prop="name">
            <template slot-scope="scope">
              <el-input v-model="scope.row.name" placeholder="请输入传感器名称"/>
            </template>
          </el-table-column>
          <el-table-column label="传感器图标" prop="iconFile">
            <template slot-scope="scope">
              <imageUpload v-model="scope.row.iconFile" :limit="1" class="sensorPhoto"/>
            </template>
          </el-table-column>
          <el-table-column label="排序编号" prop="sort">
            <template slot-scope="scope">
              <el-input v-model="scope.row.sort" placeholder="请输入排序编号"/>
            </template>
          </el-table-column>
          <el-table-column label="单位" prop="unit">
            <template slot-scope="scope">
              <el-input v-model="scope.row.unit" placeholder="请输入单位"/>
            </template>
          </el-table-column>
          <el-table-column label="传感器类型" prop="type">
            <template slot-scope="scope">
              <el-select v-model="scope.row.type" placeholder="请输入传感器类型">
                <el-option
                  v-for="dict in dict.type.iot_device_sensor_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
                <!--                <dict-tag :options="dict.type.iot_device_sensor_type" :value="scope.row.type"/>-->
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="从站地址" prop="slaveUnitId">
            <template slot-scope="scope">
              <el-input v-model="scope.row.slaveUnitId" placeholder="请输入从站地址"/>
            </template>
          </el-table-column>
          <el-table-column label="功能码" prop="slaveCode">
            <template slot-scope="scope">
              <el-input v-model="scope.row.slaveCode" placeholder="请输入功能码"/>
            </template>
          </el-table-column>
          <el-table-column label="地址Hi" prop="slaveAddressHi">
            <template slot-scope="scope">
              <el-input v-model="scope.row.slaveAddressHi" placeholder="请输入地址Hi"/>
            </template>
          </el-table-column>
          <el-table-column label="地址Lo" prop="slaveAddressLo">
            <template slot-scope="scope">
              <el-input v-model="scope.row.slaveAddressLo" placeholder="请输入地址Lo"/>
            </template>
          </el-table-column>
          <el-table-column label="数量Hi" prop="slaveAmountHi">
            <template slot-scope="scope">
              <el-input v-model="scope.row.slaveAmountHi" placeholder="请输入数量Hi"/>
            </template>
          </el-table-column>
          <el-table-column label="数量Lo" prop="slaveAmountLo">
            <template slot-scope="scope">
              <el-input v-model="scope.row.slaveAmountLo" placeholder="请输入数量Lo"/>
            </template>
          </el-table-column>
          <el-table-column label="采集周期" prop="cycleTime">
            <template slot-scope="scope">
              <el-input v-model="scope.row.cycleTime" placeholder="请输入采集周期"/>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
    import {listDevice, getDevice, delDevice, addDevice, updateDevice, exportDevice} from "@/api/iot/device";

    import {
        projectListBusiness,
        deviceGatewayList
    } from "@/api/iot/console";

    export default {
        name: "Device",
        dicts: ['iot_device_status', 'iot_device_sensor_type'],
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
                if (this.projectId) {
                    this.queryParams.projectId = this.projectId;

                    deviceGatewayList({
                        projectId: this.queryParams.projectId,
                    }).then((response) => {
                        this.deviceGatewayData = response;
                    });
                }
            }
        },
        data() {
            return {
                // 遮罩层
                loading: true,
                // 导出遮罩层
                exportLoading: false,
                // 选中数组
                ids: [],
                // 子表选中数据
                checkedWxcxcDeviceSensor: [],
                // 非单个禁用
                single: true,
                // 非多个禁用
                multiple: true,
                // 显示搜索条件
                showSearch: true,
                // 总条数
                total: 0,
                // 设备表表格数据
                deviceList: [],
                // 设备传感器表格数据
                wxcxcDeviceSensorList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                    projectId: null,
                    name: null,
                    manufacturer: null,
                    gatewayId: null,
                    modelNumber: null,
                    photoFile: null,
                    detail: null,
                    onlineFlag: null,
                    alarmFlag: null,
                    positionLat: null,
                    positionLng: null,
                },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                    projectId: [
                        {required: true, message: "所属项目不能为空", trigger: "change"}
                    ],
                    name: [
                        {required: true, message: "设备名称不能为空", trigger: "blur"}
                    ],
                    gatewayId: [
                        {required: true, message: "网关ID不能为空", trigger: "change"}
                    ],
                },
                projectData: [],
                deviceGatewayData: [],
                formDeviceGatewayData: []
            };
        },
        created() {
            this.getList();
        },
        methods: {
            /** 查询设备表列表 */
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

                listDevice(this.queryParams).then(response => {
                    this.deviceList = response.rows;
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
                    projectId: null,
                    name: null,
                    manufacturer: null,
                    gatewayId: null,
                    modelNumber: null,
                    photoFile: null,
                    detail: null,
                    onlineFlag: null,
                    alarmFlag: null,
                    positionLat: null,
                    positionLng: null,
                    delFlag: null,
                    createBy: null,
                    createTime: null,
                    updateBy: null,
                    updateTime: null
                };
                this.wxcxcDeviceSensorList = [];
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
                this.title = "添加设备表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getDevice(id).then(response => {
                    this.form = response.data;
                    this.wxcxcDeviceSensorList = response.data.wxcxcDeviceSensorList;

                    deviceGatewayList({
                        projectId: this.form.projectId,
                    }).then((response) => {
                        this.formDeviceGatewayData = response;
                    });

                    this.open = true;
                    this.title = "修改设备表";
                });
            },
            /** 提交按钮 */
            submitForm() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        this.form.wxcxcDeviceSensorList = this.wxcxcDeviceSensorList;
                        if (this.form.id != null) {
                            updateDevice(this.form).then(response => {
                                this.$modal.msgSuccess("修改成功");
                                this.open = false;
                                this.getList();
                            });
                        } else {
                            addDevice(this.form).then(response => {
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
                this.$modal.confirm('是否确认删除设备表编号为"' + ids + '"的数据项？').then(function () {
                    return delDevice(ids);
                }).then(() => {
                    this.getList();
                    this.$modal.msgSuccess("删除成功");
                }).catch(() => {
                });
            },
            /** 设备传感器序号 */
            rowWxcxcDeviceSensorIndex({row, rowIndex}) {
                row.index = rowIndex + 1;
            },
            /** 设备传感器添加按钮操作 */
            handleAddWxcxcDeviceSensor() {
                let obj = {};
                obj.name = "";
                obj.iconFile = "";
                obj.sort = "";
                obj.unit = "";
                obj.type = "";
                obj.slaveUnitId = "";
                obj.slaveCode = "";
                obj.slaveAddressHi = "";
                obj.slaveAddressLo = "";
                obj.slaveAmountHi = "";
                obj.slaveAmountLo = "";
                obj.cycleTime = "";
                this.wxcxcDeviceSensorList.push(obj);
            },
            /** 设备传感器删除按钮操作 */
            handleDeleteWxcxcDeviceSensor() {
                if (this.checkedWxcxcDeviceSensor.length == 0) {
                    this.$modal.msgError("请先选择要删除的设备传感器数据");
                } else {
                    const wxcxcDeviceSensorList = this.wxcxcDeviceSensorList;
                    const checkedWxcxcDeviceSensor = this.checkedWxcxcDeviceSensor;
                    this.wxcxcDeviceSensorList = wxcxcDeviceSensorList.filter(function (item) {
                        return checkedWxcxcDeviceSensor.indexOf(item.index) == -1
                    });
                }
            },
            /** 复选框选中数据 */
            handleWxcxcDeviceSensorSelectionChange(selection) {
                this.checkedWxcxcDeviceSensor = selection.map(item => item.index)
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$modal.confirm('是否确认导出所有设备表数据项？').then(() => {
                    this.exportLoading = true;
                    return exportDevice(queryParams);
                }).then(response => {
                    this.$download.name(response.msg);
                    this.exportLoading = false;
                }).catch(() => {
                });
            },
            conditionProjectChange() {
                if (null != this.queryParams.projectId) {
                    deviceGatewayList({
                        projectId: this.queryParams.projectId,
                    }).then((response) => {
                        this.deviceGatewayData = response;
                    });

                    this.$emit('projectCurrent', this.queryParams.projectId);

                    this.getList();
                }
            },
            dialogFormProjectChange() {
                if (null != this.form.projectId) {
                    deviceGatewayList({
                        projectId: this.form.projectId,
                    }).then((response) => {
                        this.formDeviceGatewayData = response;
                        this.form.gatewayId = null;
                    });

                    this.getList();
                }
            }
        }
    };
</script>

<style>
  .sensorPhoto .el-upload {
    width: 50px !important;
    height: 50px !important;

  }

  .sensorPhoto .el-upload .el-icon-plus {
    width: 50px;
    height: 50px;
    vertical-align: top;
    margin-top: 10px;
  }

  .sensorPhoto .el-upload__tip {
    display: none;
  }

  .sensorPhoto .el-upload-list__item {
    width: 100px !important;
    height: 50px !important;
  }

  .sensorPhoto .el-upload-list__item-thumbnail {
    width: 50px !important;
    height: 50px !important;
    margin-left: 25px;
  }
</style>
