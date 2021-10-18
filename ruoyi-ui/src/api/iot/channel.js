import request from '@/utils/request'

// 查询栏目列表
export function listChannel(query) {
  return request({
    url: '/iot/channel/list',
    method: 'get',
    params: query
  })
}

// 查询栏目详细
export function getChannel(id) {
  return request({
    url: '/iot/channel/' + id,
    method: 'get'
  })
}

// 新增栏目
export function addChannel(data) {
  return request({
    url: '/iot/channel',
    method: 'post',
    data: data
  })
}

// 修改栏目
export function updateChannel(data) {
  return request({
    url: '/iot/channel',
    method: 'put',
    data: data
  })
}

// 删除栏目
export function delChannel(id) {
  return request({
    url: '/iot/channel/' + id,
    method: 'delete'
  })
}

// 导出栏目
export function exportChannel(query) {
  return request({
    url: '/iot/channel/export',
    method: 'get',
    params: query
  })
}