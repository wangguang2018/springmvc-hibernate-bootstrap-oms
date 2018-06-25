package com.iguangtech.api.common;

public class Constant {
    public static final String MEMBER_ID = "memberId";
    public static final String AGENT_ID = "agentId";
    public static final String MobilePattern = "^(?:\\+86)?1\\d{10}$";
    /**
     * 经度正则表达式
     */
    public static final String LongitudePattern = "^(-|\\+)?(180\\.0{4,10}|(\\d{1,2}|1[0-7]\\d)\\.\\d{4,10})$";

    /**
     * 纬度正则表达式
     */
    public static final String LatitudePattern = "^(-|\\+)?(90\\.0{4,10}|(\\d|[1-8]\\d)\\.\\d{4,10})$";
    public static final Integer PAGE = 1;
    public static final Integer PAGE_SIZE = 15;

}
