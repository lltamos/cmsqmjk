package com.quanmin.service.impl;

import com.quanmin.dao.mapper.RecommendMapper;
import com.quanmin.dao.mapper.SmscodeMapper;
import com.quanmin.dao.mapper.SysUserMapper;
import com.quanmin.model.*;
import com.quanmin.service.RecommendService;
import com.quanmin.util.Commons;
import com.quanmin.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: By heasy.
 * @Date: 2017/9/25.
 * @Contcat: yz972641975@gmail.com.
 * @Description:
 * @Modified By:
 */
@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    private SmscodeMapper smscodeMapper;

    @Autowired
    private RecommendMapper recommendMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Integer insertCode(String phone, int code) {
        try {
            Smscode smscode = new Smscode();
            smscode.setCode(code + "");
            smscode.setPhone(phone);
            smscode.setType(3);
            smscode.setTimestamp(new Date());
            int i = smscodeMapper.insertSelective(smscode);

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }


    @Override
    @Transactional
    public ResultUtils applyRegister(String referee, String referral, String code)  {

        SmscodeExample smscodeExample = new SmscodeExample();
        SmscodeExample.Criteria smscodeExampleCriteria = smscodeExample.createCriteria();
        smscodeExampleCriteria.andPhoneEqualTo(referee);
        smscodeExampleCriteria.andCodeEqualTo(code);
        List<Smscode> smscodes = smscodeMapper.selectByExample(smscodeExample);

        if (null != smscodes && smscodes.size() > 0) {
            // 查询验证码是否过期
            Date timestamp = smscodes.get(0).getTimestamp();
            // 五分钟的时间
            long d = (timestamp.getTime() + (5 * 60 * 60 * 1000));
            if (System.currentTimeMillis() >= d) {
                return ResultUtils.returnFail(Commons.SMS_CODE_EXPIRED_CODE, Commons.SMS_CODE_EXPRIED_STR);
            }
            SysUserExample sysUserExample = new SysUserExample();
            SysUserExample.Criteria criteria = sysUserExample.createCriteria();
            criteria.andPhoneEqualTo(referee);
            criteria.andDelStatusEqualTo(0);
            List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);

            if (null != sysUserList && (sysUserList.size() == 0)) {

                Recommend recommendRecord = new Recommend();
                recommendRecord.setCreateTime(new Date());
                recommendRecord.setDelStatus(0);
                recommendRecord.setReferee(referee);
                recommendRecord.setReferral(referral);
                recommendMapper.insertSelective(recommendRecord);

                Smscode smscode = new Smscode();
                smscode.setId(smscodes.get(0).getId());
                smscode.setStatus("1");
                smscodeMapper.updateByPrimaryKeySelective(smscode);
                return ResultUtils.returnSucess();
            } else {
                return ResultUtils.returnFail(Commons.SMS_CODE_RECOMMEND_CODE, Commons.SMS_CODE_RECOMMEND_STR);
            }
        }else {
            return ResultUtils.returnFail(Commons.DATA_CHECK_CODE, Commons.DATA_CHECK_STR);
        }
    }
}
