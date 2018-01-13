package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.core.impl;

import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Day;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.EntitiesListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.impl.EntitiesListWithProgressbarPresenterImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.core.ScheduleListPresenter;

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
