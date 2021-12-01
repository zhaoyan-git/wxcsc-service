import request from '@/utils/request'

export function projectStructureTypeList(query) {
  return request({
    url: '/iot/console/projectStructureTypeList',
    method: 'get',
    params: query
  })
}

export function projectListBusiness(query) {
  return request({
    url: '/iot/console/projectListBusiness',
    method: 'get',
    params: query
  })
}

export function projectListByUserRole(query) {
  return request({
    url: '/iot/console/projectListByUserRole',
    method: 'get',
    params: query
  })
}

export function projectStructureListByProjectId(query) {
  return request({
    url: '/iot/console/projectStructureListByProjectId',
    method: 'get',
    params: query
  })
}

export function getBusiness(query) {
  return request({
    url: '/iot/console/business/get',
    method: 'get',
    params: query
  })
}

export function updateBusiness(data) {
  return request({
    url: '/iot/console/business/update',
    method: 'post',
    data: data
  })
}

export function consoleListBusinessMemberDto(data) {
  return request({
    url: '/iot/console/businessMemeber/listDto',
    method: 'post',
    data: data
  })
}

export function deviceGatewayList(data) {
  return request({
    url: '/iot/console/deviceGatewayList',
    method: 'get',
    params: data
  })
}

export function projectPointGroup(query) {
  return request({
    url: '/iot/console/projectPointGroup',
    method: 'get',
    params: query
  })
}

export function projectDeviceList(query) {
  return request({
    url: '/iot/console/projectDeviceList',
    method: 'get',
    params: query
  })
}

export function dashboardBusiness(query) {
  return request({
    url: '/iot/console/dashboardBusiness',
    method: 'get',
    params: query
  })
}

export function projectAlarmRecordListByUser(query) {
  return request({
    url: '/iot/console/projectAlarmRecord/list/byUser',
    method: 'get',
    params: query
  })
}


export function reportForm(query) {
  return request({
    url: '/iot/console/reportForm',
    method: 'get',
    params: query
  })
}

// 根据当前企业获取项目列表
export function getListByBusinessId(query) {
  return request({
    url: '/iot/console/getListByBusinessId',
    method: 'get',
    params: query
  })
}
