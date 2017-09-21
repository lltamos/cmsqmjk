package com.quanmin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quanmin.dao.jpa.AppiontOrderDao;
import com.quanmin.dao.mapper.AppointOrderMapper;
import com.quanmin.model.custom.PageArgs;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.model.jpapo.AppointOrder;
import com.quanmin.service.AppointOrderService;
import com.quanmin.util.ResultUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: By llsmpsvn.
 * @Date: 2017/9/12.
 * @Contcat: llsmpsvn@gmail.com.
 * @Description:
 * @Modified By:
 */
@Service
@Transactional
public class AppointOrderServiceImpl implements AppointOrderService {

    @Resource
    private AppiontOrderDao appiontOrderDao;

    @Resource
    AppointOrderMapper appointOrderMapper;

    @Override
    public ResultUtils productionOrder(AppointOrder appointOrder) {
        if (appointOrder == null || appointOrder.getUserId() == null) return ResultUtils.returnException();
        appointOrder.setCreateTime(new Date());
        appointOrder.setUpdateTime(new Date());
        appiontOrderDao.save(appointOrder);
        return ResultUtils.returnSucess();
    }

    @Override
    public ResultUtils factorQueryList(PageArgs pageArgs, AppointOrder appointOrder) {
        appointOrder.setExt1(pageArgs.getQueryStr());
        if (pageArgs.isDisPart()) {
            //不分页
            List<Map<String, Object>> maps=appointOrderMapper.factorQueryList(appointOrder);
            return maps != null && maps.size() > 0 ? ResultUtils.returnSucess(maps) : ResultUtils.returnFail();
        } else {
            //分页
            PageHelper.startPage(pageArgs.getPage(), pageArgs.getSize(), true);
            List<Map<String, Object>> maps=appointOrderMapper.factorQueryList(appointOrder);
            Page page=(Page) maps;
            return maps != null && maps.size() > 0 ? ResultUtils.returnSucess(maps, page.getPages()) : ResultUtils.returnFail();
        }

    }


}
