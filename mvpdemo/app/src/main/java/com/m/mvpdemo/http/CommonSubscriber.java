package com.m.mvpdemo.http;


import com.m.mvpdemo.exception.ExceptionHandle;
import com.m.mvpdemo.mvp.IView;
import com.m.mvpdemo.util.CommonUtil;
import com.m.mvpdemo.util.LogUtil;
import com.m.mvpdemo.util.SystemUtil;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by m
 *
 * 可配置 加载===
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private IView mView;
    private String mErrorMsg;
    private boolean isShowErrorState = true;

    protected CommonSubscriber() {
    }

    protected CommonSubscriber(IView view, String errorMsg) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected CommonSubscriber(IView view, boolean isShowErrorState) {
        this.mView = view;
        this.isShowErrorState = isShowErrorState;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!SystemUtil.isNetworkConnected()) {
            CommonUtil.showLongToast("网络出现异常，正在尝试加载缓存...");
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        LogUtil.e("error:" + e.getMessage());
        if (e instanceof ExceptionHandle.ResponeThrowable) {
            onError((ExceptionHandle.ResponeThrowable) e);
        } else {
            onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    public abstract void onError(ExceptionHandle.ResponeThrowable e);
}
