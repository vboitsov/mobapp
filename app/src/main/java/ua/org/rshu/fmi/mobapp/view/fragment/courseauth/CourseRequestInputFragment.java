package ua.org.rshu.fmi.mobapp.view.fragment.courseauth;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ua.org.rshu.fmi.mobapp.FMIApplication;
import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Course;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.CourseRequestForm;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Group;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.MakeRequestResponse;
import ua.org.rshu.fmi.mobapp.service.fmiservices.FmiService;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;

public class CourseRequestInputFragment extends Fragment {

    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    @BindView(R.id.tv_email_input) TextView emailTextView;

    @BindView(R.id.tv_name) EditText nameTextView;

    @BindView(R.id.tv_surname) EditText surnameTextView;

    @BindView(R.id.tv_middlename) EditText middlenameTextView;

    @BindView(R.id.tv_phone_number) EditText phoneNumberTextView;

    @BindView(R.id.tv_group_input) TextView groupTextView;

    @BindView(R.id.linear_courses_container) LinearLayout coursesContainerLinearLayout;

    private Unbinder mUnbinder;

    @Inject FmiService fmiService;

    private String email;

    private Group group;

    private ArrayList<Course> courseArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_course_request_input, container, false);

        mUnbinder = ButterKnife.bind(this, rootView);
        FMIApplication.getsAppComponent().inject(this);
        setUpToolbar();
        loadArguments();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null){
            mUnbinder.unbind();
        }
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

    private void loadArguments() {
        group = getArguments().getParcelable(BundleKeysConst.BUNDLE_GROUP_ID_KEY);
        groupTextView.setText(group.getGroupName());

        courseArrayList = getArguments().getParcelableArrayList(BundleKeysConst.BUNDLE_COURSES_ARRAY_KEY);
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (Course course : courseArrayList) {
            View courseView = layoutInflater.inflate(R.layout.item_course, null);
            ((TextView) courseView.findViewById(R.id.tv_course_name)).setText(course.getCourseName());
            ((TextView) courseView.findViewById(R.id.tv_course_description)).setText(course.getCourseDescription());
            coursesContainerLinearLayout.addView(courseView);
        }

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        email = sharedPref.getString(BundleKeysConst.BUNDLE_EMAIL_KEY, null);
        emailTextView.setText(email);
    }

    public void startRequestInfoFragment() {
        CourseRequestInfoFragment courseRequestInfoFragment = new CourseRequestInfoFragment();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.constraint_container, courseRequestInfoFragment, "REQUEST_INFO_TAG")
                .addToBackStack(null)
                .commit();
    }

    @OnClick(R.id.btn_send_request)
    public void sendRequest() {
        if(!TextUtils.isEmpty(nameTextView.getText().toString()) && !TextUtils.isEmpty(surnameTextView.getText().toString())
                && !TextUtils.isEmpty(middlenameTextView.getText().toString()) && !TextUtils.isEmpty(phoneNumberTextView.getText().toString())) {
            new MakeRequest(this).execute();
        } else {
            Toast toast = Toast.makeText(getContext(),
                    "Будь ласка, заповніть усі поля", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public String getToken() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(BundleKeysConst.BUNDLE_TOKEN_KEY, null);
    }

    public CourseRequestForm getCourseRequestFrom() {
        String name = nameTextView.getText().toString();
        String surname = surnameTextView.getText().toString();
        String middlename = middlenameTextView.getText().toString();
        String phoneNumber = phoneNumberTextView.getText().toString();
        return new CourseRequestForm(email, phoneNumber, name, surname, middlename, group, courseArrayList, "Заявка розглядається");
    }

    private static class MakeRequest extends AsyncTask<Void, Void, MakeRequestResponse> {

        private CourseRequestInputFragment courseRequestInputFragment;

        MakeRequest(CourseRequestInputFragment courseRequestInputFragment) {
            this.courseRequestInputFragment = courseRequestInputFragment;
        }

        @Override
        protected void onPreExecute() {
            courseRequestInputFragment.showProgressBar();
        }

        @Override
        protected MakeRequestResponse doInBackground(Void... voids) {
            boolean isConnected = false;

            MakeRequestResponse makeRequestResponse = null;
            while (!isConnected) {
                try {
                    System.out.println(courseRequestInputFragment.getCourseRequestFrom());
                    makeRequestResponse = courseRequestInputFragment.fmiService
                            .makeRequest(courseRequestInputFragment.getToken(), courseRequestInputFragment.getCourseRequestFrom()).execute().body();
                    isConnected = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return makeRequestResponse;
        }

        @Override
        protected void onPostExecute(MakeRequestResponse makeRequestResponse) {
            courseRequestInputFragment.hideProgressBar();
            if (makeRequestResponse != null && makeRequestResponse.isMade()) {
                System.out.println("MAKE REQUEST RESPONSE: " + makeRequestResponse.isMade());
                courseRequestInputFragment.startRequestInfoFragment();
            } else {
                Toast toast = Toast.makeText(courseRequestInputFragment.getContext(),
                        "Упс! Вииникла помилка, спробуйте пізніше", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
