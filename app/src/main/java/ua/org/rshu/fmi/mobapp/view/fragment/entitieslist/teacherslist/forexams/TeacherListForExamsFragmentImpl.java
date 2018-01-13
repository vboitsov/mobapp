package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.teacherslist.forexams;

import android.os.Bundle;

import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Teacher;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.examslist.forteacher.TeacherExamsListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.teacherslist.core.impl.TeacherListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;

/**
 * Created by vb on 06/01/2018.
 */

public class TeacherListForExamsFragmentImpl extends TeacherListFragmentImpl {

    @Override
    public void showNext(Teacher teacher) {
        TeacherExamsListFragmentImpl examsListFragment = new TeacherExamsListFragmentImpl();

        Bundle bundle = new Bundle();
        bundle.putLong(BundleKeysConst.BUNDLE_TEACHER_ID_KEY, teacher.getTeacherId());
        examsListFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.constraint_container, examsListFragment, "EXAMS_LIST_TAG")
                .addToBackStack(null)
                .commit();
    }
}
