package com.esgi.social.controller;

import com.esgi.social.common.api.ApiResult;
import com.esgi.social.model.entity.Tip;
import com.esgi.social.service.ITipService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/tip")
public class TipController extends BaseController {
    @Resource
    private ITipService bmsTipService;

    @GetMapping("/today")
    public ApiResult<Tip> getRandomTip() {
        Tip tip = bmsTipService.getRandomTip();
        return ApiResult.success(tip);
    }
}
