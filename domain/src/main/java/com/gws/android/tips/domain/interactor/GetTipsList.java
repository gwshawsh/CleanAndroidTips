package com.gws.android.tips.domain.interactor;

import com.gws.android.tips.domain.Tip;
import com.gws.android.tips.domain.executor.PostExecutionThread;
import com.gws.android.tips.domain.executor.ThreadExecutor;
import com.gws.android.tips.domain.repository.TipsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by gws on 2017/5/4.
 */

public class GetTipsList extends UseCase<List<Tip>,Void>{
    TipsRepository tipsRepository;
    @Inject
    GetTipsList(TipsRepository tipsRepository,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.tipsRepository = tipsRepository;
    }

    @Override
    Observable<List<Tip>> buildUseCaseObservable(Void aVoid) {
        return tipsRepository.tips();
    }
}
