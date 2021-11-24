import request from '@/utils/request'

// 查询设备网关列表
export function listDeviceGateway(query) {
  return request({
    url: '/iot/deviceGateway/list',
    method: 'get',
    params: query
  })
}

// 查询设备网关详细
export function getDeviceGateway(id) {
  return request({
    url: '/iot/deviceGateway/' + id,
    method: 'get'
  })
}

// 新增设备网关
export function addDeviceGateway(data) {
  return request({
    url: '/iot/deviceGateway',
    method: 'post',
    data: data
  })
}

// 修改设备网关
export function updateDeviceGateway(data) {
  return request({
    url: '/iot/deviceGateway',
    method: 'put',
    data: data
  })
}

// 删除设备网关
export function delDeviceGateway(id) {
  return request({
    url: '/iot/deviceGateway/' + id,
    method: 'delete'
  })
}

// 导出设备网关
export function exportDeviceGateway(query) {
  return request({
    url: '/iot/deviceGateway/export',
    method: 'get',
    params: query
  })
}

// 手动采集
export function manualGet(query) {
  return request({
    url: '/iot/deviceGateway/manualGet',
    method: 'get',
    params: query
  })
}
