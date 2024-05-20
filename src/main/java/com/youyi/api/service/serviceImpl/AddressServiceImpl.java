package com.youyi.api.service.serviceImpl;

import com.youyi.api.model.entity.Address;
import com.youyi.api.mapper.AddressMapper;
import com.youyi.api.service.AddressService;
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
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

}
