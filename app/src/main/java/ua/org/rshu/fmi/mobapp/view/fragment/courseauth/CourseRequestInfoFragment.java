package ua.org.rshu.fmi.mobapp.view.fragment.courseauth;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ua.org.rshu.fmi.mobapp.FMIApplication;
import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Course;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.CourseRequestForm;
import ua.org.rshu.fmi.mobapp.service.fmiservices.FmiService;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.GroupListForCoursesFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;

public class CourseRequestInfoFragment extends Fragment {

    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    @BindView(R.id.tv_request_status) TextView requestStatusTextView;

    @BindView(R.id.tv_email_input) TextView emailTextView;

    @BindView(R.id.tv_phone_number_input) TextView phoneNumberTextView;

    @BindView(R.id.tv_name_input) TextView nameTextView;

    @BindView(R.id.tv_surname_input) TextView surnameTextView;

    @BindView(R.id.tv_middlename_input) TextView middlenameTextView;

    @BindView(R.id.tv_group_input) TextView groupTextView;

    @BindView(R.id.linear_courses_container) LinearLayout coursesContainerLinearLayout;

    private Unbinder mUnbinder;

    private String token;

    @Inject
    FmiService fmiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_course_request_info, container, false);

        mUnbinder = ButterKnife.bind(this, rootView);
        FMIApplication.getsAppComponent().inject(this);
        setUpToolbar();
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        token = sharedPref.getString(BundleKeysConst.BUNDLE_TOKEN_KEY, null);
        new LoadRequestInfo(this).execute();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null){
            mUnbinder.unbind();
        }
    }

    @OnClick(R.id.btn_change_email)
    public void changeEmail() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(BundleKeysConst.BUNDLE_EMAIL_KEY, "");
        editor.putString(BundleKeysConst.BUNDLE_TOKEN_KEY, "");
        editor.apply();

        EmailAuthFragment emailAuthFragment = new EmailAuthFragment();

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.constraint_container, emailAuthFragment, "EMAIL_AUTH_TAG")
//                .addToBackStack(null)
                .commit();


    }

    @OnClick(R.id.btn_make_request)
    public void madeAnotherRequest() {
        GroupListForCoursesFragmentImpl groupListForCoursesFragment = new GroupListForCoursesFragmentImpl();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.constraint_container, groupListForCoursesFragment, "EMAIL_AUTH_TAG")
                .addToBackStack(null)
                .commit();
    }

    private void setUpToolbar() {
        setHasOptionsMenu(true);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Заявка на курси");
        }
    }


    public void showProgressBar() {
        mProgressBar.setVisibility(ProgressBar.VISIBLE);
    }

    public void hideProgressBar() {
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    private void fillRequestInfo(CourseRequestForm courseRequestForm) {
        if (courseRequestForm.getStatus() != null) {
            requestStatusTextView.setText(courseRequestForm.getStatus());
        } else {
            changeEmail();
            return;
        }

        if(courseRequestForm.getEmail() != null) emailTextView.setText(courseRequestForm.getEmail());

        if(courseRequestForm.getPhoneNumber() != null) phoneNumberTextView.setText(courseRequestForm.getPhoneNumber());

        if(courseRequestForm.getName() != null) nameTextView.setText(courseRequestForm.getName());

        if(courseRequestForm.getSurname() != null) surnameTextView.setText(courseRequestForm.getSurname());

        if(courseRequestForm.getMiddleName() != null) middlenameTextView.setText(courseRequestForm.getMiddleName());

        if(courseRequestForm.getGroup() != null) groupTextView.setText(courseRequestForm.getGroup().getGroupName());

        if (courseRequestForm.getCourses() != null) {
            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            for (Course course : courseRequestForm.getCourses()) {
                View courseView = layoutInflater.inflate(R.layout.item_course, null);
                ((TextView) courseView.findViewById(R.id.tv_course_name)).setText(course.getCourseName());
                ((TextView) courseView.findViewById(R.id.tv_course_description)).setText(course.getCourseDescription());
                coursesContainerLinearLayout.addView(courseView);
            }
        }
    }


    private static class LoadRequestInfo extends AsyncTask<Void, Void, CourseRequestForm> {

        private CourseRequestInfoFragment courseRequestInfoFragment;

        LoadRequestInfo(CourseRequestInfoFragment courseRequestInfoFragment) {
            this.courseRequestInfoFragment = courseRequestInfoFragment;
        }

        @Override
        protected void onPreExecute() {
            courseRequestInfoFragment.showProgressBar();
        }

        @Override
        protected CourseRequestForm doInBackground(Void... voids) {
            boolean isConnected = false;

            CourseRequestForm emailAuthResponse = null;
            while (!isConnected) {
                try {
                    emailAuthResponse = courseRequestInfoFragment.fmiService.getCourseRequestForm(courseRequestInfoFragment.token).execute().body();
                    isConnected = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return emailAuthResponse;
        }

        @Override
        protected void onPostExecute(CourseRequestForm courseRequestForm) {
            courseRequestInfoFragment.hideProgressBar();
            courseRequestInfoFragment.fillRequestInfo(courseRequestForm);
        }
    }
}
