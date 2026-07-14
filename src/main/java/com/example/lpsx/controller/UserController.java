package com.example.lpsx.controller;

import com.example.lpsx.common.result.Result;
import com.example.lpsx.entity.CustomerLead;
import com.example.lpsx.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户接口 - 需 JWT 鉴权
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;
    @GetMapping("/lead-history")
    public Result<List<CustomerLead>> leadHistory(@RequestAttribute("userId") Integer userId) {
        return Result.success(userService.getLeadHistory(userId));
    }
}
