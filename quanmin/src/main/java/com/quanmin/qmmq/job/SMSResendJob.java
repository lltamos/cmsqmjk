package com.quanmin.qmmq.job;

import com.quanmin.qmmq.mq.CmsJob;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: By llsmpsvn.
 * @Date: 2017/9/12.
 * @Contcat: llsmpsvn@gmail.com.
 * @Description:
 * @Modified By:
 */
@Data
@AllArgsConstructor
public class SMSResendJob implements CmsJob {
    private String phone;
    private String templateId;
    private String[] strings;
    public SMSResendJob(){}

}
