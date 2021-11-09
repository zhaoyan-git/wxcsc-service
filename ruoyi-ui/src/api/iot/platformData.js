import request from '@/utils/request'

// 查询平台数据列表
export function listPlatformData(query) {
  return request({
    url: '/iot/platformData/list',
    method: 'get',
    params: query
  })
}

// 查询平台数据详细
export function getPlatformData(id) {
  return request({
    url: '/iot/platformData/' + id,
    method: 'get'
  })
}

// 新增平台数据
export function addPlatformData(data) {
  return request({
    url: '/iot/platformData',
    method: 'post',
    data: data
  })
}

// 修改平台数据
export function updatePlatformData(data) {
  return request({
    url: '/iot/platformData',
    method: 'put',
    data: data
  })
}

// 删除平台数据
export function delPlatformData(id) {
  return request({
    url: '/iot/platformData/' + id,
    method: 'delete'
  })
}

// 导出平台数据
export function exportPlatformData(query) {
  return request({
    url: '/iot/platformData/export',
    method: 'get',
    params: query
  })
}