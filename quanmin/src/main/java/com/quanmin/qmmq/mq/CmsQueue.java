package com.quanmin.qmmq.mq;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CmsQueue<S extends CmsJob> {


    private final BlockingQueue<S> queue=new LinkedBlockingQueue<>();

    public void clean() {
        queue.clear();
    }

    public S get() {
        return queue.poll();
    }

    public int size() {
        return queue.size();
    }


    public void put(S s) {
        try {
            queue.put(s);
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }
    }


}
