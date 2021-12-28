<template>
  <div class="dashboard-editor-container">
    <el-row :gutter="40" class="panel-group">
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('dataCount')">
          <div class="card-panel-icon-wrapper icon-people">
            <svg-icon icon-class="wxcxc-data" class-name="card-panel-icon"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">总数据量</div>
            <count-to
              :start-val="0"
              :end-val="businessDataList.dataCount.expectedData[businessDataList.dataCount.expectedData.length - 1][1]"
              :duration="1500"
              class="card-panel-num"
            />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('projectCount')">
          <div class="card-panel-icon-wrapper icon-message">
            <svg-icon icon-class="list" class-name="card-panel-icon"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">发布项目数</div>
            <count-to
              :start-val="0"
              :end-val="businessDataList.projectCount.expectedData[businessDataList.projectCount.expectedData.length - 1][1]"
              :duration="1500"
              class="card-panel-num"
            />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('structureCount')">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon icon-class="example" class-name="card-panel-icon"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">结构物数量</div>
            <count-to
              :start-val="0"
              :end-val="businessDataList.structureCount.expectedData[businessDataList.structureCount.expectedData.length - 1][1]"
              :duration="1500"
              class="card-panel-num"
            />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('deviceCount')">
          <div class="card-panel-icon-wrapper icon-shopping">
            <svg-icon icon-class="example" class-name="card-panel-icon"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">设备总数</div>
            <count-to
              :start-val="0"
              :end-val="businessDataList.deviceCount.expectedData[businessDataList.deviceCount.expectedData.length - 1][1]"
              :duration="1500"
              class="card-panel-num"
            />
          </div>
        </div>
      </el-col>
    </el-row>

    <el-row style="background: #fff; padding: 16px 16px 0; margin-bottom: 32px">
      <line-chart :chart-data="lineChartData"/>
    </el-row>

    <el-row style="background: #fff; padding: 16px 16px; margin-bottom: 32px">
      <div id="map" style="width: 100%; height: 30vw"></div>
    </el-row>
  </div>
</template>

<script>
    import LineChart from "./LineChart";
    import CountTo from "vue-count-to";

    import {
        dashboardBusiness
    } from "@/api/iot/console";

    // const lineChartData = {
    //     dataCount: {
    //         expectedData: [100, 120, 161, 134, 105, 160, 165],
    //     },
    //     projectCount: {
    //         expectedData: [200, 192, 120, 144, 160, 130, 140],
    //     },
    //     structureCount: {
    //         expectedData: [80, 100, 121, 104, 105, 90, 100],
    //     },
    //     deviceCount: {
    //         expectedData: [130, 140, 141, 142, 145, 150, 160],
    //     },
    // };
    export default {
        name: "DashboardAdmin",
        components: {
            LineChart,
            CountTo,
        },
        data() {
            return {
                lineChartData: {},
                businessDataList: {
                    dataCount: {expectedData: [0]},
                    projectCount: {expectedData: [0]},
                    structureCount: {expectedData: [0]},
                    deviceCount: {expectedData: [0]}
                }
            };
        },
        mounted() {
            var capitals = [];

            dashboardBusiness().then(response => {
                var businessData = response.businessData;

                this.businessDataList = {
                    dataCount: {expectedData: []},
                    projectCount: {expectedData: []},
                    structureCount: {expectedData: []},
                    deviceCount: {expectedData: []}
                }

                for (var i = 0; i < businessData.length; i++) {
                    var item = businessData[i]
                    // this.businessDataList.dataCount.expectedData.push(item.dataCount);
                    // this.businessDataList.projectCount.expectedData.push(item.projectCount);
                    // this.businessDataList.structureCount.expectedData.push(item.structureCount);
                    // this.businessDataList.deviceCount.expectedData.push(item.deviceCount);

                    this.businessDataList.dataCount.expectedData.push([
                        item.createTime,
                        item.dataCount
                    ]);
                    this.businessDataList.projectCount.expectedData.push([
                        item.createTime,
                        item.projectCount
                    ]);
                    this.businessDataList.structureCount.expectedData.push([
                        item.createTime,
                        item.structureCount
                    ]);
                    this.businessDataList.deviceCount.expectedData.push([
                        item.createTime,
                        item.deviceCount
                    ]);
                }

                this.lineChartData = this.businessDataList.dataCount;

                var projectList = response.projectList;

                for (var i = 0; i < projectList.length; i++) {
                    var item = projectList[i]
                    capitals.push({
                        name: item.name,
                        center: [item.positionLng, item.positionLat],
                    })
                }

            });

            var map = new AMap.Map("map", {
                zoom: 4,
                mapStyle: "amap://styles/whitesmoke",
                center: [108, 34],
            });


            for (var i = 0; i < capitals.length; i += 1) {
                var center = capitals[i].center;
                var circleMarker = new AMap.CircleMarker({
                    center: center,
                    radius: 10 + Math.random() * 10, //3D视图下，CircleMarker半径不要超过64px
                    strokeColor: "white",
                    strokeWeight: 2,
                    strokeOpacity: 0.5,
                    fillColor: "rgba(0,0,255,1)",
                    fillOpacity: 0.5,
                    zIndex: 10,
                    bubble: true,
                    cursor: "pointer",
                    clickable: true,
                });
                circleMarker.setMap(map);
            }
        },
        methods: {
            handleSetLineChartData(type) {
                this.lineChartData = this.businessDataList[type];
            },
        },
    };
