import request from '@/utils/request'

// 查询测点分组列表
export function listProjectPointGroup(query) {
  return request({
    url: '/iot/projectPointGroup/list',
    method: 'get',
    params: query
  })
}

// 查询测点分组详细
export function getProjectPointGroup(id) {
  return request({
    url: '/iot/projectPointGroup/' + id,
    method: 'get'
  })
}

// 新增测点分组
export function addProjectPointGroup(data) {
  return request({
    url: '/iot/projectPointGroup',
    method: 'post',
    data: data
  })
}

// 修改测点分组
export function updateProjectPointGroup(data) {
  return request({
    url: '/iot/projectPointGroup',
    method: 'put',
    data: data
  })
}

// 删除测点分组
export function delProjectPointGroup(id) {
  return request({
    url: '/iot/projectPointGroup/' + id,
    method: 'delete'
  })
}

// 导出测点分组
export function exportProjectPointGroup(query) {
  return request({
    url: '/iot/projectPointGroup/export',
    method: 'get',
    params: query
  })
}