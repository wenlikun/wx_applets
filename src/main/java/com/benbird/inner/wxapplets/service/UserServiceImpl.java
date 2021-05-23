package com.benbird.inner.wxapplets.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.benbird.inner.wxapplets.dao.TbDeptDao;
import com.benbird.inner.wxapplets.dao.TbUserDao;
import com.benbird.inner.wxapplets.exception.WxAppletsException;
import com.benbird.inner.wxapplets.pojo.TbUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 项目名: wx-applets
 * 创建者: Admin
 * 创建时间: 2021/5/17
 * 描述:
 *
 * @author Admin
 */
@Service
@Slf4j
@Scope("prototype")
public class UserServiceImpl implements UserService {
    @Value("${wx.app-id}")
    private String appId;

    @Value("${wx.app-secret}")
    private String appSecret;

    @Autowired
    private TbUserDao userDao;

    @Autowired
    private TbDeptDao deptDao;


    private String getOpenId(String code){
        String url="https://api.weixin.qq.com/sns/jscode2session";
        HashMap map=new HashMap();
        map.put("appid", appId);
        map.put("secret", appSecret);
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        String response=HttpUtil.post(url,map);
        JSONObject json=JSONUtil.parseObj(response);
        String openId=json.getStr("openid");
        if(openId==null||openId.length()==0){
            throw new RuntimeException("临时登陆凭证错误");
        }
        return openId;
    }

    @Override
    public int registerUser(String registerCode, String code, String nickname, String photo) {
        if(registerCode.equals("000000")){
            boolean bool=userDao.haveRootUser();
            if(!bool){
                String openId=getOpenId(code);
                HashMap param=new HashMap();
                param.put("openId", openId);
                param.put("nickname", nickname);
                param.put("photo", photo);
                param.put("role", "[0]");
                param.put("status", 1);
                param.put("createTime", new Date());
                param.put("root", true);
                userDao.insert(param);
                int id=userDao.searchIdByOpenId(openId);

                return id;
            }
            else{
                throw new WxAppletsException("无法绑定超级管理员账号");
            }
        }

        return 0;
    }

    @Override
    public Set<String> searchUserPermissions(int userId) {
        Set<String> permissions=userDao.searchUserPermissions(userId);
        return permissions;
    }

    @Override
    public Integer login(String code) {
        String openId=getOpenId(code);
        Integer id=userDao.searchIdByOpenId(openId);
        if(id==null){
            throw new WxAppletsException("帐户不存在");
        }

        return id;
    }

    @Override
    public TbUser searchById(int userId) {
        TbUser user=userDao.searchById(userId);

        return user;
    }

    @Override
    public String searchUserHiredate(int userId) {
        String hiredate=userDao.searchUserHiredate(userId);
        return hiredate;
    }

    @Override
    public HashMap searchUserSummary(int userId) {
        HashMap map=userDao.searchUserSummary(userId);
        return map;
    }

    @Override
    public ArrayList<HashMap> searchUserGroupByDept(String keyword) {

        return null;
    }

    @Override
    public ArrayList<HashMap> searchMembers(List param) {
        ArrayList<HashMap> list=userDao.searchMembers(param);
        return list;
    }

    @Override
    public List<HashMap> selectUserPhotoAndName(List param) {
        List<HashMap> list=userDao.selectUserPhotoAndName(param);
        return list;
    }

    @Override
    public String searchMemberEmail(int id) {
        String email=userDao.searchMemberEmail(id);
        return email;
    }

}
