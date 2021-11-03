<template>
  <div class="app-container" v-loading="pageLoading">
    <el-container class="project-data-pannel">
      <el-aside width="200px">
        <el-table
          :data="tableData"
          row-key="item"
          style="width: 100%"
          highlight-current-row
          @current-change="handleCurrentChange"
        >
          <!-- 表头及数据 START -->
          <el-table-column
            class="project-list-table"
            prop="name"
            label="项目名"
          />
          <!-- 表头及数据 END -->
        </el-table>
      </el-aside>
      <el-container>
        <el-header>
          <!-- 筛选 START -->
          <el-form :inline="true" class="top-form-inline">
            <el-form-item label="结构物">
              <el-select v-model="condition.projectStructureId" placeholder="请选择所属结构物" clearable size="small"
                         @change="conditionProjectStructureChange">
                <el-option
                  :label="item.name"
                  :value="item.id"
                  v-for="item in projectStructureData"
                  :key="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="测点/设备">
              <el-cascader
                v-model="pointOrDeviceData"
                @change="pointOrDeviceChange"
                :options="optionsPointOrDeviceData"
                :props="{ expandTrigger: 'hover' }"></el-cascader>
            </el-form-item>
            <el-form-item label="查询时间">
              <el-date-picker
                v-model="value2"
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
            <el-form-item>
              <el-button type="primary" @click="conditionQuery"
              >条件查询
              </el-button
              >
            </el-form-item>
          </el-form>
          <!-- 筛选 END -->
        </el-header>
        <el-main>
          <div
            ref="chart"
            :style="{ height: height, width: width }"
            style="padding: 50px 0 0 25px"
          />
        </el-main>
        <el-footer></el-footer>
      </el-container>
    </el-container>
  </div>
