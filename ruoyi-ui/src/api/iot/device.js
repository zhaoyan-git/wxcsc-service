import request from '@/utils/request'

// 查询设备表列表
export function listDevice(query) {
  return request({
    url: '/iot/device/list',
    method: 'get',
    params: query
  })
}

// 查询设备表详细
export function getDevice(id) {
  return request({
    url: '/iot/device/' + id,
    method: 'get'
  })
}

// 新增设备表
export function addDevice(data) {
  return request({
    url: '/iot/device',
    method: 'post',
    data: data
  })
}

// 修改设备表
export function updateDevice(data) {
  return request({
    url: '/iot/device',
    method: 'put',
    data: data
  })
}

// 删除设备表
export function delDevice(id) {
  return request({
    url: '/iot/device/' + id,
    method: 'delete'
  })
}

// 导出设备表
export function exportDevice(query) {
  return request({
    url: '/iot/device/export',
    method: 'get',
    params: query
  })
}