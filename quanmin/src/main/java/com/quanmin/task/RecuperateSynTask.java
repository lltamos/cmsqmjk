package com.quanmin.task;


import com.quanmin.dao.mapper.HospitalstoreProjectandrecuperateMapper;
import com.quanmin.dao.mapper.RecuperateMapper;
import com.quanmin.dao.mapper.RecuperateProjectMapper;
import com.quanmin.model.*;
import com.quanmin.model.custom.PhpRecuperateInfo;
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
import java.util.Date;
import java.util.List;

/**
 * 项目定时同步任务
 */
public class RecuperateSynTask {

    @Autowired
    private RecuperateMapper recuperateMapper;


    @Autowired
    private RecuperateProjectMapper recuperateProjectMapper;


    @Autowired
    private HospitalstoreProjectandrecuperateMapper hspMapper;

    //同步大数据平台项目数据
    public void recuperateSyncho() {

        String url = "http://192.168.1.110/WWW/quanminjiankang/wechat/api/apporder/recuperate";

        HttpPost httppost = new HttpPost( url );
        try {
            HttpResponse response = new DefaultHttpClient().execute( httppost );


            if (response.getStatusLine().getStatusCode() == 200) {

                String result = EntityUtils.toString( response.getEntity() );

                PhpRecuperateInfo phpRecuperateInfo = JsonUtils.jsonToPojo(result, PhpRecuperateInfo.class);
                RecuperateExample recuperateExample = new RecuperateExample();
                recuperateExample.createCriteria();

                List<Recuperate> recuperates = recuperateMapper.selectByExample( recuperateExample );

                //请求成功
                if (phpRecuperateInfo != null && phpRecuperateInfo.getResultCode().equals( "200" )) {

                    List<PhpRecuperateInfo.ValueBean> phpRecuperateInfoValue = phpRecuperateInfo.getValue();

                    for (PhpRecuperateInfo.ValueBean bean : phpRecuperateInfoValue) {

                        String recuperateid = bean.getRecuperateid();
                        Recuperate recuperate = contains( recuperateid, recuperates );



                        if (recuperate == null) {

                            RecuperateWithBLOBs recuperate1 = new RecuperateWithBLOBs();


                            recuperate1.setId( Integer.parseInt( bean.getRecuperateid() ) );
                            recuperate1.setName( bean.getName() );
                            recuperate1.setStorePrice( Float.parseFloat( bean.getPrice() ) );
                            recuperate1.setMemberPrice( Float.parseFloat( bean.getMemberprice() ) );

                            recuperate1.setDescription( bean.getExplain() );
                            recuperate1.setCreateTime( DateFormatUtil.StringToDateyyyyMMdd( bean.getCreatetime() ) );
                            recuperate1.setEffectiveDate( DateFormatUtil.StringToDateyyyyMMdd( bean.getEffectivedate() ) );
                            recuperate1.setInfo( StringUtils.isBlank( bean.getInfo() ) ? null : bean.getInfo() );
                            recuperate1.setCoverUrl( bean.getCoverUrl() );

                            recuperateMapper.insertSelective( recuperate1 );

                            HospitalstoreProjectandrecuperate hps = new HospitalstoreProjectandrecuperate();
                            hps.setCreateTime( new java.util.Date() );
                            hps.setUpdateTime( new java.util.Date() );
                            hps.setHospitalstoreId( 5 );
                            hps.setProjectOrRecuperateId( recuperate1.getId() );
                            hps.setType( "0" );
                            hspMapper.insertSelective( hps );


                            for (PhpRecuperateInfo.ValueBean.ProjectinfoBean proinfo:bean.getProjectinfo()){

                                RecuperateProject recuperateProject =new RecuperateProject();
                                recuperateProject.setCreateTime( new Date(  ) );
                                recuperateProject.setProjectId( Integer.parseInt( proinfo.getProjectid()) );
                                recuperateProject.setRecuperateId( Integer.parseInt( proinfo.getPid() ) );
                                recuperateProject.setUpdateTime( new Date() );
                                recuperateProject.setType("0");
                                recuperateProject.setTimes(Integer.parseInt(proinfo.getTimes()));

                                recuperateProjectMapper.insertSelective( recuperateProject );

                            }


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


    private Recuperate contains(String pid, List<Recuperate> list) {

        for (Recuperate valueBean : list) {
            if (StringUtils.equals( pid, valueBean.getId().toString() )) {

                return valueBean;
            }

        }


        return null;

    }

}
