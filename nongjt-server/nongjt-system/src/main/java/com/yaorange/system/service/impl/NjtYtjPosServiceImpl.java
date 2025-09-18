package com.yaorange.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yaorange.system.domain.NjtYtjPos;
import com.yaorange.system.mapper.NjtYtjPosMapper;
import com.yaorange.system.service.INjtYtjPosService;

/**
 * 一体打印机服务点 服务层处理
 * 
 * @author ruoyi
 */
@Service
public class NjtYtjPosServiceImpl implements INjtYtjPosService
{
    @Autowired
    private NjtYtjPosMapper njtYtjPosMapper;

    /**
     * 查询一体打印机服务点信息集合
     * 
     * @param njtYtjPos 一体打印机服务点信息
     * @return 一体打印机服务点信息集合
     */
    @Override
    public List<NjtYtjPos> selectNjtYtjPosList(NjtYtjPos njtYtjPos)
    {
        return njtYtjPosMapper.selectNjtYtjPosList(njtYtjPos);
    }

    /**
     * 查询所有一体打印机服务点
     * 
     * @return 一体打印机服务点列表
     */
    @Override
    public List<NjtYtjPos> selectNjtYtjPosAll()
    {
        return njtYtjPosMapper.selectNjtYtjPosAll();
    }

    /**
     * 通过主键查询一体打印机服务点信息
     * 
     * @param id 主键
     * @return 一体打印机服务点对象信息
     */
    @Override
    public NjtYtjPos selectNjtYtjPosById(Long id)
    {
        return njtYtjPosMapper.selectNjtYtjPosById(id);
    }

    /**
     * 删除一体打印机服务点信息
     * 
     * @param id 主键
     * @return 结果
     */
    @Override
    public int deleteNjtYtjPosById(Long id)
    {
        return njtYtjPosMapper.deleteNjtYtjPosById(id);
    }

    /**
     * 批量删除一体打印机服务点信息
     * 
     * @param ids 需要删除的主键数组
     * @return 结果
     */
    @Override
    public int deleteNjtYtjPosByIds(Long[] ids)
    {
        return njtYtjPosMapper.deleteNjtYtjPosByIds(ids);
    }

    /**
     * 新增保存一体打印机服务点信息
     * 
     * @param njtYtjPos 一体打印机服务点信息
     * @return 结果
     */
    @Override
    public int insertNjtYtjPos(NjtYtjPos njtYtjPos)
    {
        return njtYtjPosMapper.insertNjtYtjPos(njtYtjPos);
    }

    /**
     * 修改保存一体打印机服务点信息
     * 
     * @param njtYtjPos 一体打印机服务点信息
     * @return 结果
     */
    @Override
    public int updateNjtYtjPos(NjtYtjPos njtYtjPos)
    {
        return njtYtjPosMapper.updateNjtYtjPos(njtYtjPos);
    }
}