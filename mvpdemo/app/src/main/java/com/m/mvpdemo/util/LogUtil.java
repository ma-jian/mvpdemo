package com.m.mvpdemo.util;


import com.m.mvpdemo.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * Created by M
 */
public class LogUtil {

    public static        boolean isDebug = BuildConfig.LOG_DEBUG;
    private static final String  TAG     = "com.m.mvpdemo";

    public static void e(String tag, Object o) {
        if (isDebug) {
            Logger.e(tag, o);
        }
    }

    public static void e(Object o) {
        LogUtil.e(TAG, o);
    }

    public static void w(String tag, Object o) {
        if (isDebug) {
            Logger.w(tag, o);
        }
    }

    public static void w(Object o) {
        LogUtil.w(TAG, o);
    }

    public static void d(String msg) {
        if (isDebug) {
            Logger.d(msg);
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            Logger.i(msg);
        }
    }

    public static void json(String teg, String msg) {
        if (isDebug) {
            Logger.init(teg);
            Logger.json(msg);
        }
    }

    public static void url(String tag, String message) {
        if(isDebug) {
            Logger.init(tag);
            Logger.i(message);
        }
    }
}
