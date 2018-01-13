package ua.org.rshu.fmi.mobapp.view.adapter.impl;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Course;
import ua.org.rshu.fmi.mobapp.view.adapter.DataPostSetAdapter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.courselist.CoursesListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.courselist.CoursesListPresenter;

/**
 * Created by vb on 11/01/2018.
 */

public class CoursesListAdapter extends RecyclerView.Adapter<CoursesListAdapter.CourseViewHolder> implements DataPostSetAdapter<Course> {

    private CoursesListFragment mCourseListFragment;

    private List<Course> mCourses = new ArrayList<>();

    private CoursesListPresenter mCoursesListPresenter;

    private ArrayList<Course> selectedCourses = new ArrayList<>();

    public CoursesListAdapter(CoursesListFragment coursesListFragment, CoursesListPresenter creditsListPresenter) {
        mCourseListFragment = coursesListFragment;
        mCoursesListPresenter = creditsListPresenter;
    }

    @Override
    public CoursesListAdapter.CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new CoursesListAdapter.CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CoursesListAdapter.CourseViewHolder holder, int position) {
        Course course = mCourses.get(position);

        holder.courseNameTextView.setText(course.getCourseName());
        holder.courseDescriptionTextView.setText(course.getCourseDescription());

        holder.itemView.setOnClickListener(v -> holderOnClickListener(holder, course));
    }

    public ArrayList<Course> getSelectedCourses() {
        return selectedCourses;
    }

    private void holderOnClickListener(CoursesListAdapter.CourseViewHolder holder, Course selectedCourse) {
        System.out.println("BEFORE selected courses size^" + selectedCourses.size() + ". List: " + selectedCourses);
        if (!isContainsCourse(selectedCourse, holder) && selectedCourses.size() < 3) {
            selectedCourses.add(selectedCourse);
            holder.courseCardView.setCardBackgroundColor(Color.BLUE);
        }
        System.out.println("AFTER selected courses size^" + selectedCourses.size() + ". List: " + selectedCourses);
    }

    private boolean isContainsCourse(Course selectedCourse, CoursesListAdapter.CourseViewHolder holder) {
        for(Course course: selectedCourses) {
           if (course.getId() == selectedCourse.getId()) {
               selectedCourses.remove(course);
               holder.courseCardView.setCardBackgroundColor(Color.WHITE);
               return true;
           }
        }
        return false;
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    @Override
    public void setData(List<Course> data) {
        mCourses = data;
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_course_name) TextView courseNameTextView;

        @BindView(R.id.tv_course_description) TextView courseDescriptionTextView;

        @BindView(R.id.card_view_course) CardView courseCardView;

        CourseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
