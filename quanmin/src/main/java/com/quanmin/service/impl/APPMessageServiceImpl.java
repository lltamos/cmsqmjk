package com.quanmin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quanmin.dao.mapper.PushmessageMapper;
import com.quanmin.model.Pushmessage;
import com.quanmin.model.PushmessageExample;
import com.quanmin.model.PushmessageExample.Criteria;
import com.quanmin.service.APPMessageService;
import com.quanmin.util.ResultUtils;

@Service
public class APPMessageServiceImpl implements APPMessageService {
    @Autowired
    private PushmessageMapper pushmessageMapper;

    @Override
    public ResultUtils deleteAllMessageByMessageId(String id) {
        ResultUtils result = new ResultUtils();

        PushmessageExample example = new PushmessageExample();
        Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(Integer.parseInt(id));
        try {
            int delete = pushmessageMapper.deleteByExample(example);
            result.setMsg("清空成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setValue("");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("清空失败");
            result.setResultCode("500");
            result.setSuccess(false);
            result.setValue("");
            return result;
        }

    }

    @Override
    public ResultUtils showMessageByUserId(String id) {
        ResultUtils result = new ResultUtils();
        List<Pushmessage> list = pushmessageMapper.selectByUserId(id);

        if (null != list && list.size() > 0) {
            result.setMsg("获取成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setValue(list);
            return result;
        }
        result.setMsg("获取失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }

    @Override
    public ResultUtils showMessageDetailByMesageId(String id) {
        ResultUtils result = new ResultUtils();
        Pushmessage key = pushmessageMapper.selectByPrimaryKey(Integer.parseInt(id));
        if (null != key) {
            Pushmessage record = new Pushmessage();
            record.setReadStatus("1");
            record.setId(Integer.parseInt(id));
            pushmessageMapper.updateByPrimaryKeySelective(record);
            result.setMsg("获取成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setValue(key);
            return result;
        }
        result.setMsg("获取失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }



    @Override
    public Integer countMessageByUserId(Integer userId) {
        PushmessageExample pushmessageExample = new PushmessageExample();
        Criteria criteria = pushmessageExample.createCriteria();
        criteria.andReadStatusEqualTo("0");
        criteria.andUserIdEqualTo(userId);
        criteria.andDelStatusEqualTo("0");
        return pushmessageMapper.countByExample(pushmessageExample);
    }

}
