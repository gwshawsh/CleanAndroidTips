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
package com.gws.android.tips.data.repository.datasource.user;

import com.gws.android.tips.data.entity.TipEntity;
import com.gws.android.tips.domain.Result;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface UserDataStore {
  /**
   * Get an {@link Observable} which will emit a List of {@link TipEntity}.
   */
  Observable<List<TipEntity>> userEntityList();

  /**
   * Get an {@link Observable} which will emit a {@link TipEntity} by its id.
   *
   * @param userId The id to retrieve tip data.
   */
  Observable<TipEntity> userEntityDetails(final int userId);

  Observable<Result> login(final String userId, final String password);
}
