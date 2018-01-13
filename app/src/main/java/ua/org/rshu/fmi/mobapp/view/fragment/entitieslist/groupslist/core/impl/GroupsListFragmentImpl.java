package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.core.impl;

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
import ua.org.rshu.fmi.mobapp.view.adapter.impl.GroupsListAdapter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.impl.EntitiesListWithProgressbarFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.core.GroupsListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.core.GroupsListPresenter;

public abstract class GroupsListFragmentImpl extends EntitiesListWithProgressbarFragmentImpl implements GroupsListFragment {

    @BindView(R.id.recycler_view_groups_list) RecyclerView mGroupsRecyclerView;

//    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    @Inject GroupsListPresenter mGroupsListPresenter;

    public static final String TOOLBAR_TITLE = "Groups";

    private GroupsListAdapter mGroupsListAdapter;

    private Unbinder mUnbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_groups_list, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        FMIApplication.getsAppComponent().inject(this);
        System.out.println("Presenter:" + (mGroupsListPresenter == null));

        if(mGroupsListPresenter != null) {
            mGroupsListPresenter.bindView(this);
        }

        setUpToolbar();
        initRecyclerView();


        return rootView;
    }



    @Override
    public void onPause() {
        super.onPause();
        mGroupsListPresenter.unbindView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mGroupsListPresenter.disposePagination();
    }


    @Override
    public void updateRecyclerView() {
        mGroupsListAdapter.notifyDataSetChanged();
        Logger.d("Recycler view is updated");
    }

    private void setUpToolbar() {
        setHasOptionsMenu(true);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Вибір групи");
        }
    }

    /**
     * A method which initializes recycler view with data
     */
    private void initRecyclerView() {
        mGroupsRecyclerView.setHasFixedSize(true);

        mGroupsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mGroupsListAdapter = new GroupsListAdapter(this, mGroupsListPresenter);
        mGroupsRecyclerView.setAdapter(mGroupsListAdapter);

        new GroupsListFragmentImpl.LoadForAdapterAsyncTask(this).execute();

        mGroupsListPresenter.subscribeRecyclerViewForPagination(mGroupsRecyclerView);
    }

    private static class LoadForAdapterAsyncTask extends AsyncTask<Void, Void, Void> {

        private GroupsListFragmentImpl groupsListFragment;

        LoadForAdapterAsyncTask(GroupsListFragmentImpl groupsListFragment) {
            this.groupsListFragment = groupsListFragment;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            groupsListFragment.mGroupsListPresenter.setDataToAdapter(groupsListFragment.mGroupsListAdapter);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            groupsListFragment.updateRecyclerView();
        }
    }
}
