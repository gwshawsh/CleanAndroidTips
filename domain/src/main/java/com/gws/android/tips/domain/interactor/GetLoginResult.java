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
package com.gws.android.tips.domain.interactor;

import com.gws.android.tips.domain.Result;
import com.gws.android.tips.domain.executor.PostExecutionThread;
import com.gws.android.tips.domain.executor.ThreadExecutor;
import com.gws.android.tips.domain.repository.TipRepository;
import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;

import io.reactivex.Observable;


public class GetLoginResult extends UseCase<Result, GetLoginResult.Params> {

  private final TipRepository tipRepository;

  @Inject
  GetLoginResult(TipRepository tipRepository, ThreadExecutor threadExecutor,
                 PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.tipRepository = tipRepository;
  }

  @Override Observable<Result> buildUseCaseObservable(Params params) {
    Preconditions.checkNotNull(params);
    return this.tipRepository.login(params.userId,params.password);
  }

  public static final class Params {

    private final String userId;
    private final String password;

    private Params(String userId,String password) {
      this.userId = userId;
      this.password = password;
    }

    public static Params forUser(String userId,String password) {
      return new Params(userId,password);
    }
  }
}
