/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.gws.android.tips.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.gws.android.tips.presentation.R;
import com.gws.android.tips.presentation.internal.di.HasComponent;
import com.gws.android.tips.presentation.internal.di.components.DaggerTipComponent;
import com.gws.android.tips.presentation.internal.di.components.TipComponent;
import com.gws.android.tips.presentation.ui.base.BaseActivity;
import com.gws.android.tips.presentation.ui.fragment.TipListFragment;


public class TipsListActivity extends BaseActivity implements HasComponent<TipComponent>{

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, TipsListActivity.class);
  }

  private TipComponent tipComponent;

  @Override
  protected int getLayoutId() {
    return R.layout.activity_layout;
  }

  @Override
  protected void initView(Bundle savedInstanceState) {
    super.initView(savedInstanceState);
    if (savedInstanceState == null) {
      addFragment(R.id.fragmentContainer, new TipListFragment());
    }
  }

  @Override
  protected void initInject() {
    super.initInject();
    this.tipComponent = DaggerTipComponent.builder()
            .appComponent(getAppComponent())
            .activityModule(getActivityModule())
            .build();
  }

  @Override public TipComponent getComponent() {
    return tipComponent;
  }

}
