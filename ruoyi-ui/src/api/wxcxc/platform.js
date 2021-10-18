import request from '@/utils/request'

const apiPrefix = "/wxcxc/api/platform"

export function businessList(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/business/list',
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

export function businessDel(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/business/del',
        method: 'post',
        data
    })
}

export function userListByEnterpriseUser(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/user/list/by_role_name/enterprise_user',
        method: 'post',
        data
    })
}

export function productList(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/product/list',
        method: 'post',
        data
    })
}

export function productSave(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/product/save',
        method: 'post',
        data
    })
}

export function productDel(data) {
    return request({
        url: process.env.VUE_APP_SERVERAPI + apiPrefix + '/product/del',
        method: 'post',
        data
    })
}