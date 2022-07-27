package com.esgi.social.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esgi.social.mapper.PromotionMapper;
import com.esgi.social.model.entity.Promotion;
import com.esgi.social.service.IPromotionService;
import org.springframework.stereotype.Service;


@Service
public class IPromotionServiceImpl extends ServiceImpl<PromotionMapper, Promotion> implements IPromotionService {

}
