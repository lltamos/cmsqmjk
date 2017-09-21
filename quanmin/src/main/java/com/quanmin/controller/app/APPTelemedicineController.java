package com.quanmin.controller.app;

import com.quanmin.model.PositionHistory;
import com.quanmin.service.APPTelemedicineService;
import com.quanmin.util.JsonUtils;
import com.quanmin.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 远程医疗
 *
 * @author heasy
 */
@Controller
@RequestMapping(value = "/api/1/")
public class APPTelemedicineController {

    @Autowired
    private APPTelemedicineService telemedicineService;

    /**
     * 获取医疗类型
     *
     * @return
     */
    @RequestMapping(value = "/getmedicaltype")
    @ResponseBody
    public ResultUtils getmedicaltype() {
        ResultUtils result = telemedicineService.getmedicaltype();
        return result;
    }

    /**
     * 获取医院信息
     *
     * @param medicalTypeId
     * @return
     */
    @RequestMapping(value = "/gethospitalinformation")
    @ResponseBody
    public ResultUtils getHospitalInformation(Integer medicalTypeId) {
        ResultUtils result = telemedicineService.getHospitalStoreInformationByMedicalTypeId(medicalTypeId);
        return result;
    }

    /**
     * 获取医院信息
     *
     * @param infoId
     * @return
     */
    @RequestMapping(value = "/gethospitalinformationdetail")
    @ResponseBody
    public ResultUtils getHospitalInformationDetail(Integer infoId) {
        ResultUtils result = telemedicineService.getHospitalInformationDetailByHospitalStoreInformationId(infoId);
        return result;
    }

    /**
     * 根据userId和cityname信息记录对应的门店信息
     *
     * @return
     */
    @RequestMapping("getstoreinfo")
    @ResponseBody
    public ResultUtils getStoreByuserIdAndPosition(PositionHistory positionHistory,Integer medicalTypeId,Integer cityId) {
        ResultUtils result = telemedicineService.getStoreByuserIdAndPosition(positionHistory,medicalTypeId,cityId);
        return result;

    }

    /**
     * 获取项目预约时间
     */
    @RequestMapping("getreservationtime")
    @ResponseBody
    public ResultUtils getProjectReservationTime(@RequestParam Integer userId,@RequestParam Integer projectId,@RequestParam Integer storeId){
        return telemedicineService.getReservationTime( userId,projectId,storeId );
    }


    /**
     * 方案预约
     */
    @RequestMapping("getRecuperateReservation")
    @ResponseBody
    public ResultUtils getRecuperateReservation(@RequestParam Integer userId,@RequestParam Integer recuperateId){
        return telemedicineService.getRecuperateReservation( userId,recuperateId );
    }
}
