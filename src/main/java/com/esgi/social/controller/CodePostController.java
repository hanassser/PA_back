package com.esgi.social.controller;

import com.esgi.social.common.api.ApiResult;
import com.esgi.social.jwt.JwtUtil;
import com.esgi.social.model.dto.CreateCodePostDTO;
import com.esgi.social.model.entity.CodePost;
import com.esgi.social.model.entity.UmsUser;
import com.esgi.social.service.ICodePostService;
import com.esgi.social.service.IUmsUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/codepost")
public class CodePostController extends BaseController {

    @Resource
    private ICodePostService iCodePostService;
    @Resource
    private IUmsUserService umsUserService;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ApiResult<CodePost> create(@RequestHeader(value = JwtUtil.USER_NAME) String userName
            , @RequestBody CreateCodePostDTO dto) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        CodePost codePost = iCodePostService.create(dto, user);
        return ApiResult.success(codePost);
    }

}
