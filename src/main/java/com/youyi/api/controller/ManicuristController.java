package com.youyi.api.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youyi.api.model.entity.Manicurist;
import com.youyi.api.model.vo.ManicuristVO;
import com.youyi.api.service.ManicuristService;
import com.youyi.api.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
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
@RequestMapping("/youyi/manicurist")
public class ManicuristController {

    @Resource
    private ManicuristService manicuristService;

    @GetMapping("/list")
    public ResponseResult<List<ManicuristVO>> manicureList(Integer current) {
        QueryWrapper<Manicurist> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("good");
        List<Manicurist> manicuristList = manicuristService.list(wrapper);
        List<ManicuristVO> manicuristVOList = new ArrayList<>();
        manicuristList.forEach(m -> {
            ManicuristVO manicuristVO = BeanUtil.copyProperties(m, ManicuristVO.class);
            List<String> workList = Arrays.asList(m.getWorks().split("-"));
            manicuristVO.setWorkList(workList);
            manicuristVOList.add(manicuristVO);
        });
        return ResponseResult.success(manicuristVOList);
    }

    @PostMapping("/delete")
    public ResponseResult<String> delete(@RequestBody Manicurist Manicurist) {
        manicuristService.removeById(Manicurist);
        return ResponseResult.success();
    }

    @PostMapping("/addAndUpdate")
    public ResponseResult<String> addAndUpdate(@RequestBody Manicurist manicurist) {
        manicuristService.saveOrUpdate(manicurist);
        return ResponseResult.success();
    }
}

