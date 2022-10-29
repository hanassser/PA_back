package com.esgi.social.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@TableName("bms_codepost")
@AllArgsConstructor
@NoArgsConstructor
public class CodePost implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @NotBlank(message = "Title can not be empty")
    @TableField(value = "title")
    private String title;

    @NotBlank(message = "Code can not be empty")
    @TableField("code")
    private String code;

    @NotBlank(message = "Language can not be empty")
    @TableField("language")
    private String language;

//    @NotBlank(message = "Language can not be empty")
    @TableField("language_id")
    private int languageId;

    @NotBlank(message = "author_id can not be empty")
    @TableField("user_id")
    private String userId;

    @NotBlank(message = "Description can not be empty")
    @TableField("description")
    private String description;

    @TableField("original_post_id")
    private String originalPostId;

    @TableField("was_reviewed")
    private boolean wasReviewed;

    @TableField("comments")
    @Builder.Default
    private Integer comments = 0;

//    @TableField("collects")
//    @Builder.Default
//    private Integer collects = 0;

    @TableField("view")
    @Builder.Default
    private Integer view = 0;


    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "modify_time", fill = FieldFill.UPDATE)
    private Date modifyTime;
}