</script>

<style lang="scss" scoped>
  .dashboard-editor-container {
    padding: 32px;
    background-color: rgb(240, 242, 245);
    position: relative;

    .github-corner {
      position: absolute;
      top: 0px;
      border: 0;
      right: 0;
    }

    .chart-wrapper {
      background: #fff;
      padding: 16px 16px 0;
      margin-bottom: 32px;
    }
  }

  @media (max-width: 1024px) {
    .chart-wrapper {
      padding: 8px;
    }
  }
</style>

<style lang="scss" scoped>
  .panel-group {
    margin-top: 18px;

    .card-panel-col {
      margin-bottom: 32px;
    }

    .card-panel {
      height: 108px;
      cursor: pointer;
      font-size: 12px;
      position: relative;
      overflow: hidden;
      color: #666;
      background: #fff;
      box-shadow: 4px 4px 40px rgba(0, 0, 0, 0.05);
      border-color: rgba(0, 0, 0, 0.05);

      &:hover {
        .card-panel-icon-wrapper {
          color: #fff;
        }

        .icon-people {
          background: #40c9c6;
        }

        .icon-message {
          background: #36a3f7;
        }

        .icon-money {
          background: #f4516c;
        }

        .icon-shopping {
          background: #34bfa3;
        }
      }

      .icon-people {
        color: #40c9c6;
      }

      .icon-message {
        color: #36a3f7;
      }

      .icon-money {
        color: #f4516c;
      }

      .icon-shopping {
        color: #34bfa3;
      }

      .card-panel-icon-wrapper {
        float: left;
        margin: 14px 0 0 14px;
        padding: 16px;
        transition: all 0.38s ease-out;
        border-radius: 6px;
      }

      .card-panel-icon {
        float: left;
        font-size: 48px;
      }

      .card-panel-description {
        float: right;
        font-weight: bold;
        margin: 26px;
        margin-left: 0px;

        .card-panel-text {
          line-height: 18px;
          color: rgba(0, 0, 0, 0.45);
          font-size: 16px;
          margin-bottom: 12px;
        }

        .card-panel-num {
          font-size: 20px;
        }
      }
    }
  }

  @media (max-width: 550px) {
    .card-panel-description {
      display: none;
    }

    .card-panel-icon-wrapper {
      float: none !important;
      width: 100%;
      height: 100%;
      margin: 0 !important;

      .svg-icon {
        display: block;
        margin: 14px auto !important;
        float: none !important;
      }
    }
  }
</style>
