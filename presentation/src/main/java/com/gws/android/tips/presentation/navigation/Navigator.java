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
package com.gws.android.tips.presentation.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.gws.android.tips.presentation.app.App;
import com.gws.android.tips.presentation.ui.activity.TipsListActivity;
import com.gws.android.tips.presentation.ui.activity.TipDetailsActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

  @Inject
  public Navigator() {
    //empty
  }


  public void toTipsList() {
    this.to(TipsListActivity.getCallingIntent(getContext()));
  }

  public void toTipDetails(int userId) {
    Intent intent = TipDetailsActivity.getCallingIntent(getContext(), userId);
    this.to(intent);
  }

  public void to(@NonNull Class<? extends Activity> targetActivity) {
    this.to(new Intent(getContext(),targetActivity));
  }

  public void to(Intent intent) {
    Context context =getContext();
    if (context != null) {
      context.startActivity(intent);
    }
  }

  private Context getContext(){
    return App.getInstance().getCurrentActivity();
  }
}
