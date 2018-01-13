package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.forteacher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ua.org.rshu.fmi.mobapp.FMIApplication;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.core.impl.ScheduleListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;

/**
 * Created by vb on 05/01/2018.
 */

public class TeacherScheduleListFragmentImpl extends ScheduleListFragmentImpl {

    @Inject TeacherScheduleListPresenterImpl mTeacherScheduleListPresenter;

    private long teacherId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        teacherId = getArguments().getLong(BundleKeysConst.BUNDLE_TEACHER_ID_KEY);
        FMIApplication.getsAppComponent().inject(this);
        mScheduleListPresenter = mTeacherScheduleListPresenter;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public long getTeacherId() {
        return teacherId;
    }
}
