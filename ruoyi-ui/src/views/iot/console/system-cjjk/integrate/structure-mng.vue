<template>
  <div>
    <!-- 筛选 START -->
    <el-form :inline="true" class="top-form-inline">
      <el-form-item label="编号">
        <el-input v-model="condition.id" placeholder="编号"/>
      </el-form-item>
      <el-form-item label="项目">
        <el-select v-model="condition.projectId"
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
      <el-form-item label="结构物名称">
        <el-input v-model="condition.name" placeholder="结构物名称"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="conditionQuery">条件查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="success" @click="handleCreate">添加结构物</el-button>
      </el-form-item>
    </el-form>
    <!-- 筛选 END -->

    <!-- 数据列表 START -->
    <el-table
      v-loading="pageLoading"
      :data="tableData"
      row-key="item"
      style="width: 100%"
    >
      <!-- 表头及数据 START -->
      <el-table-column prop="id" label="编号"/>
      <el-table-column prop="name" label="结构物名称"/>
      <el-table-column prop="projectId" label="所属项目">
        <template slot-scope="scope">
          <span
            v-for="item in projectData"
            v-if="item.id == scope.row.projectId">
            {{item.name}}
          </span>
        </template>
      </el-table-column>
      <el-table-column prop="typeId" label="结构物类型">
        <template slot-scope="scope">
          <span
            v-for="item in structureTypeData"
            v-if="item.id == scope.row.typeId">
            {{item.name}}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间">
        <template slot-scope="scope">
          {{ scope.row.createTime | parseTime("{y}-{m}-{d} {h}:{i}:{s}") }}
        </template>
      </el-table-column>
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
      :page.sync="condition.pageNum"
      :limit.sync="condition.pageSize"
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
        label-width="130px"
      >
        <el-form-item label="结构物名称" prop="name">
          <el-input v-model="dataForm.name"/>
        </el-form-item>
        <el-form-item label="结构物图片">
          <imageUpload v-model="dataForm.photoFile"/>
        </el-form-item>
        <el-form-item label="所属项目" prop="projectId">
          <el-select
            v-model="dataForm.projectId"
            placeholder="请选择所属项目"
          >
            <el-option
              v-for="item in projectData"
              v-bind:key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="选择结构物类型" prop="typeId">
          <el-select
            v-model="dataForm.typeId"
            placeholder="请选择结构物类型"
          >
            <el-option
              v-for="item in structureTypeData"
              v-bind:key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button
          type="primary"
          @click="dialogStatus === 'create' ? createData() : updateData()"
        >
          提交
        </el-button>
        <el-button @click="dialogFormVisible = false"> 关闭</el-button>
      </div>
    </el-dialog>
    <!-- 创建及编辑视图 START -->
  </div>
</template>
<script>
    import {
        listProjectStructure,
        getProjectStructure,
        delProjectStructure,
        addProjectStructure,
        updateProjectStructure,
        exportProjectStructure
    } from "@/api/iot/projectStructure";

    import {
        projectStructureTypeList,
        projectListBusiness
    } from "@/api/iot/console";

    export default {
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
        data() {
            return {
                // 总条数
                total: 0,
                pageLoading: false,
                processEnv: process.env,
                dataForm: {},
                tableData: [],
                projectData: [],
                structureTypeData: [],
                condition: {
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
                    name: [
                        {required: true, message: "请输入结构物名称", trigger: "blur"},
                    ],
                    "projectId": [
                        {required: true, message: "请选择所属项目", trigger: "blur"},
                    ],
                    "typeId": [
                        {required: true, message: "请选择结构物类型", trigger: "blur"},
                    ],
                },
                uploadFileList: [],
            };
        },
        mounted() {
        },
        watch: {
            isShow: function (newObject, oldObject) {
                if (this.isShow) {
                    this.loadPage();
                }
            },
            projectId: function (newObject, oldObject) {
                if (this.projectId) {
                    this.condition.projectId = this.projectId;
                }
            }
        },
        methods: {
            init() {
                this.loadPage();
            },
            loadPage() {
                if (this.pageLoading) {
                    return;
                }
                this.pageLoading = true;
                projectStructureTypeList().then((response) => {
                    this.structureTypeData = response;
                    this.structureTypeDataDict = []
                    for (var i = 0; i < this.structureTypeData.length; i++) {
                        var item = this.structureTypeData[i];
                        this.structureTypeDataDict.push({
                            label: item.name,
                            value: item.id,
                            raw: {
                                listClass: 'default'
                            }
                        });
                    }
                });

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

                listProjectStructure(this.condition).then((response) => {
                    this.tableData = response.rows;
                    this.total = response.total;
                    this.pageLoading = false;
                });
            },
            conditionQuery() {
                this.loadPage();
            },
            resetTemp() {
                this.dataForm = {
                    id: null,
                    name: null,
                    typeId: null,
                    photoFile: null,
                    projectId: null,
                    alarmFlag: null,
                    delFlag: null,
                    createBy: null,
                    createTime: null,
                    updateBy: null,
                    updateTime: null
                };
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
                })
                    .then(() => {
                        delProjectStructure(item.id || this.ids).then((response) => {
                            this.$message({
                                type: "success",
                                message: `删除成功`,
                            });
                            this.loadPage();
                        });
                    })
                    .catch(() => {
                        this.$message({
                            type: "info",
                            message: "已取消删除",
                        });
                    });
            },
            createData() {
                this.$refs["dataForm"].validate((valid) => {
                    if (valid) {
                        addProjectStructure(this.dataForm).then((response) => {
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
                        updateProjectStructure(this.dataForm).then((response) => {
                            this.$modal.msgSuccess("修改成功");
                            this.dialogFormVisible = false;
                            this.loadPage();
                        });
                    } else {
                        return false;
                    }
                });
            },
            handleAvatarSuccess(res, file) {
                this.dataForm.file.id = res.data.id;
            },
            conditionProjectChange() {
                if (null != this.condition.projectId) {
                    this.$emit('projectCurrent', this.condition.projectId);

                    this.loadPage();
                }
            }
        },
    };
</script>
