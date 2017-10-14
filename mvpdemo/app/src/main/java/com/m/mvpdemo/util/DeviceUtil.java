package com.m.mvpdemo.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import com.m.mvpdemo.bean.DeviceInfo;


public class DeviceUtil {
    public static boolean isNetConnect(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isWifiConnect(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * @param context
     * @return
     */
    public static boolean isOpenLoaction(Context context) {
        try {
            LocationManager lm = (LocationManager) context
                    .getSystemService(Context.LOCATION_SERVICE);
            boolean GPS_status = lm
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean NETWORK_status = lm
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (GPS_status == true || NETWORK_status == true) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取设备信息
     */
    @SuppressLint("HardwareIds")
    public static DeviceInfo getDeviceInfo(Context context) {
        try {

            DeviceInfo deviceInfo = new DeviceInfo();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String device_id = tm.getDeviceId();
            if (SystemUtil.isWifiConnected()) {
                WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                String mac = wifi.getConnectionInfo().getMacAddress();
                if (TextUtils.isEmpty(mac)) {
                    deviceInfo.setDeviceMac(mac);
                }
            }

            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(
                        context.getContentResolver(),
                        android.provider.Settings.Secure.ANDROID_ID);
            }
            deviceInfo.setDeviceId(device_id);

            deviceInfo.setDeviceModel(android.os.Build.MODEL);
            deviceInfo.setDeviceVersion(android.os.Build.VERSION.RELEASE);

            return deviceInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDeviceInfoJson(Context context) {
        DeviceInfo deviceInfo = getDeviceInfo(context);
        return deviceInfo.toJson();
    }

    public static String getDeviceInfoString(Context context) {
        DeviceInfo deviceInfo = getDeviceInfo(context);
        return deviceInfo.toString();
    }
}
