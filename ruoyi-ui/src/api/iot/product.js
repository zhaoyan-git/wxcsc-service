import request from '@/utils/request'

// 查询产品管理列表
export function listProduct(query) {
  return request({
    url: '/iot/product/list',
    method: 'get',
    params: query
  })
}

// 查询产品管理详细
export function getProduct(id) {
  return request({
    url: '/iot/product/' + id,
    method: 'get'
  })
}

// 新增产品管理
export function addProduct(data) {
  return request({
    url: '/iot/product',
    method: 'post',
    data: data
  })
}

// 修改产品管理
export function updateProduct(data) {
  return request({
    url: '/iot/product',
    method: 'put',
    data: data
  })
}

// 删除产品管理
export function delProduct(id) {
  return request({
    url: '/iot/product/' + id,
    method: 'delete'
  })
}

// 导出产品管理
export function exportProduct(query) {
  return request({
    url: '/iot/product/export',
    method: 'get',
    params: query
  })
}