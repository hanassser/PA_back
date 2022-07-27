package com.esgi.social.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esgi.social.model.entity.Tip;
import org.springframework.stereotype.Repository;

@Repository
public interface TipMapper extends BaseMapper<Tip> {
    Tip getRandomTip();
}
