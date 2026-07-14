package com.example.lpsx.utils;

import cn.hutool.crypto.symmetric.AES;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 微信手机号解密工具
 * <p>
 * 使用 sessionKey 对 encryptedData 做 AES-128-CBC 解密，提取手机号
 */
@Slf4j
public class WxDecryptUtils {

    /**
     * 解密微信手机号
     *
     * @param encryptedData 加密数据
     * @param iv            初始向量
     * @param sessionKey    会话密钥
     * @return 解密后的 JSON，包含 purePhoneNumber 等字段
     */
    public static String decryptPhone(String encryptedData, String iv, String sessionKey) {
        try {
            byte[] sessionKeyBytes = Base64.getDecoder().decode(sessionKey);
            byte[] ivBytes = Base64.getDecoder().decode(iv);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);

            AES aes = new AES("CBC", "PKCS7Padding", sessionKeyBytes, ivBytes);

            byte[] decrypted = aes.decrypt(encryptedBytes);
            String json = new String(decrypted, StandardCharsets.UTF_8);
            log.info("手机号解密成功: {}", json);
            return json;
        } catch (Exception e) {
            log.error("手机号解密失败", e);
            throw new RuntimeException("手机号解密失败", e);
        }
    }

    /**
     * 从解密结果 JSON 中提取纯手机号
     */
    public static String extractPurePhone(String decryptedJson) {
        JSONObject obj = JSONUtil.parseObj(decryptedJson);
        return obj.getStr("purePhoneNumber");
    }
}
