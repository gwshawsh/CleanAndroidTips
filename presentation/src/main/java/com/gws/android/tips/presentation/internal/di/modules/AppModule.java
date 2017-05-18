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
package com.gws.android.tips.presentation.internal.di.modules;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.anye.greendao.gen.DaoMaster;
import com.anye.greendao.gen.DaoSession;
import com.gws.android.tips.data.cache.UserCache;
import com.gws.android.tips.data.cache.UserCacheImpl;
import com.gws.android.tips.data.executor.JobExecutor;
import com.gws.android.tips.data.repository.TipDataRepository;
import com.gws.android.tips.domain.executor.PostExecutionThread;
import com.gws.android.tips.domain.executor.ThreadExecutor;
import com.gws.android.tips.domain.repository.TipRepository;
import com.gws.android.tips.presentation.UIThread;
import com.gws.android.tips.presentation.app.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class AppModule {
  private final App application;

  public AppModule(App application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton
  ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides @Singleton PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides @Singleton
  UserCache provideUserCache(UserCacheImpl userCache) {
    return userCache;
  }



  @Provides @Singleton
  TipRepository provideUserRepository(TipDataRepository userDataRepository) {
    return userDataRepository;
  }

  @Provides @Singleton
  DaoSession provideDaoSession() {
    SQLiteDatabase db = new DaoMaster.DevOpenHelper(application, "notes-db", null).getWritableDatabase();
    return new DaoMaster(db).newSession();
  }
}
