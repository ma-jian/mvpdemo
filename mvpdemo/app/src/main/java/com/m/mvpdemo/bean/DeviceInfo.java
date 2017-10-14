package com.m.mvpdemo.bean;

import com.google.gson.Gson;

public class DeviceInfo {

	private String deviceId; // 串号
	private String deviceMac; // mac地址
	private String deviceModel; // 设备型号
	private String deviceVersion; // 设备版本
	private String phoneType; // 号码类型

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getDeviceVersion() {
		return deviceVersion;
	}

	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String toString() {
		return this.getDeviceId() + "|" + this.getDeviceMac() + "|" + this.getDeviceModel() + "|"
				+ this.getDeviceVersion() + "|" + this.getPhoneType();
	}

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	} 

}
