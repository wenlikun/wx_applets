package com.benbird.inner.wxapplets.wrapper;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.json.JSONUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 项目名: wx-applets
 * 创建者: Admin
 * 创建时间: 2021/5/15
 * 描述:
 *
 * @author Admin
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return StrUtil.hasEmpty(value) ? value : HtmlUtil.filter(value);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null) {
            return null;
        }
        for (int i = 0; i < values.length; i++) {
            String value = values[i];
            values[i] = StrUtil.hasEmpty(value) ? value : HtmlUtil.filter(value);
        }
        return values;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> parameters = super.getParameterMap();
        LinkedHashMap<String, String[]> map = new LinkedHashMap<>();
        if (parameters == null) {
            return null;
        }
        for (String key : parameters.keySet()) {
            String[] values = parameters.get(key);
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                values[i] = StrUtil.hasEmpty(value) ? value : HtmlUtil.filter(value);
            }
            map.put(key, values);
        }
        return map;
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return StrUtil.hasEmpty(value) ? value : HtmlUtil.filter(value);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        InputStream in = super.getInputStream();
        InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
        BufferedReader buffer = new BufferedReader(reader);
        StringBuilder body = new StringBuilder();
        String line = buffer.readLine();
        while (line != null) {
            body.append(line);
            line = buffer.readLine();
        }
        buffer.close();
        reader.close();
        in.close();
        Map<String, Object> map = JSONUtil.parseObj(body.toString());
        Map<String, Object> result = new LinkedHashMap<>();
        for (String key : map.keySet()) {
            Object val = map.get(key);
            if (val instanceof String) {
                if (!StrUtil.hasEmpty(val.toString())) {
                    result.put(key, HtmlUtil.filter(val.toString()));
                }
            } else {
                result.put(key, val);
            }
        }
        String json = JSONUtil.toJsonStr(result);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(json.getBytes());
        return new ServletInputStream() {
            @Override
            public int read() {
                return inputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }

}
