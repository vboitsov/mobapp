package ua.org.rshu.fmi.mobapp.view.adapter.impl;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.News;
import ua.org.rshu.fmi.mobapp.view.adapter.DataPostSetAdapter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.newslist.NewsListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.newslist.NewsListPresenter;


/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> implements DataPostSetAdapter<News> {

    private NewsListFragment mNewsListFragment;

    private List<News> mNews = new ArrayList<>();

    private NewsListPresenter mNewsListPresenter;

    public NewsListAdapter(NewsListFragment newsListFragment, NewsListPresenter newsListPresenter) {
        mNewsListFragment = newsListFragment;
        mNewsListPresenter = newsListPresenter;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        News news = mNews.get(position);
        Picasso.with(holder.newsPicImageView.getContext()).load(news.getPic()).into(holder.newsPicImageView);
        System.out.println("PICTURE LINK:" + news.getPic());


        holder.newsTitleTextView.setText(news.getTitle());
        holder.newsDateTextView.setText(news.getDate());
        holder.newsTextTextView.setText(news.getText());

        holder.itemView.setOnClickListener(v -> mNewsListFragment.showSelectedNews(news));

    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    @Override
    public void setData(List<News> data) {
        mNews = data;
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_view_news_pic) ImageView newsPicImageView;

        @BindView(R.id.text_view_news_title) TextView newsTitleTextView;

        @BindView(R.id.text_view_news_date) TextView newsDateTextView;

        @BindView(R.id.text_view_news_text) TextView newsTextTextView;

        NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


