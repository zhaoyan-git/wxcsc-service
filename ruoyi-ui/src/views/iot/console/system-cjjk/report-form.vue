<template>
  <div class="app-container">
    <el-row>
      <el-col :span="5"><br/></el-col>
      <el-col :span="14">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="选择项目">
            <el-select v-model="form.projectId" placeholder="请选择项目">
              <el-option v-for="item in projectList"
                         :label="item.name"
                         :value="item.id"
                         :key="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="时间范围">
            <el-date-picker
              v-model="daterangeReleaseTime"
              type="daterange"
              align="right"
              unlink-panels
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              :picker-options="pickerOptions"
            >
            </el-date-picker>
          </el-form-item>
          <div style="text-align: left">
            <el-transfer
              style="text-align: left; display: inline-block"
              v-model="form.attrDataList"
              filterable
              :titles="['可选表头', '已选表头']"
              :button-texts="['删除', '选择']"
              :format="{
                  noChecked: '${total}',
                  hasChecked: '${checked}/${total}'
                }"
              @change="handleChange"
              :data="attrData">
              <span slot-scope="{ option }">{{ option.key }} - {{ option.label }}</span>
            </el-transfer>
          </div>
          <el-form-item style="margin: 30px 0px">
            <el-button type="primary" @click="handleExport" style="margin-left: 180px;">生成报表</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script>
    import {
        projectListByUserRole,
        reportForm
    } from "@/api/iot/console";


    export default {
        data() {
            return {
                form: {},
                pickerOptions: {
                    shortcuts: [
                        {
                            text: "最近一周",
                            onClick(picker) {
                                const end = new Date();
                                const start = new Date();
                                start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                                picker.$emit("pick", [start, end]);
                            },
                        },
                        {
                            text: "最近一个月",
                            onClick(picker) {
                                const end = new Date();
                                const start = new Date();
                                start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                                picker.$emit("pick", [start, end]);
                            },
                        },
                        {
                            text: "最近三个月",
                            onClick(picker) {
                                const end = new Date();
                                const start = new Date();
                                start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                                picker.$emit("pick", [start, end]);
                            },
                        },
                    ],
                },
                attrData: [{
                    key: 1,
                    label: `上次沉降值（mm）`
                },
                    {
                        key: 2,
                        label: `当前沉降值（mm）`
                    },
                    // {
                    //     key: 3,
                    //     label: `变化速率（mm/d）`
                    // },
                    {
                        key: 4,
                        label: `控制值累计变化值（mm）`
                    },
                    // {
                    //     key: 5,
                    //     label: `控制值变化速率值（mm/d）`
                    // },
                    {
                        key: 6,
                        label: `本次监测时间`
                    },
                    {
                        key: 7,
                        label: `上次检测时间`
                    }],
                daterangeReleaseTime: [],
                projectList: []
            };
        },
        created() {
            projectListByUserRole().then((response) => {
                this.projectList = response;
            });

        },
        methods: {
            handleExport() {
                if (null == this.form.projectId || "" == this.form.projectId) {
                    this.$modal.msgWarning("请选择项目");
                    return
                }

                if (null != this.daterangeReleaseTime && '' != this.daterangeReleaseTime) {
                    this.form.beginTime = this.formatDate(this.daterangeReleaseTime[0])
                    this.form.endTime = this.formatDate(this.daterangeReleaseTime[1])
                }

                console.log(this.form)

                this.$modal.confirm('是否确认导出报表？').then(() => {
                    this.exportLoading = true;
                    return reportForm(this.form);
                }).then(response => {
                    this.$download.name(response.msg);
                    this.exportLoading = false;
                }).catch(() => {
                });
            },
            formatDate(date) {
                // var date = new Date(time);

                var year = date.getFullYear(),
                    month = date.getMonth() + 1,//月份是从0开始的
                    day = date.getDate(),
                    hour = date.getHours(),
                    min = date.getMinutes();
                var newTime = '' + year +
                    (month < 10 ? '0' + month : month) +
                    (day < 10 ? '0' + day : day) +
                    (hour < 10 ? '0' + hour : hour) +
                    (min < 10 ? '0' + min : min);

                return newTime;
            }
        }
    };
</script>
