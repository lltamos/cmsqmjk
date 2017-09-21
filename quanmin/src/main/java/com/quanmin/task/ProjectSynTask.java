package com.quanmin.task;


import com.quanmin.dao.mapper.HospitalstoreProjectandrecuperateMapper;
import com.quanmin.dao.mapper.ProjectMapper;
import com.quanmin.model.HospitalstoreProjectandrecuperate;
import com.quanmin.model.Project;
import com.quanmin.model.ProjectExample;
import com.quanmin.model.custom.PhpProjectInfo;
import com.quanmin.util.DateFormatUtil;
import com.quanmin.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * 项目定时同步任务
 */
public class ProjectSynTask {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private HospitalstoreProjectandrecuperateMapper hspMapper;

    //同步大数据平台项目数据
    public void projectSyncho() {

        String url = "http://192.168.1.110/WWW/quanminjiankang/wechat/api/apporder/project";

        HttpPost httppost = new HttpPost( url );
        HttpResponse response = null;
        try {
            response = new DefaultHttpClient().execute( httppost );


            if (response.getStatusLine().getStatusCode() == 200) {

                String result = EntityUtils.toString( response.getEntity() );

                PhpProjectInfo phpProjectInfo = JsonUtils.jsonToPojo( result, PhpProjectInfo.class );
                ProjectExample projectExample = new ProjectExample();
                projectExample.createCriteria();

                List<Project> projectList = projectMapper.selectByExample( projectExample );

                //请求成功
                if (phpProjectInfo != null && phpProjectInfo.getResultCode().equals( "200" )) {

                    List<PhpProjectInfo.ValueBean> phpProjectInfoValue = phpProjectInfo.getValue();

                    for (PhpProjectInfo.ValueBean bean : phpProjectInfoValue) {

                        String projectid = bean.getProjectid();
                        Project project = contains( projectid, projectList );


                        if (project == null) {

                            Project project1 = new Project();


                            project1.setId( Integer.parseInt(bean.getProjectid()) );
                            project1.setName( bean.getName() );
                            project1.setPrice( Float.parseFloat( bean.getPrice() ) );
                            project1.setMemberPrice( Float.parseFloat( bean.getMemberprice() ) );

                            project1.setDescription( bean.getExplain() );
                            project1.setCreateTime( DateFormatUtil.StringToDateyyyyMMdd( bean.getCreatetime() ) );
                            project1.setEffectiveDate( DateFormatUtil.StringToDateyyyyMMdd( bean.getEffectivedate() ) );


                            project1.setInfo( StringUtils.isBlank( bean.getInfo() ) ? null : bean.getInfo() );
                            project1.setDevice( StringUtils.isBlank( bean.getDevice() ) ? null : bean.getDevice() );
                            project1.setAttendingFunction( StringUtils.isBlank( bean.getAttendingFunction() ) ? null : bean.getAttendingFunction() );
                            project1.setBan( bean.getBan() );


                            project1.setCoverUrl( bean.getCoverUrl() );
                            project1.setSort( Integer.parseInt( StringUtils.isBlank(bean.getSort())?"0":bean.getSort() ) );



                            projectMapper.insertSelective( project1 );

                            HospitalstoreProjectandrecuperate hps = new HospitalstoreProjectandrecuperate();
                            hps.setCreateTime( new java.util.Date() );
                            hps.setUpdateTime( new java.util.Date() );
                            hps.setHospitalstoreId( 5 );
                            hps.setProjectOrRecuperateId(project1.getId()  );
                            hps.setType( "1" );
                            hspMapper.insertSelective( hps );
                        }


                    }


                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private Project contains(String pid, List<Project> list) {

        for (Project valueBean : list) {
            if (StringUtils.equals( pid, valueBean.getId().toString() )) {

                return valueBean;
            }

        }


        return null;

    }

}
