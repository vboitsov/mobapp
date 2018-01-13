package ua.org.rshu.fmi.mobapp.view.fragment.courseauth;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.EmailAuthResponse;
import ua.org.rshu.fmi.mobapp.service.fmiservices.FmiService;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;

public class EmailAuthFragment extends Fragment {

    @BindView(R.id.input_email) EditText emailInputEditText;

    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    private Unbinder mUnbinder;

    @Inject FmiService fmiService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_email_auth, container, false);

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

    public void openCodeAuthFragment() {
        CodeAuthFragment codeAuthFragment = new CodeAuthFragment();

        Bundle bundle = new Bundle();
        bundle.putString(BundleKeysConst.BUNDLE_EMAIL_KEY, emailInputEditText.getText().toString());
        codeAuthFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.constraint_container, codeAuthFragment, "CODE_AUTH_TAG")
//                .addToBackStack(null)
                .commit();
    }

    @OnClick(R.id.btn_send_email)
    public void sendEmailForAuth() {
        String email = emailInputEditText.getText().toString();
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            new MakeRequest(this).execute();
        } else {
            Toast toast = Toast.makeText(getContext(),
                    "Некоректний email!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private static class MakeRequest extends AsyncTask<Void, Void, EmailAuthResponse> {

        private EmailAuthFragment emailAuthFragment;

        MakeRequest(EmailAuthFragment emailAuthFragment) {
            this.emailAuthFragment = emailAuthFragment;
        }

        @Override
        protected void onPreExecute() {
            emailAuthFragment.showProgressBar();
        }

        @Override
        protected EmailAuthResponse doInBackground(Void... voids) {
            boolean isConnected = false;

            EmailAuthResponse emailAuthResponse = null;
            while (!isConnected) {
                try {
                    emailAuthResponse = emailAuthFragment.fmiService.emailAuth(emailAuthFragment.emailInputEditText.getText().toString()).execute().body();
                    System.out.println("Email auth response: " + emailAuthResponse.isSent());
                    isConnected = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return emailAuthResponse;
        }

        @Override
        protected void onPostExecute(EmailAuthResponse emailAuthResponse) {
            emailAuthFragment.hideProgressBar();

            if (emailAuthResponse != null && emailAuthResponse.isSent()) {
                emailAuthFragment.openCodeAuthFragment();
            } else {
                Toast toast = Toast.makeText(emailAuthFragment.getContext(),
                        "Упс! Вииникла помилка, спробуйте пізніше", Toast.LENGTH_SHORT);
                toast.show();
            }

        }
    }
}
