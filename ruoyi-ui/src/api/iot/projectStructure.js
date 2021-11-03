import request from '@/utils/request'

// 查询项目结构物列表
export function listProjectStructure(query) {
  return request({
    url: '/iot/projectStructure/list',
    method: 'get',
    params: query
  })
}

// 查询项目结构物详细
export function getProjectStructure(id) {
  return request({
    url: '/iot/projectStructure/' + id,
    method: 'get'
  })
}

// 新增项目结构物
export function addProjectStructure(data) {
  return request({
    url: '/iot/projectStructure',
    method: 'post',
    data: data
  })
}

// 修改项目结构物
export function updateProjectStructure(data) {
  return request({
    url: '/iot/projectStructure',
    method: 'put',
    data: data
  })
}

// 删除项目结构物
export function delProjectStructure(id) {
  return request({
    url: '/iot/projectStructure/' + id,
    method: 'delete'
  })
}

// 导出项目结构物
export function exportProjectStructure(query) {
  return request({
    url: '/iot/projectStructure/export',
    method: 'get',
    params: query
  })
}
