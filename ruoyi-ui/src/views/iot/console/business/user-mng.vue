<template>
  <div class="app-container" v-loading="pageLoading">
    <!-- 筛选 START -->
    <el-form :inline="true" class="top-form-inline">
      <el-form-item label="编号">
        <el-input v-model="condition.id" placeholder="编号" />
      </el-form-item>
      <el-form-item label="用户昵称">
        <el-input v-model="condition.title" placeholder="用户昵称" />
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="condition.title" placeholder="手机号" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="conditionQuery">条件查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="success" @click="handleCreate">添加用户</el-button>
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
      <el-table-column prop="user.nickname" label="用户昵称" />
      <el-table-column prop="user.username" label="登录名" />
      <el-table-column prop="user.phone" label="手机号" />
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
            v-if="isOwn(scope.row.user.coreRoleList)"
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
        label-width="100px"
      >
        <el-form-item label="用户昵称" prop="user.nickname">
          <el-input v-model="dataForm.user.nickname" />
        </el-form-item>
        <!-- <el-form-item label="头像">
          <el-upload
            ref="upload"
            :file-list="uploadFileList"
            :action="processEnv.VUE_APP_SERVERAPI + '/api/plugin/file/single'"
            :on-success="handleAvatarSuccess"
            :on-remove="handleUploadRemove"
            class="avatar-uploader"
            :class="uploadFileList.length >= 1 ? 'hide' : ''"
            :multiple="false"
            list-type="picture-card"
            :limit="1"
          >
            <i class="el-icon-plus"></i>
          </el-upload>
        </el-form-item> -->
        <el-form-item label="登录名" prop="user.username">
          <el-input v-model="dataForm.user.username" />
        </el-form-item>
        <el-form-item label="密码" prop="user.password">
          <el-input v-model="dataForm.user.password" type="password" />
        </el-form-item>
        <el-form-item label="手机号" prop="user.phone">
          <el-input v-model="dataForm.user.phone" />
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
  findBusinessMemberList,
  businessMemberSave,
  businessMemberDel,
} from "@/api/wxcxc/console";
import { parseTime } from "@/utils";
// import md5 from "blueimp-md5";

export default {
  name: "atricleChannelJjfa",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "" || value == undefined) {
        callback(new Error("请输入密码"));
      } else {
        if (/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]+\S{5,12}$/.test(value)) {
          callback();
        } else {
          callback(new Error("需同时含有字母和数字，长度在6-12之间"));
        }
      }
    };
    return {
      processEnv: process.env,
      options: [],
      pageLoading: false,
      tableData: [],
      dataForm: {
        user: {},
      },
      currentItem: { user: {} },
      condition: {
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
        "user.nickname": [
          { required: true, message: "请输入昵称", trigger: "blur" },
        ],
        "user.username": [
          { required: true, message: "请输入登录名", trigger: "blur" },
        ],
        "user.password": [
          { required: true, validator: validatePass, trigger: "blur" },
        ],
      },
      uploadFileList: [],
    };
  },
  mounted() {
    this.init();
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
      findBusinessMemberList(this.condition).then((response) => {
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
        user: {},
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

      this.currentItem = item;
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
          businessMemberDel({ id: item.id }).then((response) => {
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
          this.dataForm.user.password = md5(
            this.dataForm.user.password + "clco"
          );
          businessMemberSave(this.dataForm).then((response) => {
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
          this.dataForm.user.password = this.currentItem.user.password
            ? this.dataForm.password
            : md5(this.dataForm.password + "clco");

          businessMemberSave(this.dataForm).then((response) => {
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
      this.dataForm.fileId = res.data.id;
    },
    handleUploadRemove(file) {
      // return isJPG && isLt2M;
    },
    formatterIsRelease(row, column) {
      switch (row.isRelease) {
        case 0:
          return "否";
        case 1:
          return "是";
      }
    },
    isOwn(roles) {
      if (null != roles && 0 < roles.length) {
        for (var i = 0; i < roles.length; i++) {
          var item = roles[i];
          if ("enterprise_own" == item.name) {
            return false;
          }
        }
        return true;
      } else {
        return true;
      }
    },
  },
};
</script>

<style lang="scss" scoped>
// page start
.el-pagination {
  text-align: center;
  margin-top: 10px;
}
.top-form-inline {
  padding-top: 22px;
  padding-left: 22px;
  background: #eee;
  align-items: center;
}
// page end
</style>
