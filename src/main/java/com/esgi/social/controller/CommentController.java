package com.esgi.social.controller;

import com.esgi.social.common.api.ApiResult;
import com.esgi.social.model.dto.CommentDTO;
import com.esgi.social.model.entity.Comment;
import com.esgi.social.model.entity.UmsUser;
import com.esgi.social.model.vo.CommentVO;
import com.esgi.social.service.ICommentService;
import com.esgi.social.service.IUmsUserService;
import com.esgi.social.jwt.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController {

    @Resource
    private ICommentService bmsCommentService;
    @Resource
    private IUmsUserService umsUserService;

    @GetMapping("/get_comments")
    public ApiResult<List<CommentVO>> getCommentsByTopicID(@RequestParam(value = "topicid", defaultValue = "1") String topicid) {
        List<CommentVO> lstBmsComment = bmsCommentService.getCommentsByTopicID(topicid);
        return ApiResult.success(lstBmsComment);
    }
    @PostMapping("/add_comment")
    public ApiResult<Comment> add_comment(@RequestHeader(value = JwtUtil.USER_NAME) String userName,
                                          @RequestBody CommentDTO dto) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        Comment comment = bmsCommentService.create(dto, user);
        return ApiResult.success(comment);
    }
}
