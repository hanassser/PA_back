package com.esgi.social.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esgi.social.mapper.TipMapper;
import com.esgi.social.model.entity.Tip;
import com.esgi.social.service.ITipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ITipServiceImpl extends ServiceImpl<TipMapper
        , Tip> implements ITipService {

    @Override
    public Tip getRandomTip() {
        Tip todayTip = null;
        try {
            todayTip = this.baseMapper.getRandomTip();
        } catch (Exception e) {
            log.info("tip转化失败");
        }
        return todayTip;
    }
}
