package com.quanmin.model.jpapo;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author: By llsmpsvn.
 * @Date: 2017/9/12.
 * @Contcat: llsmpsvn@gmail.com.
 * @Description:
 * @Modified By:
 */
@Entity
@Table(name="appoint_order")
@Data
public class AppointOrder {
    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue
    private Long id;

    private String orderNo;
    @NotNull
    private String patientName;
    @NotNull
    private String patientGender;//性别
    @NotNull
    private String patientPhone;//患者手机号
    @NotNull
    private String patientAge;//患者年纪
    @NotNull
    private String hospitalName;
    @NotNull
    private String deptName;
    private Integer timeLimit;
    @NotNull
    private Integer consultantId;
    @NotNull
    private String consultantName;

    private Double price;

    private String scheduleTimeSegment;
    private String rank;
    private String scheduleTime;

    private String treatmentTime;

    private String treatmentHospital;

    private String treatmentHospitalAddress;
    private Integer treatmentPeriod;

    private Integer period; //0 上午 1 下午
    @NotNull
    private Integer userId;
    @NotNull
    private String userPhone;
    @NotNull
    private String userNickName;
    @NotNull
    private String idCard;//身份证
    private Double appointCharge;//服务费
    private Integer appointType;//1 预约订单 2 三方号源抢号订单
    private Integer payType;//支付方式 1 支付宝 2 微信
    private Integer orderStatus;//订单状态   1抢号中 2未支付 3已支付
    private Integer lifeType;//1未完成 2已完成 3 已取消
    private Integer payStatus;//0 未支付 1 已支付

    private Integer type;//出诊类型 1-本院出诊 4-合作医院出诊（当前需求只有4）

    private String operatorId;
    private String operator;
    private String city;

    private String caseId;

    private String visitInfoId;//出诊排班ID
    private Date createTime;
    private Date updateTime;
    private Integer isCallBack;//是否走回调0 未回调 1 以回调

    private String urlHeader;


    private String ext1;
    private String ext2;
    private String ext3;
    private String payOrderNum;

    private Integer isNotifyYihu;//0 未通知，1 通知

    private Date expirationTime;


}
