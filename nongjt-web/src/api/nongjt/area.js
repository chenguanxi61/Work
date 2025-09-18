import request from '@/utils/request.js'

export function listArea(parentCode) {
    return request({
        url: '/system/area/list',
        method: 'get',
        params: { parentCode }
    })
}
