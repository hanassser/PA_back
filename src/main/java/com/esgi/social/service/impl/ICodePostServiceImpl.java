package com.esgi.social.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esgi.social.mapper.CodePostMapper;
import com.esgi.social.mapper.UmsUserMapper;
import com.esgi.social.model.dto.CreateCodePostDTO;
import com.esgi.social.model.entity.*;
import com.esgi.social.model.vo.CodePostVO;
import com.esgi.social.model.vo.ProfileVO;
import com.esgi.social.service.*;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;


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
        // 查询话题详情
        codePost.setView(codePost.getView() + 1);
        this.baseMapper.updateById(codePost);
        // emoji转码
//        topic.setContent(EmojiParser.parseToUnicode(topic.getContent()));
        map.put("codepost", codePost);
        // 标签
//        QueryWrapper<TopicTag> wrapper = new QueryWrapper<>();
//        wrapper.lambda().eq(TopicTag::getTopicId, topic.getId());
//        Set<String> set = new HashSet<>();
//        for (TopicTag articleTag : ITopicTagService.list(wrapper)) {
//            set.add(articleTag.getTagId());
//        }
//        List<Tag> tags = iTagService.listByIds(set);
//        map.put("tags", tags);

        // 作者

        ProfileVO user = iUmsUserService.getUserProfile(codePost.getUserId());
        map.put("user", user);

        return map;
    }


}
