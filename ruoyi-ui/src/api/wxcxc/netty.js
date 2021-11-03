import request from '@/utils/request'

// 查询netty列表
export function listNetty(query) {
  return request({
    url: '/wxcxc/netty/list',
    method: 'get',
    params: query
  })
}

// 查询netty详细
export function getNetty(id) {
  return request({
    url: '/wxcxc/netty/' + id,
    method: 'get'
  })
}

// 新增netty
export function addNetty(data) {
  return request({
    url: '/wxcxc/netty',
    method: 'post',
    data: data
  })
}

// 修改netty
export function updateNetty(data) {
  return request({
    url: '/wxcxc/netty',
    method: 'put',
    data: data
  })
}

// 删除netty
export function delNetty(id) {
  return request({
    url: '/wxcxc/netty/' + id,
    method: 'delete'
  })
}

// 导出netty
export function exportNetty(query) {
  return request({
    url: '/wxcxc/netty/export',
    method: 'get',
    params: query
  })
}