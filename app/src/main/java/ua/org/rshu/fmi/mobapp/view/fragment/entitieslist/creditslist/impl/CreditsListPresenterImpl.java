package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.creditslist.impl;

import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Exam;
import ua.org.rshu.fmi.mobapp.service.fmiservices.FmiService;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.EntitiesListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.impl.EntitiesListWithProgressbarPresenterImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.creditslist.CreditsListPresenter;

/**
 * Created by vb on 21/11/2017.
 */

public abstract class CreditsListPresenterImpl extends EntitiesListWithProgressbarPresenterImpl<Exam> implements CreditsListPresenter {

    private FmiService mFmiService;

    public CreditsListPresenterImpl(FmiService mFmiService) {
        this.mFmiService = mFmiService;
    }

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
////        boolean isConnected = false;
////        List<Exam> examsList = new ArrayList<>();
////
////        showProgressBarFromMainThread();
////        while (!isConnected) {
////            try {
////                examsList = mFmiService.getCredits(((CreditsListFragment) mEntitiesListFragment).getGroupId(),
////                        paginationArgs).execute().body();
////                System.out.println("load more credits: " + examsList);
////                isConnected = true;
////            } catch (NullPointerException| IOException e) {
////                e.printStackTrace();
////            }
////        }
////        hideProgressBarFromMainThread();
////        return examsList;
//    }

//    private void showProgressBarFromMainThread() {
//        Handler mainHandler = new Handler(Looper.getMainLooper());
//        Runnable myRunnable = () -> ((CreditsListFragment) mEntitiesListFragment).showProgressBar();
//        mainHandler.post(myRunnable);
//    }
//
//    private void hideProgressBarFromMainThread() {
//        Handler mainHandler = new Handler(Looper.getMainLooper());
//        Runnable myRunnable = () -> ((CreditsListFragment) mEntitiesListFragment).hideProgressBar();
//        mainHandler.post(myRunnable);
//    }
}
