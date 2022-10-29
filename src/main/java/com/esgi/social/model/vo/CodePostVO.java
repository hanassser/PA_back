package com.esgi.social.model.vo;

import com.esgi.social.model.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodePostVO implements Serializable {
    private static final long serialVersionUID = -261082150965211545L;


    private String id;

    private String userId;

    private String originalPostId;

    private String language;

    private int languageId;

    private boolean wasReviewed;

    private String avatar;

    private String alias;

    private String username;

    private String title;

    private String description;

    private String code;

    private Integer comments;

    private Integer view;

    private Date createTime;

    private Date modifyTime;
}
