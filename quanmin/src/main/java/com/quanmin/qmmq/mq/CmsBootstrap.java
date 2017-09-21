package com.quanmin.qmmq.mq;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public abstract class CmsBootstrap<T extends CmsHandler, S extends CmsJob> {

    private boolean changed=false;

    private ExecutorService executor=Executors.newFixedThreadPool(5);

    private Vector<CmsHandler> cmsHandlers=new Vector<>();
    private Vector<Future> futures=new Vector<>();

    public abstract void initial();

    public synchronized void startJob() {
        System.out.println("executor 状态" + executor.isShutdown());
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

        if (cmsHandlers == null)
            throw new NullPointerException();
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

}
