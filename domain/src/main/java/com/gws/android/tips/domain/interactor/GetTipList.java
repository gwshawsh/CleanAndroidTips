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

import com.gws.android.tips.domain.Tip;
import com.gws.android.tips.domain.executor.PostExecutionThread;
import com.gws.android.tips.domain.executor.ThreadExecutor;
import com.gws.android.tips.domain.repository.TipRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 */
public class GetTipList extends UseCase<List<Tip>, Void> {

  private final TipRepository tipRepository;

  @Inject
  GetTipList(TipRepository tipRepository, ThreadExecutor threadExecutor,
             PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.tipRepository = tipRepository;
  }

  @Override Observable<List<Tip>> buildUseCaseObservable(Void unused) {
    return this.tipRepository.tips();
  }
}
