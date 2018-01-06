package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.impl;

import android.support.v4.app.Fragment;
import android.widget.ProgressBar;

import butterknife.BindView;
import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.EntitiesListWithProgressbarFragment;

/**
 * Created by vb on 06/01/2018.
 */

public abstract class EntitiesListWithProgressbarFragmentImpl extends Fragment implements EntitiesListWithProgressbarFragment {

    @BindView(R.id.progress_bar) public ProgressBar mProgressBar;

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);
    }
}
