package com.gws.android.tips.presentation.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
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
public abstract class  BaseActivity extends AppCompatActivity {

  @Inject Navigator navigator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getApplicationComponent().inject(this);
  }

  @Override
  public void setContentView(@LayoutRes int layoutResID) {
    super.setContentView(layoutResID);
    ButterKnife.bind(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    App.getInstance().setCurrentActivity(this);
  }

  @Override
  protected void onPause() {
    super.onPause();

  }

  @Override
  protected void onDestroy() {
    clearReferences();
    super.onDestroy();
  }

  private void clearReferences(){
    Activity currActivity = App.getInstance().getCurrentActivity();
    if (this.equals(currActivity))
      App.getInstance().setCurrentActivity(null);
  }
  /**
   * Adds a {@link Fragment} to this activity's layout.
   *
   * @param containerViewId The container view to where add the fragment.
   * @param fragment The fragment to be added.
   */
  protected void addFragment(int containerViewId, Fragment fragment) {
    final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
    fragmentTransaction.add(containerViewId, fragment);
    fragmentTransaction.commit();
  }

  /**
   * Get the Main Application component for dependency injection.
   *
   * @return {@link AppComponent}
   */
  protected AppComponent getApplicationComponent() {
    return ((App) getApplication()).getAppComponent();
  }

  /**
   * Get an Activity module for dependency injection.
   *
   * @return {@link ActivityModule}
   */
  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }


}
