package com.quanmin.task;

import com.quanmin.service.IReceiptScanTaskService;

import javax.annotation.Resource;

public class ReceiptScanTask {
    @Resource
    private IReceiptScanTaskService iReceiptScanTaskService;

    public void sweepTime() {
        iReceiptScanTaskService.sweep();
    }
}
