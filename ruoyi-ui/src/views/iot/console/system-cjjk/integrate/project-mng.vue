<template>
  <div>
    <!-- 筛选 START -->
    <el-form :inline="true" class="top-form-inline">
      <el-form-item label="编号">
        <el-input v-model="queryParams.id" placeholder="编号"/>
      </el-form-item>
      <el-form-item label="项目名称">
        <el-input v-model="queryParams.name" placeholder="项目名称"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="conditionQuery">条件查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="success" @click="handleCreate">添加项目</el-button>
      </el-form-item>
    </el-form>
    <!-- 筛选 END -->

    <!-- 数据列表 START -->
    <el-table
      v-loading="loading"
      :data="tableData"
      row-key="item"
      style="width: 100%"
    >
      <!-- 表头及数据 START -->
      <el-table-column prop="id" label="编号"/>
      <el-table-column prop="name" label="项目名称"/>
      <el-table-column prop="positionLat" label="项目位置纬度"/>
      <el-table-column prop="positionLng" label="项目位置经度"/>
      <!-- 表头及数据 END -->

      <!-- 操作列 START -->
      <el-table-column min-width="80">
        <template slot="header"> 操作</template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="handleUpdate(scope.$index, scope.row)"
          >编辑
          </el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            v-if="scope.row.userId != 1 && scope.row.userId != 2"
          >删除
          </el-button
          >
        </template>
      </el-table-column>
      <!-- 操作列 END -->
    </el-table>
    <!-- 页码 START -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="loadPage"
    />
    <!-- 页码 START -->
    <!-- 数据列表 END -->

    <!-- 创建及编辑视图 START -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form
        ref="dataForm"
        :rules="rules"
        :model="dataForm"
        label-position="left"
        label-width="100px"
      >
        <el-form-item label="项目名称" prop="name">
          <el-input v-model="dataForm.name"/>
        </el-form-item>
        <el-form-item label="项目图片">
          <imageUpload v-model="dataForm.photoFile"/>
        </el-form-item>
        <el-form-item label="监测目的" prop="aim">
          <el-input type="textarea" v-model="dataForm.aim"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="details">
          <el-input type="textarea" v-model="dataForm.details"></el-input>
        </el-form-item>
        <el-form-item label="项目位置纬度" prop="positionLat">
          <el-input v-model="dataForm.positionLat" type="number"/>
        </el-form-item>
        <el-form-item label="项目位置经度" prop="positionLng">
          <el-input v-model="dataForm.positionLng" type="number"/>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false"> 关闭</el-button>
        <el-button
          type="primary"
          @click="dialogStatus === 'create' ? createData() : updateData()"
        >
          提交
        </el-button>
      </div>
    </el-dialog>
    <!-- 创建及编辑视图 START -->
  </div>
</template>
<script>
    import {listProject, getProject, delProject, addProject, updateProject, exportProject} from "@/api/iot/project";

    export default {
        props: {
            isShow: {
                type: Boolean,
                default: false,
            },
        },
        data() {
            return {
                // 总条数
                total: 0,
                processEnv: process.env,
                loading: false,
                dataForm: {},
                tableData: [],
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                    name: null,
                    businessId: null,
                    alarmFlag: null,
                },
                dialogFormVisible: false,
                textMap: {
                    edit: "编辑",
                    create: "创建",
                },
                dialogStatus: "",
                rules: {
                    name: [{required: true, message: "请输入项目名", trigger: "blur"}],
                },
                uploadFileList: [],
            };
        },
        mounted() {
            this.init();
        },
        watch: {
            isShow: function (newObject, oldObject) {
                if (this.isShow) {
                    this.loadPage();
                }
            },
        },
        methods: {
            init() {
                this.loadPage();
            },
            loadPage() {
                var that = this;
                if (this.loading) {
                    return;
                }
                this.loading = true;
                listProject(this.queryParams).then((response) => {
                    this.tableData = response.rows;
                    this.total = response.total;
                    this.loading = false;
                });
            },
            conditionQuery() {
                this.loadPage();
            },

            resetTemp() {
                this.dataForm = {};
            },
            handleCreate() {
                this.resetTemp();
                this.dialogStatus = "create";
                this.$nextTick(() => {
                    this.$refs["dataForm"].clearValidate();
                });
                this.dialogFormVisible = true;
            },
            handleDetail() {
            },
            handleUpdate(index, item) {
                this.resetTemp();
                this.dialogStatus = "edit";
                this.$nextTick(() => {
                    this.$refs["dataForm"].clearValidate();
                });

                this.dataForm = JSON.parse(JSON.stringify(item));

                this.dialogFormVisible = true;
            },
            handleDelete(index, item) {
                this.$confirm("此操作将永久删除数据, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                }).then(() => {
                    delProject(item.id || this.ids).then((response) => {
                        this.$message({
                            type: "success",
                            message: `删除成功`,
                        });
                        this.loadPage();
                    });
                }).catch(() => {
                    this.$message({
                        type: "info",
                        message: "已取消删除",
                    });
                });
            },
            createData() {
                this.$refs["dataForm"].validate((valid) => {
                    if (valid) {
                        addProject(this.dataForm).then((response) => {
                            this.$modal.msgSuccess("新增成功");
                            this.dialogFormVisible = false;
                            this.loadPage();
                        });
                    } else {
                        return false;
                    }
                });
            },
            updateData() {
                this.$refs["dataForm"].validate((valid) => {
                    if (valid) {
                        updateProject(this.dataForm).then((response) => {
                            this.$modal.msgSuccess("修改成功");
                            this.dialogFormVisible = false;
                            this.loadPage();
                        });
                    } else {
                        return false;
                    }
                });
            }
        },
    };
</script>
