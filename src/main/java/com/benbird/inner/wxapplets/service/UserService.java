package com.benbird.inner.wxapplets.service;


import com.benbird.inner.wxapplets.pojo.TbUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 项目名: wx-applets
 * 创建者: Admin
 * 创建时间: 2021/5/17
 * 描述:
 *
 * @author Admin
 */
public interface UserService {

    int registerUser(String registerCode, String code, String nickname, String photo);

    Set<String> searchUserPermissions(int userId);

    Integer login(String code);

    TbUser searchById(int userId);

    String searchUserHiredate(int userId);

    HashMap searchUserSummary(int userId);

    ArrayList<HashMap> searchUserGroupByDept(String keyword);

    ArrayList<HashMap> searchMembers(List param);

    List<HashMap> selectUserPhotoAndName(List param);

    String searchMemberEmail(int id);
}
