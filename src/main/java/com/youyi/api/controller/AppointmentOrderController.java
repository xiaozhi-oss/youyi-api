package com.youyi.api.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youyi.api.model.entity.AppointmentOrder;
import com.youyi.api.model.entity.ManicureProject;
import com.youyi.api.model.entity.Manicurist;
import com.youyi.api.model.vo.AppointmentOrderVO;
import com.youyi.api.service.AppointmentOrderService;
import com.youyi.api.service.ManicureProjectService;
import com.youyi.api.service.ManicuristService;
import com.youyi.api.utils.HostHolder;
import com.youyi.api.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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
@RequestMapping("/youyi/appointmentOrder")
public class AppointmentOrderController {

    @Resource
    private AppointmentOrderService appointmentOrderService;
    @Resource
    private ManicureProjectService manicureProjectService;
    @Resource
    private ManicuristService manicuristService;
    
    @Resource
    private HostHolder hostHolder;
    
    @PostMapping("/addOrUpdate")
    public ResponseResult<String> addOrUpdate(
            @RequestBody AppointmentOrder appointmentOrder) {
        appointmentOrder.setStatus(0);
        appointmentOrder.setUserId(hostHolder.getUser().getId());
        appointmentOrderService.saveOrUpdate(appointmentOrder);
        return ResponseResult.success();
    }

    @PostMapping("/updateStatus")
    public ResponseResult<String> updateStatus(
            @RequestBody AppointmentOrder appointmentOrder) {
        appointmentOrderService.updateById(appointmentOrder);
        return ResponseResult.success();
    }

    @PostMapping("/delete")
    public ResponseResult<String> delete(@RequestBody AppointmentOrder appointmentOrder) {
        appointmentOrderService.removeById(appointmentOrder.getId());
        return ResponseResult.success();
    }

    @GetMapping("/list")
    public ResponseResult<List<AppointmentOrderVO>> list(Integer current, Integer status) {
        QueryWrapper<AppointmentOrder> wrapper = new QueryWrapper<>();
        if (status != null) {
            wrapper.eq("status", status);
        }
        Page<AppointmentOrder> page = appointmentOrderService.page(new Page<>(current, 10), wrapper);
        List<AppointmentOrderVO> appointmentOrderVOList = new ArrayList<>();
        for (AppointmentOrder order : page.getRecords()) {
            AppointmentOrderVO appointmentOrderVO = BeanUtil.copyProperties(order, AppointmentOrderVO.class);
            Integer projectId = order.getProjectId();
            Integer mainicuristId = order.getMainicuristId();
            ManicureProject manicureProject = manicureProjectService.getById(projectId);
            Manicurist manicurist = manicuristService.getById(mainicuristId);
            appointmentOrderVO.setProjectName(manicureProject.getName());
            appointmentOrderVO.setMainicuristName(manicurist.getName());
            appointmentOrderVOList.add(appointmentOrderVO);
        }
        return ResponseResult.success(appointmentOrderVOList);
    }

    @GetMapping("/listById")
    public ResponseResult<List<AppointmentOrderVO>> listById(Integer current, Integer status) {
        QueryWrapper<AppointmentOrder> wrapper = new QueryWrapper<>();
        if (status != null) {
            wrapper.eq("status", status);
        }
        wrapper.eq("user_id", hostHolder.getUser().getId());
        Page<AppointmentOrder> page = appointmentOrderService.page(new Page<>(current, 10), wrapper);
        List<AppointmentOrderVO> appointmentOrderVOList = new ArrayList<>();
        for (AppointmentOrder order : page.getRecords()) {
            AppointmentOrderVO appointmentOrderVO = BeanUtil.copyProperties(order, AppointmentOrderVO.class);
            Integer projectId = order.getProjectId();
            Integer mainicuristId = order.getMainicuristId();
            ManicureProject manicureProject = manicureProjectService.getById(projectId);
            Manicurist manicurist = manicuristService.getById(mainicuristId);
            appointmentOrderVO.setProjectName(manicureProject.getName());
            appointmentOrderVO.setMainicuristName(manicurist.getName());
            appointmentOrderVOList.add(appointmentOrderVO);
        }
        return ResponseResult.success(appointmentOrderVOList);
    }
}

