package com.example.lpsx.controller;

import com.example.lpsx.common.result.Result;
import com.example.lpsx.service.FavoriteService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 收藏接口 - 需 JWT 鉴权
 */
@RestController
@RequestMapping("/api/favorite")
public class FavoriteController {

    @Resource
    private FavoriteService favoriteService;
    @PostMapping("/toggle")
    public Result<Void> toggle(@RequestBody Map<String, Integer> body,
                               @RequestAttribute("userId") Integer userId) {
        Integer schoolId = body.get("school_id");
        favoriteService.toggle(userId, schoolId);
        return Result.success();
    }
    @GetMapping("/list")
    public Result<List<Map<String, Object>>> list(@RequestAttribute("userId") Integer userId) {
        return Result.success(favoriteService.list(userId));
    }
}
