package com.m.mvpdemo.http.api;

import com.m.mvpdemo.bean.VersionBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Author m
 * @Date 2017/5/26
 *
 * rx 2。0
 */

public interface apiService {

    @GET("/")
    Flowable<VersionBean> checkversion(@Query("act") String act);
}
