package com.m.mvpdemo.mvp;

/**
 * @Author m
 * @Date 2017/5/23
 */

public interface IPresenter<V extends IView> {
    /**
     * 绑定view
     *
     * @param view
     */

    void attachView(V view);

    /**
     * 销毁view
     */
    void detachView();

    /**
     * 获取view
     *
     * @return
     */
    IView getView();
}
