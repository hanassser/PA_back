package com.esgi.social.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esgi.social.mapper.CodePostMapper;
import com.esgi.social.mapper.UmsUserMapper;
import com.esgi.social.model.dto.CreateCodePostDTO;
import com.esgi.social.model.entity.*;
import com.esgi.social.model.vo.CodePostVO;
import com.esgi.social.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


@Service
public class ICodePostServiceImpl extends ServiceImpl<CodePostMapper, CodePost> implements ICodePostService {

    @Resource
    private UmsUserMapper umsUserMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public CodePost create(CreateCodePostDTO dto, UmsUser user) {

        CodePost codePost = CodePost.builder()
                .userId(user.getId())
                .title(dto.getTitle())
                .code(dto.getCode())
                .description(dto.getDescription())
                .language(dto.getLanguage())
                .wasReviewed(false)
                .createTime(new Date())
                .build();
        this.baseMapper.insert(codePost);

        int newScore = user.getScore() + 1;
        umsUserMapper.updateById(user.setScore(newScore));


        return codePost;
    }

    @Override
    public Page<CodePostVO> getAllByUser(Page<CodePostVO> page, UmsUser principal){
        return this.baseMapper.getAllByUserName(page, principal.getUsername());
    }


    @Override
    public Page<CodePostVO> searchByKeyword(String keyword, Page<CodePostVO> page) {

        return this.baseMapper.searchByKeyword(page, keyword);
    }


}
