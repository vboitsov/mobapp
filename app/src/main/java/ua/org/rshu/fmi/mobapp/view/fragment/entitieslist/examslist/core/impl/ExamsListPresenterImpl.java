package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.examslist.core.impl;

import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Exam;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.EntitiesListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.impl.EntitiesListWithProgressbarPresenterImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.examslist.core.ExamsListPresenter;

/**
 * Created by vb on 21/11/2017.
 */

public abstract class ExamsListPresenterImpl extends EntitiesListWithProgressbarPresenterImpl<Exam> implements ExamsListPresenter {

    @Override
    public void bindView(EntitiesListFragment creditsListFragment) {
        mEntitiesListFragment = creditsListFragment;
    }

    @Override
    public void unbindView() {
        mEntitiesListFragment = null;
    }


}
