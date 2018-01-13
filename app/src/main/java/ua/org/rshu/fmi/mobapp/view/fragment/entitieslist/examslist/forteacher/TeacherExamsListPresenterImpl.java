package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.examslist.forteacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Exam;
import ua.org.rshu.fmi.mobapp.service.fmiservices.FmiService;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.examslist.core.impl.ExamsListPresenterImpl;

/**
 * Created by vb on 06/01/2018.
 */

public class TeacherExamsListPresenterImpl extends ExamsListPresenterImpl {

    private FmiService mFmiService;

    public TeacherExamsListPresenterImpl(FmiService mFmiService) {
        this.mFmiService = mFmiService;
    }

    @Override
    protected List<Exam> loadMoreForPagination(PaginationArgs paginationArgs) {
        boolean isConnected = false;
        List<Exam> examsList = new ArrayList<>();

        showProgressBarFromMainThread();
        while (!isConnected) {
            try {
                examsList = mFmiService.getTeacherExams(((TeacherExamsListFragmentImpl) mEntitiesListFragment).getTeacherId(),
                        paginationArgs).execute().body();
                isConnected = true;
            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
                if (mEntitiesListFragment == null) {
                    return new ArrayList<>();
                }
            }
        }
        hideProgressBarFromMainThread();
        return examsList;
    }
}
