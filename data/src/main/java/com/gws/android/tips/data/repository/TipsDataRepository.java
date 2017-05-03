package com.gws.android.tips.data.repository;

import com.gws.android.tips.domain.Result;
import com.gws.android.tips.domain.User;
import com.gws.android.tips.domain.repository.UserRepository;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by gws on 2017/5/3.
 */

public class TipsDataRepository implements UserRepository{
    @Override
    public Observable<List<User>> users() {
        return null;
    }

    @Override
    public Observable<User> user(int userId) {
        return null;
    }

    @Override
    public Observable<Result> login(String userId, String password) {
        return null;
    }
}
