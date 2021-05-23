package com.benbird.inner.wxapplets.face.compare;

import cn.hutool.core.codec.Base64Encoder;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
// 导入对应产品模块的client
import com.tencentcloudapi.cvm.v20170312.CvmClient;
// 导入要请求接口对应的request response类
import com.tencentcloudapi.cvm.v20170312.models.DescribeInstancesRequest;
import com.tencentcloudapi.cvm.v20170312.models.DescribeInstancesResponse;
import com.tencentcloudapi.cvm.v20170312.models.Filter;
//导入可选配置类
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.profile.Language;
import com.tencentcloudapi.iai.v20200303.IaiClient;
import com.tencentcloudapi.iai.v20200303.models.CompareFaceRequest;
import com.tencentcloudapi.iai.v20200303.models.CompareFaceResponse;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 项目名: wx-applets
 * 创建者: Admin
 * 创建时间: 2021/5/19
 * 描述:
 *
 * @author Admin
 */
public class TXFaceCompareTest {

    private static String secretId = "AKID85mCMGuHNDm44oHUP3SaQ0KN9eroTryq";
    private static String secretKey = "cx1oKZWCcl9G7l9H9k47tPdaGgphIcfR";

    public static void main(String[] args) {
        hardExample();
    }

    private static void simpleExample(){
        try {
            Credential cred = new Credential(secretId, secretKey);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("iai.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            IaiClient client = new IaiClient(cred, "ap-shanghai", clientProfile);
            CompareFaceRequest req = new CompareFaceRequest();
            req.setImageA(base64(new File("I:\\照片\\a.jpg")));
            req.setImageB(base64(new File("I:\\照片\\微信图片_20200720215106.jpg")));
            req.setNeedRotateDetection(1L);
            CompareFaceResponse resp = client.CompareFace(req);
            System.out.println(CompareFaceResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }


    }


    private static void hardExample(){
        try {
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            Credential cred = new Credential(secretId, secretKey);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            //  从3.1.16版本开始, 单独设置 HTTP 代理
            // httpProfile.setProxyHost("真实代理ip");
            // httpProfile.setProxyPort(真实代理端口);
            //httpProfile.setReqMethod("GET"); // get请求(默认为post请求)
            httpProfile.setProtocol("https://");  // 在外网互通的网络环境下支持http协议(默认是https协议),请选择(https:// or http://)
            httpProfile.setConnTimeout(30); // 请求连接超时时间，单位为秒(默认60秒)
            httpProfile.setWriteTimeout(30);  // 设置写入超时时间，单位为秒(默认0秒)
            httpProfile.setReadTimeout(30);  // 设置读取超时时间，单位为秒(默认0秒)
            httpProfile.setEndpoint("iai.tencentcloudapi.com"); // 指定接入地域域名(默认就近接入)
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256"); // 指定签名算法(默认为HmacSHA256)
            // 自3.1.80版本开始，SDK 支持打印日志。
            clientProfile.setHttpProfile(httpProfile);
            clientProfile.setDebug(true);
            // 从3.1.16版本开始，支持设置公共参数 Language, 默认不传，选择(ZH_CN or EN_US)
            clientProfile.setLanguage(Language.EN_US);
            // 实例化要请求产品(以cvm为例)的client对象,clientProfile是可选的
            IaiClient client = new IaiClient(cred, "ap-shanghai", clientProfile);
            // 实例化一个cvm实例信息查询请求对象,每个接口都会对应一个request对象。
            CompareFaceRequest req = new CompareFaceRequest();
            // 填充请求参数,这里request对象的成员变量即对应接口的入参
            // 你可以通过官网接口文档或跳转到request对象的定义处查看请求参数的定义
            req.setImageA(base64(new File("I:\\照片\\b.jpg")));
            req.setImageB(base64(new File("I:\\照片\\d.jpg")));
            CompareFaceResponse resp = client.CompareFace(req);
            // 输出json格式的字符串回包
            System.out.println(CompareFaceResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
    }

    private static String base64(File file){
        InputStream inputStream;
        byte[] data = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            data = new byte[inputStream.available()];
            inputStream.read(data);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Base64Encoder.encode(data);
    }


}
