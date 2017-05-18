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
package com.gws.android.tips.data.entity.mapper;

import com.gws.android.tips.data.entity.TipEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import javax.inject.Inject;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
public class TipEntityJsonMapper {

  private final Gson gson;

  @Inject
  public TipEntityJsonMapper() {
    this.gson = new Gson();
  }

  /**
   * Transform from valid json string to {@link TipEntity}.
   *
   * @param userJsonResponse A json representing a tip profile.
   * @return {@link TipEntity}.
   * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
   */
  public TipEntity transformUserEntity(String userJsonResponse) throws JsonSyntaxException {
    final Type userEntityType = new TypeToken<TipEntity>() {}.getType();
    return this.gson.fromJson(userJsonResponse, userEntityType);
  }

  /**
   * Transform from valid json string to List of {@link TipEntity}.
   *
   * @param userListJsonResponse A json representing a collection of tips.
   * @return List of {@link TipEntity}.
   * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
   */
  public List<TipEntity> transformUserEntityCollection(String userListJsonResponse)
      throws JsonSyntaxException {
    final Type listOfUserEntityType = new TypeToken<List<TipEntity>>() {}.getType();
    return this.gson.fromJson(userListJsonResponse, listOfUserEntityType);
  }
}
