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
package com.gws.android.tips.data.net;

import com.gws.android.tips.data.entity.TipEntity;
import com.gws.android.tips.domain.Result;

import java.util.List;

import io.reactivex.Observable;

/**
 * RestApi for retrieving data from the network.
 */
public interface RestApi {
  String API_BASE_URL =
      "https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture/";

  /** Api url for getting all tips */
  String API_URL_GET_USER_LIST = API_BASE_URL + "tips.json";
  /** Api url for getting a tip profile: Remember to concatenate id + 'json' */
  String API_URL_GET_USER_DETAILS = API_BASE_URL + "user_";

  /**
   * Retrieves an {@link Observable} which will emit a List of {@link TipEntity}.
   */
  Observable<List<TipEntity>> userEntityList();

  /**
   * Retrieves an {@link Observable} which will emit a {@link TipEntity}.
   *
   * @param userId The tip id used to get tip data.
   */
  Observable<TipEntity> userEntityById(final int userId);

  Observable<Result> login(final String userId,final String password) throws ClassNotFoundException;
}
