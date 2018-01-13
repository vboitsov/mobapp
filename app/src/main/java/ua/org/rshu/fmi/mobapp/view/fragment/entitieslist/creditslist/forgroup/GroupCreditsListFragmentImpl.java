package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.creditslist.forgroup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ua.org.rshu.fmi.mobapp.FMIApplication;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.creditslist.core.impl.CreditsListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;

/**
 * Created by vb on 06/01/2018.
 */

public class GroupCreditsListFragmentImpl extends CreditsListFragmentImpl {

    @Inject GroupCreditsListPresenterImpl mGroupCreditsListPresenter;

    private long groupId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        groupId = getArguments().getLong(BundleKeysConst.BUNDLE_GROUP_ID_KEY);
        FMIApplication.getsAppComponent().inject(this);
        mCreditsListPresenter = mGroupCreditsListPresenter;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public long getGroupId() {
        return groupId;
    }
}
