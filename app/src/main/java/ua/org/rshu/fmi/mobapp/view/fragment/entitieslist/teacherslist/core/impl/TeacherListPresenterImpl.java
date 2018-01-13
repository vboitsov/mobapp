package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.teacherslist.core.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Teacher;
import ua.org.rshu.fmi.mobapp.service.fmiservices.FmiService;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.EntitiesListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.impl.EntitiesListWithProgressbarPresenterImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.teacherslist.core.TeacherListPresenter;

/**
 * Created by vb on 18/11/2017.
 */

public class TeacherListPresenterImpl extends EntitiesListWithProgressbarPresenterImpl<Teacher> implements TeacherListPresenter {

    private FmiService mFmiService;

    public TeacherListPresenterImpl(FmiService mFmiService) {
        this.mFmiService = mFmiService;
    }

    @Override
    public void bindView(EntitiesListFragment teachersListFragment) {
        mEntitiesListFragment = teachersListFragment;
    }

    @Override
    public void unbindView() {
        mEntitiesListFragment = null;
    }

    @Override
    protected List<Teacher> loadMoreForPagination(PaginationArgs paginationArgs) {
        boolean isConnected = false;
        List<Teacher> teacherList = new ArrayList<>();

        showProgressBarFromMainThread();
        while (!isConnected) {
            try {
                teacherList = mFmiService.getListOfTeachers(paginationArgs).execute().body();
                System.out.println("load more teacher: " + teacherList);
                isConnected = true;
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
                if (mEntitiesListFragment == null) {
                    return new ArrayList<>();
                }
            }
        }
        hideProgressBarFromMainThread();
        return teacherList;
    }

}
