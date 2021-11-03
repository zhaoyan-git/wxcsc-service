<template>
  <div class="app-container" v-loading="pageLoading">
    <el-row>
      <el-col :span="5"><br/></el-col>
      <el-col :span="14">
        <el-form
          ref="dataForm"
          :rules="rules"
          :model="dataForm"
          label-width="120px"
        >
          <el-form-item label="企业名称" prop="name">
            <el-input v-model="dataForm.name" placeholder="请输入企业名称"/>
          </el-form-item>
          <el-form-item label="企业LOGO地址">
            <imageUpload v-model="dataForm.logoFile" :limit="1"/>
          </el-form-item>
          <el-form-item label="企业域名" prop="domainName">
            <el-input v-model="dataForm.domainName" placeholder="请输入企业域名"/>
          </el-form-item>
          <el-form-item label="企业所在地区" prop="region">
            <el-input v-model="dataForm.region" placeholder="请输入企业所在地区"/>
          </el-form-item>
          <el-form-item label="企业规模" prop="scale">
            <el-input v-model="dataForm.scale" placeholder="请输入企业规模"/>
          </el-form-item>
          <el-form-item label="联系人" prop="liaisonMan">
            <el-input v-model="dataForm.liaisonMan" placeholder="请输入联系人"/>
          </el-form-item>
          <el-form-item label="联系电话" prop="phone">
            <el-input v-model="dataForm.phone" placeholder="请输入联系电话"/>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="dataForm.email" placeholder="请输入邮箱"/>
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
    import {getBusiness, updateBusiness} from "@/api/iot/console";

    export default {
        data() {
            return {
                processEnv: process.env,
                pageLoading: false,
                dataForm: {},
                rules: {
                    name: [{required: true, message: "请输入企业名称", trigger: "blur"}],
                    domainName: [
                        {required: true, message: "请输入企业域名", trigger: "blur"},
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
                getBusiness().then((response) => {
                    this.dataForm = response.data;
                    this.pageLoading = false;
                });
            },

            updateData() {
                this.$refs["dataForm"].validate((valid) => {
                    if (valid) {
                        updateBusiness(this.dataForm).then((response) => {
                            this.$message({
                                type: "success",
                                message: `修改成功！`,
                            });
                            this.dialogFormVisible = false;
                            this.loadData();
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
