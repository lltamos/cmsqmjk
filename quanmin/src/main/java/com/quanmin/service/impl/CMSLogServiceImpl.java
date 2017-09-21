package com.quanmin.service.impl;

import com.quanmin.dao.mapper.SysLogMapper;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.model.custom.SysLogVO;
import com.quanmin.service.CMSLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: By heasy.
 * @Date: 2017/9/14.
 * @Contcat: yz972641975@gmail.com.
 * @Description: 日志管理
 * @Modified By:
 */
@Service
public class CMSLogServiceImpl implements CMSLogService {

    @Autowired
    private SysLogMapper sysLogMapper;
    /**
     * 根据条件查询日志
     * @param condition
     * @return
     */
    @Override
    public List<SysLogVO> listLogByCondition(SearchCondition condition) {
        List<SysLogVO> sysLogVOS = sysLogMapper.listLogByCondition(condition);
        return sysLogVOS;
    }
}
