package com.m.mvpdemo.base;


import com.m.mvpdemo.http.RetrofitHelp;
import com.m.mvpdemo.mvp.IModel;

/**
 * @Author m
 * @Date 2017/5/26
 * <p>
 * 可切换  传入 apiserveice
 */

public abstract class BaseModel<T> implements IModel<T> {
    protected T httpService;

    //初始化httpService
    public BaseModel() {
        RetrofitHelp.Builder<T> builder = new RetrofitHelp.Builder<>();
        httpService = builder.baseUrl(Constants.BASE_URL).apiClazz(apiService()).build().getService();
    }


    public T getService() {
        return httpService;
    }
}
