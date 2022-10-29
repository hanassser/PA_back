package com.esgi.social.model.dto;


import lombok.Data;


@Data
public class CreateCodePostDTO {

    private static final long serialVersionUID = -5957433707110390852L;

    private String title;

    private String code;

    private String language;

    private int languageId;

    private String description;

    private String originalPostId;


}
