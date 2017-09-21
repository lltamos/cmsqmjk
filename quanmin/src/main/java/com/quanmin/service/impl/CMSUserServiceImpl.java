package com.quanmin.service.impl;

import com.quanmin.dao.mapper.SysLogMapper;
import com.quanmin.dao.mapper.SysUserMapper;
import com.quanmin.dao.mapper.UserFamilyMapper;
import com.quanmin.model.SysUser;
import com.quanmin.model.SysUserExample;
import com.quanmin.model.SysUserExample.Criteria;
import com.quanmin.model.custom.FamilyInfo;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.model.custom.UserCountInfo;
import com.quanmin.service.CMSUserService;
import com.quanmin.util.DateFormatUtil;
import com.quanmin.util.ResultUtils;
import com.quanmin.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service(value = "userService")
public class CMSUserServiceImpl implements CMSUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysLogMapper sysLogMapper;
    @Autowired
    private UserFamilyMapper userFamilyMapper;

    @Override
    public SysUser userLogin(String userName, String password) {

        SysUserExample example = new SysUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userName);
        criteria.andPasswordEqualTo(password);
        criteria.andUserTypeEqualTo("1");
        List<SysUser> list = sysUserMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer checkPassword(String password, String id) {
        SysUserExample example = new SysUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(Integer.parseInt(id));
        criteria.andPasswordEqualTo(password);
        List<SysUser> list = sysUserMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            return list.size();
        }
        return null;
    }

    @Override
    public Integer updatePassword(String newpassword, String id) {
        SysUser record = new SysUser();
        record.setPassword(newpassword);
        record.setId(Integer.parseInt(id));
        int i = sysUserMapper.updateByPrimaryKeySelective(record);
        return i;
    }

    @Override
    public List<SysUser> getUserListBySearchCondition(SearchCondition condition) {
//		PageHelper.startPage(condition.getPage(),condition.getSize());
        // 查询条件1
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserTypeEqualTo("0");
        // 姓名
        if (!StringUtil.isEmpty(condition.getSearchKey())) {
            criteria.andNameLike("%" + condition.getSearchKey() + "%");
        }
        // 性别
        if (!StringUtil.isEmpty(condition.getSex())) {
            criteria.andSexEqualTo(Integer.parseInt(condition.getSex()));
        }
//        criteria.andNameNotEqualTo("");
        if (!StringUtil.isEmpty(condition.getArea())) {
            criteria.andAddressLike("%" + condition.getArea() + "%");
        }

        // 年龄
        if (!StringUtil.isEmpty(condition.getAge())) {
            if (condition.getAge().equals("0"))
                criteria.andAgeLessThan(20);
            if (condition.getAge().equals("1"))
                criteria.andAgeBetween(20, 25);
            if (condition.getAge().equals("2"))
                criteria.andAgeBetween(26, 30);
            if (condition.getAge().equals("3"))
                criteria.andAgeBetween(31, 35);
            if (condition.getAge().equals("4"))
                criteria.andAgeBetween(36, 40);
            if (condition.getAge().equals("5"))
                criteria.andAgeBetween(41, 45);
            if (condition.getAge().equals("6"))
                criteria.andAgeBetween(46, 50);
            if (condition.getAge().equals("7"))
                criteria.andAgeBetween(51, 55);
            if (condition.getAge().equals("8"))
                criteria.andAgeBetween(56, 60);
            if (condition.getAge().equals("9"))
                criteria.andAgeBetween(61, 65);
            if (condition.getAge().equals("10"))
                criteria.andAgeBetween(66, 70);
            if (condition.getAge().equals("11"))
                criteria.andAgeBetween(71, 75);
            if (condition.getAge().equals("12"))
                criteria.andAgeBetween(76, 80);
            if (condition.getAge().equals("13"))
                criteria.andAgeGreaterThan(80);
        }


        List<SysUser> list = sysUserMapper.selectByExample(example);

        return list;
    }

    @Override
    public SysUser getUserDetailInfoById(String id) {
        // TODO Auto-generated method stub
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(Integer.parseInt(id));
        return sysUser;
    }

    @Override
    public List<FamilyInfo> getUserFamilyDetailInfoByUserId(String id) {
        List<FamilyInfo> list = userFamilyMapper.selectFamilyInfoByUserId(Integer.parseInt(id));
        return list;
    }

    @Override
    public Integer checkUsernameByUserName(String username) {
        SysUserExample userExample = new SysUserExample();
        userExample.createCriteria().andUsernameEqualTo(username).andUserTypeEqualTo("1");
        List<SysUser> sysUsersList = sysUserMapper.selectByExample(userExample);
        return sysUsersList.size();
    }

    @Override
    public Integer deleteUserByUserId(Integer userId) {
        int i = sysUserMapper.deleteByPrimaryKey(userId);
        return i;
    }

    @Override
    public List<UserCountInfo> addUserCount() {
        return sysUserMapper.addUserCount();

    }

    @Override
    public List<UserCountInfo> activeUserCount() {
        return sysLogMapper.activeUserCount();
    }

    /**
     * @param data
     * @param type 1,按时，2按天，3按月，4按年
     * @return
     */
    @Override
    public ResultUtils addUserStatisticsMap(String data, Integer type) {

        HashMap<String, Object> map = new HashMap<>();

//       统计数量
        List<String> statisticsCount = sysUserMapper.addUserStatisticsCount(data, type);


//统计数据
        List<Integer> statisticsData = sysUserMapper.addUserStatisticsMap(data, type);
        TreeMap<Integer, Object> treeMap = new TreeMap<>();
        if (null != statisticsCount && null != statisticsData && statisticsData.size() > 0 && statisticsCount.size() > 0) {
            switch (type) {
                case 1:
                    for (int x = 0; x < 24; x++) {
                        treeMap.put(x, 0);
                    }
                    map.put("title", treeMap.keySet());
                    for (int y = 0; y < statisticsData.size(); y++) {
                        String str = statisticsCount.get(y);
                        treeMap.put(Integer.parseInt(str), statisticsData.get(y));
                    }
                    map.put("number", treeMap.values());
                    break;
                case 2:
                    String[] split = data.split("-");
                    int daysByYearMonth = DateFormatUtil.getDaysByYearMonth(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                    for (int x = 1; x <= daysByYearMonth; x++) {
                        treeMap.put(x, 0);
                    }
                    map.put("title", treeMap.keySet());
                    for (int y = 0; y < statisticsData.size(); y++) {
                        String str = statisticsCount.get(y);
                        treeMap.put(Integer.parseInt(str), statisticsData.get(y));
                    }
                    map.put("number", treeMap.values());
                    break;
                case 3:
                    for (int x = 1; x <= 12; x++) {
                        treeMap.put(x, 0);
                    }
                    map.put("title", treeMap.keySet());
                    for (int y = 0; y < statisticsData.size(); y++) {
                        String str = statisticsCount.get(y);
                        treeMap.put(Integer.parseInt(str), statisticsData.get(y));
                    }
                    map.put("number", treeMap.values());
                    break;
                case 4:
                    map.put("title", statisticsCount);
                    map.put("number", statisticsData);
                    break;
            }
        }
        return null != map && map.size() > 0 ? ResultUtils.returnSucess(map) : ResultUtils.returnFail();
    }

    @Override
    public ResultUtils activeUserCountMap(String data, Integer type) {
        HashMap<String, Object> map = new HashMap<>();

//       统计数量
        List<String> statisticsCount = sysLogMapper.addUserStatisticsCount(data, type);


//统计数据
        List<Integer> statisticsData = sysLogMapper.addUserStatisticsMap(data, type);
        TreeMap<Integer, Object> treeMap = new TreeMap<>();
        if (null != statisticsCount && null != statisticsData && statisticsData.size() > 0 && statisticsCount.size() > 0) {
            switch (type) {
                case 1:
                    for (int x = 0; x < 24; x++) {
                        treeMap.put(x, 0);
                    }
                    map.put("title", treeMap.keySet());
                    for (int y = 0; y < statisticsData.size(); y++) {
                        String str = statisticsCount.get(y);
                        treeMap.put(Integer.parseInt(str), statisticsData.get(y));
                    }
                    map.put("number", treeMap.values());
                    break;
                case 2:
                    String[] split = data.split("-");
                    int daysByYearMonth = DateFormatUtil.getDaysByYearMonth(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                    for (int x = 0; x < daysByYearMonth; x++) {
                        treeMap.put(x, 0);
                    }
                    map.put("title", treeMap.keySet());
                    for (int y = 0; y < statisticsData.size(); y++) {
                        String str = statisticsCount.get(y);
                        treeMap.put(Integer.parseInt(str), statisticsData.get(y));
                    }
                    map.put("number", treeMap.values());
                    break;
                case 3:
                    for (int x = 1; x <= 12; x++) {
                        treeMap.put(x, 0);
                    }
                    map.put("title", treeMap.keySet());
                    for (int y = 0; y < statisticsData.size(); y++) {
                        String str = statisticsCount.get(y);
                        treeMap.put(Integer.parseInt(str), statisticsData.get(y));
                    }
                    map.put("number", treeMap.values());
                    break;
                case 4:
                    map.put("title", statisticsCount);
                    map.put("number", statisticsData);
                    break;
            }
        }
        return null != map && map.size() > 0 ? ResultUtils.returnSucess(map) : ResultUtils.returnFail();
    }


    public static void main(String[] args) {
        TreeMap<Integer, Object> treeMap = new TreeMap<>();
        for (int x = 0; x < 24; x++) {
            treeMap.put(x, 0);
        }
        System.out.println(treeMap.values());
    }
}
