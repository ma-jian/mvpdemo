package com.m.mvpdemo.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

import com.m.mvpdemo.util.SystemUtil;

import java.util.ArrayList;

/**
 * @Author m
 * @Date 2017/5/27
 */

public class CategoryModel extends FrameLayout {

    private Context                  context;
    private int                      mScreenWidth;
    private int                      mCount;
    private ArrayList<ViewParameter> mParameterList;
    private int                      mRowCount;
    private int                      mRangeCount;
    private BaseAdapter              adapter;

    public CategoryModel(@NonNull Context context) {
        super(context, null);
    }

    public CategoryModel(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public CategoryModel(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void setadapter(Context context, BaseAdapter adapter) {
        this.adapter = adapter;
        mCount = adapter.getCount();
        initView(context);
    }

    private void initView(Context context) {
        mScreenWidth = SystemUtil.getScreenWidth(context);
        initchild(context);
    }

    private void initchild(Context context) {
        //行数
        double ceil = Math.ceil(mCount / 3.0);
        Log.d("ceil:", ceil + "mCount;" + mCount);
        mRowCount = (int) ceil;
        mRangeCount = 3;
        setPadding(1, 1, 1, SystemUtil.dp2px(context, 10));
        draw();
    }

    private void draw() {
        measureChild();
        addItemView();
    }

    private void addItemView() {
        if (mParameterList != null && mParameterList.size() > 0) {
            for (int i = 0; i < mCount; i++) {
                View view = adapter.getView(i, null, null);
                LayoutParams params = new LayoutParams(mParameterList.get(i).getWidth(), mParameterList.get(i).getHeight());
                params.topMargin = mParameterList.get(i).topPos;
                params.leftMargin = mParameterList.get(i).leftPos;
                view.setLayoutParams(params);
                view.setPadding(1, 1, 1, 1);
                addView(view);
            }
        }
    }

    private void measureChild() {
        mParameterList = new ArrayList<>();
        int leftPos = 0;
        int topPos = 0;
        int rightPos = 0;
        int bottomPos = 0;
        float ratioWidth = 0.3333f; //条目宽
        float ratioHeight = ratioWidth * 0.4f; //高 = 宽*0.4

        //宽
        int itemWidth = (int) (mScreenWidth * ratioWidth);
        //gao
        int itemHeight = (int) (mScreenWidth * ratioHeight);

        for (int j = 0; j < mRowCount; j++) {//行数
            for (int i = 0; i < mRangeCount; i++) {//列数
                leftPos = itemWidth * i;
                topPos = itemHeight * j;
                rightPos = itemWidth * (i + 1);
                bottomPos = itemHeight * (j + 1);
                mParameterList.add(new ViewParameter(leftPos, topPos, rightPos, bottomPos));
            }
        }
    }


    private class ViewParameter {
        private int leftPos;
        private int rightPos;
        private int topPos;
        private int bottomPos;

        //左上右下
        public ViewParameter(int leftPos, int topPos, int rightPos, int bottomPos) {
            this.leftPos = leftPos;
            this.rightPos = rightPos;
            this.topPos = topPos;
            this.bottomPos = bottomPos;
        }


        public int getWidth() {
            return rightPos - leftPos;
        }

        public int getHeight() {
            return bottomPos - topPos;
        }


    }
}
