package com.m.mvpdemo.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.ViewGroup;

import com.m.mvpdemo.mvp.IView;
import com.m.mvpdemo.util.CommonUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author m
 * @Date 2017/5/23
 */

public abstract class BaseCommenActivity<P extends BasePresenter> extends FragmentActivity implements IView {

    protected P        mPresenter;
    private   boolean  isExit;
    private   long     mExitTime;
    public    Context  mContext;
    private   Unbinder mBind;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayoutId());
        mPresenter = loadPresenter();
        mBind = ButterKnife.bind(this);
        mContext = this;
        initView();
        initLoad();
        initData();
        initListener();
    }

    protected abstract void initListener();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract P loadPresenter();

    protected abstract int getlayoutId();

    public void initLoad() {
        if (mPresenter != null) {
//            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        mBind.unbind();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }


    protected void openActivity(Class<? extends BaseCommenActivity> toActivity) {
        openActivity(toActivity, null);
    }

    protected void openActivity(Class<? extends BaseCommenActivity> toActivity, Bundle bundle) {
        Intent intent = new Intent(this, toActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit) {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    CommonUtil.showToast("再按一次退出程序");
                    mExitTime = System.currentTimeMillis();
                } else {
                    finish();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
   }

    public void showErrorMsg(String msg) {
        CommonUtil.showShort(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg);
    }
}
