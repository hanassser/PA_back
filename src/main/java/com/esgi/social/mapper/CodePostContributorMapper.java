package com.esgi.social.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esgi.social.model.entity.CodePostContributor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface CodePostContributorMapper extends BaseMapper<CodePostContributor> {
    /**
     *
     * @param codePostid
     * @return
     */
    Set<String> getCodePostIdsByContributorId(@Param("id") String codePostid);

    /**
     *
     * @param contributorid
     * @return
     */
    Set<String> getContributorIdsByCodePostId(@Param("id") String contributorid);
}
