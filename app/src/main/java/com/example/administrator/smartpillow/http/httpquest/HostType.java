package com.example.administrator.smartpillow.http.httpquest;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 说明:主机的类型
 */
public class HostType {
    /**
     * 多少种Host类型
     */
    public static final int TYPE_COUNT = 1;

    /**
     * 默认地址host
     */
    public static final int TYPE_DEFAULT = 1;


    /**
     * 替代枚举的方案，使用IntDef保证类型安全
     */
    @IntDef({TYPE_DEFAULT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface HostTypeChecker {

    }
}
