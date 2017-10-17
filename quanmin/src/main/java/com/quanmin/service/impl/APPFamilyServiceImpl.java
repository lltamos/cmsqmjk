package com.quanmin.service.impl;

import com.quanmin.call.UserReportListInfo;
import com.quanmin.dao.mapper.*;
import com.quanmin.model.*;
import com.quanmin.model.UserFamilyExample.Criteria;
import com.quanmin.model.custom.FamilyInfo;
import com.quanmin.service.APPFamilyService;
import com.quanmin.util.Commons;
import com.quanmin.util.RandomUtils;
import com.quanmin.util.ResultUtils;
import com.quanmin.util.SendSmsUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class APPFamilyServiceImpl implements APPFamilyService {

    @Autowired
    private UserFamilyMapper userFamilyMapper;
    @Autowired
    private FamilyMapper familyMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SmscodeMapper smscodeMapper;

    @Autowired
    private PushmessageMapper pushmessageMapper;

    @Override
    @Deprecated
    public ResultUtils saveFamily(Family family, Integer userId, String appellation) {
        ResultUtils result = new ResultUtils();

        int insertSelective = familyMapper.insertSelective(family);

        if (insertSelective > 0) {
            UserFamily record = new UserFamily();
            record.setUserId(userId);
            record.setFamilyId(family.getId());
            record.setAppellation(appellation);
            record.setCreateTime(new Date());
            userFamilyMapper.insertSelective(record);
            result.setMsg("添加成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setValue("");
            return result;
        }
        result.setMsg("添加失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }


    @Override
    @Transactional
    public ResultUtils saveFamilyMember(Family family, Integer userId, String appellation, Integer type) {
        int i = 0;
        family.setCreateTime(new Date());
        int insertSelective = familyMapper.insertSelective(family);

        if (insertSelective > 0) {
            UserFamily record = new UserFamily();
            record.setUserId(userId);
            record.setFamilyId(family.getId());
            record.setAppellation(appellation);
            record.setCreateTime(new Date());
            if (type == 1) {
                record.setCheckGreen(1);
            } else {
                record.setCheckGreen(0);
            }
            i = userFamilyMapper.insertSelective(record);
            if (i > 0 && type == 1) {
                SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
                int code = RandomUtils.getRandom4();

                char[] chars = family.getPhone().toCharArray();
                for (int x = 3; x < chars.length - 3; x++) {
                    chars[x] = '*';
                }
                String[] parm = {code + "", "10", family.getName(), sysUser.getNickname(), new String(chars)};
                ResultUtils resultUtils = smsCodeUtils(family.getPhone(), code, parm);
                if ("200".equals(resultUtils.getResultCode())) {
                    return ResultUtils.returnSucess(record.getId());
                }

            }
        }
        return i > 0 ? ResultUtils.returnSucess() : ResultUtils.returnFail();
    }

    @Override
    @Transactional
    public ResultUtils checkReportCode(String idNo, String phone, String code, Integer userFamilyId) {
        SmscodeExample example = new SmscodeExample();
        example.setOrderByClause("timestamp DESC");
        SmscodeExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo(code);
        criteria.andPhoneEqualTo(phone);
        criteria.andStatusEqualTo("0");
        criteria.andTypeEqualTo(2);
        List<Smscode> list = smscodeMapper.selectByExample(example);
        // 判断验证码是否存在
        if (list.size() > 0 && null != list) {
//             查询验证码是否过期
            Date timestamp = list.get(0).getTimestamp();
            // 10分钟的时间
            long tenMinute = (timestamp.getTime() + (10 * 60 * 60 * 1000));
            if (System.currentTimeMillis() >= tenMinute) {
                return ResultUtils.returnSucess(Commons.SMS_CODE_EXPIRED_CODE, Commons.SMS_CODE_EXPRIED_STR, "");
            }
            // 修改短信验证码状态
            Smscode smscode = new Smscode();
            smscode.setId(list.get(0).getId());
            smscode.setStatus("1");
            smscodeMapper.updateByPrimaryKeySelective(smscode);

            UserFamily userFamily = new UserFamily();
            userFamily.setCheckGreen(2);
            userFamily.setId(userFamilyId);
            ResultUtils resultUtils = checkFamilymembersReportByUserFamilyId(phone, idNo);
            List<HashMap<String, Object>> res = (List<HashMap<String, Object>>) resultUtils.getValue();
            ;
            if (null != res && res.size() > 0) {
                userFamily.setAreThereReport(1);
            }
            userFamilyMapper.updateByPrimaryKeySelective(userFamily);
            return ResultUtils.returnSucess();

        }
        return ResultUtils.returnFail();
    }

    @Override
    public ResultUtils checkFamilymembersReportByUserFamilyId(String phone, String idNo) {
        SysUser sysUser = new SysUser();
        if (!StringUtils.isEmpty(idNo)) {
            sysUser.setIdNo(idNo);
        }
        if (!StringUtils.isEmpty(phone)) {
            sysUser.setPhone(phone);
        }
        List<HashMap<String, Object>> list = UserReportListInfo.getUserReportListInfoByPhoneOrIdNo(sysUser, "1", null);


        if (null != list && list.size() > 0) {
            return ResultUtils.returnSucess(list);
        }
        return ResultUtils.returnFail();
    }


    @Deprecated
    @Override
    public ResultUtils showFamilyByUserId(Integer id) {
        ResultUtils result = new ResultUtils();
        List<FamilyInfo> list = userFamilyMapper.selectFamilyInfoByUserId(id);

        if (null != list && list.size() > 0) {
            result.setMsg("查询成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setValue(list);
            return result;
        }
        result.setMsg("查询失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }

    @Override
    public ResultUtils deleteFamily(Integer id, Integer familyId) {
        ResultUtils result = new ResultUtils();
        UserFamilyExample example = new UserFamilyExample();
        Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(id);
        criteria.andFamilyIdEqualTo(familyId);
        int i = userFamilyMapper.deleteByExample(example);

        int j = familyMapper.deleteByPrimaryKey(familyId);

        if (i > 0 && j > 0) {
            result.setMsg("删除成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setValue("");
            return result;
        }
        result.setMsg("删除失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }

    @Override
    public List<FamilyInfo> showFamilyListByUserId(Integer userId) {
        ResultUtils result = new ResultUtils();
        List<FamilyInfo> list = userFamilyMapper.showFamilyListByUserId(userId);


        return list;
    }


    @Transactional
    @Override
    public ResultUtils applyCheckReportByUserId(Integer userFamilyId, Integer userId) {

        UserFamily userFamily = userFamilyMapper.selectByPrimaryKey(userFamilyId);

        Family family = familyMapper.selectByPrimaryKey(userFamily.getFamilyId());

        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);

        //发送短信
        int code = RandomUtils.getRandom4();

        char[] chars = family.getPhone().toCharArray();
        for (int x = 3; x < chars.length - 3; x++) {
            chars[x] = '*';
        }

        String[] parm = {code + "", "10", family.getName(), sysUser.getNickname(), new String(chars)};

        ResultUtils resultUtils = smsCodeUtils(family.getPhone(), code, parm);
        if ("200".equals(resultUtils.getResultCode())) {
            userFamily.setCheckGreen(1);
            int i = userFamilyMapper.updateByPrimaryKeySelective(userFamily);
            return ResultUtils.returnSucess();
        }
        return ResultUtils.returnFail();


    }


    private ResultUtils smsCodeUtils(String phone, int code, String[] parm) {
        ResultUtils resultUtils = new ResultUtils();

        Smscode smscode = new Smscode();
        smscode.setCode(code + "");
        smscode.setPhone(phone);
        smscode.setType(2);
        smscode.setTimestamp(new Date());
        Integer result = SendSmsUtil.sendSMS(phone, "205789", parm);
        switch (result) {
            case 0:
                // 插入到数据库中
                Integer coderes = smscodeMapper.insertSelective(smscode);
                if (coderes >= 0) {
                    return ResultUtils.returnSucess("发送成功");
                } else {
                    return ResultUtils.returnFail("发送失败");
                }
            case 1:
                resultUtils.setMsg(Commons.SMS_CODE_FAST_STR);
                resultUtils.setResultCode(Commons.SMS_CODE_FAST_CODE);
                resultUtils.setSuccess(Commons.DATA_FALSE);
                break;
            case 2:
                resultUtils.setMsg(Commons.SMS_CODE_OVER_CODE);
                resultUtils.setResultCode(Commons.SMS_CODE_OVER_STR);
                resultUtils.setSuccess(Commons.DATA_FALSE);
                break;
            default:
                return ResultUtils.returnFail("发送失败");
        }
        return resultUtils;
    }
}