</template>
<script>
    // import { getList, save, del } from "@/api/plugin/article";
    import {parseTime} from "@/utils";
    // import md5 from "blueimp-md5";
    import echarts from "echarts";

    require("echarts/theme/macarons"); // echarts theme
    // import resize from "./mixins/resize";
    // import Tinymce from "@/components/Tinymce";

    import {
        listProjectPoint
    } from "@/api/iot/projectPoint";

    import {
        projectListByUserRole,
        projectStructureListByProjectId,
        projectDeviceList
    } from "@/api/iot/console";

    const lineChartData = {
        newVisitis: {
            expectedData: [100, 120, 161, 134, 105, 160, 165],
        },
        messages: {
            expectedData: [200, 192, 120, 144, 160, 130, 140],
        },
        purchases: {
            expectedData: [80, 100, 121, 104, 105, 90, 100],
        },
        shoppings: {
            expectedData: [130, 140, 141, 142, 145, 150, 160],
        },
    };

    export default {
        name: "atricleChannelJjfa",
        data() {
            return {
                processEnv: process.env,
                options: [],
                pageLoading: false,
                tableData: [],
                dataForm: {},
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
                    username: [
                        {required: true, message: "请输入登录名", trigger: "blur"},
                    ],
                    nickname: [{required: true, message: "请输入昵称", trigger: "blur"}],
                },
                uploadFileList: [
                    {
                        name: "food.jpg",
                        url: "http://www.csbme.org/upload/userfiles/images/%E5%BB%BA%E5%85%9A100%E5%91%A8%E5%B9%B4.jpg",
                    },
                ],
                width: "100%",
                height: "100%",
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
                value2: "",
                projectStructureData: [],
                optionsPointOrDeviceData: [],
                currentProjectId: "",
                pointOrDeviceData: []
            };
        },
        mounted() {
            this.init();
        },
        methods: {
            init() {
                // this.loadPage();

                projectListByUserRole().then((response) => {
                    this.tableData = response;
                });

                this.$nextTick(() => {
                    this.initChart();
                });
            },
            loadPage() {
                if (this.pageLoading) {
                    return;
                }

                this.pageLoading = true;

                projectListByUserRole().then((response) => {
                    this.tableData = response;
                });

                // 获取设备数据


                // 获取测点数据

                //   getList(this.condition).then((response) => {
                //     var data = response.data;
                //     this.tableData = data.content;
                //     this.condition.page.total = data.totalElements;
                this.pageLoading = false;
                //   });
            },
            conditionQuery() {
                if (2 != this.pointOrDeviceData.length && 3 != this.pointOrDeviceData.length) {
                    this.$modal.msgWarning("请选择测点/设备");
                    return;
                }

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
            initChart() {
                this.chart = echarts.init(this.$refs.chart, "macarons");
                this.setOptions(this.chartData);
            },
            setOptions({expectedData} = {}) {
                this.chart.setOption({
                    xAxis: {
                        data: ["8-20", "8-21", "8-22", "8-23", "8-24", "8-25", "8-26"],
                        boundaryGap: false,
                        axisTick: {
                            show: false,
                        },
                    },
                    grid: {
                        left: 10,
                        right: 10,
                        bottom: 20,
                        top: 30,
                        containLabel: true,
                    },
                    tooltip: {
                        trigger: "axis",
                        axisPointer: {
                            type: "cross",
                        },
                        padding: [5, 10],
                    },
                    yAxis: {
                        axisTick: {
                            show: false,
                        },
                    },
                    legend: {
                        data: ["设备1"],
                    },
                    series: [
                        {
                            name: "设备1",
                            itemStyle: {
                                normal: {
                                    color: "#FF005A",
                                    lineStyle: {
                                        color: "#FF005A",
                                        width: 2,
                                    },
                                },
                            },
                            smooth: true,
                            type: "line",
                            data: expectedData,
                            animationDuration: 2800,
                            animationEasing: "cubicInOut",
                        },
                    ],
                });
            },
            handleCurrentChange(val) {
                this.currentProjectId = val.id;
                projectStructureListByProjectId({
                    projectId: val.id,
                }).then((response) => {
                    this.projectStructureData = response;
                    this.optionsPointOrDeviceData = [];

                    this.condition.projectStructureId = ""
                });
            },
            conditionProjectStructureChange() {
                this.optionsPointOrDeviceData = [];

                // 获取该项目所有设备
                var projectDeviceData = [];

                console.log({
                    projectId: this.currentProjectId
                })

                projectDeviceList({
                    projectId: this.currentProjectId
                }).then((response) => {
                    for (var i = 0; i < response.length; i++) {
                        var item = response[i];
                        projectDeviceData.push({
                            value: item.id,
                            label: item.name
                        })
                    }
                });

                // 获取该项目测点及分组
                var projectPointData = [];

                listProjectPoint({
                    projectStructureId: this.condition.projectStructureId
                }).then(response => {
                    for (var i = 0; i < response.rows.length; i++) {
                        var item = response.rows[i];
                        var children = [];

                        if (null != item.children) {
                            for (var j = 0; j < item.children.length; j++) {
                                var childrenItem = item.children[j]
                                children.push({
                                    value: childrenItem.id,
                                    label: childrenItem.name,
                                })
                            }
                        }

                        projectPointData.push({
                            value: item.id,
                            label: item.name,
                            children: children
                        })
                    }
                });


                this.optionsPointOrDeviceData.push({
                    value: '0',
                    label: '测点',
                    children: projectPointData
                }, {
                    value: '0',
                    label: '设备',
                    children: projectDeviceData
                })
            },
            pointOrDeviceChange(val) {
                console.log(val)
            }
        }
    };
</script>

<style lang="scss">
  .app-container {
    height: calc(100vh - 50px);
  }

  .project-data-pannel {
    height: calc(100vh - 70px);

    .el-aside {
      .el-table {
        background-color: #eef1f6;

        th {
          background-color: #eef1f6;
          text-align: center;
        }
      }
    }
  }

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
