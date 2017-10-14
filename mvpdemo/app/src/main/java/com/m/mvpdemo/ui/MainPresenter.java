package com.m.mvpdemo.ui;

import com.m.mvpdemo.base.BasePresenter;
import com.m.mvpdemo.bean.VersionBean;
import com.m.mvpdemo.contract.MainConstract;
import com.m.mvpdemo.exception.ExceptionHandle;
import com.m.mvpdemo.http.CommonSubscriber;
import com.m.mvpdemo.util.RxUtil;

import java.util.HashMap;

/**
 * Created by majian
 * Date on 2017/10/14
 */

class MainPresenter extends BasePresenter<MainModel,MainActivity> implements MainConstract.mainPresent {


//    @Override
//    public HashMap<String, IModel> loadModel(IModel... iModels) {
//        HashMap<String, IModel> modelMap = new HashMap<>();
//        modelMap.put("main", iModels[0]);
//        return modelMap;
//    }
//
//    @Override
//    public HashMap<String, IModel> getiModelMap() {
//        return loadModel(new MainModel());
//    }


    private MainModel main = (MainModel) getiModelMap().get("main");

    @Override
    public void checkVersion() {
        addSubscribe(
                main.checkVersion()
                        .compose(RxUtil.<VersionBean>rxSchedulerHelper())
                        .subscribeWith(new CommonSubscriber<VersionBean>() {
                            @Override
                            public void onError(ExceptionHandle.ResponeThrowable e) {
                                getView().showErrorMsg(e.message);
                            }

                            @Override
                            public void onNext(VersionBean t) {
                                getView().showVersion(t.toString());
                            }
                        })
        );
    }

    @Override
    public HashMap<String, MainModel> loadModel(MainModel... iModels) {
        HashMap<String, MainModel> modelMap = new HashMap<>();
        modelMap.put("main", iModels[0]);
        return modelMap;
    }

    @Override
    public HashMap<String, MainModel> getiModelMap() {
        return loadModel(new MainModel());
    }
}
