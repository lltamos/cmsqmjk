package com.quanmin.qmmq.job;

import com.quanmin.qmmq.mq.CmsHandler;
import com.quanmin.qmmq.mq.CmsJob;
import com.quanmin.qmmq.mq.CmsQueue;
import com.quanmin.util.SendSmsUtil;
import org.springframework.stereotype.Service;

/**
 * @author DELL
 */
@Service
public class SmsResendHandler extends CmsHandler<CmsQueue<SMSResendJob>> {
    @Override
    public void handler(Object job) {
        if (job instanceof SMSResendJob) {
            SMSResendJob smsJob=(SMSResendJob) job;
            String phone=smsJob.getPhone();
            String templateId=smsJob.getTemplateId();
            String[] strings=smsJob.getStrings();
            Integer sendSMS=SendSmsUtil.sendSMS(phone, templateId, strings);
            if (sendSMS != 0) {
                getCmsQueue().put(smsJob);
            }
        }
    }

    @Override
    public Class<? extends CmsJob> getBindClass() {
        return SMSResendJob.class;
    }


}
