package com.quanmin.service;

import com.quanmin.model.Pushmessage;
import com.quanmin.util.ResultUtils;

import java.util.List;

public interface APPMessageService {

	/**
	 * 一键清空所有消息
	 * 
	 * @param id
	 * @return
	 */
	ResultUtils deleteAllMessageByMessageId(String id);

	/**
	 * 根据用户id查看信息
	 * 
	 * @param id
	 * @return
	 */
	ResultUtils showMessageByUserId(String id);

	/**
	 * 查看消息详细
	 * @param id
	 * @return
	 */
	ResultUtils showMessageDetailByMesageId(String id);

	/**
	 * 统计用户的未读消息数量
	 * @param userId
	 * @return
	 */
	Integer countMessageByUserId(Integer userId);

}
