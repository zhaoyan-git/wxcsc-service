<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="设备名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入设备名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属项目" prop="projectId">
        <el-select v-model="queryParams.projectId" placeholder="请选择所属项目" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="厂商" prop="manufacturer">
        <el-input
          v-model="queryParams.manufacturer"
          placeholder="请输入厂商"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="型号" prop="modelNumber">
        <el-input
          v-model="queryParams.modelNumber"
          placeholder="请输入型号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="sim卡号" prop="simNumber">
        <el-input
          v-model="queryParams.simNumber"
          placeholder="请输入sim卡号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="DTU ID" prop="dtuId">
        <el-input
          v-model="queryParams.dtuId"
          placeholder="请输入DTU ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否在线" prop="onlineFlag">
        <el-input
          v-model="queryParams.onlineFlag"
          placeholder="请输入是否在线"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备纬度" prop="positionLat">
        <el-input
          v-model="queryParams.positionLat"
          placeholder="请输入设备纬度"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备经度" prop="positionLng">
        <el-input
          v-model="queryParams.positionLng"
          placeholder="请输入设备经度"
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
          v-hasPermi="['iot:deviceGateway:add']"
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
          v-hasPermi="['iot:deviceGateway:edit']"
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
          v-hasPermi="['iot:deviceGateway:remove']"
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
          v-hasPermi="['iot:deviceGateway:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="deviceGatewayList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="设备经度" align="center" prop="id" />
      <el-table-column label="设备名称" align="center" prop="name" />
      <el-table-column label="所属项目" align="center" prop="projectId" />
      <el-table-column label="厂商" align="center" prop="manufacturer" />
      <el-table-column label="型号" align="center" prop="modelNumber" />
      <el-table-column label="设备说明" align="center" prop="detail" />
      <el-table-column label="sim卡号" align="center" prop="simNumber" />
      <el-table-column label="DTU ID" align="center" prop="dtuId" />
      <el-table-column label="是否在线" align="center" prop="onlineFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.iot_device_status" :value="scope.row.onlineFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="设备纬度" align="center" prop="positionLat" />
      <el-table-column label="设备经度" align="center" prop="positionLng" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['iot:deviceGateway:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['iot:deviceGateway:remove']"
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

    <!-- 添加或修改设备网关对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="设备名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入设备名称" />
        </el-form-item>
        <el-form-item label="所属项目" prop="projectId">
          <el-select v-model="form.projectId" placeholder="请选择所属项目">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="厂商" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入厂商" />
        </el-form-item>
        <el-form-item label="型号" prop="modelNumber">
          <el-input v-model="form.modelNumber" placeholder="请输入型号" />
        </el-form-item>
        <el-form-item label="设备说明" prop="detail">
          <el-input v-model="form.detail" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="sim卡号" prop="simNumber">
          <el-input v-model="form.simNumber" placeholder="请输入sim卡号" />
        </el-form-item>
        <el-form-item label="DTU ID" prop="dtuId">
          <el-input v-model="form.dtuId" placeholder="请输入DTU ID" />
        </el-form-item>
        <el-form-item label="设备纬度" prop="positionLat">
          <el-input v-model="form.positionLat" placeholder="请输入设备纬度" />
        </el-form-item>
        <el-form-item label="设备经度" prop="positionLng">
          <el-input v-model="form.positionLng" placeholder="请输入设备经度" />
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
import { listDeviceGateway, getDeviceGateway, delDeviceGateway, addDeviceGateway, updateDeviceGateway, exportDeviceGateway } from "@/api/iot/deviceGateway";

export default {
  name: "DeviceGateway",
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
      // 设备网关表格数据
      deviceGatewayList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        projectId: null,
        manufacturer: null,
        modelNumber: null,
        detail: null,
        simNumber: null,
        dtuId: null,
        onlineFlag: null,
        positionLat: null,
        positionLng: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "设备名称不能为空", trigger: "blur" }
        ],
        projectId: [
          { required: true, message: "所属项目不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询设备网关列表 */
    getList() {
      this.loading = true;
      listDeviceGateway(this.queryParams).then(response => {
        this.deviceGatewayList = response.rows;
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
        name: null,
        projectId: null,
        manufacturer: null,
        modelNumber: null,
        detail: null,
        simNumber: null,
        dtuId: null,
        onlineFlag: null,
        positionLat: null,
        positionLng: null,
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
      this.title = "添加设备网关";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDeviceGateway(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备网关";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDeviceGateway(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDeviceGateway(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除设备网关编号为"' + ids + '"的数据项？').then(function() {
        return delDeviceGateway(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$modal.confirm('是否确认导出所有设备网关数据项？').then(() => {
        this.exportLoading = true;
        return exportDeviceGateway(queryParams);
      }).then(response => {
        this.$download.name(response.msg);
        this.exportLoading = false;
      }).catch(() => {});
    }
  }
};
</script>
