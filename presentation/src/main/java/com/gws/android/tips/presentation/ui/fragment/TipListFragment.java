/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.gws.android.tips.presentation.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gws.android.tips.domain.Tip;
import com.gws.android.tips.presentation.R;
import com.gws.android.tips.presentation.internal.di.components.TipComponent;
import com.gws.android.tips.presentation.ui.base.BaseFragment;
import com.gws.android.tips.presentation.ui.presenter.TipListPresenter;
import com.gws.android.tips.presentation.ui.adapter.TipsAdapter;
import com.gws.android.tips.presentation.ui.adapter.UsersLayoutManager;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;


public class TipListFragment extends BaseFragment<TipListPresenter.View,TipListPresenter> implements TipListPresenter.View {


  @Inject
  TipsAdapter tipsAdapter;
  @Bind(R.id.swiperefreshlayout)
  SwipeRefreshLayout swipeRefreshLayout;
  @Bind(R.id.rv_users) RecyclerView rv_users;
  @Bind(R.id.rl_progress) RelativeLayout rl_progress;
  @Bind(R.id.rl_retry) RelativeLayout rl_retry;
  @Bind(R.id.bt_retry) Button bt_retry;


  public TipListFragment() {
    setRetainInstance(true);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(TipComponent.class).inject(this);
  }


  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if (savedInstanceState == null) {
      this.loadUserList();
    }

  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_user_list;
  }

  @Override
  public void initView(View rootView, Bundle savedInstanceState) {
    super.initView(rootView, savedInstanceState);
    setupRecyclerView();
  }

  @Override
  public void initListener() {
    super.initListener();
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        loadUserList();
      }
    });
  }

  @Override public void showLoading() {
    this.rl_progress.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    this.rl_progress.setVisibility(View.GONE);
    swipeRefreshLayout.setRefreshing(false);
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

  @Override public void renderTipList(List<Tip> tipModels) {
    if (tipModels != null) {
      this.tipsAdapter.setNewData(tipModels);
    }
  }

  @Override public void viewTip(Tip tipModel) {

  }



  @Override public Context context() {
    return this.getActivity();
  }

  private void setupRecyclerView() {
    this.tipsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
      @Override
      public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        mPresenter.onUserClicked(tipsAdapter.getItem(i));
      }
    });
    this.rv_users.setLayoutManager(new UsersLayoutManager(context()));
    this.rv_users.setAdapter(tipsAdapter);
  }


  private void loadUserList() {
    mPresenter.initialize();
  }

  @OnClick(R.id.bt_retry) void onButtonRetryClick() {
    loadUserList();
  }


  public void renderUserList(List<Tip> tipModelCollection) {

  }
}
