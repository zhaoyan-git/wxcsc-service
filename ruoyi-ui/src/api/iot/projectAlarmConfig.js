import request from '@/utils/request'

// 查询报警配置列表
export function listProjectAlarmConfig(query) {
  return request({
    url: '/iot/projectAlarmConfig/list',
    method: 'get',
    params: query
  })
}

// 查询报警配置详细
export function getProjectAlarmConfig(id) {
  return request({
    url: '/iot/projectAlarmConfig/' + id,
    method: 'get'
  })
}

// 新增报警配置
export function addProjectAlarmConfig(data) {
  return request({
    url: '/iot/projectAlarmConfig',
    method: 'post',
    data: data
  })
}

// 修改报警配置
export function updateProjectAlarmConfig(data) {
  return request({
    url: '/iot/projectAlarmConfig',
    method: 'put',
    data: data
  })
}

// 删除报警配置
export function delProjectAlarmConfig(id) {
  return request({
    url: '/iot/projectAlarmConfig/' + id,
    method: 'delete'
  })
}

// 导出报警配置
export function exportProjectAlarmConfig(query) {
  return request({
    url: '/iot/projectAlarmConfig/export',
    method: 'get',
    params: query
  })
}