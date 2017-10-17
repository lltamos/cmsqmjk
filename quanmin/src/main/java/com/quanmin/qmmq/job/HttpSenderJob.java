package com.quanmin.qmmq.job;

import com.quanmin.qmmq.mq.CmsJob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * @Author: By llsmpsvn.
 * @Date: 2017/9/12.
 * @Contcat: llsmpsvn@gmail.com.
 * @Description:
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpSenderJob implements CmsJob {
    private From from;
    private Map<String, String> v1;
    private List<NameValuePair> v2;
    private String v3;

    public enum From {
        APPOINTRETURN(1), YIHUQUEREN(2);

        private int nCode;

        From(int _nCode) {
            this.nCode=_nCode;
        }

        public int getValue() {
            return nCode;
        }
    }

    public HttpSenderJob(From from, Map<String, String> v1) {
        this.from=from;
        this.v1=v1;
    }

    public HttpSenderJob(From from, List v2) {
        this.from=from;
        this.v2=v2;
    }
}
