package com.gws.android.tips.domain.repository;

import com.gws.android.tips.domain.Result;
import com.gws.android.tips.domain.Tip;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by gws on 2017/5/3.
 */

public interface TipsRepository {
    Observable<Tip> tip(final String tipId);
    Observable<List<Tip>> tips();
    Observable<Result> save(Tip tip);
}
