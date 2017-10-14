package com.m.mvpdemo.base;

import android.view.View;
import android.view.ViewGroup;

import com.m.mvpdemo.R;
import com.wang.avi.AVLoadingIndicatorView;



/**
 * @Author m
 * @Date 2017/5/26
 *
 * 可以提供 各种状态view的切换
 */

public abstract class BaseActivity<P extends BasePresenter> extends BaseCommenActivity<P> {

    private static final int STATE_MAIN    = 0x00;
    private static final int STATE_LOADING = 0x01;
    private static final int STATE_ERROR   = 0x02;
    private static final int STATE_EMPTY   = 0x03;
    private AVLoadingIndicatorView mLoading;
    private int     currentState  = STATE_MAIN;
    private int     errorResource = R.layout.error_layout;
    private boolean errorIsAdded  = false;
    private boolean emptyIsAdded  = false;
    private View      mErrorView;
    private View      mNodata;
    private ViewGroup mViewMain;
    private ViewGroup mParent;
    private View      mViewLoading;

    @Override
    public void stateError() {
        if (currentState == STATE_ERROR)
            return;
        if (!errorIsAdded) {
            mErrorView = View.inflate(mContext, errorResource, mParent);
            if (mErrorView == null) {
                throw new IllegalStateException(
                        "A View should be named 'view_error' in ErrorLayoutResource.");
            }
        }
        hideCurrentView();
        errorIsAdded = true;
        currentState = STATE_ERROR;
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void stateEmpty() {
        if (currentState == STATE_EMPTY)
            return;
        if (!emptyIsAdded) {
            View.inflate(mContext, R.layout.empty_layout, mParent);
            mNodata = mParent.findViewById(R.id.nodata);
        }
        hideCurrentView();
        emptyIsAdded = true;
        currentState = STATE_EMPTY;
        mNodata.setVisibility(View.VISIBLE);
    }

    @Override
    public void stateLoading() {
        if (currentState == STATE_LOADING)
            return;
        hideCurrentView();
        currentState = STATE_LOADING;
        mViewLoading.setVisibility(View.VISIBLE);
        mLoading.show();
    }

    @Override
    public void stateMain() {
        if (currentState == STATE_MAIN)
            return;
        hideCurrentView();
        currentState = STATE_MAIN;
        mViewMain.setVisibility(View.VISIBLE);
    }

    private void hideCurrentView() {
        switch (currentState) {
            case STATE_MAIN:
                if (mViewMain != null) {
                    mViewMain.setVisibility(View.GONE);
                }
                break;
            case STATE_LOADING:
                mLoading.hide();
                mViewLoading.setVisibility(View.GONE);
                break;
            case STATE_ERROR:
                if (mErrorView != null) {
                    mErrorView.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public void initView() {
        mViewMain = (ViewGroup) findViewById(getmainLayoutId());
        if (mViewMain == null) {
            throw new IllegalStateException(
                    "The subclass of RootActivity must contain a View named 'view_main'. must be setting mViewMain");
        }
        if (!(mViewMain.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "view_main's ParentView should be a ViewGroup.");
        }
        mParent = (ViewGroup) mViewMain.getParent();
        View.inflate(mContext, R.layout.loading_layout, mParent);
        mViewLoading = mParent.findViewById(R.id.view_loading);
        mLoading = (AVLoadingIndicatorView) mViewLoading.findViewById(R.id.loading);
        mViewLoading.setVisibility(View.GONE);
        mViewMain.setVisibility(View.VISIBLE);
    }

    protected abstract int getmainLayoutId();

    public void setErrorResource(int errorLayoutResource) {
        this.errorResource = errorLayoutResource;
    }
}
