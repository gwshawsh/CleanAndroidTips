/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.gws.android.tips.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import com.gws.android.tips.presentation.R;
import com.gws.android.tips.presentation.internal.di.HasComponent;
import com.gws.android.tips.presentation.internal.di.components.DaggerTipComponent;
import com.gws.android.tips.presentation.internal.di.components.TipComponent;
import com.gws.android.tips.presentation.ui.base.BaseActivity;
import com.gws.android.tips.presentation.ui.fragment.TipDetailsFragment;

/**
 * Activity that shows details of a certain tip.
 */
public class TipDetailsActivity extends BaseActivity implements HasComponent<TipComponent> {

  private static final String INTENT_EXTRA_PARAM_USER_ID = "org.android10.INTENT_PARAM_USER_ID";
  private static final String INSTANCE_STATE_PARAM_USER_ID = "org.android10.STATE_PARAM_USER_ID";

  public static Intent getCallingIntent(Context context, int userId) {
    Intent callingIntent = new Intent(context, TipDetailsActivity.class);
    callingIntent.putExtra(INTENT_EXTRA_PARAM_USER_ID, userId);
    return callingIntent;
  }

  private int userId;
  private TipComponent tipComponent;

  @Override
  protected int getLayoutId() {
    return R.layout.activity_layout;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.activity_layout);

    this.initializeActivity(savedInstanceState);
    this.initializeInjector();
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    if (outState != null) {
      outState.putInt(INSTANCE_STATE_PARAM_USER_ID, this.userId);
    }
    super.onSaveInstanceState(outState);
  }

  /**
   * Initializes this activity.
   */
  private void initializeActivity(Bundle savedInstanceState) {
    if (savedInstanceState == null) {
      this.userId = getIntent().getIntExtra(INTENT_EXTRA_PARAM_USER_ID, -1);
      addFragment(R.id.fragmentContainer, TipDetailsFragment.forUser(userId));
    } else {
      this.userId = savedInstanceState.getInt(INSTANCE_STATE_PARAM_USER_ID);
    }
  }

  private void initializeInjector() {
    this.tipComponent = DaggerTipComponent.builder()
        .appComponent(getAppComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override public TipComponent getComponent() {
    return tipComponent;
  }
}
