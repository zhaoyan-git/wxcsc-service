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
          <div v-if="0 == echartsArray.length" style="text-align: center">
            <h4>无数据</h4>
          </div>
          <div
            v-for="item in echartsArray"
            :ref="'chart' + item.index"
            :style="{ height: height, width: width }"
            style="padding: 50px 0 0 25px"
          >
            {{item.index}}
          </div>
        </el-main>
        <el-footer></el-footer>
      </el-container>
    </el-container>
  </div>
</template>
<script>
    import {parseTime} from "@/utils";
    import echarts from "echarts";

    require("echarts/theme/macarons");

    import {
        listProjectPoint
    } from "@/api/iot/projectPoint";

    import {
        projectListByUserRole,
        projectStructureListByProjectId,
        projectDeviceList
    } from "@/api/iot/console";

    import {
        listProjectDeivceSensorData
    } from "@/api/iot/projectDeivceSensorData";

    import {
        listStructurePointData
    } from "@/api/iot/structurePointData";

    export default {
        name: "atricleChannelJjfa",
        data() {
            return {
                processEnv: process.env,
                options: [],
                pageLoading: false,
                tableData: [],
                condition: {
                    page: {
                        orderBy: "id",
                        sort: "DESC",
                        currentPage: 1,
                        pageSize: 10,
                        total: 0,
                    },
                },
                width: "100%",
                height: "50%",
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
                projectStructureData: [],
                optionsPointOrDeviceData: [],
                currentProjectId: "",
                pointOrDeviceData: [],
                daterangeReleaseTime: [],
                echartsArray: []
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
            },
            loadPage() {
                var that = this
                this.echartsArray = []

                if (this.pageLoading) {
                    return;
                }

                this.pageLoading = true;

                projectListByUserRole().then((response) => {
                    this.tableData = response;
                });

                var condition = {};

                if (null != this.daterangeReleaseTime && '' != this.daterangeReleaseTime) {
                    condition['params'] = {
                        'beginReleaseTime': this.formatDate(this.daterangeReleaseTime[0]),
                        'endReleaseTime': this.formatDate(this.daterangeReleaseTime[1])
                    }
                }

                // 获取设备数据
                // pointOrDeviceData长度为2时代表设备
                if (2 == this.pointOrDeviceData.length) {
                    condition.deviceId = this.pointOrDeviceData[1]

                    listProjectDeivceSensorData(condition).then((response) => {
                            if (null != response && 0 < response.length) {

                                for (var i = 0; i < response.length; i++) {
                                    var item = response[i]
                                    var itemData = []

                                    for (var j = 0; j < item.dataList.length; j++) {
                                        itemData.push([
                                            item.dataList[j].createTime,
                                            item.dataList[j].data
                                        ])
                                    }

                                    this.echartsArray.push(
                                        {
                                            index: i,
                                            option: that.generateOptions(item.name, "单位：" + item.unit, itemData)
                                        }
                                    )
                                }

                                this.showCharts()
                            }


                            this.pageLoading = false;
                        }
                    )
                }


                // 获取测点数据
                // pointOrDeviceData长度为3时代表设备
                if (3 == this.pointOrDeviceData.length) {
                    condition.structureId = this.condition.projectStructureId
                    condition.pointId = this.pointOrDeviceData[2]

                    listStructurePointData(condition).then((response) => {
                        if (null != response && 0 < response.length) {
                            // 累计沉降数据
                            var data = [];
                            // 与上次数据差值
                            var data2 = [];

                            for (var i = 0; i < response.length; i++) {
                                // 累计沉降数据
                                var item = response[i]
                                data.push([
                                    item.createTime,
                                    item.data
                                ])

                                // 与上次数据差值
                                if (i != 0) {
                                    data2.push([
                                        item.createTime,
                                        (item.data - response[i - 1].data).toFixed(2)
                                    ])
                                }
                            }


                            this.echartsArray = [{
                                index: "0",
                                option: that.generateOptions("累计沉降值", "单位：mm", data)
                            },
                                {
                                    index: "1",
                                    option: that.generateOptions("与上次数据差值", "单位：mm", data2)
                                }]
                            this.showCharts()
                        }

                        this.pageLoading = false;
                    });
                }
            },
            conditionQuery() {
                if (2 != this.pointOrDeviceData.length && 3 != this.pointOrDeviceData.length) {
                    this.$modal.msgWarning("请选择测点/设备");
                    return;
                }

                this.loadPage();
            },
            generateOptions(title, yAxisName, expectedData) {
                return {
                    title: {
                        text: title
                    },
                    xAxis: {
                        boundaryGap: true,
                        type: 'category'
                    },
                    tooltip: {
                        trigger: "axis",
                        axisPointer: {
                            type: "cross",
                        },
                        padding: [5, 10],
                    },
                    yAxis: {
                        name: yAxisName,
                        boundaryGap: true,
                        type: 'value'
                    },
                    series: [{
                        data: expectedData,
                        type:
                            'line',
                        smooth:
                            true
                    }],
                    toolbox: {
                        show: true,
                        feature: {
                            saveAsImage: {
                                show: true,
                                excludeComponents: ['toolbox'],
                                pixelRatio: 2
                            }
                        }
                    }
                }
            },
            // 展示图表
            showCharts() {
                if (null != this.echartsArray.length && 0 < this.echartsArray.length) {
                    this.$nextTick(() => {
                        for (var i = 0; i < this.echartsArray.length; i++) {
                            console.log(this.$refs.chart0)

                            console.log(eval('this.$refs.chart' + this.echartsArray[i].index))
                            var chart = echarts.init(eval('this.$refs.chart' + this.echartsArray[i].index)[0], "macarons");
                            chart.setOption(this.echartsArray[i].option);
                        }
                    });
                }
            },
            // 选择当前项目
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
            // 选择结构物
            conditionProjectStructureChange() {
                this.optionsPointOrDeviceData = [];

                // 获取该项目所有设备
                var projectDeviceData = [];

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
                // console.log(val)
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
