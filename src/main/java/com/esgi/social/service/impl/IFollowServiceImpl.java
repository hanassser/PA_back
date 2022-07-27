package com.esgi.social.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esgi.social.mapper.FollowMapper;
import com.esgi.social.model.entity.Follow;
import com.esgi.social.service.IFollowService;
import org.springframework.stereotype.Service;


@Service
public class IFollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements IFollowService {
}
