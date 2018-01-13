package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.newslist.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.News;
import ua.org.rshu.fmi.mobapp.service.fmiservices.FmiService;
import ua.org.rshu.fmi.mobapp.util.PaginationArgs;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.EntitiesListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.impl.EntitiesListWithProgressbarPresenterImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.newslist.NewsListPresenter;

/**
 * Created by vb on 17/11/2017.
 */

public class NewsListPresenterImpl extends EntitiesListWithProgressbarPresenterImpl<News> implements NewsListPresenter {

    private FmiService mFmiService;

    public NewsListPresenterImpl(FmiService mFmiService) {
        this.mFmiService = mFmiService;
    }

    @Override
    public void bindView(EntitiesListFragment newsListFragment) {
        mEntitiesListFragment = newsListFragment;
    }

    @Override
    public void unbindView() {
        mEntitiesListFragment = null;
    }

    @Override
    protected List<News> loadMoreForPagination(PaginationArgs paginationArgs) {
        boolean isConnected = false;
        List<News> newsList = new ArrayList<>();

        showProgressBarFromMainThread();
        while (!isConnected) {
            try {
                newsList = mFmiService.getNews(paginationArgs).execute().body();
                System.out.println("load more news: " + newsList);
                isConnected = true;
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
                if (mEntitiesListFragment == null) {
                    return new ArrayList<>();
                }
            }
        }
        hideProgressBarFromMainThread();
        return newsList;
    }

}
