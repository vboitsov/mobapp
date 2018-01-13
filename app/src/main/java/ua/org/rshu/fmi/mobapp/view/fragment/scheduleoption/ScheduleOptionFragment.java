package ua.org.rshu.fmi.mobapp.view.fragment.scheduleoption;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.GroupListForScheduleFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.forgroup.GroupScheduleListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.forteacher.TeacherScheduleListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.teacherslist.forschedule.TeacherListForScheduleFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;


public class ScheduleOptionFragment extends Fragment {

    private Unbinder mUnbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_schedule_option, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);

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

    @OnClick(R.id.button_group_schedule)
    public void openGroupListForSchedule() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        long groupId = sharedPref.getLong(BundleKeysConst.BUNDLE_GROUP_ID_KEY, -1);
        if (groupId == -1) {
            GroupListForScheduleFragmentImpl groupListForScheduleFragment = new GroupListForScheduleFragmentImpl();
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.constraint_container, groupListForScheduleFragment, "GROUP_LIST_FOR_SCHEDULE_FRAGMENT")
                    .addToBackStack(null)
                    .commit();
        } else {
            GroupScheduleListFragmentImpl groupScheduleFragment = new GroupScheduleListFragmentImpl();

            Bundle bundle = new Bundle();
            bundle.putLong(BundleKeysConst.BUNDLE_GROUP_ID_KEY, groupId);
            groupScheduleFragment.setArguments(bundle);

            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.constraint_container, groupScheduleFragment, "GROUP_SCHEDULE_TAG")
                    .addToBackStack(null)
                    .commit();
        }
    }

    @OnClick(R.id.button_teacher_schedule)
    public void openTeacherListForSchedule() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        long teacherId = sharedPref.getLong(BundleKeysConst.BUNDLE_TEACHER_ID_KEY, -1);
        if (teacherId == -1) {
            TeacherListForScheduleFragmentImpl teachersListFragment = new TeacherListForScheduleFragmentImpl();
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.constraint_container, teachersListFragment, "TEACHERS_LIST_FRAGMENT")
//                    .addToBackStack(null)
                    .commit();
        } else {
            TeacherScheduleListFragmentImpl groupScheduleFragment = new TeacherScheduleListFragmentImpl();

            Bundle bundle = new Bundle();
            bundle.putLong(BundleKeysConst.BUNDLE_TEACHER_ID_KEY, teacherId);
            groupScheduleFragment.setArguments(bundle);

            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.constraint_container, groupScheduleFragment, "GROUP_SCHEDULE_TAG")
//                    .addToBackStack(null)
                    .commit();
        }
    }

    private void setUpToolbar() {
        setHasOptionsMenu(true);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Розклад");
        }
    }




}
