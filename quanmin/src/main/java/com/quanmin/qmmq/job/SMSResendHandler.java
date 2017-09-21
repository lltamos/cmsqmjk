package com.quanmin.qmmq.job;

import com.quanmin.qmmq.mq.CmsHandler;
import com.quanmin.qmmq.mq.CmsJob;
import com.quanmin.qmmq.mq.CmsQueue;
import com.quanmin.util.SendSMSUtil;

public class SMSResendHandler extends CmsHandler<CmsQueue<SMSResendJob>> {


    public SMSResendHandler(CmsQueue<SMSResendJob> cmsQueue, Class<? extends CmsJob> bindClass) {
        super(cmsQueue, bindClass);
    }

    @Override
    public void hander(Object job) {
        if (job instanceof CmsJob) {
            SMSResendJob smsJob=(SMSResendJob) job;
            String phone=smsJob.getPhone();
            String templateId=smsJob.getTemplateId();
            String[] strings=smsJob.getStrings();
            Integer sendSMS=SendSMSUtil.sendSMS(phone, templateId, strings);
            if (sendSMS != 0) {
                getCmsQueue().put(smsJob);
            }
        }
    }


}
