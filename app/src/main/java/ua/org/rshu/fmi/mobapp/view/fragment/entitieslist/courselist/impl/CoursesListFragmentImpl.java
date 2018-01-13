package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.courselist.impl;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ua.org.rshu.fmi.mobapp.FMIApplication;
import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Course;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Group;
import ua.org.rshu.fmi.mobapp.view.adapter.impl.CoursesListAdapter;
import ua.org.rshu.fmi.mobapp.view.fragment.courseauth.CourseRequestInputFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.impl.EntitiesListWithProgressbarFragmentImpl;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.courselist.CoursesListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.courselist.CoursesListPresenter;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;


public class CoursesListFragmentImpl extends EntitiesListWithProgressbarFragmentImpl implements CoursesListFragment {

    @BindView(R.id.recycler_view_courses_list) RecyclerView mCoursesRecyclerView;

    @BindView(R.id.fab_accept_courses) FloatingActionButton mAcceptCoursesFab;

    @Inject CoursesListPresenter mCoursesListPresenter;

    public static final String TOOLBAR_TITLE = "Courses";

    private CoursesListAdapter mCoursesListAdapter;

    private Unbinder mUnbinder;

    private Group group;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        group = getArguments().getParcelable(BundleKeysConst.BUNDLE_GROUP_ID_KEY);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_courses_list, container, false);

        mUnbinder = ButterKnife.bind(this, rootView);
        FMIApplication.getsAppComponent().inject(this);

        System.out.println(group);
        setUpToolbar();
        initRecyclerView();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mCoursesListPresenter != null) {
            mCoursesListPresenter.bindView(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mCoursesListPresenter.unbindView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mCoursesListPresenter.disposePagination();
    }

    @Override
    public void updateRecyclerView() {
        mCoursesListAdapter.notifyDataSetChanged();
        Logger.d("Recycler view is updated");
    }

    private void setUpToolbar() {
        setHasOptionsMenu(true);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Вибір курсів");
        }
    }


    public long getGroupId() {
        group = getArguments().getParcelable(BundleKeysConst.BUNDLE_GROUP_ID_KEY);
        System.out.println("get group id:" + group.getGroupName() + " " + group.getGroupId());
        return group.getGroupId();
    }

    /**
     * A method which initializes recycler view with data
     */
    private void initRecyclerView() {
        mCoursesRecyclerView.setHasFixedSize(true);

        mCoursesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mCoursesListAdapter = new CoursesListAdapter(this, mCoursesListPresenter);
        mCoursesRecyclerView.setAdapter(mCoursesListAdapter);

        new CoursesListFragmentImpl.LoadForAdapterAsyncTask(this).execute();

        mCoursesListPresenter.subscribeRecyclerViewForPagination(mCoursesRecyclerView);
    }


    @OnClick(R.id.fab_accept_courses)
    public void startRequestFormFragment() {
        if (mCoursesListAdapter.getSelectedCourses().size() != 0) {
            openRequestForm();
        } else {
            Toast toast = Toast.makeText(getContext(),
                    "Необхідно вибрати хоча б один курс", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void openRequestForm() {
        ArrayList<Course> courseArrayList = mCoursesListAdapter.getSelectedCourses();

        CourseRequestInputFragment courseRequestInputFragment = new CourseRequestInputFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(BundleKeysConst.BUNDLE_GROUP_ID_KEY, group);
        bundle.putParcelableArrayList(BundleKeysConst.BUNDLE_COURSES_ARRAY_KEY, courseArrayList);
        courseRequestInputFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.constraint_container, courseRequestInputFragment, "COURSES_INPUT_FORM_LIST_TAG")
                .addToBackStack(null)
                .commit();
    }

    private static class LoadForAdapterAsyncTask extends AsyncTask<Void, Void, Void> {

        private CoursesListFragmentImpl coursesListFragment;

        LoadForAdapterAsyncTask(CoursesListFragmentImpl coursesListFragment) {
            this.coursesListFragment = coursesListFragment;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            coursesListFragment.mCoursesListPresenter.setDataToAdapter(coursesListFragment.mCoursesListAdapter);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            coursesListFragment.updateRecyclerView();
        }
    }
}
