package com.m.mvpdemo.bean;

/**
 * @Author m
 * @Date 2017/6/1
 */

public class VersionBean extends BaseBean {

    /**
     * appname : 一尘网
     * apkFileUrl :
     * versionName : 1.0
     * versionCode : 1
     * versionNote :
     * iphoneNote :
     * iphoneVersion : 1.0
     * iphoneCode : 1.0
     * versionUrl : https://appsto.re/cn/
     * isForced : 0
     * is_hide : 1
     */

    private String appname;
    private String apkFileUrl;
    private String versionName;
    private String versionCode;
    private String versionNote;
    private String iphoneNote;
    private String iphoneVersion;
    private String iphoneCode;
    private String versionUrl;
    private int    isForced;
    private int    is_hide;

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getApkFileUrl() {
        return apkFileUrl;
    }

    public void setApkFileUrl(String apkFileUrl) {
        this.apkFileUrl = apkFileUrl;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionNote() {
        return versionNote;
    }

    public void setVersionNote(String versionNote) {
        this.versionNote = versionNote;
    }

    public String getIphoneNote() {
        return iphoneNote;
    }

    public void setIphoneNote(String iphoneNote) {
        this.iphoneNote = iphoneNote;
    }

    public String getIphoneVersion() {
        return iphoneVersion;
    }

    public void setIphoneVersion(String iphoneVersion) {
        this.iphoneVersion = iphoneVersion;
    }

    public String getIphoneCode() {
        return iphoneCode;
    }

    public void setIphoneCode(String iphoneCode) {
        this.iphoneCode = iphoneCode;
    }

    public String getVersionUrl() {
        return versionUrl;
    }

    public void setVersionUrl(String versionUrl) {
        this.versionUrl = versionUrl;
    }

    public int getIsForced() {
        return isForced;
    }

    public void setIsForced(int isForced) {
        this.isForced = isForced;
    }

    public int getIs_hide() {
        return is_hide;
    }

    public void setIs_hide(int is_hide) {
        this.is_hide = is_hide;
    }


    @Override
    public String toString() {
        return "VersionBean{" +
                "appname='" + appname + '\'' +
                ", apkFileUrl='" + apkFileUrl + '\'' +
                ", versionName='" + versionName + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", versionNote='" + versionNote + '\'' +
                ", iphoneNote='" + iphoneNote + '\'' +
                ", iphoneVersion='" + iphoneVersion + '\'' +
                ", iphoneCode='" + iphoneCode + '\'' +
                ", versionUrl='" + versionUrl + '\'' +
                ", isForced=" + isForced +
                ", is_hide=" + is_hide +
                '}';
    }
}
