package com.quanmin.qmmq.mq;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Vector;
import java.util.concurrent.*;


/**
 * @author DELL
 */
public abstract class AbstractCmsBootstrap<T extends CmsHandler, S extends CmsJob> {

    private boolean changed=false;

    private ExecutorService executor;


    {
        ThreadFactory namedThreadFactory=new ThreadFactoryBuilder()
                .setNameFormat("cms-pool-%d").build();
        executor=new ThreadPoolExecutor(5, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

    private final Vector<CmsHandler> cmsHandlers=new Vector<>();
    private final Vector<Future> futures=new Vector<>();

    protected abstract void initial();

    public synchronized void startJob() {

        this.checkEngine();

        if (executor.isShutdown()) {
            initialExecutor(5);
        }

        for (CmsHandler cmsHandler : cmsHandlers) {
            Future submit=executor.submit(cmsHandler);
            futures.add(submit);
        }
    }

    private void initialExecutor(int poolNum) {
        if (!executor.isShutdown()) {
            executor.shutdownNow();
        }
        executor=Executors.newFixedThreadPool(poolNum);
    }

    public synchronized void addHandler(CmsHandler cmsHandler) {

        if (!cmsHandlers.contains(cmsHandler)) {
            cmsHandlers.addElement(cmsHandler);
        }
    }

    public synchronized void deleteHandler(T cmsHandler) {
        cmsHandlers.removeElement(cmsHandler);
    }

    public synchronized int countCmsHandlers() {
        return cmsHandlers.size();
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed=changed;
    }

    public void reStartJob() {
        startJob();
    }

    public void stop() {
        try {
            executor.shutdown();
            if (!executor.awaitTermination(4000, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    @SuppressWarnings("unchecked")
    public void addJob(CmsJob job) {
        for (CmsHandler handler : cmsHandlers) {
            Class bindClass=handler.getBindClass();
            if (bindClass.equals(job.getClass())) {
                handler.getCmsQueue().put(job);
            }
        }
    }

    public void checkEngine() {
        for (CmsHandler cmsHandler : cmsHandlers) {
            CmsQueue cmsQueue=cmsHandler.getCmsQueue();
            if (cmsQueue == null) {
                throw new NullPointerException("CmsHandler is null");
            }
        }
    }

}
