package com.esgi.social.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esgi.social.model.entity.CodePost;
import com.esgi.social.model.vo.CodePostVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodePostMapper extends BaseMapper<CodePost> {

    /**
     *
     * @param page
     * @param keyword
     * @return
     */
    Page<CodePostVO> searchByKeyword(@Param("page") Page<CodePostVO> page, @Param("keyword") String keyword);


    /**
     *
     * @param username
     * @return Page<CodePostVO>
     */
    Page<CodePostVO> getAllByUserName(@Param("page") Page<CodePostVO> page, String username);

    /**
     *
     * @param username
     * @return Page<CodePostVO>
     */
    Page<CodePostVO> getAllOriginalByUserName(@Param("page") Page<CodePostVO> page, String username);

    /**
     *
     * @param username
     * @return Page<CodePostVO>
     */
    Page<CodePostVO> getAllContributionByUserName(@Param("page") Page<CodePostVO> page, String username);

    /**
     *
     * @param userId
     * @return Page<CodePostVO>
     */
    Page<CodePostVO> selectFriendsCodePost(@Param("page") Page<CodePostVO> page, String userId);

    /**
     *
     * @param page
     * @param tab
     * @return Page<CodePostVO>
     */
    Page<CodePostVO> selectListAndPage(Page<CodePostVO> page, String tab);

    List<CodePost> selectCodePostAfter(String codePostId);
}
