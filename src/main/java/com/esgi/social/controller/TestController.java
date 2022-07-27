package com.esgi.social.controller;

import com.esgi.social.common.api.ApiResult;
import com.esgi.social.model.entity.Tip;
import com.esgi.social.service.ITipService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @GetMapping("/")
    public ApiResult<Tip> getRandomTip() {
        System.out.println("aa");
        return ApiResult.success(null);
    }
}
