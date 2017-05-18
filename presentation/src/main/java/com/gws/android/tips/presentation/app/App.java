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
package com.gws.android.tips.presentation.app;

import com.gws.android.tips.presentation.BuildConfig;
import com.gws.android.tips.presentation.app.base.BaseApp;
import com.gws.android.tips.presentation.internal.di.components.AppComponent;
import com.gws.android.tips.presentation.internal.di.components.DaggerAppComponent;
import com.gws.android.tips.presentation.internal.di.modules.AppModule;
import com.gws.android.tips.presentation.util.BuglyUtil;
import com.squareup.leakcanary.LeakCanary;

import cn.bmob.v3.Bmob;

/**
 * Android Main Application
 */
public class App extends BaseApp {

  private AppComponent appComponent;
  private static App mInstance;
  @Override public void onCreate() {
    super.onCreate();
    mInstance = this;
    this.initInjector();
    this.initializeInjector();
    this.initBugly();
    this.initBmob();
    initLeakcanary();
  }

  private void initializeInjector() {
    this.appComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .build();
  }

  public static App getInstance() {
    return mInstance;
  }

  public AppComponent getAppComponent(){
    return appComponent;
  }
  private void initInjector() {
    this.appComponent = DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .build();
  }
  private void initLeakcanary(){
    if(BuildConfig.DEBUG){
      LeakCanary.install(this);
    }
  }
  private void initBugly(){
    BuglyUtil.initBugly(this);
  }
  private void initBmob(){
    Bmob.initialize(this, Constants.BMOB_APPID);
  }
}
