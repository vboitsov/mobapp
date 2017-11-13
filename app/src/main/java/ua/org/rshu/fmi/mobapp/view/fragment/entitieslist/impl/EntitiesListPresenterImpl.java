package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.impl;

import android.support.v7.widget.RecyclerView;

import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.ReplaySubject;
import ua.org.rshu.fmi.mobapp.persistent.entity.Entity;
import ua.org.rshu.fmi.mobapp.service.BasicService;
import ua.org.rshu.fmi.mobapp.service.form.Form;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;
import ua.org.rshu.fmi.mobapp.view.adapter.DataPostSetAdapter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.EntitiesListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.EntitiesListPresenter;
import ua.org.rshu.fmi.mobapp.view.listener.RecyclerViewOnScrollListener;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public abstract class EntitiesListPresenterImpl<T1 extends Entity, T2 extends Form> implements EntitiesListPresenter<T1, T2> {
    private BasicService<T1,T2> mEntityService;

    protected EntitiesListFragment mEntitiesListFragment;

    protected RecyclerViewOnScrollListener mRecyclerViewOnScrollLister;

    private Disposable mPaginationDisposable;

    protected ReplaySubject<PaginationArgs> mPaginationSubject;

    protected List<T1> mEntities;

    protected abstract List<T1> loadMoreForPagination(PaginationArgs paginationArgs);

    public EntitiesListPresenterImpl(BasicService<T1, T2> entityService) {
        (mEntityService = entityService).openConnection();
    }

    @Override
    public void bindView(EntitiesListFragment allNotesFragment) {
        mEntitiesListFragment = allNotesFragment;
        if (!mEntityService.isConnectionOpened()) {
            mEntityService.openConnection();
        }
    }

    @Override
    public void unbindView() {
        mEntitiesListFragment = null;
        mEntityService.closeConnection();
    }

    @Override
    public void subscribeRecyclerViewForPagination(RecyclerView recyclerView) {
        initPaginationSubject();
        mRecyclerViewOnScrollLister = new RecyclerViewOnScrollListener(mPaginationSubject);
        recyclerView.addOnScrollListener(mRecyclerViewOnScrollLister);
    }

    @Override
    public void disposePagination() {
        if (mPaginationDisposable != null && !mPaginationDisposable.isDisposed()) {
            mPaginationDisposable.dispose();
        }
    }

    @Override
    public void setDataToAdapter(DataPostSetAdapter<T1> dataPostSetAdapter) {
        mEntities = loadMoreForPagination(new PaginationArgs());
        dataPostSetAdapter.setData(mEntities);
    }

    protected boolean addFirstFoundedItemsToList(List<T1> firstFoundedNotes) {
        mRecyclerViewOnScrollLister.resetListener();
        mEntities.clear();
        mEntities.addAll(firstFoundedNotes);
        return true;
    }

    private void initPaginationSubject() {
        mPaginationDisposable = (mPaginationSubject = ReplaySubject.create())
                .observeOn(Schedulers.io())
                .map(this::loadMoreForPagination)
                .filter(notes -> !notes.isEmpty())
                .map(newNotes -> mEntities.addAll(newNotes))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> mEntitiesListFragment.updateRecyclerView(),
                        throwable -> Logger.e(throwable, "Something really strange happened with pagination!"));
    }
}
