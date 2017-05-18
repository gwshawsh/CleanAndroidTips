package com.gws.android.tips.presentation.ui.activity;

import android.os.Bundle;
import android.widget.Button;

import com.gws.android.tips.presentation.R;
import com.gws.android.tips.presentation.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Main application screen. This is the app entry point.
 */
public class MainActivity extends BaseActivity {

  @Override
  protected int getLayoutId() {
    return R.layout.activity_main;
  }

  @OnClick(R.id.btn_LoadData)
  void navigateToTipsList() {
    this.navigator.toTipsList();
  }
}
