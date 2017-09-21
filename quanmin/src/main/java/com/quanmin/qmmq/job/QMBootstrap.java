package com.quanmin.qmmq.job;

import com.quanmin.qmmq.mq.CmsBootstrap;
import com.quanmin.qmmq.mq.CmsQueue;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: By llsmpsvn.
 * @Date: 2017/9/12.
 * @Contcat: llsmpsvn@gmail.com.
 * @Description:
 * @Modified By:
 */
@Component
@Scope("prototype")
public class QMBootstrap extends CmsBootstrap {


    @PostConstruct
    @Override
    public void initial() {
        CmsQueue<SMSResendJob> cmsQueue=new CmsQueue<>();
        SMSResendHandler resendHandler=new SMSResendHandler(cmsQueue, SMSResendJob.class);
        addHandler(resendHandler);
        startJob();
    }
}
