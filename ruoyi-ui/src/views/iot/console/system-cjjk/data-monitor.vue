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
            label="项目名">
            <template slot-scope="scope">
              {{ scope.row.name }}
              <el-badge is-dot :class="'alarmFlag' + scope.row.alarmFlag"
                        style="margin: 10px;">
              </el-badge>
            </template>

          </el-table-column>

          <!-- 表头及数据 END -->
        </el-table>
      </el-aside>
      <el-container>
        <el-header style="height: auto !important;">
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
                >
                  <span>{{ item.name }}</span>
                  <el-badge is-dot :class="'alarmFlag' + item.alarmFlag" style="margin: 10px;">
                  </el-badge>
                </el-option>

              </el-select>
            </el-form-item>
            <el-form-item label="测点/设备">
              <el-cascader
                v-model="pointOrDeviceData"
                @change="pointOrDeviceChange"
                :options="optionsPointOrDeviceData"
                :props="{ expandTrigger: 'hover',multiple: true }">
                <template slot-scope="{ node, data }">
                  <span>{{ data.label }}</span>
                  <!--                  <span v-if="!node.isLeaf"> ({{ data.children.length }}) </span>-->
                  <el-badge v-if="null != data.alarmFlag" is-dot :class="'alarmFlag' + data.alarmFlag"
                            style="margin: 10px;">
                  </el-badge>
                </template>
              </el-cascader>
            </el-form-item>
            <el-form-item label="数据类型" v-if="2 != pointOrDeviceData.length">
              <el-select v-model="condition.gatherType" size="small">
                <el-option
                  :label="'实时数据'"
                  :value="'1'"
                  :key="1"/>
                <el-option
                  :label="'聚集数据'"
                  :value="'2'"
                  :key="2"/>
              </el-select>
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
                :default-time="['00:00:00', '23:59:59']"
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
            无数据
            <!--            <el-empty description="无数据"></el-empty>-->
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
    import {parseTime} from '@/utils/ruoyi'
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
                    gatherType: '1',
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
                    condition.params.beginTime = condition.params.beginReleaseTime
                    condition.params.endTime = condition.params.endReleaseTime
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
                                            parseTime(item.dataList[j].createTime, '{y}-{m}-{d} {h}:{i}:{s}'),
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
                    condition.gatherType = this.condition.gatherType

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
                                    parseTime(item.createTime, '{y}-{m}-{d} {h}:{i}:{s}'),
                                    item.data
                                ])

                                // 与上次数据差值
                                if (i != 0) {
                                    data2.push([
                                        parseTime(item.createTime, '{y}-{m}-{d} {h}:{i}:{s}'),
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
                                    alarmFlag: childrenItem.alarmFlag,
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
            },
            formatDate(date) {
                var year = date.getFullYear(),
                    month = date.getMonth() + 1,//月份是从0开始的
                    day = date.getDate(),
                    hour = date.getHours(),
                    min = date.getMinutes();
                var newTime = '' + year + '-' +
                    (month < 10 ? '0' + month : month) + '-' +
                    (day < 10 ? '0' + day : day) + ' ' +
                    (hour < 10 ? '0' + hour : hour) + ':' +
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

  .el-select-dropdown__item {
    height: 53px;
  }

  .alarmFlag0 .el-badge__content {
    background-color: rgba(255, 255, 255, 0);
  }

  .alarmFlag1 .el-badge__content {
    background-color: #ff0000;
  }

  .alarmFlag2 .el-badge__content {
    background-color: #ffad00;
  }

  .alarmFlag3 .el-badge__content {
    background-color: #fff700;
  }

  // page end
</style>
