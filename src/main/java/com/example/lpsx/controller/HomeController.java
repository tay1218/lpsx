package com.example.lpsx.controller;

import com.example.lpsx.common.result.Result;
import com.example.lpsx.service.HomeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 首页聚合接口
 */
@RestController
@RequestMapping("/api/home")
public class HomeController {

    @Resource
    private HomeService homeService;
    @GetMapping("/overview")
    public Result<Map<String, Object>> overview() {
        return Result.success(homeService.overview());
    }
}
