package com.m.mvpdemo.base;

/**
 * @Author m
 * @Date 2017/5/26
 *
 * 根据需求--配置后台返回  数据 结构
 */

public class BaseHttpResult<T> {
    private int errorCode;
    private String errorMsg;
    private T resultData;
    private int resultTotal;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getResultData() {
        return resultData;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }

    public int getResultTotal() {
        return resultTotal;
    }

    public void setResultTotal(int resultTotal) {
        this.resultTotal = resultTotal;
    }
}
