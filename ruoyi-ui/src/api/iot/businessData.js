import request from '@/utils/request'

// 查询企业数据列表
export function listBusinessData(query) {
  return request({
    url: '/iot/businessData/list',
    method: 'get',
    params: query
  })
}

// 查询企业数据详细
export function getBusinessData(id) {
  return request({
    url: '/iot/businessData/' + id,
    method: 'get'
  })
}

// 新增企业数据
export function addBusinessData(data) {
  return request({
    url: '/iot/businessData',
    method: 'post',
    data: data
  })
}

// 修改企业数据
export function updateBusinessData(data) {
  return request({
    url: '/iot/businessData',
    method: 'put',
    data: data
  })
}

// 删除企业数据
export function delBusinessData(id) {
  return request({
    url: '/iot/businessData/' + id,
    method: 'delete'
  })
}

// 导出企业数据
export function exportBusinessData(query) {
  return request({
    url: '/iot/businessData/export',
    method: 'get',
    params: query
  })
}