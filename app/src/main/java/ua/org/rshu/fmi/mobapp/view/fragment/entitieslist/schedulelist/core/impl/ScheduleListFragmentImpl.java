package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.core.impl;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.view.adapter.impl.ScheduleListAdapter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.impl.EntitiesListWithProgressbarFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.core.ScheduleListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.core.ScheduleListPresenter;

public class ScheduleListFragmentImpl extends EntitiesListWithProgressbarFragmentImpl implements ScheduleListFragment {

    @BindView(R.id.recycler_view_schedule_list) RecyclerView mScheduleRecyclerView;

//    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    protected ScheduleListPresenter mScheduleListPresenter;

    public static final String TOOLBAR_TITLE = "Schedule";

    private ScheduleListAdapter mScheduleRecyclerViewAdapter;

    private Unbinder mUnbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_schedule_list, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
//        FMIApplication.getsAppComponent().inject(this);
//        setUpToolbar();
        initRecyclerView();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mScheduleListPresenter != null) {
            mScheduleListPresenter.bindView(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mScheduleListPresenter.unbindView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mScheduleListPresenter.disposePagination();
    }


    @Override
    public void updateRecyclerView() {
        mScheduleRecyclerViewAdapter.notifyDataSetChanged();
        Logger.d("Recycler view is updated");
    }

    /**
     * A method which initializes recycler view with data
     */
    private void initRecyclerView() {
        mScheduleRecyclerView.setHasFixedSize(true);

        mScheduleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mScheduleRecyclerViewAdapter = new ScheduleListAdapter(this, mScheduleListPresenter);
        mScheduleRecyclerView.setAdapter( mScheduleRecyclerViewAdapter);

        new ScheduleListFragmentImpl.LoadForAdapterAsyncTask(this).execute();

        mScheduleListPresenter.subscribeRecyclerViewForPagination(mScheduleRecyclerView);
    }

    private static class LoadForAdapterAsyncTask extends AsyncTask<Void, Void, Void> {

        private ScheduleListFragmentImpl scheduleListFragment;

        LoadForAdapterAsyncTask(ScheduleListFragmentImpl scheduleListFragment) {
            this.scheduleListFragment = scheduleListFragment;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            scheduleListFragment.mScheduleListPresenter.setDataToAdapter(scheduleListFragment.mScheduleRecyclerViewAdapter);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            scheduleListFragment.updateRecyclerView();
        }
    }
}
