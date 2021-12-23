package com.jias.page.domain;

import lombok.Data;

@Data
public class User {

    private String userId;

    private String userName;

    private String userPassword;

    private String userNick;

    private String userEmail;

    private String userPhone;

    private Integer userGender;

    private String userBirthday;

    private String userCity;

    private String userAvatar;

    private String userMarks;

    private Integer isDeleted;

    private String createTime;

    private String updateTime;
}
