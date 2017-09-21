package com.quanmin.service.impl;

import com.quanmin.dao.jpa.BusinessAccountDao;
import com.quanmin.model.jpapo.BusinessAccount;
import com.quanmin.service.AccountBlogService;
import com.quanmin.util.ResultUtils;
import com.quanmin.util.SortUtils;
import com.quanmin.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class AccountBlogServiceImpl implements AccountBlogService {
    @Resource
    private BusinessAccountDao accountDao;

    @Override
    public ResultUtils inAccount(Integer type, Integer size, Integer page, String queryStr) {
        String parseFix=StringUtil.parseFix(queryStr);
        if (type == 0) {
            if (page < 1) return ResultUtils.returnFail();
            Pageable pageable=new PageRequest(page - 1, size, SortUtils.DESCCreateTime());
            Page<BusinessAccount> businessAccountPage=accountDao.findByTypeLikes(parseFix, 1, pageable);
            return ResultUtils.returnSucess(businessAccountPage);
        }
        if (type == 1) {
            List<BusinessAccount> businessAccountPage=accountDao.findByTypeLikes(parseFix, 1,SortUtils.DESCCreateTime());
            return ResultUtils.returnSucess(businessAccountPage);
        }

        return ResultUtils.returnFail();
    }

    @Override
    public ResultUtils outAccount(Integer type, Integer size, Integer page, String queryStr) {
        String parseFix=StringUtil.parseFix(queryStr);
        if (type == 0) {
            if (page < 1) return ResultUtils.returnFail();
            Pageable pageable=new PageRequest(page-1, size, SortUtils.DESCCreateTime());
            Page<BusinessAccount> businessAccountPage=accountDao.findByTypeLikes(parseFix, 2, pageable);
            return ResultUtils.returnSucess(businessAccountPage);
        }
        if (type == 1) {
            List<BusinessAccount> businessAccountPage=accountDao.findByTypeLikes(parseFix, 2,SortUtils.DESCCreateTime());
            return ResultUtils.returnSucess(businessAccountPage);
        }
        return ResultUtils.returnFail();
    }
}
