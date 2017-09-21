package com.quanmin.service.impl;

import com.quanmin.dao.mapper.HospitalstoreProjectandrecuperateMapper;
import com.quanmin.dao.mapper.ProjectMapper;
import com.quanmin.dao.mapper.RecuperateMapper;
import com.quanmin.dao.mapper.RecuperateProjectMapper;
import com.quanmin.model.*;
import com.quanmin.model.custom.PhpRecuperateInfo;
import com.quanmin.model.po.RecuperatePo;
import com.quanmin.service.ICmsSwapDataSynService;
import com.quanmin.util.Commons;
import com.quanmin.util.ReflectionUtils;
import com.quanmin.util.ResultUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by llsmp on 2017/7/20.
 */
@Service
public class CmsSwapDataSynServiceImpl implements ICmsSwapDataSynService {


    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private HospitalstoreProjectandrecuperateMapper projectandrecuperateMapper;

    @Resource
    private RecuperateMapper recuperateMapper;

    @Resource
    private RecuperateProjectMapper recuperateProjectMapper;

    @Override
    public ResultUtils synProject(Integer type, Project project) {

        try {
            if (type == null || project == null || project.getId() == null) {
                return ResultUtils.returnFail( Commons.DATA_EXCPTION_STR );
            }


            if (type == 2) {
                projectMapper.deleteByPrimaryKey( project.getId() );
                HospitalstoreProjectandrecuperateExample example = new HospitalstoreProjectandrecuperateExample();
                HospitalstoreProjectandrecuperateExample.Criteria criteria = example.createCriteria();
                criteria.andTypeEqualTo( "1" );
                criteria.andProjectOrRecuperateIdEqualTo( project.getId() );
                projectandrecuperateMapper.deleteByExample( example );
            }

            ReflectionUtils.convers( project, String.class );

            if (type == 1) {
                projectMapper.updateByPrimaryKeySelective( project );
            }

            if (type == 0) {
                projectMapper.insertSelective( project );
                HospitalstoreProjectandrecuperate projectandrecuperate = new HospitalstoreProjectandrecuperate();
                projectandrecuperate.setType("1" );
                projectandrecuperate.setCreateTime( new Date(  ) );
                projectandrecuperate.setUpdateTime( new Date(  ) );
                projectandrecuperate.setProjectOrRecuperateId( project.getId() );
                projectandrecuperate.setHospitalstoreId( 5 );
                projectandrecuperateMapper.insertSelective( projectandrecuperate );


            }
        } catch (Exception e) {
            return ResultUtils.returnFail( Commons.DATA_EXCPTION_STR );


        }

        return ResultUtils.returnSucess( Commons.DATA_SUCCESS_STR, null );
    }

    @Override
    public ResultUtils synRecuperate(Integer type, Recuperate recuperate) {

        try {
            if (type == null || recuperate == null || recuperate.getId() == null) {
                return ResultUtils.returnFail( Commons.DATA_EXCPTION_STR );
            }
            if (type == 2) {
                recuperateMapper.deleteByPrimaryKey( recuperate.getId() );
                RecuperateProjectExample recuperateProjectExample = new RecuperateProjectExample();
                RecuperateProjectExample.Criteria criteria = recuperateProjectExample.createCriteria();
                criteria.andRecuperateIdEqualTo( recuperate.getId() );
                recuperateProjectMapper.deleteByExample( recuperateProjectExample );


            }
            if (type == 1 || type == 0) {

                ReflectionUtils.convers( recuperate, String.class );
                RecuperatePo po = (RecuperatePo) recuperate;
                List<PhpRecuperateInfo.ValueBean.ProjectinfoBean> projectinfo = po.getProjectinfo();

                if (type == 1) {

                    recuperateMapper.updateByPrimaryKeySelective( recuperate );
                    RecuperateProjectExample recuperateProjectExample = new RecuperateProjectExample();
                    RecuperateProjectExample.Criteria criteria = recuperateProjectExample.createCriteria();
                    criteria.andRecuperateIdEqualTo( recuperate.getId() );
                    recuperateProjectMapper.deleteByExample( recuperateProjectExample );
                }


                if (type == 0) {
                    recuperateMapper.insertSelective( recuperate );
                }


                for (PhpRecuperateInfo.ValueBean.ProjectinfoBean m : projectinfo) {
                    String projectid = m.getProjectid();
                    String times = m.getTimes();
                    RecuperateProject recuperateProject = new RecuperateProject();
                    recuperateProject.setCreateTime( new Date() );
                    recuperateProject.setUpdateTime( new Date() );
                    recuperateProject.setRecuperateId( recuperate.getId() );
                    recuperateProject.setProjectId( Integer.parseInt( projectid ) );
                    recuperateProject.setType( "0" );
                    recuperateProject.setTimes( Integer.parseInt( times ) );
                    recuperateProjectMapper.insertSelective( recuperateProject );
                }
            }


        } catch (Exception e) {
            return ResultUtils.returnFail( Commons.DATA_EXCPTION_STR );


        }

        return ResultUtils.returnSucess( Commons.DATA_SUCCESS_STR, null );


    }
}
