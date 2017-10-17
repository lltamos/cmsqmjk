package com.quanmin.qmmq;

import com.quanmin.qmmq.job.*;
import com.quanmin.qmmq.mq.AbstractCmsBootstrap;
import com.quanmin.qmmq.mq.CmsQueue;

import java.util.ArrayList;

public class MqTest {


    public static void main(String[] args) throws Exception {
        AbstractCmsBootstrap cmsBootstrap=new CmsBootstrap();
        CmsQueue<SMSResendJob> cmsQueue=new CmsQueue<>();
        CmsQueue<HttpSenderJob> cmsQueue1=new CmsQueue<>();



        SmsResendHandler testCsmHandler1=new SmsResendHandler();
        testCsmHandler1.setCmsQueue(cmsQueue);
        HttpSenderHandler testCsmHandler=new HttpSenderHandler();
        testCsmHandler.setCmsQueue(cmsQueue1);
        cmsBootstrap.addHandler(testCsmHandler);
        cmsBootstrap.addHandler(testCsmHandler1);
        cmsBootstrap.startJob();

        for (int i=0; i < 4; i++) {
            cmsQueue.put(new SMSResendJob());
        }
        for (int i=0; i < 10; i++) {
            if (i % 2 == 0) {
                cmsQueue1.put(new HttpSenderJob(HttpSenderJob.From.APPOINTRETURN, new ArrayList()));
            } else {
                cmsQueue1.put(new HttpSenderJob(HttpSenderJob.From.YIHUQUEREN, new ArrayList()));
            }

        }
    }

}
