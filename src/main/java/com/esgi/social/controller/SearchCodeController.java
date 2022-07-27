package com.esgi.social.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esgi.social.common.api.ApiResult;
import com.esgi.social.model.vo.CodePostVO;
import com.esgi.social.model.vo.PostVO;
import com.esgi.social.service.ICodePostService;
import com.esgi.social.service.IPostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/searchcode")
public class SearchCodeController extends BaseController {

    @Resource
    private ICodePostService codePostService;

    @GetMapping
    public ApiResult<Page<CodePostVO>> searchList(@RequestParam("keyword") String keyword,
                                              @RequestParam("pageNum") Integer pageNum,
                                              @RequestParam("pageSize") Integer pageSize) {
        Page<CodePostVO> results = codePostService.searchByKeyword(keyword, new Page<>(pageNum, pageSize));
        return ApiResult.success(results);
    }

}
