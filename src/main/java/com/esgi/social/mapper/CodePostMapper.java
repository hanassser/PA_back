package com.esgi.social.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esgi.social.model.entity.CodePost;
import com.esgi.social.model.vo.CodePostVO;
import com.esgi.social.model.vo.PostVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
     * @param page
     * @param tab
     * @return Page<CodePostVO>
     */
    Page<CodePostVO> selectListAndPage(Page<CodePostVO> page, String tab);
}
