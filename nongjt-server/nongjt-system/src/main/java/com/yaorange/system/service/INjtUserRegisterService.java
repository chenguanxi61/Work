package com.yaorange.system.service;

import java.util.List;
import com.yaorange.system.domain.vo.NjtUserRegister ;

/**
 * 用户注册信息Service接口
 *
 * @author yanye
 * @date 2025-09-18
 */
public interface INjtUserRegisterService
{
    /**
     * 查询用户注册信息
     *
     * @param id 用户注册信息主键
     * @return 用户注册信息
     */
    public NjtUserRegister selectNjtUserRegisterById(Long id);

    /**
     * 查询用户注册信息列表
     *
     * @param njtUserRegister 用户注册信息
     * @return 用户注册信息集合
     */
    public List<NjtUserRegister> selectNjtUserRegisterList(NjtUserRegister njtUserRegister);

    /**
     * 新增用户注册信息
     *
     * @param njtUserRegister 用户注册信息
     * @return 结果
     */
    public int insertNjtUserRegister(NjtUserRegister njtUserRegister);

    /**
     * 修改用户注册信息
     *
     * @param njtUserRegister 用户注册信息
     * @return 结果
     */
    public int updateNjtUserRegister(NjtUserRegister njtUserRegister);

    /**
     * 批量删除用户注册信息
     *
     * @param ids 需要删除的用户注册信息主键集合
     * @return 结果
     */
    public int deleteNjtUserRegisterByIds(Long[] ids);

    /**
     * 删除用户注册信息信息
     *
     * @param id 用户注册信息主键
     * @return 结果
     */
    public int deleteNjtUserRegisterById(Long id);
}
