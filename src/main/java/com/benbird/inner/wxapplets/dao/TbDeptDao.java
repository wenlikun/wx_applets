package com.benbird.inner.wxapplets.dao;

import com.benbird.inner.wxapplets.pojo.TbDept;

public interface TbDeptDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TbDept record);

    int insertSelective(TbDept record);

    TbDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbDept record);

    int updateByPrimaryKey(TbDept record);
}