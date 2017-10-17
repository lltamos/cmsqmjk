package com.quanmin.service.impl;

import com.quanmin.dao.mapper.*;
import com.quanmin.model.*;
import com.quanmin.service.APPTelemedicineService;
import com.quanmin.util.Commons;
import com.quanmin.util.HttpRequestUtils;
import com.quanmin.util.ResultUtils;
import com.quanmin.util.StringUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

@SuppressWarnings("ALL")
@Service
public class APPTelemedicineServiceImpl implements APPTelemedicineService {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private MedicaltypeMapper medicaltypeMapper;
    @Autowired
    private HospitalStoreInformationMapper hospitalStoreInformationMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private RecuperateMapper recuperateMapper;

    @Autowired
    private PositionHistoryMapper positionHistoryMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public ResultUtils getmedicaltype() {
        ResultUtils result = new ResultUtils();
        MedicaltypeExample example = new MedicaltypeExample();
        example.createCriteria();
        example.setOrderByClause( "id DESC" );
        List<Medicaltype> list = medicaltypeMapper.selectByExample( example );
        if (null != list && list.size() > 0) {
            result.setMsg( "获取成功" );
            result.setResultCode( "200" );
            result.setSuccess( true );
            result.setValue( list );
            return result;
        }
        result.setMsg( "获取失败" );
        result.setResultCode( "500" );
        result.setSuccess( false );
        result.setValue( "" );
        return result;
    }

    @Override
    public ResultUtils getHospitalStoreInformationByMedicalTypeId(Integer medicalTypeId) {
        ResultUtils result = new ResultUtils();
        List<HospitalStoreInformation> list = hospitalStoreInformationMapper
                .getHospitalStoreInformationByMedicalTypeId( medicalTypeId, null );
        if (null != list && list.size() > 0) {
            result.setMsg( "获取成功" );
            result.setResultCode( "200" );
            result.setSuccess( true );
            result.setValue( list );
            return result;
        }
        result.setMsg( "获取失败" );
        result.setResultCode( "500" );
        result.setSuccess( false );
        result.setValue( "" );
        return result;
    }

    @Override
    public ResultUtils getHospitalInformationDetailByHospitalStoreInformationId(Integer infoId) {
        ResultUtils result = new ResultUtils();
        HashMap<String, Object> map = new HashMap<>();
        // 查看详细信息
        HospitalStoreInformation information = hospitalStoreInformationMapper.selectByPrimaryKey( infoId );
        if (null != information) {
            map.put( "hosipitalstroeinfo", information );
        }
        // 查询检查项目
        List<Project> projectList = projectMapper.selectProjectByInfoId( infoId );
        if (null != projectList && projectList.size() > 0) {
            map.put( "projectlists", projectList );
        }
        //查询调理方案

        List<Recuperate> recuperateList = recuperateMapper.selectRecperateByInforId( infoId );

        if (null != recuperateList && recuperateList.size() > 0) {
            map.put( "recuperatelist", recuperateList );
        }

        if (null != map && map.size() > 0) {
            result.setMsg( "获取成功" );
            result.setResultCode( "200" );
            result.setSuccess( true );
            result.setValue( map );
            return result;
        }
        result.setMsg( "获取失败" );
        result.setResultCode( "500" );
        result.setSuccess( false );
        result.setValue( "" );
        return result;
    }

    @Override
    public ResultUtils getStoreByuserIdAndPosition(PositionHistory positionHistory, Integer medicalTypeId, Integer cityId) {
        ResultUtils result = new ResultUtils();

        if (null != positionHistory.getPositionId() && "".equals(positionHistory.getPositionId())) {
//        添加缓存历史位置
            Jedis jedis = jedisPool.getResource();
            String positionUserIds = jedis.get( "position" + positionHistory.getUserId() );
            if (StringUtil.isEmpty( positionUserIds )) {
                positionHistory.setCreateTime( new Date() );
                int i = positionHistoryMapper.insertSelective( positionHistory );
                jedis.set( "position" + positionHistory.getUserId(), positionUserIds + "," + positionHistory.getPositionId() );
            } else {
                String[] positionUserArrays = positionUserIds.split( "," );
                for (int x = 0; x < positionUserArrays.length; x++) {
                    if (!StringUtil.isEmpty( positionUserArrays[x] )) {
                        if (Integer.parseInt( positionUserArrays[x] ) != positionHistory.getPositionId()) {
                            positionHistory.setCreateTime( new Date() );
                            int i = positionHistoryMapper.insertSelective( positionHistory );
                            jedis.set( "position" + positionHistory.getUserId(), positionUserIds + "," + positionHistory.getPositionId() );
                        }
                    }
                }
            }
            jedis.close();
        }
//        返回数据
        //当传入id为0是，判断为北京
        List<HospitalStoreInformation> list = hospitalStoreInformationMapper
                .getHospitalStoreInformationByMedicalTypeId( medicalTypeId, cityId == 0 ? 1 : cityId );
        if (null != list && list.size() > 0) {
            result.setMsg( "获取成功" );
            result.setResultCode( "200" );
            result.setSuccess( true );
            result.setValue( list );
            return result;
        }
        result.setMsg( "获取失败" );
        result.setResultCode( "500" );
        result.setSuccess( false );
        return result;
    }

    @Override
    public ResultUtils getReservationTime(Integer userId, Integer projectId, Integer storeId) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey( userId );
        String ext1 = sysUser.getExt1();
        List<NameValuePair> param = new ArrayList<>();
        Map<String, Object> m;
        if (org.apache.commons.lang3.StringUtils.isBlank( ext1 )) {

            param.add( new BasicNameValuePair( "appMobile", sysUser.getUsername() ) );
            param.add( new BasicNameValuePair( "user_status", "1" ) );
            param.add( new BasicNameValuePair( "userIdcard", sysUser.getIdNo() ) );
            param.add( new BasicNameValuePair( "userIdcard", storeId.toString() ) );
            param.add( new BasicNameValuePair( "userType", "3" ) );
            param.add( new BasicNameValuePair( "isVip", "0" ) );

            m = HttpRequestUtils.httpPost( Commons.API_GET_USER_ID, param );
            Object pUserId = m.get( "userid" );
            if (userId == null) {
                ResultUtils.returnFail(Commons.DATA_EXCPTION_STR);
            }

            ext1 = pUserId.toString();
            sysUser.setExt1( ext1 );
            SysUserExample sysUserExample = new SysUserExample();
            SysUserExample.Criteria criteria = sysUserExample.createCriteria();
            criteria.andIdEqualTo( userId );
            sysUserMapper.updateByExampleSelective( sysUser, sysUserExample );
        }

        param.clear();
        param.add( new BasicNameValuePair( "userId", ext1 ) );
        param.add( new BasicNameValuePair( "projectId", projectId.toString() ) );
        m = HttpRequestUtils.httpPost( Commons.API_GET_ORDERTIME, param );

        return ResultUtils.returnSucess( Commons.DATA_SUCCESS_STR, m );
    }

    @Override
    public ResultUtils getRecuperateReservation(Integer userId, Integer recuperateId) {





        return null;
    }

}
