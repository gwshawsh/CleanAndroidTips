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
package com.gws.android.tips.data.repository;

import com.gws.android.tips.data.entity.mapper.TipEntityDataMapper;
import com.gws.android.tips.data.repository.datasource.user.UserDataStore;
import com.gws.android.tips.data.repository.datasource.user.UserDataStoreFactory;
import com.gws.android.tips.domain.Result;
import com.gws.android.tips.domain.Tip;
import com.gws.android.tips.domain.repository.TipRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import cn.bmob.v3.BmobUser;
import io.reactivex.Observable;

/**
 * {@link TipRepository} for retrieving tip data.
 */
@Singleton
public class TipDataRepository implements TipRepository {

  private final UserDataStoreFactory userDataStoreFactory;
  private final TipEntityDataMapper tipEntityDataMapper;

  /**
   * Constructs a {@link TipRepository}.
   *
   * @param dataStoreFactory A factory to construct different data source implementations.
   * @param tipEntityDataMapper {@link TipEntityDataMapper}.
   */
  @Inject
  TipDataRepository(UserDataStoreFactory dataStoreFactory,
                    TipEntityDataMapper tipEntityDataMapper) {
    this.userDataStoreFactory = dataStoreFactory;
    this.tipEntityDataMapper = tipEntityDataMapper;
  }

  @Override public Observable<List<Tip>> tips() {
    final UserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();
    return userDataStore.userEntityList().map(this.tipEntityDataMapper::transform);
  }

  @Override public Observable<Tip> tip(int userId) {
    final UserDataStore userDataStore = this.userDataStoreFactory.create(userId);
    return userDataStore.userEntityDetails(userId).map(this.tipEntityDataMapper::transform);
  }

  @Override
  public Observable<Result> login(String userId, String password) {
    BmobUser user = new BmobUser();
    user.setUsername(userId);
    user.setPassword(password);
    return null;
  }
}
