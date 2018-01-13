package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.teacherslist.core.impl;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ua.org.rshu.fmi.mobapp.FMIApplication;
import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.view.adapter.impl.TeachersListAdapter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.impl.EntitiesListWithProgressbarFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.teacherslist.core.TeacherListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.teacherslist.core.TeacherListPresenter;


public abstract



class TeacherListFragmentImpl extends EntitiesListWithProgressbarFragmentImpl implements TeacherListFragment {

    @BindView(R.id.recycler_view_teachers_list) RecyclerView mTeachersRecyclerView;

//    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    @Inject
    TeacherListPresenter mTeacherListPresenter;

    public static final String TOOLBAR_TITLE = "News";

    private TeachersListAdapter mTeachersRecyclerViewAdapter;

    private Unbinder mUnbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_teachers_list, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        FMIApplication.getsAppComponent().inject(this);
        setUpToolbar();
        initRecyclerView();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mTeacherListPresenter != null) {
            mTeacherListPresenter.bindView(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mTeacherListPresenter.unbindView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mTeacherListPresenter.disposePagination();
    }

    @Override
    public void updateRecyclerView() {
        mTeachersRecyclerViewAdapter.notifyDataSetChanged();
        Logger.d("Recycler view is updated");
    }

    private void setUpToolbar() {
        setHasOptionsMenu(true);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Вибір викладача");
        }
    }


    /**
     * A method which initializes recycler view with data
     */
    private void initRecyclerView() {
        mTeachersRecyclerView.setHasFixedSize(true);

        mTeachersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mTeachersRecyclerViewAdapter = new TeachersListAdapter(this, mTeacherListPresenter);
        mTeachersRecyclerView.setAdapter(mTeachersRecyclerViewAdapter);

        new LoadForAdapterAsyncTask(this).execute();

        mTeacherListPresenter.subscribeRecyclerViewForPagination(mTeachersRecyclerView);
    }

    private static class LoadForAdapterAsyncTask extends AsyncTask<Void, Void, Void> {

        private TeacherListFragmentImpl teachersListFragment;

        LoadForAdapterAsyncTask(TeacherListFragmentImpl teachersListFragment) {
            this.teachersListFragment = teachersListFragment;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            teachersListFragment.mTeacherListPresenter.setDataToAdapter(teachersListFragment.mTeachersRecyclerViewAdapter);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            teachersListFragment.updateRecyclerView();
        }
    }

}
