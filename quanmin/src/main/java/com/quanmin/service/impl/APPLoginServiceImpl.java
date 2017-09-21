package com.quanmin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.quanmin.call.UserInfo;
import com.quanmin.call.UserIpInfo;
import com.quanmin.dao.mapper.*;
import com.quanmin.model.*;
import com.quanmin.model.SmscodeExample.Criteria;
import com.quanmin.service.APPLoginService;
import com.quanmin.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class APPLoginServiceImpl implements APPLoginService {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private SmscodeMapper smscodeMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private TokenMapper tokenMapper;
    @Autowired
    private PushmessageMapper pushmessageMapper;
    @Autowired
    private UserIpMapper userIpMapper;

    @Override
    public Integer getCodeLogin(String phone, int code) {
        try {
            Smscode smscode = new Smscode();
            smscode.setCode(code + "");
            smscode.setPhone(phone);
            smscode.setType(1);
            smscode.setTimestamp(new Date());
            int i = smscodeMapper.insertSelective(smscode);

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Override
    public ResultUtils userLogin(String phone, String code, String registrationId, HttpServletRequest request) {

        ResultUtils rs = new ResultUtils();
        HashMap<String, Object> hs = new HashMap<>();
        String token = "";
        // 是否是会员
        String member = "0";
        // 查询验证码
        SmscodeExample example = new SmscodeExample();
        example.setOrderByClause("timestamp DESC");
        Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo(code);
        criteria.andPhoneEqualTo(phone);
        criteria.andStatusEqualTo("0");
        criteria.andTypeEqualTo(1);
        List<Smscode> list = smscodeMapper.selectByExample(example);

        // 判断验证码是否存在

        if (list.size() > 0 && null != list) {
            // 查询验证码是否过期
            // Date timestamp = list.get(0).getTimestamp();
            // // 五分钟的时间
            // long d = (timestamp.getTime() + (5 * 60 * 60 * 1000));
            // if (System.currentTimeMillis() >= d) {
            // rs.setMsg("验证码超时");
            // rs.setResultCode("500");
            // rs.setSuccess(false);
            // return rs;
            //
            // }
            // // 修改短信验证码状态
            // Smscode smscode = new Smscode();
            // smscode.setId(list.get(0).getId());
            // smscode.setStatus("1");
            // int j = smscodeMapper.updateByPrimaryKeySelective(smscode);

            // 判断用户在大数据平台是否有数据
            SysUser record = UserInfo.getUserInfoByPhoneAndIdNo(phone, "");

            if (null == record) {
                rs.setMsg(Commons.DATA_EXCPTION_STR);
                rs.setResultCode(Commons.DATA_EXCPTION_CODE);
                rs.setSuccess(Commons.DATA_FALSE);
                rs.setValue("");
                return rs;
            }
            if (null != record) {
                record.setPhone(phone);
                record.setUsername(phone);

                // 根据手机号查询本地用户
                SysUserExample exampleUser = new SysUserExample();
                com.quanmin.model.SysUserExample.Criteria userCriteria = exampleUser.createCriteria();
                userCriteria.andPhoneEqualTo(phone);
                userCriteria.andUserTypeEqualTo("0");
                List<SysUser> selectByExample = sysUserMapper.selectByExample(exampleUser);

                // 如果本地用户不存在
                if (selectByExample.size() == 0 && null != list) {
                    char[] chars = phone.toCharArray();
                    for (int x = 3; x < chars.length - 3; x++) {
                        chars[x] = '*';
                    }
                    record.setNickname(new String(chars));
                    record.setHeadImageUrl("/upload/head.png");
                    record.setCreateDate(new Date());
                    record.setRegisterIp(request.getRemoteAddr());
                    record.setLastLoginIp(request.getRemoteAddr());
                    record.setRegisterTime(new Date());
                    int i = sysUserMapper.insertSelective(record);
                    this.saveUserIpInfo(request.getRemoteAddr(), record.getId());
                    // TODO：临时消息提醒，后期需要撤销
                    Pushmessage push = new Pushmessage();
                    push.setReadStatus("0");
                    push.setDelStatus("0");
                    push.setTitle("全民健康上线啦~");
                    push.setText("您的全生命周期的健康管理工作是我们的责任与使命，和全面健康一起开启您的健康管理日程吧~");
                    push.setUserId(record.getId());
                    pushmessageMapper.insert(push);
                    push.setTitle("欢迎来到全民健康");
                    push.setText(
                            "全民健康医疗产业集团是集健康筛查、健康康复、健康云大数据平台、健康智能硬件孵化、健康产业基地， 为一体的全产业链集团。“没有全民健康，就没有全面小康”，我们以“全民健康”为最高使命，打造全产业链的全民健康生态系统， 通过整合欧美国家功能性医学40多年的慢性病防治经验，结合中国实际国情，建立了整套全民健康医疗系统模式。 我们联合了清华大学健康数据研究中心，作为我们的数据应用联合研发团队，为用户提供全民健康云大数据平台， 为老百姓提供从全民筛查到全民康复的整套慢性病防治解决方案，其中包括健康风险评估系统、病因筛查、大病干预、 精准修复、慢病与疑难病康复等专业医学内容，我们提出的整体有效康复解决方案，达到整体高于传统医学三倍以上疾病全方位干预修复率。全民健康慢性病防治管理系统全面的服务于人类健康、社会健康、生态健康。");
                    push.setUserId(record.getId());
                    pushmessageMapper.insert(push);

                    token = this.saveToken(phone, code, record.getId(), registrationId);
                    hs.put("userinfo", record);
                } else {
                    // 如果这个用户存在了。拿着身份证和手机号在大数据平台进行检查
                    if (selectByExample.size() > 0 && !StringUtil.isEmpty(selectByExample.get(0).getIdNo())) {
                        record = UserInfo.getUserInfoByPhoneAndIdNo(phone, selectByExample.get(0).getIdNo());
                    }
                    // 存在了。需要更新用户信息
                    record.setId(selectByExample.get(0).getId());
                    // record.setName(selectByExample.get(0).getName());
                    record.setLastLoginIp(request.getRemoteAddr());

                    if (!StringUtil.isEmpty(selectByExample.get(0).getWeight())) {
                        record.setWeight(selectByExample.get(0).getWeight());
                    }
                    if (!StringUtil.isEmpty(selectByExample.get(0).getHeight())) {
                        record.setHeight(selectByExample.get(0).getHeight());
                    }

                    int i = sysUserMapper.updateByPrimaryKeySelective(record);

                    this.saveUserIpInfo(request.getRemoteAddr(), record.getId());

                    token = this.saveToken(phone, code, selectByExample.get(0).getId(), registrationId);
                    hs.put("userinfo", selectByExample.get(0));
                }
                if (null != record.getName() && !(record.getName().equals(""))) {
                    member = "1";
                }
            }
            hs.put("token", token);
            hs.put("member", member);
            rs.setMsg(Commons.DATA_SUCCESS_STR);
            rs.setResultCode(Commons.DATA_SUCCESS_CODE);
            rs.setSuccess(Commons.DATA_TRUE);
            rs.setValue(hs);
            return rs;

        } else {
            rs.setMsg(Commons.DATA_CHECK_STR);
            rs.setResultCode(Commons.DATA_CHECK_CODE);
            rs.setSuccess(Commons.DATA_FALSE);
            rs.setValue(hs);
            return rs;
        }
    }

    /**
     * 根据用户id，手机号，验证,四位随机数生成唯一的token值
     *
     * @param phone
     * @param code
     * @param userId
     * @return
     */
    public String saveToken(String phone, String code, Integer userId, String registrationId) {

        try {
            String token = EncryptUtils.md5(phone + code + userId + RandomUtils.getRandom4());
            Token record = new Token();
            record.setUserId(userId);
            record.setToken(token);
            record.setRegistrationId(registrationId);
            record.setCreateTime(new Date());
            tokenMapper.insertSelective(record);
//把token 存入到redis中
            Jedis jedis = jedisPool.getResource();
            jedis.set("tokenAndUserIdAndtokenId" + phone, token + "," + userId + "," + record.getId());
            jedis.set(token, token + "," + userId);
            jedis.close();
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultUtils logout(SysUser user, String token) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(token);
        jedis.close();

        ResultUtils rs = new ResultUtils();
        if (null != token || null != user.getId()) {
            TokenExample example = new TokenExample();
            com.quanmin.model.TokenExample.Criteria criteria = example.createCriteria();
            criteria.andTokenEqualTo(token);
            criteria.andUserIdEqualTo(user.getId());
            int result = tokenMapper.deleteByExample(example);
        }
        rs.setMsg(Commons.DATA_SUCCESS_STR);
        rs.setResultCode(Commons.DATA_SUCCESS_CODE);
        rs.setSuccess(Commons.DATA_TRUE);
        return rs;
    }

    @Override
    public ResultUtils checkIdCardByUserIdAndIdCard(SysUser user) {
        ResultUtils rs = new ResultUtils();
        HashMap<String, Object> hs = new HashMap<>();
//校验身份证是否输入正确
        if (!IDCardUtil.isIDCard(null == user.getIdNo() || user.getIdNo().equals("") ? "" : user.getIdNo())) {
            rs.setMsg(Commons.DATA_VILIDATE_ID_STR);
            rs.setResultCode(Commons.DATA_VILIDATE_ID_CODE);
            rs.setSuccess(Commons.DATA_FALSE);
            rs.setValue("");
            return rs;
        } else {
            // 判断用户在大数据平台是否有数据
            SysUser record = UserInfo.getUserInfoByPhoneAndIdNo(user.getPhone(), user.getIdNo());
            // 是否是会员
            String member = "0";
            // 根据传入的用户id进行进行用户信息的查找
            SysUserExample exampleUser = new SysUserExample();
            com.quanmin.model.SysUserExample.Criteria userCriteria = exampleUser.createCriteria();
            userCriteria.andPhoneEqualTo(user.getPhone());
            userCriteria.andIdEqualTo(user.getId());
            userCriteria.andUserTypeEqualTo("0");
            List<SysUser> selectByExample = sysUserMapper.selectByExample(exampleUser);

            // 如果本地用户不存在
            if (selectByExample.size() == 0) {
                rs.setMsg(Commons.DATA_EXCPTION_STR);
                rs.setResultCode(Commons.DATA_EXCPTION_CODE);
                rs.setSuccess(Commons.DATA_FALSE);
                rs.setValue("");
                return rs;
            } else {
                SysUser sysUser = selectByExample.get(0);
                // 获取是不是同步到了数据。如果同步到了，进行修改
                if (null != record.getName() && !(record.getName().equals(""))) {
                    sysUser.setName(record.getName());
                    sysUser.setSex(record.getSex());
                    sysUser.setBirthday(record.getBirthday());
                    sysUser.setIdNo(record.getIdNo());
                    sysUser.setAge(record.getAge());
                    sysUser.setAddress(record.getAddress());
                    sysUser.setHeight(record.getHeight());
                    sysUser.setWeight(record.getWeight());
                    member = "1";
                } else {
                    // 修改idNo信息
                    sysUser.setIdNo(record.getIdNo());
                }
                int i = sysUserMapper.updateByPrimaryKeySelective(sysUser);
                hs.put("userinfo", sysUser);
                hs.put("member", member);
                rs.setMsg(Commons.DATA_SUCCESS_STR);
                rs.setResultCode(Commons.DATA_SUCCESS_CODE);
                rs.setSuccess(Commons.DATA_TRUE);
                rs.setValue(hs);
                return rs;
            }
        }
    }


    public String saveUserIpInfo(String ip, Integer userId) {
        try {
            String result = UserIpInfo.getIpInfo(ip);
            UserIp userip = new UserIp();
            JSONObject rootjsonBean = JSONObject.parseObject(result);
            Object rescode = rootjsonBean.get("code");
            if (rescode.toString().equals("0")) {
                Object data = rootjsonBean.get("data");
                JSONObject jsonBean = JSONObject.parseObject(data.toString());
                userip = JSONObject.toJavaObject(jsonBean, UserIp.class);
                userip.setIp(ip);
                userip.setCreateTime(new Date());
                userip.setUserId(userId);
            }

            UserIpExample useripexample = new UserIpExample();
            com.quanmin.model.UserIpExample.Criteria criteria = useripexample.createCriteria();
            criteria.andUserIdEqualTo(userId);
            List<UserIp> list = userIpMapper.selectByExample(useripexample);

            if (null != list && list.size() > 0) {
                userip.setId(list.get(0).getId());
                userIpMapper.updateByPrimaryKeySelective(userip);
            } else {
                userIpMapper.insertSelective(userip);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }

    @Override
    public ResultUtils validateidcard(String idCard) {
        ResultUtils rs = new ResultUtils();

        // 如果本地用户不存在
        if (IDCardUtil.isIDCard(idCard)) {
            rs.setMsg(Commons.DATA_SUCCESS_STR);
            rs.setResultCode(Commons.DATA_SUCCESS_CODE);
            rs.setSuccess(Commons.DATA_TRUE);
            rs.setValue("");
            return rs;
        } else {
            rs.setMsg(Commons.DATA_ERROR_STR);
            rs.setResultCode(Commons.DATA_ERROR_CODE);
            rs.setSuccess(Commons.DATA_FALSE);
            rs.setValue("");
            return rs;
        }
    }

    @Override
    public ResultUtils generateCache() {
        TokenExample example = new TokenExample();

        List<Token> tokenList = tokenMapper.selectByExample(example);
        for (int i = 0; i < tokenList.size(); i++) {
            SysUser user = sysUserMapper.selectByPrimaryKey(tokenList.get(i).getUserId());
            if (null != user) {
                Jedis jedis = jedisPool.getResource();
                String phone = user.getPhone();
                String token = tokenList.get(i).getToken();
                Integer userId = tokenList.get(i).getUserId();
                Integer tokenid = tokenList.get(i).getId();
                jedis.set("tokenAndUserIdAndtokenId" + phone, token + "," + userId + "," + tokenid);
                jedis.set(tokenList.get(i).getToken(), tokenList.get(i).getToken() + "," + tokenList.get(i).getUserId());
                jedis.close();
            }
        }
        return null;
    }

    @Override
    public ResultUtils touristsLogin(String phone, HttpServletRequest request, HttpServletResponse response) {
        Jedis jedis = jedisPool.getResource();

        ResultUtils rs = new ResultUtils();
        HashMap<String, Object> hs = new HashMap<>();
        // 是否是会员
        String member = "0";
        SysUser record = new SysUser();
        // 根据手机号查询本地用户
        SysUserExample exampleUser = new SysUserExample();
        com.quanmin.model.SysUserExample.Criteria userCriteria = exampleUser.createCriteria();
        userCriteria.andPhoneEqualTo(phone);
        userCriteria.andUserTypeEqualTo("0");
        List<SysUser> selectByExample = sysUserMapper.selectByExample(exampleUser);
        record.setId(selectByExample.get(0).getId());
        record.setLastLoginIp(request.getRemoteAddr());
        int i = sysUserMapper.updateByPrimaryKeySelective(record);
        this.saveUserIpInfo(request.getRemoteAddr(), record.getId());

        jedis.set("tokenAndUserIdAndtokenId","f47ae89213514fa79dad5107662e9c24" );
        hs.put("userinfo", selectByExample.get(0));
        hs.put("token", "f47ae89213514fa79dad5107662e9c24");
        hs.put("member", member);

        rs.setMsg(Commons.DATA_SUCCESS_STR);
        rs.setResultCode(Commons.DATA_SUCCESS_CODE);
        rs.setSuccess(Commons.DATA_TRUE);
        rs.setValue(hs);
        jedis.close();
        return rs;


    }

    @Override
    public ResultUtils updatenickname() {
        try {
            SysUserExample sysUserExample = new SysUserExample();
            List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
            for (SysUser users : sysUsers) {
                if (PhoneFormatCheckUtils.isPhoneLegal(users.getNickname())) {
                    char[] chars = users.getNickname().toCharArray();
                    if (null != chars && chars.length == 11) {
                        for (int x = 3; x < chars.length - 3; x++) {
                            chars[x] = '*';
                        }
                        SysUser u = new SysUser();
                        u.setId(users.getId());
                        u.setNickname(new String(chars));
                        sysUserMapper.updateByPrimaryKeySelective(u);
                    }
                }
            }
            return ResultUtils.returnSucess();

        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.returnFail();
        }
    }

}
