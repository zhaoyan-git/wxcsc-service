<template>
  <div class="app-container" v-loading="pageLoading">
    <el-tabs tab-position="left" @tab-click="tabClick">
      <el-tab-pane label="项目管理">
        <project-mng :isShow="0 == showIndex"></project-mng>
      </el-tab-pane>
      <el-tab-pane label="结构物管理">
        <structure-mng :isShow="1 == showIndex"></structure-mng>
      </el-tab-pane>
      <el-tab-pane label="测点管理">
        <point-mng :isShow="2 == showIndex"></point-mng>
      </el-tab-pane>
      <el-tab-pane label="设备管理">
        <device-mng :isShow="3 == showIndex"></device-mng>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
import ProjectMng from "./integrate/project-mng";
import StructureMng from "./integrate/structure-mng";
import PointMng from "./integrate/point-mng";
import DeviceMng from "./integrate/device-mng";

export default {
  name: "atricleChannelJjfa",
  components: {
    ProjectMng,
    StructureMng,
    PointMng,
    DeviceMng,
  },
  data() {
    return {
      processEnv: process.env,
      options: [],
      pageLoading: false,
      showIndex: 0,
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      this.$nextTick(() => {
        this.initChart();
      });
    },
    tabClick(e) {
      this.showIndex = e.$data.index;
    },
  },
};
</script>

<style lang="scss">
.el-tabs {
  height: calc(100vh - 100px);
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
