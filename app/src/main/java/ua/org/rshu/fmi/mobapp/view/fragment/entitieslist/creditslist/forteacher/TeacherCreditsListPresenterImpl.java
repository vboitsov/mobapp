package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.creditslist.forteacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Credit;
import ua.org.rshu.fmi.mobapp.service.fmiservices.FmiService;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.creditslist.core.impl.CreditsListPresenterImpl;

/**
 * Created by vb on 06/01/2018.
 */

public class TeacherCreditsListPresenterImpl extends CreditsListPresenterImpl {

    private FmiService mFmiService;

    public TeacherCreditsListPresenterImpl(FmiService mFmiService) {
        this.mFmiService = mFmiService;
    }

    @Override
    protected List<Credit> loadMoreForPagination(PaginationArgs paginationArgs) {
        boolean isConnected = false;
        List<Credit> creditsList = new ArrayList<>();

        showProgressBarFromMainThread();
        while (!isConnected) {
            try {
                creditsList = mFmiService.getGroupCredits(((TeacherCreditsListFragmentImpl) mEntitiesListFragment).getTeacherId(),
                        paginationArgs).execute().body();
                System.out.println("load more credits: " + creditsList);
                isConnected = true;
            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }
        hideProgressBarFromMainThread();
        return creditsList;
    }
}