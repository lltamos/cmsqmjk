package com.quanmin.qmmq.job;

import com.quanmin.qmmq.mq.AbstractCmsBootstrap;
import com.quanmin.qmmq.mq.CmsQueue;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author DELL
 * @Author: By llsmpsvn.
 * @Date: 2017/9/12.
 * @Contcat: llsmpsvn@gmail.com.
 * @Description:
 * @Modified By:
 */
@Component
@Scope("prototype")
public class CmsBootstrap extends AbstractCmsBootstrap {

    @Resource
    HttpSenderHandler httpSenderHandler;
    @Resource
    SmsResendHandler resendHandler;

    @PostConstruct
    @Override
    protected void initial() {
        CmsQueue<SMSResendJob> smsResendJobCmsQueue=new CmsQueue<>();
        CmsQueue<HttpSenderJob> httpSenderJobCmsQueue=new CmsQueue<>();
        resendHandler.setCmsQueue(smsResendJobCmsQueue);
        httpSenderHandler.setCmsQueue(httpSenderJobCmsQueue);
        addHandler(resendHandler);
        addHandler(httpSenderHandler);
        startJob();
    }
}
