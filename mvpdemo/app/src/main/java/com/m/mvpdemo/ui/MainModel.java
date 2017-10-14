package com.m.mvpdemo.ui;

import com.m.mvpdemo.base.BaseModel;
import com.m.mvpdemo.bean.VersionBean;
import com.m.mvpdemo.http.api.apiService;

import io.reactivex.Flowable;

/**
 * Created by majian
 * Date on 2017/10/14
 */

public class MainModel extends BaseModel<apiService>{
    @Override
    public Class<apiService> apiService() {
        return apiService.class;
    }


    public Flowable<VersionBean> checkVersion() {
        return httpService.checkversion("version");
    }
}
