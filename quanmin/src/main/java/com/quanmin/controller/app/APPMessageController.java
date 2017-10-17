package com.quanmin.controller.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quanmin.model.Pushmessage;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quanmin.service.APPMessageService;
import com.quanmin.util.JsonUtils;
import com.quanmin.util.ResultUtils;

import java.util.HashMap;
import java.util.List;

/**
 * 消息管理
 *
 * @author heasy
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping(value = "/api/1/")
public class APPMessageController {

    @Autowired
    private APPMessageService messageService;

    /**
     * 根据用户id查看消息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/showmessage")
    @ResponseBody
    public ResultUtils showMessage(@Param("id") String id, HttpServletRequest httpServletRequest, String token, String phoneType, String version) {
        Object attribute = httpServletRequest.getAttribute("token");

        ResultUtils result = messageService.showMessageByUserId(id);
        return result;
    }

    /**
     * 查看消息详细
     *
     * @param messageId
     * @return
     */
    @RequestMapping(value = "/showmessagedetail")
    @ResponseBody
    public ResultUtils showMessageDetail(HttpServletRequest request, HttpServletResponse response, String messageId) {
        ResultUtils result = messageService.showMessageDetailByMesageId(messageId);
        return result;
    }

    /**
     * 一键清空所以消息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteallmessage")
    @ResponseBody
    public ResultUtils deleteAllMessage(String id) {
        ResultUtils result = messageService.deleteAllMessageByMessageId(id);
        return result;
    }


    /**
     * 统计用户的未读消息数量
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "/countmessage")
    @ResponseBody
    public ResultUtils countMessageByUserId(Integer userId) {
        try {
            HashMap<String,Object> map = new HashMap<>();
            Integer result = messageService.countMessageByUserId(userId);
            map.put("countunreadmessage",null!=result?result:0);
            return null!=map&&map.size() > 0 ? ResultUtils.returnSucess(map) : ResultUtils.returnFail();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.returnFail("调用接口异常!");
        }
    }
}
