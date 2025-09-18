package com.yaorange.system.mapper;

import java.util.List;
import com.yaorange.system.domain.NjtYtjPos;

/**
 * 一体打印机服务点 数据层
 * 
 * @author ruoyi
 */
public interface NjtYtjPosMapper
{
    /**
     * 查询一体打印机服务点集合
     * 
     * @param njtYtjPos 一体打印机服务点信息
     * @return 一体打印机服务点集合
     */
    public List<NjtYtjPos> selectNjtYtjPosList(NjtYtjPos njtYtjPos);

    /**
     * 查询所有一体打印机服务点
     * 
     * @return 一体打印机服务点列表
     */
    public List<NjtYtjPos> selectNjtYtjPosAll();

    /**
     * 通过主键查询一体打印机服务点信息
     * 
     * @param id 主键
     * @return 一体打印机服务点对象信息
     */
    public NjtYtjPos selectNjtYtjPosById(Long id);

    /**
     * 删除一体打印机服务点信息
     * 
     * @param id 主键
     * @return 结果
     */
    public int deleteNjtYtjPosById(Long id);

    /**
     * 批量删除一体打印机服务点信息
     * 
     * @param ids 需要删除的主键数组
     * @return 结果
     */
    public int deleteNjtYtjPosByIds(Long[] ids);

    /**
     * 修改一体打印机服务点信息
     * 
     * @param njtYtjPos 一体打印机服务点信息
     * @return 结果
     */
    public int updateNjtYtjPos(NjtYtjPos njtYtjPos);

    /**
     * 新增一体打印机服务点信息
     * 
     * @param njtYtjPos 一体打印机服务点信息
     * @return 结果
     */
    public int insertNjtYtjPos(NjtYtjPos njtYtjPos);
}