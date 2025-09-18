import request from '@/utils/request'

// 查询用户注册信息列表
export function listRegister(query) {
  return request({
    url: '/nongjt/register/list',
    method: 'get',
    params: query
  })
}

// 查询用户注册信息详细
export function getRegister(id) {
  return request({
    url: '/nongjt/register/' + id,
    method: 'get'
  })
}

// 新增用户注册信息
export function addRegister(data) {
  return request({
    url: '/nongjt/register',
    method: 'post',
    data: data
  })
}

// 修改用户注册信息
export function updateRegister(data) {
  return request({
    url: '/nongjt/register',
    method: 'put',
    data: data
  })
}

// 删除用户注册信息
export function delRegister(id) {
  return request({
    url: '/nongjt/register/' + id,
    method: 'delete'
  })
}
