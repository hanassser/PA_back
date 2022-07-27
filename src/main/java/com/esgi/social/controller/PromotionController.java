package com.esgi.social.controller;

import com.esgi.social.common.api.ApiResult;
import com.esgi.social.model.entity.Promotion;
import com.esgi.social.service.IPromotionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/promotion")
public class PromotionController extends BaseController {

    @Resource
    private IPromotionService bmsPromotionService;

    @GetMapping("/all")
    public ApiResult<List<Promotion>> list() {
        List<Promotion> list = bmsPromotionService.list();
        return ApiResult.success(list);
    }

}
