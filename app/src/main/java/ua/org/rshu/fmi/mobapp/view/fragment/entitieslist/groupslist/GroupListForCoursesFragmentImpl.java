package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist;

import android.os.Bundle;

import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Group;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.courselist.impl.CoursesListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.core.impl.GroupsListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;

/**
 * Created by vb on 12/01/2018.
 */

public class GroupListForCoursesFragmentImpl extends GroupsListFragmentImpl {

    @Override
    public void showNext(Group group) {
        CoursesListFragmentImpl coursesListFragment = new CoursesListFragmentImpl();

        Bundle bundle = new Bundle();
        bundle.putParcelable(BundleKeysConst.BUNDLE_GROUP_ID_KEY, group);
        coursesListFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.constraint_container, coursesListFragment, "COURSES_LIST_TAG")
                .addToBackStack(null)
                .commit();
    }
}
