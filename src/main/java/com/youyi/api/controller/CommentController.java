package com.youyi.api.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youyi.api.model.entity.Comment;
import com.youyi.api.model.entity.OrderProductInfo;
import com.youyi.api.model.entity.UserInfo;
import com.youyi.api.model.vo.CommentVO;
import com.youyi.api.service.CommentService;
import com.youyi.api.service.OrderProductInfoService;
import com.youyi.api.utils.HostHolder;
import com.youyi.api.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
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
@RequestMapping("/youyi/comment")
public class CommentController {

    @Resource
    private CommentService commentService;
    
    @Resource
    private OrderProductInfoService orderProductInfoService;
    
    @Resource
    private HostHolder hostHolder;
    
    @PostMapping("/addOrUpdate")
    public ResponseResult<String> addOrUpdate(@RequestBody Comment comment) {
        UserInfo user = hostHolder.getUser();
        comment.setUsername(user.getUsername());
        comment.setAvgUrl(user.getUrl());
        comment.setUserId(user.getId());
        comment.setTime(new Date());
        commentService.saveOrUpdate(comment);
        OrderProductInfo info = new OrderProductInfo();
        info.setId(comment.getProductId());
        info.setReviewStatus(1);
        orderProductInfoService.updateById(info);
        return ResponseResult.success();
    }

    @PostMapping("/delete")
    public ResponseResult<String> delete(@RequestBody Integer id) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", hostHolder.getUser().getId());
        return ResponseResult.success();
    }

    @GetMapping("/allList")
    public ResponseResult<List<CommentVO>> allList(Integer current) {
        Page<Comment> page = commentService.page(new Page<>(current, 10));
        List<Comment> commentList = page.getRecords();
        List<CommentVO> commentVOList = new ArrayList<>();
        commentList.forEach(c -> {
            CommentVO commentVO = BeanUtil.copyProperties(c, CommentVO.class);
            List<String> list = Arrays.asList(c.getImgIds().split("-"));
            commentVO.setImgList(list);
            commentVOList.add(commentVO);
        });
        return ResponseResult.success(commentVOList);
    }

    @GetMapping("/listById")
    public ResponseResult<List<CommentVO>> listByUser(Integer current, Integer productId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id", productId);
        Page<Comment> page = commentService.page(new Page<>(current, 10), wrapper);
        List<Comment> commentList = page.getRecords();
        List<CommentVO> commentVOList = new ArrayList<>();
        commentList.forEach(c -> {
            CommentVO commentVO = BeanUtil.copyProperties(c, CommentVO.class);
            List<String> list = Arrays.asList(c.getImgIds().split("-"));
            commentVO.setImgList(list);
            commentVOList.add(commentVO);
        });
        return ResponseResult.success(commentVOList);
    }
}

