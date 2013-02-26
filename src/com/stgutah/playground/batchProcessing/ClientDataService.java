package com.stgutah.playground.batchProcessing;

import java.util.logging.Logger;
import java.text.MessageFormat;

/**
 * Created by IntelliJ IDEA.
 * User: dqromney
 * Date: Apr 12, 2010
 * Time: 12:25:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClientDataService
{
    Logger log = Logger.getLogger(ClientDataService.class.getName());
    WorkQueue workQueue;
    Worker worker;

    boolean inBatch = false;

    public boolean beginBatch() {
        inBatch = true;
        workQueue = new WorkQueue();
        worker = new Worker(workQueue);
        return inBatch;
    }

    public void processBatch() {
        worker.run();
    }

    public boolean endBatch() {
        inBatch = false;
        worker.stop();
        return inBatch;
    }

    public boolean hasMore() {
        boolean hasMore = true;
        if (workQueue.queue.isEmpty()) {
            hasMore = false;
        }
        return hasMore;
    }

    public boolean addForProcessng(Long data) {
        log.info(MessageFormat.format("Data =[{0}] added ", data));
        workQueue.addWork(data);
        return true;
    }
}
