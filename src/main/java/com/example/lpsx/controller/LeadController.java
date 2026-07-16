package com.example.lpsx.controller;

import com.example.lpsx.common.result.Result;
import com.example.lpsx.service.LeadService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 留资接口 �?需 JWT 鉴权
 */
@RestController
@RequestMapping("/api/lead")
public class LeadController {

    @Resource
    private LeadService leadService;

    @PostMapping("/submit")
    public Result<Void> submit(@RequestBody Map<String, String> body,
                               @RequestAttribute("userId") Integer userId) {
        String parentName = body.get("parent_name");
        String childGrade = body.get("child_grade");
        String targetSchool = body.get("target_school");
        String parentPhone = body.get("parent_phone");
        leadService.submit(userId, parentName, childGrade, targetSchool, parentPhone);
        return Result.success();
    }
}
