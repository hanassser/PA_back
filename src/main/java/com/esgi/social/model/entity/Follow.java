package com.esgi.social.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("bms_follow")
public class Follow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * key
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * id of person who is being followed
     */
    @TableField("parent_id")
    private String parentId;

    @TableField("follower_name")
    private String followerName;
    /**
     * id of followers
     */
    @TableField("follower_id")
    private String followerId;

    public Follow() {
    }

}
