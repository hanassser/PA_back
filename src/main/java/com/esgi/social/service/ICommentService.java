package com.esgi.social.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esgi.social.model.dto.CommentDTO;
import com.esgi.social.model.entity.Comment;
import com.esgi.social.model.entity.UmsUser;
import com.esgi.social.model.vo.CommentVO;

import java.util.List;


public interface ICommentService extends IService<Comment> {
    /**
     *
     *
     * @param topicid
     * @return {@link Comment}
     */
    List<CommentVO> getCommentsByTopicID(String topicid);

    Comment create(CommentDTO dto, UmsUser principal);
}
