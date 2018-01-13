package ua.org.rshu.fmi.mobapp.view.fragment.creditsoption;

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
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.creditslist.forgroup.GroupCreditsListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.creditslist.forteacher.TeacherCreditsListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.examslist.forgroup.GroupExamsListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.examslist.forteacher.TeacherExamsListFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.GroupListForCreditsFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.GroupListForExamsFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.teacherslist.forcredtis.TeacherListForCreditsFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.teacherslist.forexams.TeacherListForExamsFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;


public class CreditsOptionFragment extends Fragment {

    private Unbinder mUnbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_credits_option, container, false);
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

    @OnClick(R.id.button_group_credits)
    public void openGroupListForCredits() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        long groupId = sharedPref.getLong(BundleKeysConst.BUNDLE_GROUP_ID_KEY, -1);
        if (groupId == -1) {

            GroupListForCreditsFragmentImpl groupListForCreditsFragment = new GroupListForCreditsFragmentImpl();
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.constraint_container, groupListForCreditsFragment, "GROUPS_LIST_FOR_CREDITS_FRAGMENTS")
                    .addToBackStack(null)
                    .commit();
        } else {
            GroupCreditsListFragmentImpl creditsListFragment = new GroupCreditsListFragmentImpl();

            Bundle bundle = new Bundle();
            bundle.putLong(BundleKeysConst.BUNDLE_GROUP_ID_KEY, groupId);
            creditsListFragment.setArguments(bundle);

            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.constraint_container, creditsListFragment, "CREDITS_LIST_TAG")
                    .addToBackStack(null)
                    .commit();
        }

    }


    @OnClick(R.id.button_group_exams)
    public void openGroupListForExams() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        long groupId = sharedPref.getLong(BundleKeysConst.BUNDLE_GROUP_ID_KEY, -1);
        if (groupId == -1) {
            GroupListForExamsFragmentImpl groupListForExamsFragment = new GroupListForExamsFragmentImpl();
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.constraint_container, groupListForExamsFragment, "GROUP_LIST_FOR_EXAMS_FRAGMENT")
                    .addToBackStack(null)
                    .commit();
        } else {
            GroupExamsListFragmentImpl examsListFragment = new GroupExamsListFragmentImpl();

            Bundle bundle = new Bundle();
            bundle.putLong(BundleKeysConst.BUNDLE_GROUP_ID_KEY, groupId);
            examsListFragment.setArguments(bundle);

            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.constraint_container, examsListFragment, "EXAMS_LIST_TAG")
                    .addToBackStack(null)
                    .commit();
        }
    }

    @OnClick(R.id.button_teacher_credits)
    public void openTeacherListForCredits() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        long teacherId = sharedPref.getLong(BundleKeysConst.BUNDLE_TEACHER_ID_KEY, -1);
        if (teacherId == -1) {
            TeacherListForCreditsFragmentImpl teacherListForCreditsFragment = new TeacherListForCreditsFragmentImpl();
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.constraint_container, teacherListForCreditsFragment, "TEACHER_LIST_FOR_CREDITS_FRAGMENTS")
                    .addToBackStack(null)
                    .commit();
        } else {
            TeacherCreditsListFragmentImpl examsListFragment = new TeacherCreditsListFragmentImpl();

            Bundle bundle = new Bundle();
            bundle.putLong(BundleKeysConst.BUNDLE_TEACHER_ID_KEY, teacherId);
            examsListFragment.setArguments(bundle);

            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.constraint_container, examsListFragment, "EXAMS_LIST_TAG")
                    .addToBackStack(null)
                    .commit();
        }
    }

    @OnClick(R.id.button_teacher_exams)
    public void openTeacherListForExams() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        long teacherId = sharedPref.getLong(BundleKeysConst.BUNDLE_TEACHER_ID_KEY, -1);
        if (teacherId == -1) {
            TeacherListForExamsFragmentImpl teacherListForExamsFragment = new TeacherListForExamsFragmentImpl();
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.constraint_container, teacherListForExamsFragment, "TEACHER_LIST_FOR_EXAMS_FRAGMENT")
                    .addToBackStack(null)
                    .commit();
        } else {
            TeacherExamsListFragmentImpl examsListFragment = new TeacherExamsListFragmentImpl();

            Bundle bundle = new Bundle();
            bundle.putLong(BundleKeysConst.BUNDLE_TEACHER_ID_KEY, teacherId);
            examsListFragment.setArguments(bundle);

            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.constraint_container, examsListFragment, "EXAMS_LIST_TAG")
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void setUpToolbar() {
        setHasOptionsMenu(true);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Навчальний процес");
        }
    }
}
