package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.forgroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Day;
import ua.org.rshu.fmi.mobapp.service.fmiservices.FmiService;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.core.impl.ScheduleListPresenterImpl;

/**
 * Created by vb on 05/01/2018.
 */

public class GroupScheduleListPresenterImpl extends ScheduleListPresenterImpl {

    private FmiService mFmiService;

    public GroupScheduleListPresenterImpl(FmiService mFmiService) {
        this.mFmiService = mFmiService;
    }

    @Override
    protected List<Day> loadMoreForPagination(PaginationArgs paginationArgs) {
        boolean isConnected = false;
        List<Day> daysList = new ArrayList<>();

        showProgressBarFromMainThread();
        while (!isConnected) {
            try {
                daysList = mFmiService.getScheduleOfGroup(((GroupScheduleListFragmentImpl) mEntitiesListFragment).getGroupId(),
                        paginationArgs).execute().body();
                System.out.println("load more credits: " + daysList);
                isConnected = true;
            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
                if (mEntitiesListFragment == null) {
                    return new ArrayList<>();
                }
            }
        }
        hideProgressBarFromMainThread();
        return daysList;
    }
}
