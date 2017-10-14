package com.m.mvpdemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by m
 */

public abstract class BaseWebActivity extends BaseActivity {
    protected Activity context;
    private   WebView  mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        String type = getIntent().getStringExtra("type");
        String url = getIntent().getStringExtra("url");
        loadWeb(type, url);
    }

    public void onResume() {
        super.onResume();
    }

    protected abstract void loadWeb(String type, String url);

    public void initData() {
        mWebView = injectWebView();
        com.tencent.smtt.sdk.WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        if (SystemUtil.isNetworkConnected()) {
//            webSettings.setCacheMode(com.tencent.smtt.sdk.WebSettings.LOAD_DEFAULT);
//        } else {
//            webSettings.setCacheMode(com.tencent.smtt.sdk.WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        }
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLoadsImagesAutomatically(true);

        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
//        mWebView.addJavascriptInterface(new JsObject(), "demo");
//        String ua = webSettings.getUserAgentString() + "/demo/1.0/";
//        webSettings.setUserAgentString(ua);

        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
    }

    protected abstract WebView injectWebView();

    class JsObject {
        @JavascriptInterface
        public void open(String type, String url) {
            //            LinkWeb.openWeb(context, type, url);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return false;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void onPause() {
        super.onPause();
    }
}
