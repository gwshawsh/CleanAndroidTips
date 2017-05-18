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
import com.gws.android.tips.domain.Tip;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link TipEntity} (in the data layer) to {@link Tip} in the
 * domain layer.
 */
@Singleton
public class TipEntityDataMapper {

  @Inject
  TipEntityDataMapper() {}

  /**
   * Transform a {@link TipEntity} into an {@link Tip}.
   *
   * @param tipEntity Object to be transformed.
   * @return {@link Tip} if valid {@link TipEntity} otherwise null.
   */
  public Tip transform(TipEntity tipEntity) {
    Tip tip = null;
    if (tipEntity != null) {
      tip = new Tip();
      tip.setId(tipEntity.getId());
      tip.setTitle(tipEntity.getTitle());
      tip.setCreatTime(tipEntity.getCreatTime());
      tip.setContent(tipEntity.getContent());
      tip.setUpdateTime(tipEntity.getUpdateTime());
    }
    return tip;
  }

  /**
   * Transform a List of {@link TipEntity} into a Collection of {@link Tip}.
   *
   * @param userEntityCollection Object Collection to be transformed.
   * @return {@link Tip} if valid {@link TipEntity} otherwise null.
   */
  public List<Tip> transform(Collection<TipEntity> userEntityCollection) {
    final List<Tip> userList = new ArrayList<>(20);
    for (TipEntity userEntity : userEntityCollection) {
      final Tip user = transform(userEntity);
      if (user != null) {
        userList.add(user);
      }
    }
    return userList;
  }
}
