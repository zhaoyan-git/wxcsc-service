import request from '@/utils/request'

// 查询企业人员权限列表
export function listBusinessMemberRole(query) {
  return request({
    url: '/iot/businessMemberRole/list',
    method: 'get',
    params: query
  })
}

// 查询企业人员权限详细
export function getBusinessMemberRole(id) {
  return request({
    url: '/iot/businessMemberRole/' + id,
    method: 'get'
  })
}

// 新增企业人员权限
export function addBusinessMemberRole(data) {
  return request({
    url: '/iot/businessMemberRole',
    method: 'post',
    data: data
  })
}

// 修改企业人员权限
export function updateBusinessMemberRole(data) {
  return request({
    url: '/iot/businessMemberRole',
    method: 'put',
    data: data
  })
}

// 删除企业人员权限
export function delBusinessMemberRole(id) {
  return request({
    url: '/iot/businessMemberRole/' + id,
    method: 'delete'
  })
}

// 导出企业人员权限
export function exportBusinessMemberRole(query) {
  return request({
    url: '/iot/businessMemberRole/export',
    method: 'get',
    params: query
  })
}