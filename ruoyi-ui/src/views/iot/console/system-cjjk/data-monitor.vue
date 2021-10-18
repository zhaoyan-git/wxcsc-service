<template>
  <div class="app-container" v-loading="pageLoading">
    <el-container class="project-data-pannel">
      <el-aside width="200px">
        <el-table
          :data="tableData"
          row-key="item"
          style="width: 100%"
          @sort-change="sortChange"
          highlight-current-row
        >
          <!-- 表头及数据 START -->
          <el-table-column
            class="project-list-table"
            sortable="id"
            prop="id"
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
              <el-select placeholder="请选择结构物">
                <el-option label="结构物1" value="结构物1"></el-option>
                <el-option label="结构物2" value="结构物2"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="采集设备">
              <el-input v-model="condition.title" placeholder="请选择测点/设备" />
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
                >条件查询</el-button
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
        <el-footer> </el-footer>
      </el-container>
    </el-container>
  </div>
</template>
<script>
// import { getList, save, del } from "@/api/plugin/article";
import { parseTime } from "@/utils";
// import md5 from "blueimp-md5";
import echarts from "echarts";
require("echarts/theme/macarons"); // echarts theme
// import resize from "./mixins/resize";
// import Tinymce from "@/components/Tinymce";

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
      dataForm: {
      },
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
          { required: true, message: "请输入登录名", trigger: "blur" },
        ],
        nickname: [{ required: true, message: "请输入昵称", trigger: "blur" }],
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
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      this.loadPage();
      this.$nextTick(() => {
        this.initChart();
      });
    },
    loadPage() {
      if (this.pageLoading) {
        return;
      }
      //   this.pageLoading = true;
      //   getList(this.condition).then((response) => {
      //     var data = response.data;
      //     this.tableData = data.content;
      //     this.condition.page.total = data.totalElements;
      this.pageLoading = false;
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
    initChart() {
      this.chart = echarts.init(this.$refs.chart, "macarons");
      this.setOptions(this.chartData);
    },
    setOptions({ expectedData } = {}) {
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
  },
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
