package com.example.lpsx.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.lpsx.common.exception.BusinessException;
import com.example.lpsx.entity.User;
import com.example.lpsx.mapper.UserMapper;
import com.example.lpsx.service.AuthService;
import com.example.lpsx.utils.JwtUtils;
import com.example.lpsx.utils.WxDecryptUtils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * 认证服务实现
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private JwtUtils jwtUtils;

    @Value("${wechat.appid}")
    private String appId;

    @Value("${wechat.secret}")
    private String appSecret;

    /** 服务端缓存 sessionKey，key=userId, value=sessionKey */
    private final Map<Integer, String> sessionKeyCache = new ConcurrentHashMap<>();

    @Override
    public String wxLogin(String code) {
        try {
        // 1. 调用微信 jscode2session 接口
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, Object> params = new HashMap<>();
        params.put("appid", appId);
        params.put("secret", appSecret);
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");

        String response = HttpUtil.get(url, params);
        JSONObject result = JSONUtil.parseObj(response);
        log.info("微信登录响应: {}", response);

        // 2. 检查微信返回结果
        if (result.containsKey("errcode") && result.getInt("errcode") != 0) {
            throw new BusinessException("微信登录失败: " + result.getStr("errmsg"));
        }

        String openid = result.getStr("openid");
        String sessionKey = result.getStr("session_key");

        // 3. 查库，若无则注册
        User user = userMapper.selectOne(
                new LambdaQueryWrapper<User>().eq(User::getOpenid, openid));
        if (user == null) {
            user = new User();
            user.setOpenid(openid);
            user.setNickname("微信用户" + ThreadLocalRandom.current().nextInt(1000, 10000));
            userMapper.insert(user);
            log.info("新用户注册: userId={}, openid={}", user.getId(), openid);
        }

        // 4. 缓存 sessionKey（用于后续手机号解密）
        sessionKeyCache.put(user.getId(), sessionKey);

        // 5. 生成 JWT
        return jwtUtils.createToken(user.getId());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("微信登录异常", e);
            throw new BusinessException("登录失败: " + e.getMessage());
        }
    }

    @Override
    public void decryptPhone(String encryptedData, String iv, Integer userId) {
        // 从缓存取 sessionKey
        String sessionKey = sessionKeyCache.get(userId);
        if (sessionKey == null) {
            throw new RuntimeException("sessionKey 不存在，请重新登录");
        }

        // 解密获取手机号
        String decryptedJson = WxDecryptUtils.decryptPhone(encryptedData, iv, sessionKey);
        String phone = WxDecryptUtils.extractPurePhone(decryptedJson);

        // 更新 user 表
        User user = userMapper.selectById(userId);
        if (user != null) {
            user.setPhone(phone);
            userMapper.updateById(user);
        }
    }
}
