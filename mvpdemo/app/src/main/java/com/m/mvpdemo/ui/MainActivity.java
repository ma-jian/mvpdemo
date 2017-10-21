package com.m.mvpdemo.ui;

import android.view.View;
import android.widget.TextView;

import com.m.mvpdemo.R;
import com.m.mvpdemo.base.BaseActivity;
import com.m.mvpdemo.contract.MainConstract;

/**
 * Created by majian
 * Date on 2017/10/14
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainConstract.mainView{
    @Override
    protected void initListener() {
        findViewById(R.id.check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.checkVersion();
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.attachView(this);
    }

    @Override
    protected MainPresenter loadPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.main_activity;
    }

    @Override
    protected int getmainLayoutId() {
        return R.id.view;
    }

    @Override
    public void showVersion(String s) {
        TextView text = (TextView) findViewById(R.id.text);

        text.setText(s);
    }
}
