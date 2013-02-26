package com.stgutah.playground.loops;

import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: dqromney
 * Date: Apr 6, 2010
 * Time: 12:09:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class BatchLoop
{
    List<Long> pSystemUserIdList;

    public static void main(String[] args) {
        BatchLoop batchLoop = new BatchLoop();
        batchLoop.init();
        batchLoop.execute();
    }

    public void init() {
        pSystemUserIdList = new ArrayList<Long>(0);

        for(Long i = 1L; i <= 10001L; i ++) {
            pSystemUserIdList.add(i);
        }
    }

    public void execute() {
        int remainder = pSystemUserIdList.size();
        int subSetCount = 1000;
        int count = 0;
        int from = count * subSetCount;
        int to = remainder >= subSetCount ? from + subSetCount : from + remainder;
        List<Long> aggregatedResults = new ArrayList<Long>(0);
        List<Long> subList;

        boolean hasMore = true;
        System.out.println("(While Loop) Remainder=[" +remainder+ "] from=[" + from + "] to=[" + to + "] hasMore=[" + hasMore + "]");
        while(hasMore) {
            subList = pSystemUserIdList.subList(from, to);
            System.out.println(StringUtils.join(subList, ','));
            remainder = remainder - subSetCount;
            count ++;
            from = count * subSetCount;
            to = remainder >= subSetCount ? from + subSetCount : from + remainder;
            hasMore = remainder > 0;
            System.out.println("(While Loop) Remainder=[" +remainder+ "] from=[" + from + "] to=[" + to + "] hasMore=[" + hasMore + "]");
            aggregatedResults.addAll(subList);
        }
        System.out.println("aggregatedResults=[" +StringUtils.join(aggregatedResults,',')+ "]");

        subList = null;
        aggregatedResults = new ArrayList<Long>(0); 
        remainder = pSystemUserIdList.size();
        count = 0;
        from = count * subSetCount;
        System.out.println("\n(Do-While Loop) Remainder=[" +remainder+ "] from=[" + from + "] to=[" + to + "]");
        do {
            subList = pSystemUserIdList.subList(from, to);
            System.out.println(StringUtils.join(subList, ','));
            remainder = remainder - subSetCount;
            count ++;
            from = count * subSetCount;
            to = remainder >= subSetCount ? from + subSetCount : from + remainder;
            System.out.println("(Do-While Loop) Remainder=[" +remainder+ "] from=[" + from + "] to=[" + to + "]");
            aggregatedResults.addAll(subList);
        } while(remainder > 0);
        System.out.println("aggregatedResults=[" +StringUtils.join(aggregatedResults,',')+ "]");
    }
}
