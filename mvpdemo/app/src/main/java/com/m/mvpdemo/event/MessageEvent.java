package com.m.mvpdemo.event;

/**
 * @Author m
 * @Date 2017/5/31
 */
public class MessageEvent {
    private String msg;

    public MessageEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
