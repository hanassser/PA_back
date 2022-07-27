package com.esgi.social.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esgi.social.mapper.BillboardMapper;
import com.esgi.social.model.entity.Billboard;
import com.esgi.social.service.IBillboardService;
import org.springframework.stereotype.Service;

@Service
public class IBillboardServiceImpl extends ServiceImpl<BillboardMapper
        , Billboard> implements IBillboardService {

}
