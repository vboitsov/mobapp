package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.newslist;

import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.News;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.EntitiesListWithProgressbarFragment;

/**
 * Created by vb on 16/11/2017.
 */

public interface NewsListFragment extends EntitiesListWithProgressbarFragment {

//    void showProgressBar();
//
//    void hideProgressBar();

    void showSelectedNews(News news);



}
