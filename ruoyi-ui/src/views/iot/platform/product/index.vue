<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="设备类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择设备类型" clearable size="small">
          <el-option
            v-for="dict in dict.type.iot_device_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="产品名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入产品名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['iot:product:add']"
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
          v-hasPermi="['iot:product:edit']"
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
          v-hasPermi="['iot:product:remove']"
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
          v-hasPermi="['iot:product:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="productList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="产品ID" align="center" prop="id"/>
      <el-table-column label="设备类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.iot_device_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="产品名称" align="center" prop="name"/>
      <el-table-column label="厂商" align="center" prop="manufacturer"/>
      <el-table-column label="型号" align="center" prop="modelNumber"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['iot:product:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['iot:product:remove']"
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

    <!-- 添加或修改产品管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="设备类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择设备类型">
            <el-option
              v-for="dict in dict.type.iot_device_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="产品名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入产品名称"/>
        </el-form-item>
        <el-form-item label="厂商" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入厂商"/>
        </el-form-item>
        <el-form-item label="型号" prop="modelNumber">
          <el-input v-model="form.modelNumber" placeholder="请输入型号"/>
        </el-form-item>
        <el-form-item label="产品图片">
          <imageUpload v-model="form.photoFile" :limit="1"/>
        </el-form-item>
        <el-form-item label="产品说明" prop="detail">
          <el-input v-model="form.detail" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-divider content-position="center">产品传感器管理信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddWxcxcProductSensor">添加
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteWxcxcProductSensor">删除
            </el-button>
          </el-col>
        </el-row>
        <el-table :data="wxcxcProductSensorList" :row-class-name="rowWxcxcProductSensorIndex"
                  @selection-change="handleWxcxcProductSensorSelectionChange" ref="wxcxcProductSensor">
          <el-table-column type="selection" width="50" align="center"/>
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="传感器名称" prop="name">
            <template slot-scope="scope">
              <el-input v-model="scope.row.name" placeholder="请输入传感器名称"/>
            </template>
          </el-table-column>
          <el-table-column label="传感器图标" prop="iconFile">
            <template slot-scope="scope">
              <imageUpload v-model="scope.row.iconFile" :limit="1" class="sensorPhoto" />
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
              <el-select v-model="scope.row.type" placeholder="请选择传感器类型">
                <el-option
                  v-for="dict in dict.type.iot_device_sensor_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
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
    import {listProduct, getProduct, delProduct, addProduct, updateProduct, exportProduct} from "@/api/iot/product";

    export default {
        name: "Product",
        dicts: ['iot_device_type', "iot_device_sensor_type"],
        data() {
            return {
                // 遮罩层
                loading: true,
                // 导出遮罩层
                exportLoading: false,
                // 选中数组
                ids: [],
                // 子表选中数据
                checkedWxcxcProductSensor: [],
                // 非单个禁用
                single: true,
                // 非多个禁用
                multiple: true,
                // 显示搜索条件
                showSearch: true,
                // 总条数
                total: 0,
                // 产品管理表格数据
                productList: [],
                // 产品传感器管理表格数据
                wxcxcProductSensorList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                    type: null,
                    name: null,
                    manufacturer: null,
                    modelNumber: null,
                    photoFile: null,
                    detail: null,
                },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                    type: [
                        {required: true, message: "设备类型不能为空", trigger: "change"}
                    ],
                    name: [
                        {required: true, message: "产品名称不能为空", trigger: "blur"}
                    ],
                }
            };
        },
        created() {
            this.getList();
        },
        methods: {
            /** 查询产品管理列表 */
            getList() {
                this.loading = true;
                listProduct(this.queryParams).then(response => {
                    this.productList = response.rows;
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
                    type: null,
                    name: null,
                    manufacturer: null,
                    modelNumber: null,
                    photoFile: null,
                    detail: null,
                    createBy: null,
                    createTime: null,
                    updateBy: null,
                    updateTime: null
                };
                this.wxcxcProductSensorList = [];
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
                this.title = "添加产品管理";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getProduct(id).then(response => {
                    this.form = response.data;
                    this.wxcxcProductSensorList = response.data.wxcxcProductSensorList;
                    this.open = true;
                    this.title = "修改产品管理";
                });
            },
            /** 提交按钮 */
            submitForm() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        this.form.wxcxcProductSensorList = this.wxcxcProductSensorList;
                        if (this.form.id != null) {
                            updateProduct(this.form).then(response => {
                                this.$modal.msgSuccess("修改成功");
                                this.open = false;
                                this.getList();
                            });
                        } else {
                            addProduct(this.form).then(response => {
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
                this.$modal.confirm('是否确认删除产品管理编号为"' + ids + '"的数据项？').then(function () {
                    return delProduct(ids);
                }).then(() => {
                    this.getList();
                    this.$modal.msgSuccess("删除成功");
                }).catch(() => {
                });
            },
            /** 产品传感器管理序号 */
            rowWxcxcProductSensorIndex({row, rowIndex}) {
                row.index = rowIndex + 1;
            },
            /** 产品传感器管理添加按钮操作 */
            handleAddWxcxcProductSensor() {
                let obj = {};
                obj.name = "";
                obj.iconFile = "";
                obj.sort = "";
                obj.unit = "";
                obj.type = "";
                this.wxcxcProductSensorList.push(obj);
            },
            /** 产品传感器管理删除按钮操作 */
            handleDeleteWxcxcProductSensor() {
                if (this.checkedWxcxcProductSensor.length == 0) {
                    this.$modal.msgError("请先选择要删除的产品传感器管理数据");
                } else {
                    const wxcxcProductSensorList = this.wxcxcProductSensorList;
                    const checkedWxcxcProductSensor = this.checkedWxcxcProductSensor;
                    this.wxcxcProductSensorList = wxcxcProductSensorList.filter(function (item) {
                        return checkedWxcxcProductSensor.indexOf(item.index) == -1
                    });
                }
            },
            /** 复选框选中数据 */
            handleWxcxcProductSensorSelectionChange(selection) {
                this.checkedWxcxcProductSensor = selection.map(item => item.index)
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$modal.confirm('是否确认导出所有产品管理数据项？').then(() => {
                    this.exportLoading = true;
                    return exportProduct(queryParams);
                }).then(response => {
                    this.$download.name(response.msg);
                    this.exportLoading = false;
                }).catch(() => {
                });
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

  .sensorPhoto .el-upload__tip{
    display: none;
  }

  .sensorPhoto .el-upload-list__item{
    width: 100px!important;
    height: 50px!important;
  }

  .sensorPhoto .el-upload-list__item-thumbnail{
    width: 50px !important;
    height: 50px !important;
    margin-left: 25px;
  }
</style>
