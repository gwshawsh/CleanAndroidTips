/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gws.android.tips.presentation.ui.presenter;

import com.gws.android.tips.domain.Tip;
import com.gws.android.tips.domain.exception.DefaultErrorBundle;
import com.gws.android.tips.domain.exception.ErrorBundle;
import com.gws.android.tips.domain.interactor.DefaultObserver;
import com.gws.android.tips.domain.interactor.GetTipList;
import com.gws.android.tips.presentation.exception.ErrorMessageFactory;
import com.gws.android.tips.presentation.internal.di.PerActivity;
import com.gws.android.tips.presentation.ui.base.BasePresenter;
import com.gws.android.tips.presentation.ui.base.Presenter;
import com.gws.android.tips.presentation.ui.view.LoadDataView;

import java.util.List;

import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class TipListPresenter extends BasePresenter<TipListPresenter.View> {

  public interface View extends LoadDataView{
    void renderTipList(List<Tip> tipModelCollection);
    void viewTip(Tip tipModel);
  }


  private final GetTipList getTipListUseCase;

  @Inject
  public TipListPresenter(GetTipList getTipsListTipCase) {
    this.getTipListUseCase = getTipsListTipCase;
  }

  @Override public void destroy() {
    this.getTipListUseCase.dispose();
  }

  public void initialize() {
    this.loadUserList();
  }


  private void loadUserList() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getUserList();
  }

  public void onUserClicked(Tip tipModel) {
    getView().viewTip(tipModel);
  }

  private void showViewLoading() {
    getView().showLoading();
  }

  private void hideViewLoading() {
    getView().hideLoading();
  }


  private void hideViewRetry() {
    getView().hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(getView().context(),
        errorBundle.getException());
    getView().showError(errorMessage);
  }

  private void showUsersInView(List<Tip> usersCollection) {
    getView().renderTipList(usersCollection);
  }

  private void getUserList() {
    this.getTipListUseCase.execute(new TipListObserver(), null);
  }

  private final class TipListObserver extends DefaultObserver<List<Tip>> {

    @Override public void onComplete() {
      TipListPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      TipListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      getView().hideLoading();
      getView().showError(new DefaultErrorBundle((Exception) e).getErrorMessage());
      getView().showRetry();
    }

    @Override public void onNext(List<Tip> users) {
      TipListPresenter.this.showUsersInView(users);
    }
  }
}
