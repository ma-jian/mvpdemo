package com.m.mvpdemo.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.m.mvpdemo.mvp.IView;
import com.m.mvpdemo.util.CommonUtil;


/**
 * @Author m
 * @Date 2017/5/26
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseImpl, IView {

    protected P mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
   }

    public void showErrorMsg(String msg) {
        CommonUtil.showShort(((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0), msg);
    }

}
