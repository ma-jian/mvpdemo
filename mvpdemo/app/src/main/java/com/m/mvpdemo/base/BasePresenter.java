package com.m.mvpdemo.base;


import com.m.mvpdemo.mvp.IModel;
import com.m.mvpdemo.mvp.IPresenter;
import com.m.mvpdemo.mvp.IView;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @Author m
 * @Date 2017/5/23
 *
 * 范型传入 model，view类型
 */

public abstract class BasePresenter<M extends IModel, V extends IView> implements IPresenter<V> {
    private WeakReference mWeakReference;
    protected V                   mView;
    protected CompositeDisposable mCompositeDisposable;

    @Override
    public void attachView(V view) {
        mWeakReference = new WeakReference(view);
        mView = view;
    }

    @Override
    public void detachView() {
        if (mWeakReference != null) {
            mWeakReference.clear();
            mWeakReference = null;
        }
        unSubscribe();
    }


    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    @Override
    public V getView() {
        if (mWeakReference.get() == null)
            throw new IllegalStateException("view not found");
        return (V) mWeakReference.get();
    }


    //多种model
    public abstract HashMap<String, M> loadModel(M... iModels);

    public abstract HashMap<String, M> getiModelMap();

}
