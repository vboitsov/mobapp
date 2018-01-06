package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core;

import ua.org.rshu.fmi.mobapp.persistent.BasicEntity;

/**
 * Created by vb on 06/01/2018.
 */

public interface EntitiesListWithProgressbarPresenter<T extends BasicEntity> extends EntitiesListPresenter<T> {

    void showProgressBarFromMainThread();

    void hideProgressBarFromMainThread();
}
