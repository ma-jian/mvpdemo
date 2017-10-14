package com.m.mvpdemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by m
 */

public class mvpApplication extends Application {
    private static mvpApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initX5(); //腾讯x5

        BuildConfig.LOG_DEBUG = true;  //日志开关

        registerActivityLifecycleCallbacks(new activitylife());
    }

    private void initX5() {
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    public static mvpApplication getInstance() {
        return application;
    }

    // 全局activity 生命周期配置。  
    private class activitylife implements ActivityLifecycleCallbacks{
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }
}
