package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.impl;

import android.os.Handler;
import android.os.Looper;

import ua.org.rshu.fmi.mobapp.persistent.BasicEntity;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.EntitiesListWithProgressbarFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.EntitiesListWithProgressbarPresenter;

/**
 * Created by vb on 06/01/2018.
 */

public abstract class EntitiesListWithProgressbarPresenterImpl<T extends BasicEntity> extends EntitiesListPresenterImpl<T>
        implements EntitiesListWithProgressbarPresenter<T> {

    @Override
    public void showProgressBarFromMainThread() {
        Handler mainHandler = new Handler(Looper.getMainLooper());
        Runnable myRunnable = () -> ((EntitiesListWithProgressbarFragment) mEntitiesListFragment).showProgressBar();
        mainHandler.post(myRunnable);
    }

    @Override
    public void hideProgressBarFromMainThread() {
        Handler mainHandler = new Handler(Looper.getMainLooper());
        Runnable myRunnable = () -> ((EntitiesListWithProgressbarFragment) mEntitiesListFragment).hideProgressBar();
        mainHandler.post(myRunnable);
    }
}
