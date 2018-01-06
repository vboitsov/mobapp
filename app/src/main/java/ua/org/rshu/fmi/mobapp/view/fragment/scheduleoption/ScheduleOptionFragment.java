package ua.org.rshu.fmi.mobapp.view.fragment.scheduleoption;

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
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.forschedule.GroupListForScheduleFragmentImpl;


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
        GroupListForScheduleFragmentImpl groupListForScheduleFragment = new GroupListForScheduleFragmentImpl();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.constraint_container, groupListForScheduleFragment, "GROUP_LIST_FOR_SCHEDULE_FRAGMENT")
                .addToBackStack(null)
                .commit();

    }

    @OnClick(R.id.button_teacher_schedule)
    public void openTeacherListForSchedule() {
//        TeacherListFragmentImpl teachersListFragment = new TeacherListFragmentImpl();
//        getActivity().getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.constraint_container, teachersListFragment, "TEACHERS_LIST_FRAGMENT")
//                .addToBackStack(null)
//                .commit();
    }

    private void setUpToolbar() {
        setHasOptionsMenu(true);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Розклад пар");
        }
    }
}
