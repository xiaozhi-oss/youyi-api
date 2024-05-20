package com.youyi.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youyi.api.model.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YOUYI
 * @since 2023-12-15 12:13:16
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    
    List<Message> getAllMessageList();
}
