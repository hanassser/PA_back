package com.esgi.social.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esgi.social.mapper.CodePostMapper;
import com.esgi.social.mapper.UmsUserMapper;
import com.esgi.social.model.dto.CreateCodePostDTO;
import com.esgi.social.model.entity.*;
import com.esgi.social.model.vo.CodePostVO;
import com.esgi.social.model.vo.PostVO;
import com.esgi.social.model.vo.ProfileVO;
import com.esgi.social.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ICodePostServiceImpl extends ServiceImpl<CodePostMapper, CodePost> implements ICodePostService {

    @Resource
    private UmsUserMapper umsUserMapper;

    @Autowired
    private IUmsUserService iUmsUserService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CodePost create(CreateCodePostDTO dto, UmsUser user) {

        CodePost codePost = CodePost.builder()
                .userId(user.getId())
                .title(dto.getTitle())
                .code(dto.getCode())
                .description(dto.getDescription())
                .language(dto.getLanguage())
                .languageId(dto.getLanguageId())
                .wasReviewed(false)
                .originalPostId(dto.getOriginalPostId())
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

    @Override
    public Map<String, Object> viewTopic(String id) {
        Map<String, Object> map = new HashMap<>(16);
        CodePost codePost = this.baseMapper.selectById(id);
        Assert.notNull(codePost, "Le post n'existe pas");

        codePost.setView(codePost.getView() + 1);
        this.baseMapper.updateById(codePost);

        map.put("codepost", codePost);

        ProfileVO user = iUmsUserService.getUserProfile(codePost.getUserId());
        map.put("user", user);

        return map;
    }

    @Override
    public Page<CodePostVO> getList(Page<CodePostVO> page, String tab) {
        //        setTopicTags(iPage);
        return this.baseMapper.selectListAndPage(page, tab);
    }

//    private void setTopicTags(Page<PostVO> iPage) {
//        iPage.getRecords().forEach(topic -> {
//            List<TopicTag> topicTags = ITopicTagService.selectByTopicId(topic.getId());
//            if (!topicTags.isEmpty()) {
//                List<String> tagIds = topicTags.stream().map(TopicTag::getTagId).collect(Collectors.toList());
//                List<Tag> tags = tagMapper.selectBatchIds(tagIds);
//                topic.setTags(tags);
//            }
//        });
//    }


}
