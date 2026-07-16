package com.example.lpsx.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lpsx.common.result.Result;
import com.example.lpsx.entity.School;
import com.example.lpsx.service.SchoolService;

import jakarta.annotation.Resource;

/**
 * 学校接口
 */
@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    @Resource
    private SchoolService schoolService;
    @GetMapping("/list")
    public Result<?> list(@RequestParam(defaultValue = "1") int page,
                          @RequestParam(defaultValue = "10") int size,
                          @RequestParam(required = false) String city,
                          @RequestParam(required = false) String curriculum,
                          @RequestParam(required = false) String district,
                          @RequestParam(required = false) String grades,
                          @RequestParam(required = false) String name) {
        return Result.success(schoolService.listSchools(page, size, city, curriculum, district, grades, name));
    }
    @GetMapping("/{id}")
    public Result<School> detail(@PathVariable int id) {
        return Result.success(schoolService.getDetail(id));
    }
    @PostMapping("/compare")
    public Result<List<School>> compare(@RequestBody Map<String, List<Integer>> body) {
        return Result.success(schoolService.compare(body.get("schoolIds")));
    }
}
