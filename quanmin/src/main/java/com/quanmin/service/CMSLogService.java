package com.quanmin.service;

import com.quanmin.model.custom.SearchCondition;
import com.quanmin.model.custom.SysLogVO;

import java.util.List;

/**
 * @Author: By heasy.
 * @Date: 2017/9/14.
 * @Contcat: yz972641975@gmail.com.
 * @Description: 日志管理
 * @Modified By:
 */
public interface CMSLogService {

    /**
     * 根据条件查询日志
     * @param condition
     * @return
     */
    List<SysLogVO> listLogByCondition(SearchCondition condition);
}
