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
package com.gws.android.tips.presentation.internal.di.components;

import android.content.Context;

import com.anye.greendao.gen.DaoSession;
import com.gws.android.tips.domain.executor.PostExecutionThread;
import com.gws.android.tips.domain.executor.ThreadExecutor;
import com.gws.android.tips.domain.repository.TipRepository;
import com.gws.android.tips.presentation.internal.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = AppModule.class)
public interface AppComponent {
  /*void inject(BaseActivity baseActivity);
  void inject(BaseFragment baseFragment);*/

  //Exposed to sub-graphs.
  Context context();
  ThreadExecutor threadExecutor();
  PostExecutionThread postExecutionThread();
  TipRepository userRepository();
  DaoSession daoSession();
}
