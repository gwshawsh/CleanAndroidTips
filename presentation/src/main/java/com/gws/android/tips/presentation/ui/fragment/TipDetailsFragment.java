/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.gws.android.tips.presentation.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fernandocejas.arrow.checks.Preconditions;
import com.gws.android.tips.domain.Tip;
import com.gws.android.tips.presentation.R;
import com.gws.android.tips.presentation.internal.di.components.TipComponent;
import com.gws.android.tips.presentation.ui.base.BaseFragment;
import com.gws.android.tips.presentation.ui.presenter.TipDetailsPresenter;
import com.gws.android.tips.presentation.ui.widget.AutoLoadImageView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that shows details of a certain tip.
 */
public class TipDetailsFragment extends BaseFragment<TipDetailsPresenter.View,TipDetailsPresenter> implements TipDetailsPresenter.View {
  private static final String PARAM_USER_ID = "param_user_id";

  @Inject
  TipDetailsPresenter tipDetailsPresenter;

  @Bind(R.id.iv_cover)
  AutoLoadImageView iv_cover;
  @Bind(R.id.tv_fullname) TextView tv_fullname;
  @Bind(R.id.tv_email) TextView tv_email;
  @Bind(R.id.tv_followers) TextView tv_followers;
  @Bind(R.id.tv_description) TextView tv_description;
  @Bind(R.id.rl_progress) RelativeLayout rl_progress;
  @Bind(R.id.rl_retry) RelativeLayout rl_retry;
  @Bind(R.id.bt_retry) Button bt_retry;

  public static TipDetailsFragment forUser(int userId) {
    final TipDetailsFragment tipDetailsFragment = new TipDetailsFragment();
    final Bundle arguments = new Bundle();
    arguments.putInt(PARAM_USER_ID, userId);
    tipDetailsFragment.setArguments(arguments);
    return tipDetailsFragment;
  }

  public TipDetailsFragment() {
    setRetainInstance(true);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(TipComponent.class).inject(this);
  }

  @Override
  public void initView(View rootView, Bundle savedInstanceState) {
    super.initView(rootView, savedInstanceState);
    if (savedInstanceState == null) {
      this.loadUserDetails();
    }
  }

  @Override public void onResume() {
    super.onResume();
    this.tipDetailsPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.tipDetailsPresenter.pause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_user_details;
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.tipDetailsPresenter.destroy();
  }


  @Override public void showLoading() {
    this.rl_progress.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    this.rl_progress.setVisibility(View.GONE);
  }

  @Override public void showRetry() {
    this.rl_retry.setVisibility(View.VISIBLE);
  }

  @Override public void hideRetry() {
    this.rl_retry.setVisibility(View.GONE);
  }

  @Override public void showError(String message) {
    this.toast(message);
  }

  @Override public Context context() {
    return getActivity().getApplicationContext();
  }

  /**
   * Load tip details.
   */
  private void loadUserDetails() {
    if (this.tipDetailsPresenter != null) {
      this.tipDetailsPresenter.initialize(currentUserId());
    }
  }


  private int currentUserId() {
    final Bundle arguments = getArguments();
    Preconditions.checkNotNull(arguments, "Fragment arguments cannot be null");
    return arguments.getInt(PARAM_USER_ID);
  }

  @OnClick(R.id.bt_retry)
  void onButtonRetryClick() {
    TipDetailsFragment.this.loadUserDetails();
  }

  @Override
  public void renderTip(Tip tip) {
    if (tip != null) {
      /*this.iv_cover.setImageUrl(tip.getCoverUrl());
      this.tv_fullname.setText(tip.getFullName());
      this.tv_email.setText(tip.getEmail());
      this.tv_followers.setText(String.valueOf(tip.getFollowers()));
      this.tv_description.setText(tip.getDescription());*/
    }
  }
}
