import request from '@/utils/request'

// 查询企业人员列表
export function listBusinessMember(query) {
  return request({
    url: '/iot/businessMember/list',
    method: 'get',
    params: query
  })
}

// 查询企业人员详细
export function getBusinessMember(id) {
  return request({
    url: '/iot/businessMember/' + id,
    method: 'get'
  })
}

// 新增企业人员
export function addBusinessMember(data) {
  return request({
    url: '/iot/businessMember',
    method: 'post',
    data: data
  })
}

// 修改企业人员
export function updateBusinessMember(data) {
  return request({
    url: '/iot/businessMember',
    method: 'put',
    data: data
  })
}

// 删除企业人员
export function delBusinessMember(id) {
  return request({
    url: '/iot/businessMember/' + id,
    method: 'delete'
  })
}

// 导出企业人员
export function exportBusinessMember(query) {
  return request({
    url: '/iot/businessMember/export',
    method: 'get',
    params: query
  })
}

// 查询企业人员列表
export function listBusinessMemberDto(query) {
  return request({
    url: '/iot/businessMember/businessMemberDto/list',
    method: 'get',
    params: query
  })
}

// 新增企业人员
export function addBusinessMemberDto(data) {
  return request({
    url: '/iot/businessMember/businessMemberDto',
    method: 'post',
    data: data
  })
}
