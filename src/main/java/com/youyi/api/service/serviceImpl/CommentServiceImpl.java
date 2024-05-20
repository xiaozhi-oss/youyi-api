package com.youyi.api.service.serviceImpl;

import com.youyi.api.model.entity.Comment;
import com.youyi.api.mapper.CommentMapper;
import com.youyi.api.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YOUYI
 * @since 2023-12-13 09:01:07
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
