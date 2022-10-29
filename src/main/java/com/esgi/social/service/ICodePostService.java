package com.esgi.social.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esgi.social.model.dto.CreateCodePostDTO;
import com.esgi.social.model.entity.CodePost;
import com.esgi.social.model.entity.UmsUser;
import com.esgi.social.model.vo.CodePostVO;

import java.util.List;
import java.util.Map;


public interface ICodePostService extends IService<CodePost> {


    /**

     *
     * @param dto
     * @param principal
     * @return CodePost
     */
    CodePost create(CreateCodePostDTO dto, UmsUser principal);

    /**

     *
     * @param principal
     * @return CodePost
     */
    Page<CodePostVO> getAllByUser(Page<CodePostVO> page, UmsUser principal);

    /**
     *
     * @param keyword
     * @param page
     * @return
     */
    Page<CodePostVO> searchByKeyword(String keyword, Page<CodePostVO> page);

    Map<String, Object> viewTopic(String id);
}
