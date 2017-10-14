package com.m.mvpdemo.base;

/**
 * @Author m
 * @Date 2017/5/23
 */

public interface BaseImpl {
    /**
     * 初始化数据方法
     */
    void initData();

    /**
     * 初始化UI控件方法
     */
    void initView();

    /**
     * 初始化事件监听方法
     */
    void initListener();

    /**
     * 初始化界面加载
     */
    void initLoad();
}
