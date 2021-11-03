<template>
  <div class="dashboard-editor-container">
    <el-row :gutter="40" class="panel-group">
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('newVisitis')">
          <div class="card-panel-icon-wrapper icon-people">
            <svg-icon icon-class="peoples" class-name="card-panel-icon"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">入驻企业数量</div>
            <count-to
              :start-val="0"
              :end-val="125"
              :duration="1500"
              class="card-panel-num"
            />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('messages')">
          <div class="card-panel-icon-wrapper icon-message">
            <svg-icon icon-class="list" class-name="card-panel-icon"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">平台总项目数</div>
            <count-to
              :start-val="0"
              :end-val="130"
              :duration="1500"
              class="card-panel-num"
            />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('purchases')">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon icon-class="example" class-name="card-panel-icon"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">平台设备数</div>
            <count-to
              :start-val="0"
              :end-val="350"
              :duration="1500"
              class="card-panel-num"
            />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('shoppings')">
          <div class="card-panel-icon-wrapper icon-shopping">
            <svg-icon icon-class="international" class-name="card-panel-icon"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">平台DTU数</div>
            <count-to
              :start-val="0"
              :end-val="50"
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
    import LineChart from "@/views/dashboard/LineChart";
    import CountTo from "vue-count-to";

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
        name: "DashboardAdmin",
        components: {
            LineChart,
            CountTo,
        },
        data() {
            return {
                lineChartData: lineChartData.newVisitis,
            };
        },
        mounted() {
            var map = new AMap.Map("map", {
                zoom: 4,
                mapStyle: "amap://styles/whitesmoke",
                center: [108, 34],
            });

            var capitals = [
                {
                    citycode: "010",
                    adcode: "110000",
                    name: "北京市",
                    center: [116.407394, 39.904211],
                },
                {
                    citycode: "023",
                    adcode: "500000",
                    name: "重庆市",
                    center: [106.551643, 29.562849],
                },
                {
                    citycode: "1853",
                    adcode: "820000",
                    name: "澳门特别行政区",
                    center: [113.543028, 22.186835],
                },
                {
                    adcode: "440000",
                    name: "广东省",
                    center: [113.26641, 23.132324],
                },
                {
                    adcode: "350000",
                    name: "福建省",
                    center: [119.295143, 26.100779],
                },
                {
                    adcode: "460000",
                    name: "海南省",
                    center: [110.349228, 20.017377],
                },
                {
                    adcode: "140000",
                    name: "山西省",
                    center: [112.562678, 37.873499],
                },
                {
                    citycode: "1886",
                    adcode: "710000",
                    name: "台湾省",
                    center: [121.509062, 25.044332],
                },
                {
                    adcode: "520000",
                    name: "贵州省",
                    center: [106.70546, 26.600055],
                },
                {
                    adcode: "450000",
                    name: "广西壮族自治区",
                    center: [108.327546, 22.815478],
                },
                {
                    adcode: "620000",
                    name: "甘肃省",
                    center: [103.826447, 36.05956],
                },
                {
                    adcode: "410000",
                    name: "河南省",
                    center: [113.753394, 34.765869],
                },
                {
                    adcode: "130000",
                    name: "河北省",
                    center: [114.530235, 38.037433],
                },
                {
                    adcode: "340000",
                    name: "安徽省",
                    center: [117.329949, 31.733806],
                },
                {
                    adcode: "430000",
                    name: "湖南省",
                    center: [112.9836, 28.112743],
                },
                {
                    citycode: "021",
                    adcode: "310000",
                    name: "上海市",
                    center: [121.473662, 31.230372],
                },
                {
                    adcode: "420000",
                    name: "湖北省",
                    center: [114.341745, 30.546557],
                },
                {
                    adcode: "230000",
                    name: "黑龙江省",
                    center: [126.661665, 45.742366],
                },
                {
                    adcode: "150000",
                    name: "内蒙古自治区",
                    center: [111.76629, 40.81739],
                },
                {
                    adcode: "640000",
                    name: "宁夏回族自治区",
                    center: [106.259126, 38.472641],
                },
                {
                    adcode: "360000",
                    name: "江西省",
                    center: [115.81635, 28.63666],
                },
                {
                    adcode: "320000",
                    name: "江苏省",
                    center: [118.762765, 32.060875],
                },
                {
                    adcode: "220000",
                    name: "吉林省",
                    center: [125.32568, 43.897016],
                },
                {
                    adcode: "210000",
                    name: "辽宁省",
                    center: [123.431382, 41.836175],
                },
                {
                    adcode: "370000",
                    name: "山东省",
                    center: [117.019915, 36.671156],
                },
                {
                    adcode: "650000",
                    name: "新疆维吾尔自治区",
                    center: [87.627704, 43.793026],
                },
                {
                    citycode: "022",
                    adcode: "120000",
                    name: "天津市",
                    center: [117.200983, 39.084158],
                },
                {
                    adcode: "630000",
                    name: "青海省",
                    center: [101.780268, 36.620939],
                },
                {
                    adcode: "610000",
                    name: "陕西省",
                    center: [108.954347, 34.265502],
                },
                {
                    adcode: "540000",
                    name: "西藏自治区",
                    center: [91.117525, 29.647535],
                },
                {
                    adcode: "510000",
                    name: "四川省",
                    center: [104.075809, 30.651239],
                },
                {
                    citycode: "1852",
                    adcode: "810000",
                    name: "香港特别行政区",
                    center: [114.171203, 22.277468],
                },
                {
                    adcode: "530000",
                    name: "云南省",
                    center: [102.710002, 25.045806],
                },
                {
                    adcode: "330000",
                    name: "浙江省",
                    center: [120.152585, 30.266597],
                },
            ];

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
                this.lineChartData = lineChartData[type];
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
