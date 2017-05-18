package com.gws.android.tips.presentation.ui.base;

import android.app.Activity;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import javax.inject.Inject;

/**
 * Created by gws on 2017/5/18.
 */

public class BasePresenter<V> implements Presenter {
    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Inject
    public Activity mContext;

    @Inject
    public BasePresenter() {

    }
    protected Reference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public V getView() {
        return mViewRef != null ? mViewRef.get() : null;
    }
}
