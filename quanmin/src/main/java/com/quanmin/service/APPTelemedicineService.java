package com.quanmin.service;

import com.quanmin.model.PositionHistory;
import com.quanmin.util.ResultUtils;

public interface APPTelemedicineService {

	/**
	 * 获取医疗类型
	 * @return
	 */
	ResultUtils getmedicaltype();

	/**
	 * 获取医院信息
	 * @param medicalTypeId
	 * @return
	 */
	ResultUtils getHospitalStoreInformationByMedicalTypeId(Integer medicalTypeId);
	/**
	 * 获取医院详细信息
	 * @return
	 */
	ResultUtils getHospitalInformationDetailByHospitalStoreInformationId(Integer infoId);

	/**
	 * 根据userId和cityname信息记录对应的门店信息
	 * @return
	 */
    ResultUtils getStoreByuserIdAndPosition(PositionHistory positionHistory,Integer MedicalTypeId,Integer cityId);


    ResultUtils getReservationTime(Integer userId,Integer projectId,Integer storeId);

    ResultUtils getRecuperateReservation(Integer userId, Integer recuperateId);
}
