package com.yaorange.system.service.impl;

import java.util.List;
import com.yaorange.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yaorange.system.mapper.NjtUserRegisterMapper ;
import com.yaorange.system.domain.vo.NjtUserRegister ;
import com.yaorange.system.service.INjtUserRegisterService ;

/**
 * 用户注册信息Service业务层处理
 *
 * @author yanye
 * @date 2025-09-18
 */
@Service
public class NjtUserRegisterServiceImpl implements INjtUserRegisterService
{
    @Autowired
    private NjtUserRegisterMapper njtUserRegisterMapper;

    /**
     * 查询用户注册信息
     *
     * @param id 用户注册信息主键
     * @return 用户注册信息
     */
    @Override
    public NjtUserRegister selectNjtUserRegisterById(Long id)
    {
        return njtUserRegisterMapper.selectNjtUserRegisterById(id);
    }

    /**
     * 查询用户注册信息列表
     *
     * @param njtUserRegister 用户注册信息
     * @return 用户注册信息
     */
    @Override
    public List<NjtUserRegister> selectNjtUserRegisterList(NjtUserRegister njtUserRegister)
    {
        return njtUserRegisterMapper.selectNjtUserRegisterList(njtUserRegister);
    }

    /**
     * 新增用户注册信息
     *
     * @param njtUserRegister 用户注册信息
     * @return 结果
     */
    @Override
    public int insertNjtUserRegister(NjtUserRegister njtUserRegister)
    {
        njtUserRegister.setCreateTime(DateUtils.getNowDate());
        return njtUserRegisterMapper.insertNjtUserRegister(njtUserRegister);
    }

    /**
     * 修改用户注册信息
     *
     * @param njtUserRegister 用户注册信息
     * @return 结果
     */
    @Override
    public int updateNjtUserRegister(NjtUserRegister njtUserRegister)
    {
        njtUserRegister.setUpdateTime(DateUtils.getNowDate());
        return njtUserRegisterMapper.updateNjtUserRegister(njtUserRegister);
    }

    /**
     * 批量删除用户注册信息
     *
     * @param ids 需要删除的用户注册信息主键
     * @return 结果
     */
    @Override
    public int deleteNjtUserRegisterByIds(Long[] ids)
    {
        return njtUserRegisterMapper.deleteNjtUserRegisterByIds(ids);
    }

    /**
     * 删除用户注册信息信息
     *
     * @param id 用户注册信息主键
     * @return 结果
     */
    @Override
    public int deleteNjtUserRegisterById(Long id)
    {
        return njtUserRegisterMapper.deleteNjtUserRegisterById(id);
    }
}
