import request from '@/utils/request'

// 查询项目列表
export function listProject(query) {
  return request({
    url: '/iot/project/list',
    method: 'get',
    params: query
  })
}

// 查询项目详细
export function getProject(id) {
  return request({
    url: '/iot/project/' + id,
    method: 'get'
  })
}

// 新增项目
export function addProject(data) {
  return request({
    url: '/iot/project',
    method: 'post',
    data: data
  })
}

// 修改项目
export function updateProject(data) {
  return request({
    url: '/iot/project',
    method: 'put',
    data: data
  })
}

// 删除项目
export function delProject(id) {
  return request({
    url: '/iot/project/' + id,
    method: 'delete'
  })
}

// 导出项目
export function exportProject(query) {
  return request({
    url: '/iot/project/export',
    method: 'get',
    params: query
  })
}

// 根据企业ID获取项目列表
export function getListByBusinessId(query) {
  return request({
    url: '/iot/project/getListByBusinessId',
    method: 'get',
    params: query
  })
}
