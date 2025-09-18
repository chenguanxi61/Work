package com.yaorange.system.mapper;

import java.util.List;
import com.yaorange.system.domain.NjtArea;

/**
 * 地区层级关系 数据层
 *
 * @author ruoyi
 */
public interface NjtAreaMapper
{
    /**
     * 根据父编码查询子地区集合
     *
     * @param parentCode 父编码（顶级传 "0"）
     * @return 地区集合
     */
    public List<NjtArea> selectByParentCode(String parentCode);

    /**
     * 通过主键查询地区信息
     *
     * @param id 主键
     * @return 地区对象信息
     */
    public NjtArea selectById(String id);

    /**
     * 新增地区信息
     *
     * @param area 地区对象
     * @return 结果
     */
    public int insertArea(NjtArea area);

    /**
     * 修改地区信息
     *
     * @param area 地区对象
     * @return 结果
     */
    public int updateArea(NjtArea area);

    /**
     * 删除地区信息
     *
     * @param id 主键
     * @return 结果
     */
    public int deleteById(String id);

    /**
     * 批量删除地区信息
     *
     * @param ids 主键数组
     * @return 结果
     */
    public int deleteByIds(String[] ids);
}