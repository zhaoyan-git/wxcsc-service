import request from '@/utils/request'

// 查询结构物选项列表
export function listProjectStructureType(query) {
  return request({
    url: '/iot/projectStructureType/list',
    method: 'get',
    params: query
  })
}

// 查询结构物选项详细
export function getProjectStructureType(id) {
  return request({
    url: '/iot/projectStructureType/' + id,
    method: 'get'
  })
}

// 新增结构物选项
export function addProjectStructureType(data) {
  return request({
    url: '/iot/projectStructureType',
    method: 'post',
    data: data
  })
}

// 修改结构物选项
export function updateProjectStructureType(data) {
  return request({
    url: '/iot/projectStructureType',
    method: 'put',
    data: data
  })
}

// 删除结构物选项
export function delProjectStructureType(id) {
  return request({
    url: '/iot/projectStructureType/' + id,
    method: 'delete'
  })
}

// 导出结构物选项
export function exportProjectStructureType(query) {
  return request({
    url: '/iot/projectStructureType/export',
    method: 'get',
    params: query
  })
}