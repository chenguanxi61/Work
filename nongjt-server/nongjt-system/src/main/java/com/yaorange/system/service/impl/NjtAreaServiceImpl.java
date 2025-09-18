package com.yaorange.system.service.impl;

import com.yaorange.system.domain.NjtArea;
import com.yaorange.system.mapper.NjtAreaMapper;
import com.yaorange.system.service.INjtAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NjtAreaServiceImpl implements INjtAreaService {

    @Autowired
    private NjtAreaMapper njtAreaMapper;

    @Override
    public List<NjtArea> selectByParentCode(String parentCode) {
        return njtAreaMapper.selectByParentCode(parentCode);
    }

    @Override
    public NjtArea selectById(String id) {
        return njtAreaMapper.selectById(id);
    }

    @Override
    public int insertArea(NjtArea area) {
        return njtAreaMapper.insertArea(area);
    }

    @Override
    public int updateArea(NjtArea area) {
        return njtAreaMapper.updateArea(area);
    }

    @Override
    public int deleteById(String id) {
        return njtAreaMapper.deleteById(id);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return njtAreaMapper.deleteByIds(ids);
    }
}