import request from '@/utils/request'

// 查询聚集配置列表
export function listPointGatherConfig(query) {
  return request({
    url: '/iot/pointGatherConfig/list',
    method: 'get',
    params: query
  })
}

// 查询聚集配置详细
export function getPointGatherConfig(id) {
  return request({
    url: '/iot/pointGatherConfig/' + id,
    method: 'get'
  })
}

// 新增聚集配置
export function addPointGatherConfig(data) {
  return request({
    url: '/iot/pointGatherConfig',
    method: 'post',
    data: data
  })
}

// 修改聚集配置
export function updatePointGatherConfig(data) {
  return request({
    url: '/iot/pointGatherConfig',
    method: 'put',
    data: data
  })
}

// 删除聚集配置
export function delPointGatherConfig(id) {
  return request({
    url: '/iot/pointGatherConfig/' + id,
    method: 'delete'
  })
}

// 导出聚集配置
export function exportPointGatherConfig(query) {
  return request({
    url: '/iot/pointGatherConfig/export',
    method: 'get',
    params: query
  })
}