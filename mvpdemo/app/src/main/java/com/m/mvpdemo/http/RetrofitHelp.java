package com.m.mvpdemo.http;


import android.text.TextUtils;

import com.m.mvpdemo.BuildConfig;
import com.m.mvpdemo.base.Constants;
import com.m.mvpdemo.bean.DeviceInfo;
import com.m.mvpdemo.mvpApplication;
import com.m.mvpdemo.util.DeviceUtil;
import com.m.mvpdemo.util.FileUtils;
import com.m.mvpdemo.util.LogUtil;
import com.m.mvpdemo.util.SpUtils;
import com.m.mvpdemo.util.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author m
 * @Date 2017/5/26
 *
 * 网路配置
 */

public class RetrofitHelp<T> {

    private T mApiService;
    private String baseUrl;
    private Class<T> clazz;

    public RetrofitHelp() {
    }

    public RetrofitHelp(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.clazz = builder.clazz;
    }

    public T getService() {
        if (mApiService == null) {
            mApiService = getRetrofit().create(clazz);
        }
        return mApiService;
    }

    public static final class Builder<T> {
        private String baseUrl;
        private Class<T> clazz;

        public Builder<T> baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder<T> apiClazz(Class<T> clazz) {
            this.clazz = clazz;
            return this;
        }

        public RetrofitHelp<T> build() {
            return new RetrofitHelp<T>(this);
        }

    }


    /**
     * 设置公共参数
     */
    private static Interceptor addQueryParameterInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request request;
                DeviceInfo deviceInfo = DeviceUtil.getDeviceInfo(mvpApplication.getInstance());
                HttpUrl.Builder builder = originalRequest.url().newBuilder();
                builder.addQueryParameter("version", SystemUtil.getVersionName(mvpApplication.getInstance()));
                if (deviceInfo != null) {
                    builder.addQueryParameter("device_id", deviceInfo.getDeviceId())
                            .addQueryParameter("os_model", deviceInfo.getDeviceModel())
                            .addQueryParameter("os_mac", deviceInfo.getDeviceMac())
                            .addQueryParameter("os_version", deviceInfo.getDeviceVersion());
                }
                builder.addQueryParameter("source", SystemUtil.getChannelName(mvpApplication.getInstance()));
                builder.addQueryParameter("api_key", "android");
                HttpUrl modifiedUrl = builder.build();
                request = originalRequest.newBuilder().url(modifiedUrl).build();
                return chain.proceed(request);
            }
        };
    }

    /**
     * 设置头
     */
    private static Interceptor addHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .header("token", (String) SpUtils.get("token", ""))
                        .method(originalRequest.method(), originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }

    private static Retrofit retrofit;

    private Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (RetrofitHelp.class) {
                if (retrofit == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    if (BuildConfig.LOG_DEBUG) {
                        Interceptor loggingIntercept = new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request request = chain.request();
                                Response response = chain.proceed(request);
                                ResponseBody responseBody = response.body();
                                BufferedSource source = responseBody.source();
                                source.request(Long.MAX_VALUE); // Buffer the entire body.
                                Buffer buffer = source.buffer();
                                Charset UTF8 = Charset.forName("UTF-8");

                                LogUtil.json("mvp_JSON", buffer.clone().readString(UTF8));
                                LogUtil.url("mvp_URL", request.url().toString());
                                return response;
                            }
                        };
                        builder.addInterceptor(loggingIntercept);
                    }

                    File netCache = FileUtils.getCacheDirectory(mvpApplication.getInstance(), Constants.CACHE_PATH);
                    LogUtil.d("cacheFile:" + netCache.getAbsolutePath());
                    Cache cache = new Cache(netCache, 1024 * 1024 * 50);//50 m

                    OkHttpClient client = builder
                            .addInterceptor(addQueryParameterInterceptor())  //参数添加
                            .retryOnConnectionFailure(true)
                            .addInterceptor(new CacheInterceptor(mvpApplication.getInstance()))
                            .addNetworkInterceptor(new CacheInterceptor(mvpApplication.getInstance()))
                            .cache(cache)  //添加缓存
                            .connectTimeout(5, TimeUnit.SECONDS)
                            .readTimeout(5, TimeUnit.SECONDS)
                            .writeTimeout(5, TimeUnit.SECONDS)
                            .build();

                    // 获取retrofit的实例
                    retrofit = new Retrofit
                            .Builder()
                            .baseUrl(TextUtils.isEmpty(this.baseUrl) ? Constants.BASE_URL : this.baseUrl)
                            .client(client)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }
}
