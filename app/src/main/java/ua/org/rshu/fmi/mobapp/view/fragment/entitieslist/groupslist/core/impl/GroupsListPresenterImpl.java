package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.core.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Group;
import ua.org.rshu.fmi.mobapp.service.fmiservices.FmiService;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.EntitiesListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.impl.EntitiesListWithProgressbarPresenterImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.core.GroupsListPresenter;

/**
 * Created by vb on 19/11/2017.
 */

public class GroupsListPresenterImpl extends EntitiesListWithProgressbarPresenterImpl<Group> implements GroupsListPresenter {

    private FmiService mFmiService;

    public GroupsListPresenterImpl(FmiService mFmiService) {
        this.mFmiService = mFmiService;
    }

    @Override
    public void bindView(EntitiesListFragment groupsListFragment) {
        mEntitiesListFragment = groupsListFragment;
    }

    @Override
    public void unbindView() {
        mEntitiesListFragment = null;
    }

    @Override
    protected List<Group> loadMoreForPagination(PaginationArgs paginationArgs) {
        boolean isConnected = false;
        List<Group> groupsList = new ArrayList<>();

        showProgressBarFromMainThread();
        while (!isConnected) {
            try {
                groupsList = mFmiService.getListOfGroups(paginationArgs).execute().body();
                isConnected = true;
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
                if (mEntitiesListFragment == null) {
                    return new ArrayList<>();
                }
            }
        }
        hideProgressBarFromMainThread();
        return groupsList;
    }

//    protected void showProgressBarFromMainThread() {
//        Handler mainHandler = new Handler(Looper.getMainLooper());
//        Runnable myRunnable = () -> ((GroupsListFragment) mEntitiesListFragment).showProgressBar();
//        mainHandler.post(myRunnable);
//    }
//
//    protected void hideProgressBarFromMainThread() {
//        Handler mainHandler = new Handler(Looper.getMainLooper());
//        Runnable myRunnable = () -> ((GroupsListFragment) mEntitiesListFragment).hideProgressBar();
//        mainHandler.post(myRunnable);
//    }
}
