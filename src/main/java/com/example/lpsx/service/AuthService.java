package com.example.lpsx.service;

/**
 * 认证服务 — 微信登录 & 手机号绑定
 */
public interface AuthService {

    /**
     * 微信登录
     *
     * @param code 微信临时 code
     * @return JWT Token
     */
    String wxLogin(String code);

    /**
     * 解密并绑定手机号
     *
     * @param encryptedData 加密数据
     * @param iv            初始向量
     * @param userId        当前用户 ID
     */
    void decryptPhone(String encryptedData, String iv, Integer userId);
}
