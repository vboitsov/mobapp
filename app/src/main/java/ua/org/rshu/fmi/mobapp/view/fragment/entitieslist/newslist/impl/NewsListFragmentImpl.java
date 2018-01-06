package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.newslist.impl;

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
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.News;
import ua.org.rshu.fmi.mobapp.view.adapter.impl.NewsListAdapter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.impl.EntitiesListWithProgressbarFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.newslist.NewsListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.newslist.NewsListPresenter;
import ua.org.rshu.fmi.mobapp.view.fragment.newscontent.NewsContentFragment;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;

public class NewsListFragmentImpl extends EntitiesListWithProgressbarFragmentImpl implements NewsListFragment {

    @BindView(R.id.recycler_view_news_list) RecyclerView mNewsRecyclerView;

//    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    @Inject NewsListPresenter mNewsListPresenter;

    public static final String TOOLBAR_TITLE = "News";

    private NewsListAdapter mNewsRecyclerViewAdapter;

    private Unbinder mUnbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_list, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        FMIApplication.getsAppComponent().inject(this);
        setUpToolbar();

        initRecyclerView();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mNewsListPresenter != null) {
            mNewsListPresenter.bindView(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mNewsListPresenter.unbindView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mNewsListPresenter.disposePagination();
    }


    @Override
    public void updateRecyclerView() {
        mNewsRecyclerViewAdapter.notifyDataSetChanged();
        Logger.d("Recycler view is updated");
    }

    @Override
    public void showSelectedNews(News news) {
        NewsContentFragment newsContentFragment = new NewsContentFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(BundleKeysConst.BUNDLE_NEWS_OBJECT_KEY, news);
        newsContentFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.constraint_container, newsContentFragment, "NEWS_CONTENT_TAG")
                .addToBackStack(null)
                .commit();

    }

    private void setUpToolbar() {
        setHasOptionsMenu(true);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Новини та оголошення");
        }
    }

    /**
     * A method which initializes recycler view with data
     */
    private void initRecyclerView() {
        mNewsRecyclerView.setHasFixedSize(true);

        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mNewsRecyclerViewAdapter = new NewsListAdapter(this, mNewsListPresenter);
        mNewsRecyclerView.setAdapter(mNewsRecyclerViewAdapter);

        new LoadForAdapterAsyncTask(this).execute();

        mNewsListPresenter.subscribeRecyclerViewForPagination(mNewsRecyclerView);
    }

    private static class LoadForAdapterAsyncTask extends AsyncTask<Void, Void, Void> {

        private NewsListFragmentImpl newsListFragment;

        LoadForAdapterAsyncTask(NewsListFragmentImpl newsListFragment) {
            this.newsListFragment = newsListFragment;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            newsListFragment.mNewsListPresenter.setDataToAdapter(newsListFragment.mNewsRecyclerViewAdapter);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            newsListFragment.updateRecyclerView();
        }
    }
}
