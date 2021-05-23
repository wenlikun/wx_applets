package com.benbird.inner.wxapplets.dao;

import com.benbird.inner.wxapplets.pojo.TbRole;

public interface TbRoleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TbRole record);

    int insertSelective(TbRole record);

    TbRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbRole record);

    int updateByPrimaryKey(TbRole record);
}