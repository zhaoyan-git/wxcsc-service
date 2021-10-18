<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文章ID" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入文章ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发布时间">
        <el-date-picker
          v-model="daterangeReleaseTime"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
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
          v-hasPermi="['iot:article:add']"
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
          v-hasPermi="['iot:article:edit']"
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
          v-hasPermi="['iot:article:remove']"
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
          v-hasPermi="['iot:article:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="articleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="文章ID" align="center" prop="id"/>
      <el-table-column label="标题" align="center" prop="title"/>
      <el-table-column label="排序" align="center" prop="sort"/>
      <el-table-column label="是否发布" align="center" prop="releaseFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.iot_article_release_flag" :value="scope.row.releaseFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="发布时间" align="center" prop="releaseTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.releaseTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['iot:article:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['iot:article:remove']"
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

    <!-- 添加或修改文章对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题"/>
        </el-form-item>
        <el-form-item label="简介" prop="details">
          <el-input v-model="form.details" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="文章内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="标题图片">
          <imageUpload :limit="1" v-model="form.titlePhoto"/>
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入排序"/>
        </el-form-item>
        <el-form-item label="是否发布" prop="releaseFlag">
          <el-select v-model="form.releaseFlag" placeholder="请选择是否发布">
            <el-option
              v-for="dict in dict.type.iot_article_release_flag"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <!--        <el-form-item label="所属栏目" prop="channelId">-->
        <!--          <el-input v-model="form.title" placeholder="所属栏目" />-->
        <!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
    import {listArticle, getArticle, delArticle, addArticle, updateArticle, exportArticle} from "@/api/iot/article";

    export default {
        name: "Article",
        dicts: ['iot_article_release_flag'],
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
                // 文章表格数据
                articleList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 所属栏目id时间范围
                daterangeReleaseTime: [],
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                    id: null,
                    channelId: 1,
                    title: null,
                    releaseTime: null,
                },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                    title: [
                        {required: true, message: "标题不能为空", trigger: "blur"}
                    ],
                    sort: [
                        {required: true, message: "排序不能为空", trigger: "blur"}
                    ],
                }
            };
        },
        created() {
            this.getList();
        },
        methods: {
            /** 查询文章列表 */
            getList() {
                this.loading = true;
                this.queryParams.params = {};
                if (null != this.daterangeReleaseTime && '' != this.daterangeReleaseTime) {
                    this.queryParams.params["beginReleaseTime"] = this.daterangeReleaseTime[0];
                    this.queryParams.params["endReleaseTime"] = this.daterangeReleaseTime[1];
                }
                listArticle(this.queryParams).then(response => {
                    this.articleList = response.rows;
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
                    title: null,
                    details: null,
                    content: null,
                    titlePhoto: null,
                    sort: 0,
                    releaseFlag: null,
                    releaseTime: null,
                    channelId: 1,
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
                this.daterangeReleaseTime = [];
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
                this.title = "添加文章";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getArticle(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改文章";
                });
            },
            /** 提交按钮 */
            submitForm() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != null) {
                            updateArticle(this.form).then(response => {
                                this.$modal.msgSuccess("修改成功");
                                this.open = false;
                                this.getList();
                            });
                        } else {
                            addArticle(this.form).then(response => {
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
                this.$modal.confirm('是否确认删除文章编号为"' + ids + '"的数据项？').then(function () {
                    return delArticle(ids);
                }).then(() => {
                    this.getList();
                    this.$modal.msgSuccess("删除成功");
                }).catch(() => {
                });
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$modal.confirm('是否确认导出所有文章数据项？').then(() => {
                    this.exportLoading = true;
                    return exportArticle(queryParams);
                }).then(response => {
                    this.$download.name(response.msg);
                    this.exportLoading = false;
                }).catch(() => {
                });
            }
        }
    };
</script>
