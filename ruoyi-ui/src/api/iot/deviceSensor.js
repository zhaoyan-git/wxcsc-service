import request from '@/utils/request'

// 查询设备传感器列表
export function listDeviceSensor(query) {
  return request({
    url: '/iot/deviceSensor/list',
    method: 'get',
    params: query
  })
}

// 查询设备传感器详细
export function getDeviceSensor(id) {
  return request({
    url: '/iot/deviceSensor/' + id,
    method: 'get'
  })
}

// 新增设备传感器
export function addDeviceSensor(data) {
  return request({
    url: '/iot/deviceSensor',
    method: 'post',
    data: data
  })
}

// 修改设备传感器
export function updateDeviceSensor(data) {
  return request({
    url: '/iot/deviceSensor',
    method: 'put',
    data: data
  })
}

// 删除设备传感器
export function delDeviceSensor(id) {
  return request({
    url: '/iot/deviceSensor/' + id,
    method: 'delete'
  })
}

// 导出设备传感器
export function exportDeviceSensor(query) {
  return request({
    url: '/iot/deviceSensor/export',
    method: 'get',
    params: query
  })
}