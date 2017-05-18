package com.gws.android.tips.data.repository;

import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.TipEntityDao;
import com.gws.android.tips.data.entity.TipEntity;
import com.gws.android.tips.data.entity.mapper.TipsEntityDataMapper;
import com.gws.android.tips.domain.Result;
import com.gws.android.tips.domain.Tip;
import com.gws.android.tips.domain.repository.TipsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by gws on 2017/5/3.
 */

public class TipsDataRepository implements TipsRepository{
    DaoSession daoSession;
    TipsEntityDataMapper tipsEntityDataMapper;
    @Inject
    TipsDataRepository(DaoSession daoSession,TipsEntityDataMapper tipsEntityDataMapper){
        this.daoSession = daoSession;
        this.tipsEntityDataMapper = tipsEntityDataMapper;
    }

    @Override
    public Observable<Tip> tip(String tipId) {
        List<TipEntity> list = daoSession.getTipEntityDao().queryBuilder().where(TipEntityDao.Properties.Id.eq(tipId)).list();
        return Observable.just(list.get(0)).map(tipsEntityDataMapper::transform);
    }

    @Override
    public Observable<List<Tip>> tips() {
        List<TipEntity> list = daoSession.getTipEntityDao().loadAll();
        return Observable.just(list).map(tipsEntityDataMapper::transform);
    }

    @Override
    public Observable<Result> save(Tip tip) {
        return null;
    }
}
