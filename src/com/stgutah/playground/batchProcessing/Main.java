package com.stgutah.playground.batchProcessing;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Created by IntelliJ IDEA.
 * User: dqromney
 * Date: Apr 12, 2010
 * Time: 12:17:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main
{
    Logger log = Logger.getLogger(Main.class.getName());
    ClientDataService clientDataService;

    // TODO Unfinished - complete sometime
    public static void main(String[] args) {
        Main main = new Main();
        main.init();
        main.execute();
    }

    public void init() {
        clientDataService = new ClientDataService();
    }

    public void execute() {
        clientDataService.beginBatch();
        clientDataService.addForProcessng(1L);
        clientDataService.addForProcessng(2L);
        clientDataService.addForProcessng(3L);
        clientDataService.addForProcessng(4L);
        clientDataService.addForProcessng(5L);

//        while(clientDataService.hasMore()) {
            clientDataService.processBatch();
//        }

        clientDataService.endBatch();
    }
}
