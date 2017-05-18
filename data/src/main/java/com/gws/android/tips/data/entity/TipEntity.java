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
package com.gws.android.tips.data.entity;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

/**
 * User Entity used in the data layer.
 */
public class TipEntity {

  @Id
  private String id;
  private String title;
  private String content;
  private String creatTime = new Date().toString();
  private String updateTime;
  public String getUpdateTime() {
    return this.updateTime;
  }
  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }
  public String getCreatTime() {
    return this.creatTime;
  }
  public void setCreatTime(String creatTime) {
    this.creatTime = creatTime;
  }
  public String getContent() {
    return this.content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getTitle() {
    return this.title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getId() {
    return this.id;
  }
  public void setId(String id) {
    this.id = id;
  }
  @Generated(hash = 1697931926)
  public TipEntity(String id, String title, String content, String creatTime,
                   String updateTime) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.creatTime = creatTime;
    this.updateTime = updateTime;
  }
  @Generated(hash = 1338328526)
  public TipEntity() {
  }
}
