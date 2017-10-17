package com.quanmin.qmmq.mq;

import com.quanmin.service.impl.AppointOrderServiceImpl;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public abstract class CmsHandler<T extends CmsQueue<? extends CmsJob>> implements Runnable {

    protected final Logger logger=LoggerFactory.getLogger(AppointOrderServiceImpl.class);

    private T cmsQueue;

    private boolean smark=true;

    private Long interval=1000L;

    @NotNull
    private Class<? extends CmsJob> bindClass=getBindClass();

    @Override
    public void run() {
        while (smark && !Thread.currentThread().isInterrupted()) {
            try {
                Object cmsJob=cmsQueue.get();
                if (cmsJob != null) {
                    handler(cmsJob);
                }
                interval();
            } catch (InterruptedException e) {
                System.out.println(1);
                break;//捕获到异常之后，执行break跳出循环。
            }

        }
    }

    private void interval() throws InterruptedException {
        Thread.sleep(interval);
    }


    public abstract void handler(Object job);

    public void toggle() {
        smark=!smark;
    }

    public void setInterval(Long interval) {
        this.interval=interval;
    }


    protected T getCmsQueue() {
        return cmsQueue;
    }

    public void setCmsQueue(T cmsQueue) {
        this.cmsQueue=cmsQueue;
    }

    public abstract Class<? extends CmsJob> getBindClass();

}
