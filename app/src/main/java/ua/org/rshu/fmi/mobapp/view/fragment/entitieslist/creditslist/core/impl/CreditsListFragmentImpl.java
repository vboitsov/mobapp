package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.creditslist.core.impl;

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
import ua.org.rshu.fmi.mobapp.view.adapter.impl.CreditsListAdapter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.impl.EntitiesListWithProgressbarFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.creditslist.core.CreditsListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.creditslist.core.CreditsListPresenter;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;

public abstract class CreditsListFragmentImpl extends EntitiesListWithProgressbarFragmentImpl implements CreditsListFragment {

    @BindView(R.id.recycler_view_credits_list) RecyclerView mCreditsRecyclerView;

    protected CreditsListPresenter mCreditsListPresenter;

    public static final String TOOLBAR_TITLE = "Credits";

    private CreditsListAdapter mCreditsRecyclerViewAdapter;

    private Unbinder mUnbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_credits_list, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        if(mCreditsListPresenter != null) {
            mCreditsListPresenter.bindView(this);
        }

        setUpToolbar();
        initRecyclerView();

        System.out.println(getArguments().getLong(BundleKeysConst.BUNDLE_GROUP_ID_KEY));
        return rootView;
    }



    @Override
    public void onPause() {
        super.onPause();
        mCreditsListPresenter.unbindView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mCreditsListPresenter.disposePagination();
    }


    @Override
    public void updateRecyclerView() {
        mCreditsRecyclerViewAdapter.notifyDataSetChanged();
        Logger.d("Recycler view is updated");

    }

    private void setUpToolbar() {
        setHasOptionsMenu(true);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Заліки");
        }
    }


    /**
     * A method which initializes recycler view with data
     */
    private void initRecyclerView() {
        mCreditsRecyclerView.setHasFixedSize(true);

        mCreditsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mCreditsRecyclerViewAdapter = new CreditsListAdapter(this, mCreditsListPresenter);
        mCreditsRecyclerView.setAdapter(mCreditsRecyclerViewAdapter);

        new CreditsListFragmentImpl.LoadForAdapterAsyncTask(this).execute();

        mCreditsListPresenter.subscribeRecyclerViewForPagination(mCreditsRecyclerView);
    }

    private static class LoadForAdapterAsyncTask extends AsyncTask<Void, Void, Void> {

        private CreditsListFragmentImpl creditsListFragment;

        LoadForAdapterAsyncTask(CreditsListFragmentImpl creditsListFragment) {
            this.creditsListFragment = creditsListFragment;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            creditsListFragment.mCreditsListPresenter.setDataToAdapter(creditsListFragment.mCreditsRecyclerViewAdapter);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            creditsListFragment.updateRecyclerView();
        }
    }
}
