import request from '@/utils/request'

// 查询项目设备数据表列表
export function listProjectDeivceSensorData(query) {
  return request({
    url: '/iot/projectDeivceSensorData/list',
    method: 'get',
    params: query
  })
}

// 查询项目设备数据表详细
export function getProjectDeivceSensorData(id) {
  return request({
    url: '/iot/projectDeivceSensorData/' + id,
    method: 'get'
  })
}

// 新增项目设备数据表
export function addProjectDeivceSensorData(data) {
  return request({
    url: '/iot/projectDeivceSensorData',
    method: 'post',
    data: data
  })
}

// 修改项目设备数据表
export function updateProjectDeivceSensorData(data) {
  return request({
    url: '/iot/projectDeivceSensorData',
    method: 'put',
    data: data
  })
}

// 删除项目设备数据表
export function delProjectDeivceSensorData(id) {
  return request({
    url: '/iot/projectDeivceSensorData/' + id,
    method: 'delete'
  })
}

// 导出项目设备数据表
export function exportProjectDeivceSensorData(query) {
  return request({
    url: '/iot/projectDeivceSensorData/export',
    method: 'get',
    params: query
  })
}