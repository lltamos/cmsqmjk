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
    @NotNull
    private Integer userId;
    private String appointOrderNum;
    @NotNull
    private String patient;
    @NotNull
    private String hospital;
    @NotNull
    private String office;
    @NotNull
    private String doctor;
    private Date appointTime;//预约时间
    private Integer period;//1 上午 2 下午
    @NotNull
    private String seeHospital; //就诊医院
    @NotNull
    private String seeHospitalAddress;
    @NotNull
    private Date seeTime;//就诊时间
    @NotNull
    private Long phone;
    @NotNull
    private Long idCard;// 身份证
    private Double appointCharge;
    private int payType;//支付方式
    private int orderStatus;//订单状态   1抢号中 2未支付 3已支付 4已完成 5已取消
    private int payStatus;

    private String operatorId;
    private String operator;

    private Date createTime;
    private Date updateTime;

    private String ext1;
    private String ext2;
    private String ext3;


}
