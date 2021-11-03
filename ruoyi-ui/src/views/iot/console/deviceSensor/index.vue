<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="所属设备" prop="deviceId">
        <el-select v-model="queryParams.deviceId" placeholder="请选择所属设备" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="传感器名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入传感器名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排序编号" prop="sort">
        <el-input
          v-model="queryParams.sort"
          placeholder="请输入排序编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单位" prop="unit">
        <el-input
          v-model="queryParams.unit"
          placeholder="请输入单位"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="传感器类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择传感器类型" clearable size="small">
          <el-option
            v-for="dict in dict.type.iot_device_sensor_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="从站地址" prop="slaveUnitId">
        <el-input
          v-model="queryParams.slaveUnitId"
          placeholder="请输入从站地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="功能码" prop="slaveCode">
        <el-input
          v-model="queryParams.slaveCode"
          placeholder="请输入功能码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="地址Hi" prop="slaveAddressHi">
        <el-input
          v-model="queryParams.slaveAddressHi"
          placeholder="请输入地址Hi"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="地址Lo" prop="slaveAddressLo">
        <el-input
          v-model="queryParams.slaveAddressLo"
          placeholder="请输入地址Lo"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数量Hi" prop="slaveAmountHi">
        <el-input
          v-model="queryParams.slaveAmountHi"
          placeholder="请输入数量Hi"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数量Lo" prop="slaveAmountLo">
        <el-input
          v-model="queryParams.slaveAmountLo"
          placeholder="请输入数量Lo"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="采集周期" prop="cycleTime">
        <el-input
          v-model="queryParams.cycleTime"
          placeholder="请输入采集周期"
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
          v-hasPermi="['iot:deviceSensor:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['iot:deviceSensor:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['iot:deviceSensor:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['iot:deviceSensor:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="deviceSensorList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="采集周期" align="center" prop="id" />
      <el-table-column label="所属设备" align="center" prop="deviceId" />
      <el-table-column label="传感器名称" align="center" prop="name" />
      <el-table-column label="传感器图标" align="center" prop="iconFile" />
      <el-table-column label="排序编号" align="center" prop="sort" />
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column label="传感器类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.iot_device_sensor_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="从站地址" align="center" prop="slaveUnitId" />
      <el-table-column label="功能码" align="center" prop="slaveCode" />
      <el-table-column label="地址Hi" align="center" prop="slaveAddressHi" />
      <el-table-column label="地址Lo" align="center" prop="slaveAddressLo" />
      <el-table-column label="数量Hi" align="center" prop="slaveAmountHi" />
      <el-table-column label="数量Lo" align="center" prop="slaveAmountLo" />
      <el-table-column label="采集周期" align="center" prop="cycleTime" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['iot:deviceSensor:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['iot:deviceSensor:remove']"
          >删除</el-button>
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

    <!-- 添加或修改设备传感器对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="所属设备" prop="deviceId">
          <el-select v-model="form.deviceId" placeholder="请选择所属设备">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="传感器名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入传感器名称" />
        </el-form-item>
        <el-form-item label="传感器图标">
          <imageUpload v-model="form.iconFile"/>
        </el-form-item>
        <el-form-item label="排序编号" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入排序编号" />
        </el-form-item>
        <el-form-item label="单位" prop="unit">
          <el-input v-model="form.unit" placeholder="请输入单位" />
        </el-form-item>
        <el-form-item label="传感器类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择传感器类型">
            <el-option
              v-for="dict in dict.type.iot_device_sensor_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="从站地址" prop="slaveUnitId">
          <el-input v-model="form.slaveUnitId" placeholder="请输入从站地址" />
        </el-form-item>
        <el-form-item label="功能码" prop="slaveCode">
          <el-input v-model="form.slaveCode" placeholder="请输入功能码" />
        </el-form-item>
        <el-form-item label="地址Hi" prop="slaveAddressHi">
          <el-input v-model="form.slaveAddressHi" placeholder="请输入地址Hi" />
        </el-form-item>
        <el-form-item label="地址Lo" prop="slaveAddressLo">
          <el-input v-model="form.slaveAddressLo" placeholder="请输入地址Lo" />
        </el-form-item>
        <el-form-item label="数量Hi" prop="slaveAmountHi">
          <el-input v-model="form.slaveAmountHi" placeholder="请输入数量Hi" />
        </el-form-item>
        <el-form-item label="数量Lo" prop="slaveAmountLo">
          <el-input v-model="form.slaveAmountLo" placeholder="请输入数量Lo" />
        </el-form-item>
        <el-form-item label="采集周期" prop="cycleTime">
          <el-input v-model="form.cycleTime" placeholder="请输入采集周期" />
        </el-form-item>
        <el-form-item label="删除标志" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
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
import { listDeviceSensor, getDeviceSensor, delDeviceSensor, addDeviceSensor, updateDeviceSensor, exportDeviceSensor } from "@/api/iot/deviceSensor";

export default {
  name: "DeviceSensor",
  dicts: ['iot_device_sensor_type'],
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
      // 设备传感器表格数据
      deviceSensorList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deviceId: null,
        name: null,
        iconFile: null,
        sort: null,
        unit: null,
        type: null,
        slaveUnitId: null,
        slaveCode: null,
        slaveAddressHi: null,
        slaveAddressLo: null,
        slaveAmountHi: null,
        slaveAmountLo: null,
        cycleTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        deviceId: [
          { required: true, message: "所属设备不能为空", trigger: "change" }
        ],
        name: [
          { required: true, message: "传感器名称不能为空", trigger: "blur" }
        ],
        slaveUnitId: [
          { required: true, message: "从站地址不能为空", trigger: "blur" }
        ],
        slaveCode: [
          { required: true, message: "功能码不能为空", trigger: "blur" }
        ],
        slaveAddressHi: [
          { required: true, message: "地址Hi不能为空", trigger: "blur" }
        ],
        slaveAddressLo: [
          { required: true, message: "地址Lo不能为空", trigger: "blur" }
        ],
        slaveAmountHi: [
          { required: true, message: "数量Hi不能为空", trigger: "blur" }
        ],
        slaveAmountLo: [
          { required: true, message: "数量Lo不能为空", trigger: "blur" }
        ],
        cycleTime: [
          { required: true, message: "采集周期不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询设备传感器列表 */
    getList() {
      this.loading = true;
      listDeviceSensor(this.queryParams).then(response => {
        this.deviceSensorList = response.rows;
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
        deviceId: null,
        name: null,
        iconFile: null,
        sort: null,
        unit: null,
        type: null,
        slaveUnitId: null,
        slaveCode: null,
        slaveAddressHi: null,
        slaveAddressLo: null,
        slaveAmountHi: null,
        slaveAmountLo: null,
        cycleTime: null,
        delFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备传感器";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDeviceSensor(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备传感器";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDeviceSensor(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDeviceSensor(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除设备传感器编号为"' + ids + '"的数据项？').then(function() {
        return delDeviceSensor(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$modal.confirm('是否确认导出所有设备传感器数据项？').then(() => {
        this.exportLoading = true;
        return exportDeviceSensor(queryParams);
      }).then(response => {
        this.$download.name(response.msg);
        this.exportLoading = false;
      }).catch(() => {});
    }
  }
};
</script>
