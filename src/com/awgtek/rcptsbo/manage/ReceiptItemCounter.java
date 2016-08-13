package com.awgtek.rcptsbo.manage;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(objectName = "mbeans:name=theReceiptItemCounter", 
description = "Counts the number of receipt items added since server start")
public class ReceiptItemCounter {

    private static AtomicInteger counter = new AtomicInteger(0);
    
    @ManagedOperation(description = "incremtn th e counter manually")
    public int incrementAndGet() {
        return counter.incrementAndGet();
    }
    
    @ManagedAttribute(description = "Shows the count of receipt items created so far")
    public int getCurrentCount() {
        return counter.get();
    }
    
    @ManagedOperation
    public void resetCounter() {
        resetCounter(0);
    }
    
    @ManagedOperation
    public void resetCounter(int start) {
        counter = new AtomicInteger(start);
    }
	
}
