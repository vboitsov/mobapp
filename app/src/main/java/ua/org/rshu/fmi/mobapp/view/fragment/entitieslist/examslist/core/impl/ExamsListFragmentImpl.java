package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.examslist.core.impl;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import ua.org.rshu.fmi.mobapp.view.adapter.impl.ExamsListAdapter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.impl.EntitiesListWithProgressbarFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.examslist.core.ExamsListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.examslist.core.ExamsListPresenter;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;

public abstract class ExamsListFragmentImpl extends EntitiesListWithProgressbarFragmentImpl implements ExamsListFragment {

    @BindView(R.id.recycler_view_credits_list) RecyclerView mExamsRecyclerView;

//    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    protected ExamsListPresenter mExamsListPresenter;

    public static final String TOOLBAR_TITLE = "Credits";

    private ExamsListAdapter mExamsRecyclerViewAdapter;

    private Unbinder mUnbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_credits_list, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        setUpToolbar();
        initRecyclerView();

        System.out.println(getArguments().getLong(BundleKeysConst.BUNDLE_GROUP_ID_KEY));
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mExamsListPresenter != null) {
            mExamsListPresenter.bindView(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mExamsListPresenter.unbindView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mExamsListPresenter.disposePagination();
    }


    @Override
    public void updateRecyclerView() {
        mExamsRecyclerViewAdapter.notifyDataSetChanged();
        Logger.d("Recycler view is updated");

    }

    private void setUpToolbar() {
        setHasOptionsMenu(true);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Екзамени");
        }
    }

    /**
     * A method which initializes recycler view with data
     */
    private void initRecyclerView() {
        mExamsRecyclerView.setHasFixedSize(true);

        mExamsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mExamsRecyclerViewAdapter = new ExamsListAdapter(this, mExamsListPresenter);
        mExamsRecyclerView.setAdapter(mExamsRecyclerViewAdapter);

        new ExamsListFragmentImpl.LoadForAdapterAsyncTask(this).execute();

        mExamsListPresenter.subscribeRecyclerViewForPagination(mExamsRecyclerView);
    }

    private static class LoadForAdapterAsyncTask extends AsyncTask<Void, Void, Void> {

        private ExamsListFragmentImpl examsListFragment;

        LoadForAdapterAsyncTask(ExamsListFragmentImpl examsListFragment) {
            this.examsListFragment = examsListFragment;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            examsListFragment.mExamsListPresenter.setDataToAdapter(examsListFragment.mExamsRecyclerViewAdapter);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            examsListFragment.updateRecyclerView();
        }
    }
}
