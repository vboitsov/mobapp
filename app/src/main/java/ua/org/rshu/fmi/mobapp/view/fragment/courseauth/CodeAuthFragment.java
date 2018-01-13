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
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ua.org.rshu.fmi.mobapp.FMIApplication;
import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.CodeAuthResponse;
import ua.org.rshu.fmi.mobapp.service.fmiservices.FmiService;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;


public class CodeAuthFragment extends Fragment {

    @BindView(R.id.input_password) EditText passwordInputEditText;

    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    private Unbinder mUnbinder;

    @Inject
    FmiService fmiService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_code_auth, container, false);

        mUnbinder = ButterKnife.bind(this, rootView);
        FMIApplication.getsAppComponent().inject(this);
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

    private void setUpToolbar() {
        setHasOptionsMenu(true);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Авторизація");
        }
    }

    public void showProgressBar() {
        mProgressBar.setVisibility(ProgressBar.VISIBLE);
    }

    public void hideProgressBar() {
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    public void openCourseRequestInfoFragment(String token) {
        String email = getArguments().getString(BundleKeysConst.BUNDLE_EMAIL_KEY);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(BundleKeysConst.BUNDLE_EMAIL_KEY, email);
        editor.putString(BundleKeysConst.BUNDLE_TOKEN_KEY, token);
        editor.commit();

        CourseRequestInfoFragment courseRequestInfoFragment = new CourseRequestInfoFragment();

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.constraint_container, courseRequestInfoFragment, "COURSE_REQUEST_INFO_TAG")
//                .addToBackStack(null)
                .commit();
    }

    @OnClick(R.id.btn_login)
    public void sendEmailForAuth() {
        if (!TextUtils.isEmpty(passwordInputEditText.getText().toString())) {
            new CodeAuthFragment.MakeRequest(this).execute();
        } else {
            Toast toast = Toast.makeText(getContext(),
                    "Введіть код!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private static class MakeRequest extends AsyncTask<Void, Void, CodeAuthResponse> {

        private CodeAuthFragment codeAuthFragment;


        MakeRequest(CodeAuthFragment codeAuthFragment) {
            this.codeAuthFragment = codeAuthFragment;
        }

        @Override
        protected void onPreExecute() {
            codeAuthFragment.showProgressBar();
        }

        @Override
        protected CodeAuthResponse doInBackground(Void... voids) {
            boolean isConnected = false;

            CodeAuthResponse codeAuthResponse = null;
            while (!isConnected) {
                try {
                    System.out.println("CODE IN STRING " + codeAuthFragment.passwordInputEditText.getText().toString());
                    int code = Integer.parseInt(codeAuthFragment.passwordInputEditText.getText().toString());
                    System.out.println("CODE IN INT:" + code);
                    codeAuthResponse = codeAuthFragment.fmiService.codeAuth(code).execute().body();
                    isConnected = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return codeAuthResponse;
        }

        @Override
        protected void onPostExecute(CodeAuthResponse codeAuthResponse) {
            if (codeAuthResponse != null && !TextUtils.isEmpty(codeAuthResponse.getToken())) {
                codeAuthFragment.openCourseRequestInfoFragment(codeAuthResponse.getToken());
            } else {
                Toast toast = Toast.makeText(codeAuthFragment.getContext(),
                        "Невірний код", Toast.LENGTH_SHORT);
                toast.show();
            }
            codeAuthFragment.hideProgressBar();
        }
    }
}
