<template>
  <div class="app-container" v-loading="pageLoading">
    <!-- 筛选 START -->
    <el-form :inline="true" class="top-form-inline">
      <el-form-item label="编号">
        <el-input v-model="condition.id" placeholder="编号" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="conditionQuery">条件查询</el-button>
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
      <el-table-column prop="name" label="标题" />
      <el-table-column prop="domainName" label="内容" />
      <el-table-column prop="domainName" label="是否已读" />
      <el-table-column prop="phone" label="报警时间" />
      <!-- 表头及数据 END -->

      <!-- 操作列 START -->
      <el-table-column min-width="80">
        <template slot="header"> 操作 </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="handleUpdate(scope.$index, scope.row)"
            >查看</el-button
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
        <el-form-item label="企业名称" prop="name">
          <el-input v-model="dataForm.name" />
        </el-form-item>
        <el-form-item label="企业logo">
          <el-upload
            ref="upload"
            accept="image/*"
            :file-list="dataFormUploadFileList"
            :action="processEnv.VUE_APP_SERVERAPI + '/api/plugin/file/single'"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            :on-remove="handleUploadRemove"
            class="avatar-uploader"
            :class="uploadFileList.length >= 1 ? 'hide' : ''"
            :multiple="false"
            list-type="picture-card"
            :limit="1"
          >
            <i class="el-icon-plus"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="企业域名" prop="domainName">
          <el-input v-model="dataForm.domainName" />
        </el-form-item>
        <!-- <el-form-item label="企业所在地区" prop="coreRegionId">
          <el-input v-model="dataForm.coreRegionId" />
        </el-form-item> -->
        <el-form-item label="企业规模" prop="scale">
          <el-input v-model="dataForm.scale" />
        </el-form-item>
        <el-form-item label="联系人" prop="liaisonMan">
          <el-input v-model="dataForm.liaisonMan" />
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="dataForm.phone" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="dataForm.email" />
        </el-form-item>
        <el-form-item label="企业拥有者" prop="owner">
          <el-select
            v-model="dataForm.owner.coreUserId"
            placeholder="请选择企业拥有者"
            @change="ownerSelectChange"
          >
            <el-option label="新用户" :value="-1"></el-option>
            <el-option
              v-if="
                null != currentData.owner.coreUserId &&
                null != dataForm.owner.coreUserId
              "
              :label="currentData.owner.nickname"
              :value="currentData.owner.coreUserId"
            ></el-option>
            <el-option
              v-for="item in enterpriseUserList"
              v-bind:key="item.id"
              :label="item.nickname"
              :value="item.userId"
            ></el-option>
          </el-select>
        </el-form-item>
        <block v-if="-1 == dataForm.owner.coreUserId">
          <el-form-item label="账号" prop="owner_username">
            <el-input v-model="dataForm.owner.username" />
          </el-form-item>
          <el-form-item label="密码" prop="owner_password">
            <el-input v-model="dataForm.owner.password" />
          </el-form-item>
          <el-form-item label="昵称" prop="owner_nickname">
            <el-input v-model="dataForm.owner.nickname" />
          </el-form-item>
          <el-form-item label="手机号" prop="owner_phone">
            <el-input v-model="dataForm.owner.phone" />
          </el-form-item>
        </block>
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
// import {
//   businessList,
//   businessSave,
//   businessDel,
//   userListByEnterpriseUser,
// } from "@/api/wxcxc/platform";
import { parseTime } from "@/utils";
// import md5 from "blueimp-md5";

export default {
  data() {
    return {
      processEnv: process.env,
      options: [],
      pageLoading: false,
      tableData: [],
      dataForm: {
        owner: {
          coreUserId: -1,
        },
      },
      currentData: {
        owner: {},
      },
      condition: {
        owner: {},
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
        name: [{ required: true, message: "请输入企业名称", trigger: "blur" }],
        domainName: [
          { required: true, message: "请输入企业域名", trigger: "blur" },
        ],
        owner_username: [
          { required: true, message: "请输入登录名", trigger: "blur" },
        ],
        owner_nickname: [
          { required: true, message: "请输入昵称", trigger: "blur" },
        ],
      },
      uploadFileList: [],
      enterpriseUserList: [],
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
    //   this.pageLoading = true;
    //   businessList(this.condition).then((response) => {
    //     var data = response.data;
    //     this.tableData = data.content;
    //     this.condition.page.total = data.totalElements;
    //     this.pageLoading = false;
    //   });
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
      // 清空表单数据
      this.dataForm = {
        owner: {
          coreUserId: -1,
        },
      };

      // 清空上传图片列表
      if (this.$refs.upload) {
        this.$refs.upload.clearFiles();
        this.uploadFileList = [];
      }

      // 清空企业拥有者选项
      userListByEnterpriseUser().then((response) => {
        this.enterpriseUserList = response.data;
      });
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
    handleUpdate(index, item) {
      this.resetTemp();
      this.dialogStatus = "edit";
      this.$nextTick(() => {
        this.$refs["dataForm"].clearValidate();
      });

      this.dataForm = JSON.parse(JSON.stringify(item));
      this.currentData = JSON.parse(JSON.stringify(item));

      if (null != this.dataForm.logoFile) {
        this.dataFormUploadFileList = [
          {
            name: this.dataForm.logoFile.currentName,
            url: this.dataForm.logoFile.filePath,
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
          // businessDel({ id: item.id }).then((response) => {
          //   if (0 == response.code) {
          //     this.$message({
          //       type: "success",
          //       message: `删除成功`,
          //     });
          //     this.loadPage();
          //   } else {
          //     this.$message.error("系统错误");
          //   }
          // });
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
          if (-1 == this.dataForm.owner.coreUserId) {
            this.dataForm.owner.coreUserId = null;
            this.dataForm.owner.password = md5(
              this.dataForm.owner.password + "clco"
            );
          }

          // businessSave(this.dataForm).then((response) => {
          //   if (0 == response.code) {
          //     this.$message({
          //       type: "success",
          //       message: `创建成功！`,
          //     });
          //     this.dialogFormVisible = false;
          //     this.loadPage();
          //   } else {
          //     this.$message.error("系统错误");
          //   }
          // });
        } else {
          return false;
        }
      });
    },
    updateData() {
      this.$refs["dataForm"].validate((valid) => {
        if (valid) {
          if (-1 == this.dataForm.owner.coreUserId) {
            this.dataForm.owner.coreUserId = null;
            this.dataForm.owner.password = md5(
              this.dataForm.owner.password + "clco"
            );
          }

          businessSave(this.dataForm).then((response) => {
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
      this.dataForm.logoFileId = res.data.id;
    },
    beforeAvatarUpload(file) {
      // const isJPG = file.type === "image/jpeg";
      // const isLt2M = file.size / 1024 / 1024 < 2;
      // if (!isJPG) {
      //   this.$message.error("上传头像图片只能是 JPG 格式!");
      // }
      // if (!isLt2M) {
      //   this.$message.error("上传头像图片大小不能超过 2MB!");
      // }
      // return isJPG && isLt2M;
    },
    ownerSelectChange() {
      if (-1 == this.dataForm.owner.coreUserId) {
        this.dataForm.owner = { coreUserId: -1 };
      }
    },
    handleUploadRemove(res, file) {
      this.dataForm.logoFileId = null;
      this.uploadFileList = [];
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
