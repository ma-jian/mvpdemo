package com.m.mvpdemo.util;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.m.mvpdemo.mvpApplication;


/**
 * @Author m
 * @Date 2017/5/23
 */

public class CommonUtil {
    public static Toast toast;

    public static void showToast(String str) {

        if (toast == null) {
            toast = Toast.makeText(mvpApplication.getInstance(), str, Toast.LENGTH_SHORT);
        } else {
            toast.setText(str);
        }
        toast.show();
    }

    public static void showLongToast(String str) {
        if (toast == null) {
            toast = Toast.makeText(mvpApplication.getInstance(), str, Toast.LENGTH_LONG);
        } else {
            toast.setText(str);
        }
        toast.show();
    }


    public static void showLong(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static void showShort(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }
}
