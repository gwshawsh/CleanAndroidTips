/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
import com.gws.android.tips.domain.interactor.GetTipDetails;
import com.gws.android.tips.domain.interactor.GetTipDetails.Params;
import com.gws.android.tips.presentation.exception.ErrorMessageFactory;
import com.gws.android.tips.presentation.internal.di.PerActivity;
import com.gws.android.tips.presentation.ui.base.BasePresenter;
import com.gws.android.tips.presentation.ui.base.Presenter;
import com.gws.android.tips.presentation.ui.view.LoadDataView;

import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class TipDetailsPresenter extends BasePresenter<TipDetailsPresenter.View> {

  public interface View extends LoadDataView{
    void renderTip(Tip user);
  }


  private final GetTipDetails getTipDetailsUseCase;

  @Inject
  public TipDetailsPresenter(GetTipDetails getTipDetailsUseCase) {
    this.getTipDetailsUseCase = getTipDetailsUseCase;
  }

 

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.getTipDetailsUseCase.dispose();
  }

  /**
   * Initializes the presenter by showing/hiding proper views
   * and retrieving tip details.
   */
  public void initialize(int userId) {
    this.hideViewRetry();
    this.showViewLoading();
    this.getUserDetails(userId);
  }

  private void getUserDetails(int userId) {
    this.getTipDetailsUseCase.execute(new UserDetailsObserver(), Params.forTip(userId));
  }

  private void showViewLoading() {
    getView().showLoading();
  }

  private void hideViewLoading() {
    getView().hideLoading();
  }

  private void showViewRetry() {
    getView().showRetry();
  }

  private void hideViewRetry() {
    getView().hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(getView().context(),
        errorBundle.getException());
    getView().showError(errorMessage);
  }

  private void showUserDetailsInView(Tip tip) {
    getView().renderTip(tip);
  }

  private final class UserDetailsObserver extends DefaultObserver<Tip> {

    @Override public void onComplete() {
      TipDetailsPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      TipDetailsPresenter.this.hideViewLoading();
      TipDetailsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      TipDetailsPresenter.this.showViewRetry();
    }

    @Override public void onNext(Tip user) {
      TipDetailsPresenter.this.showUserDetailsInView(user);
    }
  }
}
