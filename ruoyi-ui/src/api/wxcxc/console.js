import request from '@/utils/request'

const apiPrefix = "/wxcxc/api/console"

export function findBusinessBaseInfo(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/business/baseinfo',
        method: 'post',
        data
    })
}

export function businessSave(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/business/save',
        method: 'post',
        data
    })
}

export function findBusinessMemberList(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/business/member/list',
        method: 'post',
        data
    })
}

export function businessMemberSave(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/business/member/save',
        method: 'post',
        data
    })
}

export function businessMemberDel(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/business/member/del',
        method: 'post',
        data
    })
}

export function businessMemberRoleProjectList(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/business/member/role/project/list',
        method: 'post',
        data
    })
}

export function businessMemberRoleProjectSave(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/business/member/role/project/save',
        method: 'post',
        data
    })
}

export function projectListBusiness(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/project/list/business',
        method: 'post',
        data
    })
}

export function findProjectList(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/project/list',
        method: 'post',
        data
    })
}

export function projectSave(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/project/save',
        method: 'post',
        data
    })
}

export function projectDel(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/project/del',
        method: 'post',
        data
    })
}

export function findProjectStructureList(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/project/structure/list',
        method: 'post',
        data
    })
}

export function projectStructureSave(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/project/structure/save',
        method: 'post',
        data
    })
}

export function projectStructureDel(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/project/structure/del',
        method: 'post',
        data
    })
}

export function findProjectPointList(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/project/point/list',
        method: 'post',
        data
    })
}

export function projectPointSave(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/project/point/save',
        method: 'post',
        data
    })
}

export function projectPointDel(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/project/point/del',
        method: 'post',
        data
    })
}

export function projectStructureListByProjectId(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/project/structure/list/projectId',
        method: 'post',
        data
    })
}

export function projectStructureTypeList(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/project/structure/type/list',
        method: 'post',
        data
    })
}