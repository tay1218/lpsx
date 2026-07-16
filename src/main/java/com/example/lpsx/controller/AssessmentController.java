package com.example.lpsx.controller;

import com.example.lpsx.common.result.Result;
import com.example.lpsx.entity.School;
import com.example.lpsx.service.AssessmentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 快速择校匹配接�?
 */
@RestController
@RequestMapping("/api/assessment")
public class AssessmentController {

    @Resource
    private AssessmentService assessmentService;

    @PostMapping("/match")
    public Result<List<School>> match(@RequestBody Map<String, List<String>> body) {
        return Result.success(assessmentService.match(body.get("answers")));
    }
}
