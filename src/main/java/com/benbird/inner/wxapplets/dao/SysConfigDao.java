package com.benbird.inner.wxapplets.dao;

import com.benbird.inner.wxapplets.pojo.SysConfig;

public interface SysConfigDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    SysConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);
}