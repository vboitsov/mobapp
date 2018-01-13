package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.teacherslist.forschedule;

import android.os.Bundle;

import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Teacher;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.forteacher.TeacherScheduleListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.teacherslist.core.impl.TeacherListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;

/**
 * Created by vb on 06/01/2018.
 */

public class TeacherListForScheduleFragmentImpl extends TeacherListFragmentImpl {

    @Override
    public void showNext(Teacher teacher) {
        TeacherScheduleListFragmentImpl groupScheduleFragment = new TeacherScheduleListFragmentImpl();

        Bundle bundle = new Bundle();
        bundle.putLong(BundleKeysConst.BUNDLE_TEACHER_ID_KEY, teacher.getTeacherId());
        groupScheduleFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.constraint_container, groupScheduleFragment, "GROUP_SCHEDULE_TAG")
                .addToBackStack(null)
                .commit();
    }
}
