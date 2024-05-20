package com.youyi.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youyi.api.model.entity.ManicureProject;
import com.youyi.api.service.ManicureProjectService;
import com.youyi.api.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YOUYI
 * @since 2023-12-13 09:01:07
 */
@RestController
@RequestMapping("/youyi/manicureProject")
public class ManicureProjectController {


    @Resource
    private ManicureProjectService manicureProjectService;

    @GetMapping("/list")
    public ResponseResult<List<ManicureProject>> manicureList(Integer current) {
        QueryWrapper<ManicureProject> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        Page<ManicureProject> page = manicureProjectService.page(new Page<>(current, 10), wrapper);
        List<ManicureProject> records = page.getRecords();
        return ResponseResult.success(records);
    }

    @PostMapping("/delete")
    public ResponseResult<String> delete(@RequestBody ManicureProject manicureProject) {
        manicureProjectService.removeById(manicureProject);
        return ResponseResult.success();
    }

    @PostMapping("/addAndUpdate")
    public ResponseResult<String> addAndUpdate(@RequestBody ManicureProject manicureProject) {
        manicureProject.setCreateTime(new Date());
        manicureProjectService.saveOrUpdate(manicureProject);
        return ResponseResult.success();
    }
}

