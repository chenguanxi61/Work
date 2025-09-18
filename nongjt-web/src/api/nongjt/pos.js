// src/api/nongjt/pos.js
import request from '@/utils/request'

// 查询一体机服务点列表
export function listPos(query) {
    return request({
        url: '/ytj/pos/list',
        method: 'get',
        params: query
    })
}

// 查询一体机服务点详细
export function getPos(id) {
    return request({
        url: '/ytj/pos/' + id,
        method: 'get'
    })
}

// 新增一体机服务点
export function addPos(data) {
    return request({
        url: '/ytj/pos',
        method: 'post',
        data: data
    })
}

// 修改一体机服务点
export function updatePos(data) {
    return request({
        url: '/ytj/pos',
        method: 'put',
        data: data
    })
}

// 删除一体机服务点
export function delPos(id) {
    return request({
        url: '/ytj/pos/' + id,
        method: 'delete'
    })
}

// 导出一体机服务点
export function exportPos(query) {
    return request({
        url: '/ytj/pos/export',
        method: 'post',
        params: query
    })
}

// 获取服务点选择框列表
export function optionselect() {
    return request({
        url: '/ytj/pos/optionselect',
        method: 'get'
    })
}
