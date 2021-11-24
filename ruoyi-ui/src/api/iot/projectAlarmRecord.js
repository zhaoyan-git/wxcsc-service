import request from '@/utils/request'

// 查询报警记录列表
export function listProjectAlarmRecord(query) {
  return request({
    url: '/iot/projectAlarmRecord/list',
    method: 'get',
    params: query
  })
}

// 查询报警记录详细
export function getProjectAlarmRecord(id) {
  return request({
    url: '/iot/projectAlarmRecord/' + id,
    method: 'get'
  })
}

// 新增报警记录
export function addProjectAlarmRecord(data) {
  return request({
    url: '/iot/projectAlarmRecord',
    method: 'post',
    data: data
  })
}

// 修改报警记录
export function updateProjectAlarmRecord(data) {
  return request({
    url: '/iot/projectAlarmRecord',
    method: 'put',
    data: data
  })
}

// 删除报警记录
export function delProjectAlarmRecord(id) {
  return request({
    url: '/iot/projectAlarmRecord/' + id,
    method: 'delete'
  })
}

// 导出报警记录
export function exportProjectAlarmRecord(query) {
  return request({
    url: '/iot/projectAlarmRecord/export',
    method: 'get',
    params: query
  })
}