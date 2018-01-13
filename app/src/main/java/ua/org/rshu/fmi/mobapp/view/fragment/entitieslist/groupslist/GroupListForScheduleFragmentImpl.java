package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist;

import android.os.Bundle;

import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Group;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.core.impl.GroupsListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.forgroup.GroupScheduleListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;

/**
 * Created by vb on 22/11/2017.
 */

public class GroupListForScheduleFragmentImpl extends GroupsListFragmentImpl{

    @Override
    public void showNext(Group group) {
        GroupScheduleListFragmentImpl groupScheduleFragment = new GroupScheduleListFragmentImpl();

        Bundle bundle = new Bundle();
        bundle.putLong(BundleKeysConst.BUNDLE_GROUP_ID_KEY, group.getGroupId());
        groupScheduleFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.constraint_container, groupScheduleFragment, "GROUP_SCHEDULE_TAG")
                .addToBackStack(null)
                .commit();

    }
}
