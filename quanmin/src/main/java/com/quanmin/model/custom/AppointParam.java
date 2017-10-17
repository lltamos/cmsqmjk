package com.quanmin.model.custom;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Author: By llsmpsvn.
 * @Date: 2017/9/12.
 * @Contcat: llsmpsvn@gmail.com.
 * @Description:
 * @Modified By:
 */
@Data
public class AppointParam {
    @NotNull
    private String yihutoken;
    @NotNull
    private String platform;
    @NotNull
    private String deviceType;
    @NotNull
    private String version;
    @NotNull
    private String consultantId;
    @NotNull
    private String date;
    @NotNull
    private String segment;
    @NotNull
    private String isTemporary;
    @NotNull
    private String visitInfoId;
    @NotNull
    private String type;
    @NotNull
    private String caseId;
    @NotNull
    private String name;
    @NotNull
    private String gender;

    @NotNull
    private String age;
    @NotNull
    private String mobile;
    @NotNull
    private String cardNo;
    @NotNull
    private Integer userId;

    @NotNull
    private String userPhone;
    @NotNull
    private String userNickName;

    @NotNull
    private String city;

    private String urlHeader;

    private String rank;

}
