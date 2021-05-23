package com.benbird.inner.wxapplets.dao;

import com.benbird.inner.wxapplets.pojo.TbMeeting;

public interface TbMeetingDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TbMeeting record);

    int insertSelective(TbMeeting record);

    TbMeeting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbMeeting record);

    int updateByPrimaryKey(TbMeeting record);
}