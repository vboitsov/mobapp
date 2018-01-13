package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.courselist.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Course;
import ua.org.rshu.fmi.mobapp.service.fmiservices.FmiService;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.EntitiesListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.impl.EntitiesListWithProgressbarPresenterImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.courselist.CoursesListPresenter;

/**
 * Created by vb on 11/01/2018.
 */

public class CoursesListPresenterImpl extends EntitiesListWithProgressbarPresenterImpl<Course> implements CoursesListPresenter {

    private FmiService mFmiService;

    public CoursesListPresenterImpl(FmiService mFmiService) {
        this.mFmiService = mFmiService;
    }

    @Override
    public void bindView(EntitiesListFragment coursesFragment) {
        System.out.println("WE BIND COURSES FRAGMENT!");
        mEntitiesListFragment = coursesFragment;
    }

    @Override
    public void unbindView() {
        System.out.println("WE UNBIND COURSES FRAGMENT!");
        mEntitiesListFragment = null;
    }

    @Override
    protected List<Course> loadMoreForPagination(PaginationArgs paginationArgs) {
        boolean isConnected = false;
        List<Course> courseList = new ArrayList<>();

        showProgressBarFromMainThread();
        while (!isConnected) {
            try {
                if (mEntitiesListFragment == null) System.out.println("FRAGMENT IS NULL!");
                courseList = mFmiService.getListOfCourses(((CoursesListFragmentImpl) mEntitiesListFragment).getGroupId(), paginationArgs).execute().body();
                isConnected = true;
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        hideProgressBarFromMainThread();
        return courseList;
    }
}
