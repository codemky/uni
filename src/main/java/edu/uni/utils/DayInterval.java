package edu.uni.utils;

import java.util.Date;

/**
 * @Author:梁俊杰
 * @Description:
 * @Date:Created in 20:47 2019/4/29
 */
public class DayInterval {

    private Date start;
    private Date end;

    public DayInterval() {
    }

    public DayInterval(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public long DayLimit(){
        long beginTime = start.getTime();
        long endTime = end.getTime();
        long betweenDays = (long)((endTime - beginTime) / (1000 * 60 * 60 *24));
        return betweenDays;
    }
}
