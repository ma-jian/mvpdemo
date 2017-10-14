package com.m.mvpdemo.mvp;

/**
 * @Author m
 * @Date 2017/5/23
 */

public interface IView {

    void showErrorMsg(String msg);

    void stateError();

    void stateEmpty();

    void stateLoading();

    void stateMain();

}
