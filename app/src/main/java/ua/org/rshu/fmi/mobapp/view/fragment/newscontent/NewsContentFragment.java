package ua.org.rshu.fmi.mobapp.view.fragment.newscontent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.News;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;

public class NewsContentFragment extends Fragment {

    @BindView(R.id.image_view_news_pic) ImageView newsPicImageView;

    @BindView(R.id.text_view_news_title) TextView newsTitleTextView;

    @BindView(R.id.text_view_news_date) TextView newsDateTextView;

    @BindView(R.id.text_view_news_text) TextView newsTextTextView;

    private Unbinder mUnbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_content, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        fillContent();
        setUpToolbar();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null){
            mUnbinder.unbind();
        }
    }

    /**
     * A method which hears when user click on button and goes one fragment below from current
     */
    @OnClick(R.id.im_btn_arrow_back_to_list)
    public void backToPreviousFragment() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        getActivity().onBackPressed();
    }

    private void fillContent() {
        News news = getArguments().getParcelable(BundleKeysConst.BUNDLE_NEWS_OBJECT_KEY);

        Picasso.with(newsPicImageView.getContext()).load(news.getPic()).into(newsPicImageView);

        newsTitleTextView.setText(news.getTitle());

        newsDateTextView.setText(news.getDate());

        newsTextTextView.setText(news.getText());

    }

    /**
     * A method which sets defined view of main toolbar
     */
    private void setUpToolbar() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

}
