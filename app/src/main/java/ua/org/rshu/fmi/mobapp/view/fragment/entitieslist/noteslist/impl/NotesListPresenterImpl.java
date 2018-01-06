package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.noteslist.impl;

import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;

import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.ReplaySubject;
import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.entity.Note;
import ua.org.rshu.fmi.mobapp.service.notepadservices.core.NoteService;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.EntitiesListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.noteslist.NotesListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.noteslist.NotesListPresenter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.impl.EntitiesListPresenterImpl;
import ua.org.rshu.fmi.mobapp.view.listener.SearchViewOnQueryTextListener;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class NotesListPresenterImpl extends EntitiesListPresenterImpl<Note> implements NotesListPresenter {

    private NoteService mNoteService;

    private Disposable mSearchDisposable;

    private Disposable mFoundedPaginationDisposable;

    private ReplaySubject<PaginationArgs> mFoundedPaginationSubject;

    public NotesListPresenterImpl(NoteService entityService) {
        (mNoteService = entityService).openConnection();
    }

    @Override
    public void bindView(EntitiesListFragment allNotesFragment) {
        mEntitiesListFragment = allNotesFragment;
        if (!mNoteService.isConnectionOpened()) {
            mNoteService.openConnection();
        }
    }

    @Override
    public void unbindView() {
        mEntitiesListFragment = null;
        mNoteService.closeConnection();
    }
    @Override
    protected List<Note> loadMoreForPagination(PaginationArgs paginationArgs) {
        return mNoteService.getNotes(paginationArgs);
    }

    @Override
    public void subscribeSearchView(MenuItem searchItem) {
        MenuItemCompat.setOnActionExpandListener(searchItem, this);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        initSearchSubject(searchView);
        initFoundedPaginationSubject();
    }

    @Override
    public void disposeSearch() {
        if (mSearchDisposable != null && !mSearchDisposable.isDisposed()) {
            mSearchDisposable.dispose();
        }
        if (mFoundedPaginationDisposable != null && !mFoundedPaginationDisposable.isDisposed()) {
            mFoundedPaginationDisposable.dispose();
        }
    }

    private void initSearchSubject(SearchView searchView) {
        ReplaySubject<String> searchQuerySubject;

        mSearchDisposable = (searchQuerySubject = ReplaySubject.create())
                .debounce(400, TimeUnit.MILLISECONDS)
                .map(title -> loadMoreForSearch(title, new PaginationArgs()))
                .map(this::addFirstFoundedItemsToList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> mEntitiesListFragment.updateRecyclerView(),
                        throwable -> Logger.e(throwable, "Something really strange happened with search!"));

        searchView.setOnQueryTextListener(new SearchViewOnQueryTextListener(searchQuerySubject));
    }

    private void initFoundedPaginationSubject() {
        mFoundedPaginationDisposable = (mFoundedPaginationSubject = ReplaySubject.create())
                .observeOn(Schedulers.io())
                .map(paginationArgs -> loadMoreForSearch(((NotesListFragment) mEntitiesListFragment).getSearchQuery(), paginationArgs))
                .filter(notes -> !notes.isEmpty())
                .map(newNotes -> mEntities.addAll(newNotes))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> mEntitiesListFragment.updateRecyclerView(),
                        throwable -> Logger.e(throwable, "Something really strange happened with search pagination!"));
    }

    @NonNull
    private List<Note> loadMoreForSearch(String query, PaginationArgs paginationArgs) {
        return mNoteService.getByTitle(query, paginationArgs);
    }

    /**
     * A method which expands search view and changes a subject of recycler view's onScrollListener.
     * @param item
     * @return
     */
    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        ((NotesListFragment) mEntitiesListFragment).switchToSearchMode();
        mRecyclerViewOnScrollLister.changeSubject(mFoundedPaginationSubject);
        return true;
    }

    /**
     * A method which collapses search view and changes a subject of recycler view's onScrollListener.
     * @param item
     * @return
     */
    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        ((NotesListFragment) mEntitiesListFragment).switchToNormalMode();
        mRecyclerViewOnScrollLister.changeSubject(mPaginationSubject);
        addFirstFoundedItemsToList(loadMoreForPagination(new PaginationArgs()));
        mEntitiesListFragment.updateRecyclerView();
        return true;
    }

    protected boolean addFirstFoundedItemsToList(List<Note> firstFoundedNotes) {
        mRecyclerViewOnScrollLister.resetListener();
        mEntities.clear();
        mEntities.addAll(firstFoundedNotes);
        return true;
    }
}
