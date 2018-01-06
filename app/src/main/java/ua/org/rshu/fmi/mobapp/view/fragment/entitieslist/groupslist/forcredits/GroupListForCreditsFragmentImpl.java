package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.forcredits;

import android.os.Bundle;

import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Group;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.creditslist.impl.CreditsListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.core.impl.GroupsListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;

/**
 * Created by vb on 22/11/2017.
 */

public class GroupListForCreditsFragmentImpl extends GroupsListFragmentImpl {

    @Override
    public void showNext(Group group) {
        CreditsListFragmentImpl creditsListFragment = new CreditsListFragmentImpl();

        Bundle bundle = new Bundle();
        bundle.putLong(BundleKeysConst.BUNDLE_GROUP_ID_KEY, group.getGroupId());
        creditsListFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.constraint_container, creditsListFragment, "CREDITS_LIST_TAG")
                .addToBackStack(null)
                .commit();

    }
}
