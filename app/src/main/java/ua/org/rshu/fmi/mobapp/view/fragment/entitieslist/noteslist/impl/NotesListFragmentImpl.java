package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.noteslist.impl;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ua.org.rshu.fmi.mobapp.FMIApplication;
import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.entity.Note;
import ua.org.rshu.fmi.mobapp.view.activity.noteeditor.impl.NoteEditorActivityImpl;
import ua.org.rshu.fmi.mobapp.view.adapter.impl.NotesListAdapter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.noteslist.NotesListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.noteslist.NotesListPresenter;
import ua.org.rshu.fmi.mobapp.view.fragment.notecontent.NoteContentFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;
import ua.org.rshu.fmi.mobapp.view.util.consts.FragmentConst;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class NotesListFragmentImpl extends Fragment implements NotesListFragment {
    @BindView(R.id.recycler_view_all_entities) RecyclerView mNotesRecyclerView;

    @BindView(R.id.fab_new_note) FloatingActionButton mNewNoteFab;

    @Inject NotesListPresenter mNotesListPresenter;

    public static final String TOOLBAR_TITLE = "All Notes";

    private Unbinder mUnbinder;

    private NotesListAdapter mNotesRecyclerViewAdapter;

    private SearchView mSearchView;

    private MenuItem mMenuSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notes_list, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        FMIApplication.getsAppComponent().inject(this);
        setUpToolbar();
        initRecyclerView();
        return rootView;
    }
    
    @Override
    public void onResume() {
        super.onResume();
        if(mNotesListPresenter != null) {
            mNotesListPresenter.bindView(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mNotesListPresenter.unbindView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_entities_list, menu);

        mMenuSearch = menu.findItem(R.id.item_action_search);

        mSearchView = (SearchView) MenuItemCompat.getActionView(mMenuSearch);

        mNotesListPresenter.subscribeSearchView(mMenuSearch);

        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mNotesListPresenter.disposePagination();
        mNotesListPresenter.disposeSearch();
    }

    @Override
    public void updateRecyclerView() {
        mNotesRecyclerViewAdapter.notifyDataSetChanged();
        Logger.d("Recycler view is updated");
    }

    @Override
    public void showSelectedNote(Note note) {
        NoteContentFragmentImpl noteContentFragmentImpl = new NoteContentFragmentImpl();

        Bundle bundle = new Bundle();
        bundle.putParcelable(BundleKeysConst.BUNDLE_NOTE_OBJECT_KEY, note);
        noteContentFragmentImpl.setArguments(bundle);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.constraint_container, noteContentFragmentImpl, FragmentConst.TAG_NOTE_CONTENT_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public String getSearchQuery() {
        return mSearchView.getQuery().toString();
    }

    @Override
    public void switchToSearchMode() {
        mSearchView.setQueryHint(getString(R.string.fragment_all_notes_menu_search_query_hint));
        mSearchView.requestFocus();
        Drawable bottomUnderline = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            bottomUnderline = getResources().getDrawable(R.drawable.search_view_bottom_underline, null);
        }
        mSearchView.setBackground(bottomUnderline);
    }

    @Override
    public void switchToNormalMode() {
    }

    /**
     * A method which initializes recycler view with data
     */
    private void initRecyclerView() {
        mNotesRecyclerView.setHasFixedSize(true);

        mNotesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mNotesRecyclerViewAdapter = new NotesListAdapter(this,mNotesListPresenter);
//        mNotesListPresenter.setDataToAdapter(mNotesRecyclerViewAdapter);
        mNotesRecyclerView.setAdapter(mNotesRecyclerViewAdapter);
        mNotesListPresenter.setDataToAdapter(mNotesRecyclerViewAdapter);
        updateRecyclerView();
        mNotesListPresenter.subscribeRecyclerViewForPagination(mNotesRecyclerView);
    }

    /**
     * A method which sets defined view of toolbar
     */
    private void setUpToolbar() {
        setHasOptionsMenu(true);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Блокнот");
        }
    }

    @OnClick(R.id.fab_new_note)
    public void startNoteEditorActivity() {
        getActivity().startActivity(new Intent(getActivity(), NoteEditorActivityImpl.class));
        getActivity().finish();
    }


}