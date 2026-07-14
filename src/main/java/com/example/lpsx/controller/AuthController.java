package com.example.lpsx.controller;

import com.example.lpsx.common.result.Result;
import com.example.lpsx.service.AuthService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 认证接口 �?微信登录 & 手机号绑�?
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Resource
    private AuthService authService;

    @PostMapping("/wx-login")
    public Result<Map<String, String>> wxLogin(@RequestBody Map<String, String> body) {
        String code = body.get("code");
        String token = authService.wxLogin(code);
        return Result.success(Map.of("token", token));
    }

    @PostMapping("/decrypt-phone")
    public Result<Void> decryptPhone(@RequestBody Map<String, String> body,
                                     @RequestAttribute("userId") Integer userId) {
        String encryptedData = body.get("encryptedData");
        String iv = body.get("iv");
        authService.decryptPhone(encryptedData, iv, userId);
        return Result.success();
    }
}
