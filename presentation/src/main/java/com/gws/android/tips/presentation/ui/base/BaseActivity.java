package com.gws.android.tips.presentation.ui.base;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gws.android.tips.presentation.app.App;
import com.gws.android.tips.presentation.internal.di.components.AppComponent;
import com.gws.android.tips.presentation.internal.di.modules.ActivityModule;
import com.gws.android.tips.presentation.navigation.Navigator;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Base {@link android.app.Activity} class for every Activity in this application.
 */
public abstract class  BaseActivity<V,T extends BasePresenter<V>> extends AppCompatActivity {

  @Inject protected Navigator navigator;
  @Inject
  protected T mPresenter;


  protected abstract int getLayoutId();
  protected  void initInject(){

  }
  /**
   * setContentView 之前的操作
   */
  public void init() {
  }
  protected  void initView(Bundle savedInstanceState) {
  }
  protected  void initData() {
  }

  protected  void initListener() {
  }
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    init();
    if(null!=mPresenter){
      mPresenter.attachView((V) this);
    }
    setContentView(getLayoutId());
    ButterKnife.bind(this);
    initInject();
    initView(savedInstanceState);
    initData();
    initListener();
  }

  @Override
  protected void onDestroy() {
    ButterKnife.unbind(this);
    super.onDestroy();
    if (mPresenter != null) {
      mPresenter.detachView();
    }
  }

  protected void addFragment(int containerViewId, Fragment fragment) {
    final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
    fragmentTransaction.add(containerViewId, fragment);
    fragmentTransaction.commit();
  }


  protected AppComponent getAppComponent() {
    return App.getInstance().getAppComponent();
  }

  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }


}
