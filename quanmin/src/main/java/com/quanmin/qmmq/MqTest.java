package com.quanmin.qmmq;

import com.quanmin.qmmq.job.QMBootstrap;
import com.quanmin.qmmq.job.SMSResendJob;
import com.quanmin.qmmq.mq.CmsBootstrap;
import com.quanmin.qmmq.mq.CmsHandler;
import com.quanmin.qmmq.mq.CmsJob;
import com.quanmin.qmmq.mq.CmsQueue;

public class MqTest {


    public static void main(String[] args) throws Exception {
        CmsBootstrap cmsBootstrap=new QMBootstrap();
        CmsQueue<SMSResendJob> cmsQueue=new CmsQueue<>();
        CmsQueue<SMSResendJob> cmsQueue1=new CmsQueue<>();
//        for (int i=0; i < 1; i++) {
//            cmsQueue.put(new SMSResendJob("" + i, null, null));
//        }
//        for (int i=0; i < 1; i++) {
//            cmsQueue1.put(new SMSResendJob("phone", "模版id" + i, null));
//        }

        TestCsmHandler testCsmHandler=new TestCsmHandler(cmsQueue, SMSResendJob.class);
        TestCsmHandler1 testCsmHandler1=new TestCsmHandler1(cmsQueue1, SMSResendJob.class);


        cmsBootstrap.addHandler(testCsmHandler);
        cmsBootstrap.addHandler(testCsmHandler1);
        cmsBootstrap.startJob();
        stop(cmsBootstrap, cmsQueue1);

    }

    private static void stop(CmsBootstrap cmsBootstrap, CmsQueue<SMSResendJob> cmsQueue) {

        try {
            Thread.sleep(2000L);
//            addJob(cmsQueue);
        } catch (InterruptedException e) {
            System.out.println("111");
        }
        cmsBootstrap.stop();
    }


    static void addJob(CmsQueue<SMSResendJob> cmsQueue) {
        try {
            cmsQueue.put(new SMSResendJob("" + "last", null, null));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static class TestCsmHandler extends CmsHandler<CmsQueue<SMSResendJob>> {


        public TestCsmHandler(CmsQueue<SMSResendJob> cmsQueue, Class<? extends CmsJob> bindClass) {
            super(cmsQueue, bindClass);
        }

        @Override
        public void hander(Object job) {
            System.out.println("TestCsmHandler");
        }
    }

    static class TestCsmHandler1 extends CmsHandler<CmsQueue<SMSResendJob>> {


        public TestCsmHandler1(CmsQueue<SMSResendJob> cmsQueue, Class<? extends CmsJob> bindClass) {
            super(cmsQueue, bindClass);
        }

        @Override
        public void hander(Object job) {
            System.out.println("TestCsmHandler1");
        }
    }



}
