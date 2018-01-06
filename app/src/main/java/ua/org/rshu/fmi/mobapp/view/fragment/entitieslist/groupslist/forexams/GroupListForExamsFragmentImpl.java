package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.forexams;

import android.os.Bundle;

import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Group;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.examslist.core.impl.ExamsListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.core.impl.GroupsListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;

/**
 * Created by vb on 22/11/2017.
 */

public class GroupListForExamsFragmentImpl extends GroupsListFragmentImpl {

    @Override
    public void showNext(Group group) {
        ExamsListFragmentImpl examsListFragment = new ExamsListFragmentImpl();

        Bundle bundle = new Bundle();
        bundle.putLong(BundleKeysConst.BUNDLE_GROUP_ID_KEY, group.getGroupId());
        examsListFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.constraint_container, examsListFragment, "EXAMS_LIST_TAG")
                .addToBackStack(null)
                .commit();

    }
}
