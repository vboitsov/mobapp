package ua.org.rshu.fmi.mobapp.view.fragment.credits;

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
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.forcredits.GroupListForCreditsFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.forexams.GroupListForExamsFragmentImpl;


public class CreditsOptionFragment extends Fragment {

    private Unbinder mUnbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
        GroupListForCreditsFragmentImpl groupListForCreditsFragment = new GroupListForCreditsFragmentImpl();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.constraint_container, groupListForCreditsFragment, "GROUPS_LIST_FOR_CREDITS_FRAGMENTS")
                .addToBackStack(null)
                .commit();

    }

    @OnClick(R.id.button_group_exams)
    public void openGroupListForExams() {
        GroupListForExamsFragmentImpl groupListForExamsFragment = new GroupListForExamsFragmentImpl();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.constraint_container, groupListForExamsFragment, "GROUP_LIST_FOR_EXAMS_FRAGMENT")
                .addToBackStack(null)
                .commit();

    }

    private void setUpToolbar() {
        setHasOptionsMenu(true);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Навчальний процес");
        }
    }


}
