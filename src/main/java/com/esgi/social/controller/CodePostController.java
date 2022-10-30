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
import com.vdurmont.emoji.EmojiParser;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
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

    @GetMapping("/listog4user")
    public ApiResult<Page<CodePostVO>> getAllOriginalByUserName(@RequestHeader(value = JwtUtil.USER_NAME) String userName,
                                                    @RequestParam(value = "tab", defaultValue = "latest") String tab,
                                                    @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                                    @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        Page<CodePostVO> list = iCodePostService.getAllOriginalByUserName(new Page<>(pageNo, pageSize),user);
        return ApiResult.success(list);
    }

    @GetMapping("/listfriendscp")
    public ApiResult<Page<CodePostVO>> getAllFriendsCodePost(@RequestHeader(value = JwtUtil.USER_NAME) String userName,
                                                                @RequestParam(value = "tab", defaultValue = "latest") String tab,
                                                                @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                                                @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        Page<CodePostVO> list = iCodePostService.getAllFriendsCodePost(new Page<>(pageNo, pageSize),user);
        return ApiResult.success(list);
    }

    @GetMapping("/listcontrib4user")
    public ApiResult<Page<CodePostVO>> getAllContributionByUserName(@RequestHeader(value = JwtUtil.USER_NAME) String userName,
                                                                @RequestParam(value = "tab", defaultValue = "latest") String tab,
                                                                @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                                                @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        Page<CodePostVO> list = iCodePostService.getAllContributionByUserName(new Page<>(pageNo, pageSize),user);
        return ApiResult.success(list);
    }

    @GetMapping("/listbefore")
    public ApiResult<List<CodePost>> getCodePostChainBefore(@RequestParam(value = "codePostId") String codePostId) {
        List<CodePost> list = iCodePostService.getCodePostChainBefore(codePostId);
        return ApiResult.success(list);
    }


    @GetMapping("/listafter")
    public ApiResult<List<CodePost>> getCodePostChainAfter(@RequestParam(value = "codePostId") String codePostId) {
        List<CodePost> list = iCodePostService.getCodePostChainAfter(codePostId);
        return ApiResult.success(list);
    }

    @GetMapping("/follows")
    public ApiResult<List<String>> selectFollowList(@RequestParam(value = "userId") String userId) {
        List<String> list = iCodePostService.selectFollowList(userId);
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

    @PostMapping("/update")
    public ApiResult<CodePost> update(@RequestHeader(value = JwtUtil.USER_NAME) String userName, @Valid @RequestBody CodePost codepost) {
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        Assert.isTrue(umsUser.getId().equals(codepost.getUserId()), "user cant be modified");
        codepost.setModifyTime(new Date());
        codepost.setWasReviewed(true);
        System.out.println(codepost.toString());
        System.out.println(iCodePostService.updateById(codepost));
        return ApiResult.success(codepost);
    }

}
