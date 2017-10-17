package com.quanmin.controller.cms;

import com.quanmin.model.custom.PageArgs;
import com.quanmin.model.jpapo.AppointOrder;
import com.quanmin.service.AppointOrderService;
import com.quanmin.util.ResultUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: By llsmpsvn.
 * @Date: 2017/9/12.
 * @Contcat: llsmpsvn@gmail.com.
 * @Description:
 * @Modified By:
 */
@RestController
@RequestMapping("/cms/1/appoint")
public class CMSAppointOrderController {

    @Resource
    private AppointOrderService appointOrderService;

    /**
     * @param pageArgs
     * @param hospital //医院
     * @param office   //科室
     * @param period   //时间段
     * @return
     */
    @RequestMapping("factorquerylist")
    public ResultUtils factorQueryList(PageArgs pageArgs, AppointOrder appointOrder) {
        return appointOrderService.factorQueryList(pageArgs, appointOrder);
    }

    @RequestMapping("callbackalipay")
    public void callbackalipay(HttpServletRequest request, HttpServletResponse response) {
        appointOrderService.callbackalipay(request, response);
    }

    @RequestMapping("getareas")
    public ResultUtils getAreas() {
        return appointOrderService.getAreas();
    }

    @RequestMapping("getareashospital")
    public ResultUtils getAreasHospital(String city) {
        return appointOrderService.getAreasHospital(city);
    }

    @RequestMapping("gethospitaloffice")
    public ResultUtils getHospitalOffice(String city, String hospital) {
        return appointOrderService.getHospitalOffice(city, hospital);
    }

    //edit
    @RequestMapping("editappointorder")
    public ResultUtils editAppointOrder(AppointOrder appointOrder) {
        return appointOrderService.editAppointOrder(appointOrder);
    }

    /**
     * @param appointId
     * @param userId
     * @param type      0 正常抢号订单  1三方抢号订单
     * @return
     */
    @RequestMapping("appointditails")
    public ResultUtils appointDitails(Long appointId, Integer userId) {
        return appointOrderService.appointDitails(appointId, userId);
    }

    /**
     * @param appointId
     * @param userId
     * @param type      0 正常抢号订单  1三方抢号订单
     * @return
     */
    @RequestMapping("fetchhospitallistbyconsultant")
    public ResultUtils fetchHospitallistByConsultant(String consultantId) {
        return appointOrderService.fetchHospitallistByConsultant(consultantId);
    }


    /**
     * 取消
     *
     * @param appointId
     * @param userId
     * @return
     */
    @RequestMapping("cancelappointorder")
    @ResponseBody
    public ResultUtils cancelAppointOrder(Long appointId, Integer userId) {
        return appointOrderService.cancelAppointOrder(appointId, userId);
    }
}
