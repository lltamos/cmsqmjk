package com.quanmin.service;

import com.quanmin.model.custom.AppointParam;
import com.quanmin.model.custom.PageArgs;
import com.quanmin.model.jpapo.AppointOrder;
import com.quanmin.util.ResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: By llsmpsvn.
 * @Date: 2017/9/12.
 * @Contcat: llsmpsvn@gmail.com.
 * @Description:
 * @Modified By:
 */
public interface AppointOrderService {

    ResultUtils productionOrder(AppointParam appointParam);

    ResultUtils productionExtraOrder(AppointOrder appointOrder);

    ResultUtils factorQueryList(PageArgs args, AppointOrder appointOrder);

    ResultUtils completeAppoint(PageArgs args, Integer userId);

    ResultUtils progressAppoint(PageArgs args, Integer userId);


    ResultUtils appointDitails(Long appointId, Integer userId);

    ResultUtils returnAppiont(Long appointId, Integer userId, String desc);

    ResultUtils payAppoint(Long appointId, Integer userId, String body, String desc);

    void callbackalipay(HttpServletRequest request, HttpServletResponse response);

    //获取地区
    ResultUtils getAreas();

    //获取医院列表
    ResultUtils getAreasHospital(String city);

    //获取医院列表
    ResultUtils getHospitalOffice(String city, String hospital);

    ResultUtils cancelAppointOrder(Long appointId, Integer userId);

    ResultUtils countConsultantOrder(Integer consultantId, Integer userId);

    ResultUtils editAppointOrder(AppointOrder appointOrder);

    ResultUtils fetchHospitallistByConsultant(String consultantId);
}
