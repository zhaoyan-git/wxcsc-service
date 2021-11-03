<template>
  <div class="app-container" v-loading="pageLoading">
    <el-tabs tab-position="left" @tab-click="tabClick">
      <el-tab-pane label="项目管理">
        <project-mng :isShow="0 == showIndex"></project-mng>
      </el-tab-pane>
      <el-tab-pane label="结构物管理">
        <structure-mng :isShow="1 == showIndex" :projectId="projectId" @projectCurrent="changeProjectId"></structure-mng>
      </el-tab-pane>
      <el-tab-pane label="测点管理">
        <point-mng :isShow="2 == showIndex" :projectId="projectId" @projectCurrent="changeProjectId"></point-mng>
      </el-tab-pane>
      <el-tab-pane label="设备管理">
        <device-mng :isShow="3 == showIndex" :projectId="projectId" @projectCurrent="changeProjectId"></device-mng>
      </el-tab-pane>
      <el-tab-pane label="网关管理">
        <deviceGateway :isShow="4 == showIndex" :projectId="projectId" @projectCurrent="changeProjectId"></deviceGateway>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
    import ProjectMng from "./integrate/project-mng";
    import StructureMng from "./integrate/structure-mng";
    import PointMng from "./integrate/point-mng";
    import DeviceMng from "./integrate/device-mng";
    import deviceGateway from "./integrate/deviceGateway";

    export default {
        components: {
            ProjectMng,
            StructureMng,
            PointMng,
            DeviceMng,
            deviceGateway
        },
        data() {
            return {
                processEnv: process.env,
                options: [],
                pageLoading: false,
                showIndex: 0,
                projectId: null
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
            changeProjectId(projectId) {
                this.projectId = projectId
            }
        },
    };
</script>

<style lang="scss">
  .el-tabs {
    height: calc(100vh - 130px);
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

  .el-tabs__content {
    height: 100vh;
  }

  // page end
</style>
