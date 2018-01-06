package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.core.impl;

import android.os.Handler;
import android.os.Looper;

import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Day;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.EntitiesListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.impl.EntitiesListWithProgressbarPresenterImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.creditslist.CreditsListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.core.ScheduleListPresenter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.impl.EntitiesListPresenterImpl;

/**
 * Created by vb on 04/01/2018.
 */

public abstract class ScheduleListPresenterImpl extends EntitiesListWithProgressbarPresenterImpl<Day> implements ScheduleListPresenter {

    @Override
    public void bindView(EntitiesListFragment creditsListFragment) {
        mEntitiesListFragment = creditsListFragment;
    }

    @Override
    public void unbindView() {
        mEntitiesListFragment = null;
    }

}
