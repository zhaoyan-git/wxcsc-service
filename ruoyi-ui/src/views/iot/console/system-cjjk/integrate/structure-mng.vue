<template>
  <div>
    <!-- 筛选 START -->
    <el-form :inline="true" class="top-form-inline">
      <el-form-item label="编号">
        <el-input v-model="condition.id" placeholder="编号" />
      </el-form-item>
      <el-form-item label="项目">
        <el-select v-model="condition.projectDto.id" placeholder="请选择项目">
          <el-option
            :label="item.name"
            :value="item.id"
            v-for="item in projectData"
            :key="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="结构物名称">
        <el-input v-model="condition.title" placeholder="结构物名称" />
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
      :data="tableData"
      row-key="item"
      style="width: 100%"
      @sort-change="sortChange"
    >
      <!-- 表头及数据 START -->
      <el-table-column sortable="id" prop="id" label="编号" />
      <el-table-column prop="name" label="结构物名称" />
      <el-table-column prop="projectDto.name" label="所属项目" />
      <el-table-column prop="projectStructureTypeDto.name" label="结构物类型" />
      <el-table-column label="创建时间">
        <template slot-scope="scope">
          {{ scope.row.createTime | parseTime("{y}-{m}-{d} {h}:{i}:{s}") }}
        </template>
      </el-table-column>
      <!-- 表头及数据 END -->

      <!-- 操作列 START -->
      <el-table-column min-width="80">
        <template slot="header"> 操作 </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="handleUpdate(scope.$index, scope.row)"
            >编辑</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            v-if="scope.row.userId != 1 && scope.row.userId != 2"
            >删除</el-button
          >
        </template>
      </el-table-column>
      <!-- 操作列 END -->
    </el-table>
    <!-- 页码 START -->
    <el-pagination
      :current-page="condition.page.currentPage"
      :page-sizes="[10, 20, 30, 40]"
      :page-size="condition.page.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="condition.page.total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
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
          <el-input v-model="dataForm.name" />
        </el-form-item>
        <el-form-item label="结构物图片">
          <el-upload
            ref="upload"
            :file-list="uploadFileList"
            :action="processEnv.VUE_APP_SERVERAPI + '/api/plugin/file/single'"
            :on-success="handleAvatarSuccess"
            class="avatar-uploader"
            :multiple="false"
            list-type="picture-card"
            :limit="1"
          >
            <i class="el-icon-plus"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="所属项目" prop="projectDto.id">
          <el-select
            v-model="dataForm.projectDto.id"
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
        <el-form-item label="选择结构物类型" prop="projectStructureTypeDto.id">
          <el-select
            v-model="dataForm.projectStructureTypeDto.id"
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
        <el-button @click="dialogFormVisible = false"> 关闭 </el-button>
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
import {
  findProjectStructureList,
  projectStructureSave,
  projectStructureDel,
  projectStructureTypeList,
  projectListBusiness,
} from "@/api/wxcxc/console";

export default {
  props: {
    isShow: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      processEnv: process.env,
      dataForm: {
        projectDto: {},
        projectStructureTypeDto: {},
        file: {},
      },
      tableData: [],
      projectData: [],
      structureTypeData: [],
      condition: {
        file: {},
        projectDto: {},
        page: {
          orderBy: "id",
          sort: "DESC",
          currentPage: 1,
          pageSize: 10,
          total: 0,
        },
      },
      dialogFormVisible: false,
      textMap: {
        edit: "编辑",
        create: "创建",
      },
      dialogStatus: "",
      rules: {
        name: [
          { required: true, message: "请输入结构物名称", trigger: "blur" },
        ],
        "projectDto.id": [
          { required: true, message: "请选择所属项目", trigger: "blur" },
        ],
        "projectStructureType.id": [
          { required: true, message: "请选择结构物类型", trigger: "blur" },
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
        this.structureTypeData = response.data;
      });

      projectListBusiness().then((response) => {
        this.projectData = response.data;
        // this.condition.projectDto.id = this.projectData[0].id;
      });

      findProjectStructureList(this.condition).then((response) => {
        var data = response.data;
        this.tableData = data.content;
        this.condition.page.total = data.totalElements;
        this.pageLoading = false;
      });
    },
    conditionQuery() {
      this.loadPage();
    },
    sortChange(val) {
      var orderBy = val.prop;
      var sort = val.order;
      if (sort === "ascending") {
        this.condition.page.sort = "ASC";
      } else {
        this.condition.page.sort = "DESC";
      }
      this.condition.page.orderBy = orderBy;
      this.loadPage();
    },
    resetTemp() {
      this.dataForm = {
        projectDto: {},
        projectStructureTypeDto: {},
        file: {},
      };

      // 清空上传图片列表
      if (this.$refs.upload) {
        this.$refs.upload.clearFiles();
        this.uploadFileList = [];
      }
    },
    handleSizeChange(val) {
      this.condition.page.pageSize = val;
      this.loadPage();
    },
    handleCurrentChange(val) {
      this.condition.page.currentPage = val;
      this.loadPage();
    },
    handleCreate() {
      this.resetTemp();
      this.dialogStatus = "create";
      this.$nextTick(() => {
        this.$refs["dataForm"].clearValidate();
      });
      this.dialogFormVisible = true;
    },
    handleDetail() {},
    handleUpdate(index, item) {
      this.resetTemp();
      this.dialogStatus = "edit";
      this.$nextTick(() => {
        this.$refs["dataForm"].clearValidate();
      });

      this.dataForm = JSON.parse(JSON.stringify(item));

      // 图片回显
      if (null != this.dataForm.file) {
        this.uploadFileList = [
          {
            name: this.dataForm.file.currentName,
            url: this.dataForm.file.filePath,
          },
        ];
      }

      this.dialogFormVisible = true;
    },
    handleDelete(index, item) {
      this.$confirm("此操作将永久删除数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          projectStructureDel({ id: item.id }).then((response) => {
            if (0 == response.code) {
              this.$message({
                type: "success",
                message: `删除成功`,
              });
              this.loadPage();
            } else {
              this.$message.error("系统错误");
            }
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
          projectStructureSave(this.dataForm).then((response) => {
            if (0 == response.code) {
              this.$message({
                type: "success",
                message: `创建成功！`,
              });
              this.dialogFormVisible = false;
              this.loadPage();
            } else {
              this.$message.error("系统错误");
            }
          });
        } else {
          return false;
        }
      });
    },
    updateData() {
      this.$refs["dataForm"].validate((valid) => {
        if (valid) {
          projectStructureSave(this.dataForm).then((response) => {
            if (0 == response.code) {
              this.$message({
                type: "success",
                message: `修改成功！`,
              });
              this.dialogFormVisible = false;
              this.loadPage();
            } else {
              this.$message.error("系统错误");
            }
          });
        } else {
          return false;
        }
      });
    },
    handleAvatarSuccess(res, file) {
      this.dataForm.file.id = res.data.id;
    },
  },
};
</script>
