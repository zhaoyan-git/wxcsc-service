import request from '@/utils/request'

// 查询项目测点列表
export function listProjectPoint(query) {
  return request({
    url: '/iot/projectPoint/list',
    method: 'get',
    params: query
  })
}

// 查询项目测点详细
export function getProjectPoint(id) {
  return request({
    url: '/iot/projectPoint/' + id,
    method: 'get'
  })
}

// 新增项目测点
export function addProjectPoint(data) {
  return request({
    url: '/iot/projectPoint',
    method: 'post',
    data: data
  })
}

// 修改项目测点
export function updateProjectPoint(data) {
  return request({
    url: '/iot/projectPoint',
    method: 'put',
    data: data
  })
}

// 删除项目测点
export function delProjectPoint(id) {
  return request({
    url: '/iot/projectPoint/' + id,
    method: 'delete'
  })
}

// 导出项目测点
export function exportProjectPoint(query) {
  return request({
    url: '/iot/projectPoint/export',
    method: 'get',
    params: query
  })
}