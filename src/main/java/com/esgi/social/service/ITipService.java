package com.esgi.social.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esgi.social.model.entity.Tip;

public interface ITipService extends IService<Tip> {
    Tip getRandomTip();
}
