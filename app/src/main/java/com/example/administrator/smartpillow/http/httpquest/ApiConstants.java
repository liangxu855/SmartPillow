package com.example.administrator.smartpillow.http.httpquest;

/**
 * 说明: [ Base URL: 106.14.76.4/wisePillow ]
 */
public class ApiConstants {
    //是不正式环境
    public static boolean IS_OFFICIAL = true;
    public static boolean ISNOT_OFFICIAL=false;

    //wisePillow-正式地址/测试地址
    public static final String BASE_HOST = ISNOT_OFFICIAL ? "http://106.14.76.4/wisePillow" : "http://106.14.76.4/wisePillow";


    /**
     * 获取对应的host
     * @return host
     */
    public static String getHost() {

        return BASE_HOST;
    }


}
