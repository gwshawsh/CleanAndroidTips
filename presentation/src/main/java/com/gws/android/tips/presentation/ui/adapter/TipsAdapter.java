/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.gws.android.tips.presentation.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gws.android.tips.domain.Tip;
import com.gws.android.tips.presentation.R;

import javax.inject.Inject;

/**
 * Adaptar that manages a collection of {@link Tip}.
 */
public class TipsAdapter extends BaseQuickAdapter<Tip,BaseViewHolder> {

  @Inject
  public TipsAdapter() {
    super(R.layout.item_list_tip);
  }


  @Override
  protected void convert(BaseViewHolder baseViewHolder, Tip tipModel) {
    baseViewHolder.setText(R.id.title,tipModel.getTitle());
  }
}
