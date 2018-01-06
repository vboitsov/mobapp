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

//    @Override
//    protected List<Exam> loadMoreForPagination(PaginationArgs paginationArgs) {
//        boolean isConnected = false;
//        List<Exam> examsList = new ArrayList<>();
//
//        showProgressBarFromMainThread();
//        while (!isConnected) {
//            try {
//                examsList = mFmiService.getT(((ExamsListFragment) mEntitiesListFragment).getGroupId(),
//                        paginationArgs).execute().body();
//                isConnected = true;
//            } catch (NullPointerException| IOException e) {
//                e.printStackTrace();
//            }
//        }
//        hideProgressBarFromMainThread();
//        return examsList;
//    }

//    protected void showProgressBarFromMainThread() {
//        Handler mainHandler = new Handler(Looper.getMainLooper());
//        Runnable myRunnable = () -> ((ExamsListFragment) mEntitiesListFragment).showProgressBar();
//        mainHandler.post(myRunnable);
//    }
//
//    protected void hideProgressBarFromMainThread() {
//        Handler mainHandler = new Handler(Looper.getMainLooper());
//        Runnable myRunnable = () -> ((ExamsListFragment) mEntitiesListFragment).hideProgressBar();
//        mainHandler.post(myRunnable);
//    }
}
