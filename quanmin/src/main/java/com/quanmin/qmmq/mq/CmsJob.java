package com.quanmin.qmmq.mq;

public interface CmsJob {

    default int getCount() {
        return 0;
    }

}
