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
public class TipsEntityDataMapper {

  @Inject
  TipsEntityDataMapper() {}

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
      tip.setContent(tipEntity.getContent());
      tip.setCreatTime(tipEntity.getCreatTime());
      tip.setTitle(tipEntity.getTitle());
      tip.setUpdateTime(tipEntity.getUpdateTime());
    }
    return tip;
  }

  /**
   * Transform a List of {@link TipEntity} into a Collection of {@link Tip}.
   *
   * @param tipEntityCollection Object Collection to be transformed.
   * @return {@link Tip} if valid {@link TipEntity} otherwise null.
   */
  public List<Tip> transform(Collection<TipEntity> tipEntityCollection) {
    final List<Tip> tipList = new ArrayList<>(20);
    for (TipEntity tipEntity : tipEntityCollection) {
      final Tip tip = transform(tipEntity);
      if (tip != null) {
        tipList.add(tip);
      }
    }
    return tipList;
  }
}
