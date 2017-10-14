package com.m.mvpdemo.contract;

/**
 * Created by majian
 * Date on 2017/10/14
 */

public interface MainConstract {
    interface mainView{
        void showVersion(String s);
    }

    interface mainPresent{
        void checkVersion();
    }
}
