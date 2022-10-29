package com.esgi.social.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esgi.social.common.api.ApiResult;
import com.esgi.social.jwt.JwtUtil;
import com.esgi.social.model.dto.CreateCodePostDTO;
import com.esgi.social.model.dto.CreateTopicDTO;
import com.esgi.social.model.entity.CodePost;
import com.esgi.social.model.entity.Post;
import com.esgi.social.model.entity.UmsUser;
import com.esgi.social.model.vo.CodePostVO;
import com.esgi.social.model.vo.PostVO;
import com.esgi.social.service.ICodePostService;
import com.esgi.social.service.IUmsUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


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

    @GetMapping("/list4user")
    public ApiResult<Page<CodePostVO>> getAllByUser(@RequestHeader(value = JwtUtil.USER_NAME) String userName,
                                            @RequestParam(value = "tab", defaultValue = "latest") String tab,
                                            @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                            @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        Page<CodePostVO> list = iCodePostService.getAllByUser(new Page<>(pageNo, pageSize),user);
        return ApiResult.success(list);
    }

    @RequestMapping(value = "/contribute", method = RequestMethod.POST)
    public ApiResult<CodePost> contribute(@RequestHeader(value = JwtUtil.USER_NAME) String userName
            , @RequestBody CreateCodePostDTO dto) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        CodePost codePost = iCodePostService.create(dto, user);
        return ApiResult.success(codePost);
    }

    @GetMapping()
    public ApiResult<Map<String, Object>> view(@RequestParam("id") String id) {
        Map<String, Object> map = iCodePostService.viewTopic(id);
        return ApiResult.success(map);
    }

    @GetMapping("/list")
    public ApiResult<Page<CodePostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab,
                                        @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<CodePostVO> list = iCodePostService.getList(new Page<>(pageNo, pageSize), tab);
        return ApiResult.success(list);
    }

}
