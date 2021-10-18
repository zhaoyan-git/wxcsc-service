<template>
  <div class="app-container" v-loading="pageLoading">
    <el-row>
      <el-col :span="5"><br /></el-col>
      <el-col :span="14">
        <el-form
          ref="dataForm"
          :rules="rules"
          :model="dataForm"
          label-width="100px"
        >
          <el-form-item label="企业名称" prop="name">
            <el-input v-model="dataForm.name" />
          </el-form-item>
          <el-form-item label="企业logo">
            <el-upload
              ref="upload"
              accept="image/*"
              :file-list="uploadFileList"
              :action="processEnv.VUE_APP_SERVERAPI + '/api/plugin/file/single'"
              :on-success="handleUploadSuccess"
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
          <!-- <el-form-item label="企业所在地区" prop="sort">
            <el-input v-model="dataForm.sort" />
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
          <el-form-item>
            <el-button type="primary" @click="updateData">保存信息</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import { findBusinessBaseInfo, businessSave } from "@/api/wxcxc/console";

export default {
  data() {
    return {
      processEnv: process.env,
      pageLoading: false,
      dataForm: {},
      rules: {
        name: [{ required: true, message: "请输入企业名称", trigger: "blur" }],
        domainName: [
          { required: true, message: "请输入企业域名", trigger: "blur" },
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
      this.loadData();
    },
    loadData() {
      if (this.pageLoading) {
        return;
      }
      this.pageLoading = true;
      findBusinessBaseInfo().then((response) => {
        this.dataForm = response.data;
        this.pageLoading = false;

        if (null != this.dataForm.logoFile) {
          this.uploadFileList = [
            {
              name: this.dataForm.logoFile.currentName,
              url: this.dataForm.logoFile.filePath,
            },
          ];
        }
      });
    },
    handleUploadSuccess(res, file) {
      this.dataForm.logoFileId = res.data.id;
      this.uploadFileList.push({
        name: res.data.currentName,
        url: res.data.filePath,
      });
      console.log(res.data);
    },
    handleUploadRemove(res, file) {
      this.dataForm.logoFileId = null;
      this.uploadFileList = [];
    },
    updateData() {
      this.$refs["dataForm"].validate((valid) => {
        if (valid) {
          businessSave(this.dataForm).then((response) => {
            if (0 == response.code) {
              this.$message({
                type: "success",
                message: `修改成功！`,
              });
              this.dialogFormVisible = false;
              this.loadData();
            } else {
              this.$message.error("系统错误");
            }
          });
        } else {
          return false;
        }
      });
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
