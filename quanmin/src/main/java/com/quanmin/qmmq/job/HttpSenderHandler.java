package com.quanmin.qmmq.job;

import com.alipay.api.AlipayApiException;
import com.quanmin.qmmq.mq.CmsHandler;
import com.quanmin.qmmq.mq.CmsJob;
import com.quanmin.qmmq.mq.CmsQueue;
import com.quanmin.util.HttpRequestUtils;
import com.quanmin.util.LoadPropertiesDataUtils;
import com.quanmin.util.alipayutil.PayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.quanmin.util.Commons.YIHU_HOST;

/**
 * @Author: By llsmpsvn.
 * @Date: 2017/9/12.
 * @Contcat: llsmpsvn@gmail.com.
 * @Description:
 * @Modified By:
 */
@Service
public class HttpSenderHandler extends CmsHandler<CmsQueue<HttpSenderJob>> {


    private final String YIHUYISHEN_HOST = LoadPropertiesDataUtils.getValue(YIHU_HOST);

    @Override
    public void handler(Object job) {
        if (job instanceof HttpSenderJob) {
            HttpSenderJob mengJob = (HttpSenderJob) job;

            switch (mengJob.getFrom()) {
                case APPOINTRETURN:
                    String orderNo = mengJob.getV1().get("orderNo");
                    String price = mengJob.getV1().get("price");
                    String desc = mengJob.getV1().get("desc");
                    try {
                        boolean aBoolean = PayUtils.submitRebate(orderNo, Double.parseDouble(price), desc);
//                        if (!aBoolean) {
//                            getCmsQueue().put(mengJob);
//                        }
                    } catch (AlipayApiException e) {
                        e.printStackTrace();
                        getCmsQueue().put(mengJob);
                    }
                    break;
                case YIHUQUEREN:
                    System.out.println("YIHUQUEREN");
                    try {
                        Map<String, Object> m = HttpRequestUtils.httpPost(YIHUYISHEN_HOST, mengJob.getV2());
                        if (m != null && StringUtils.equals(String.valueOf(m.get("success")), "true")) {
                            //调用退款
                            logger.info("支付成功通知一呼成功");
                        }
                    } catch (Exception e) {
                        getCmsQueue().put(mengJob);
                    }
                    break;
                default:
                    break;
            }


        }
    }

    @Override
    public Class<? extends CmsJob> getBindClass() {
        return HttpSenderJob.class;
    }
}
