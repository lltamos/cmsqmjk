package com.quanmin.qmmq.mq;

import javax.validation.constraints.NotNull;

public abstract class CmsHandler<T extends CmsQueue<? extends CmsJob>> implements Runnable {
    private T cmsQueue;

    private boolean smark=true;

    private Long interval=1000L;

    @NotNull
    private Class<? extends CmsJob> bindClass;

    @Override
    public void run() {
        while (smark && !Thread.currentThread().isInterrupted()) {
            Object cmsJob=cmsQueue.get();
            if (cmsJob != null)
                hander(cmsJob);
            interval();
        }
    }

    public void interval() {
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    public abstract void hander(Object job);

    public void toggle() {
        smark=!smark;
    }

    public void setInterval(Long interval) {
        this.interval=interval;
    }

    public CmsHandler(T cmsQueue, Class<? extends CmsJob> bindClass) {
        this.cmsQueue=cmsQueue;
        this.bindClass=bindClass;
    }

    public T getCmsQueue() {
        return cmsQueue;
    }

    public void setCmsQueue(T cmsQueue) {
        this.cmsQueue=cmsQueue;
    }

    public Class<?> getBindClass() {
        return bindClass;
    }

    public void setBindClass(Class<? extends CmsJob> bindClass) {
        this.bindClass=bindClass;
    }
}
