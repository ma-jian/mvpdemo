package com.m.mvpdemo.util;


import com.m.mvpdemo.base.BaseHttpResult;
import com.m.mvpdemo.exception.ApiException;
import com.m.mvpdemo.exception.ExceptionHandle;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by m.
 */
public class RxUtil {

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<BaseHttpResult<T>, T> handleResult() {   //判断结果
        return new FlowableTransformer<BaseHttpResult<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<BaseHttpResult<T>> httpResponseFlowable) {

                return httpResponseFlowable.flatMap(new Function<BaseHttpResult<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(BaseHttpResult<T> baseHttpResult) {
                        if (baseHttpResult.getErrorCode() == 200) { //根据服务器约定判断
                            return createData(baseHttpResult.getResultData());
                        } else {
                            return Flowable.error(new ApiException(baseHttpResult.getErrorMsg(), baseHttpResult.getErrorCode()));
                        }
                    }
                }).onErrorResumeNext(new Function<Throwable, Publisher<? extends T>>() {
                    @Override
                    public Publisher<? extends T> apply(@NonNull Throwable throwable) throws Exception {
                        return Flowable.error(ExceptionHandle.handleException(throwable));
                    }
                });
            }
        };
    }


    /**
     * 生成Flowable
     *
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }

}
