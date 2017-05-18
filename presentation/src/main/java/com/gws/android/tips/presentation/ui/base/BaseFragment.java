/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.gws.android.tips.presentation.ui.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gws.android.tips.presentation.app.App;
import com.gws.android.tips.presentation.internal.di.HasComponent;
import com.gws.android.tips.presentation.internal.di.components.AppComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Base {@link android.app.Fragment} class for every fragment in this application.
 */
public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {
  private Activity baseActivity;
  @Inject
  protected T mPresenter;
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
  }

  protected void toast(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    init();
    if (mPresenter != null) {
      mPresenter.attachView((V) this);//因为之后所有的子类都要实现对应的View接口
    }
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(getLayoutId(), container, false);
    ButterKnife.bind(this, view);
    initInject();
    return view;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initView(view,savedInstanceState);
    initData();
    initListener();
  }

  @Override
  public void onDestroyView() {
    ButterKnife.unbind(this);
    super.onDestroyView();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if (mPresenter != null) {
      mPresenter.detachView();
    }
  }

  protected abstract int getLayoutId();

  protected <C> C getComponent(Class<C> componentType) {
    return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
  }

  public void init() {

  }

  public void initView(View rootView, Bundle savedInstanceState) {
  }

  public void initData() {

  }

  public void initListener() {

  }
  protected  void initInject(){

  }
  protected AppComponent getAppComponent() {
    return App.getInstance().getAppComponent();
  }
}
