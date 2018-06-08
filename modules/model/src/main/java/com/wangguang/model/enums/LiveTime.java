package com.wangguang.model.enums;

/**
 * USER: douya
 * DATE: 2017-08-07
 */
public enum LiveTime {

    SECONDS_15(15 * 1000), SECONDS_30(30 * 1000),

    MINUTES_1(1 * 60 * 1000), MINUTES_2(2 * 60 * 1000), MINUTES_5(5 * 60 * 1000), MINUTES_10(10 * 60 * 1000), MINUTES_30(
				30 * 60 * 1000),

    HOURS_1(1 * 60 * 60 * 1000), HOURS_2(2 * 60 * 60 * 1000), HOURS_5(5 * 60 * 60 * 1000), HOURS_12(
            12 * 60 * 60 * 1000),HOURS_36(36*60*60*1000),

    DAYS_1(1 * 24 * 60 * 60 * 1000), DAYS_2(2 * 24 * 60 * 60 * 1000), DAYS_5(5 * 24 * 60 * 60 * 1000), DAYS_15(15
            * 24 * 60 * 60 * 1000L),DAYS_30(30* 24 * 60 * 60 * 1000L)

            ;
    public final long time;

    LiveTime(long time) {
        this.time = time;
    }


}
