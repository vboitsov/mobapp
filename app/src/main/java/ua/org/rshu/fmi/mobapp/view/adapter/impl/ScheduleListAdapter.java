package ua.org.rshu.fmi.mobapp.view.adapter.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Day;
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Lesson;
import ua.org.rshu.fmi.mobapp.view.adapter.DataPostSetAdapter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.core.ScheduleListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.core.ScheduleListPresenter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.core.impl.ScheduleListFragmentImpl;

/**
 * Created by vb on 04/01/2018.
 */

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ScheduleViewHolder> implements DataPostSetAdapter<Day> {

    private ScheduleListFragment mScheduleListFragment;

    private List<Day> mDays = new ArrayList<>();

    private ScheduleListPresenter mScheduleListPresenter;

    private LayoutInflater layoutInflater;

    public ScheduleListAdapter(ScheduleListFragment scheduleListFragment, ScheduleListPresenter scheduleListPresenter) {
        mScheduleListFragment = scheduleListFragment;
        mScheduleListPresenter = scheduleListPresenter;
        layoutInflater = (LayoutInflater) ((ScheduleListFragmentImpl) mScheduleListFragment).getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int position) {
        Day day = mDays.get(position);

        holder.dayNameTextView.setText(day.getDayName());
        holder.dayDateTextView.setText(day.getDayDate());
        holder.updateDateTextView.setText(day.getUpdateTime());

        System.out.println(day);


        for(Lesson lesson: day.getLessons()) {
            View lessonView;
            if (!lesson.isHalfGrouped()) {
                System.out.println("NOT HALF GROUPED");
                lessonView = layoutInflater.inflate(R.layout.item__lesson, null);
                ((TextView) lessonView.findViewById(R.id.tv_order_of_lesson)).setText(Integer.toString(lesson.getLessonOrder()));
                ((TextView) lessonView.findViewById(R.id.tv_lesson1_name)).setText(lesson.getFirstSubjectName());
                ((TextView) lessonView.findViewById(R.id.tv_teacher1_name)).setText(lesson.getFirstSubjectGroupOrTeacherName());
                ((TextView) lessonView.findViewById(R.id.tv_lesson1_audience)).setText(lesson.getFirstSubjectAudience());
            } else {
                System.out.println("HALF GROUPED");
                lessonView = layoutInflater.inflate(R.layout.item__lesson_half, null);
                ((TextView) lessonView.findViewById(R.id.tv_order_of_lesson)).setText(Integer.toString(lesson.getLessonOrder()));
                if (!TextUtils.isEmpty(lesson.getFirstSubjectName())) {
                    ((TextView) lessonView.findViewById(R.id.tv_lesson1_name)).setText(lesson.getFirstSubjectName());
                    ((TextView) lessonView.findViewById(R.id.tv_teacher1_name)).setText(lesson.getFirstSubjectGroupOrTeacherName());
                    ((TextView) lessonView.findViewById(R.id.tv_lesson1_audience)).setText(lesson.getFirstSubjectAudience());
                }
                if (!TextUtils.isEmpty(lesson.getSecondSubjectName())) {
                    ((TextView) lessonView.findViewById(R.id.tv_lesson2_name)).setText(lesson.getSecondSubjectName());
                    ((TextView) lessonView.findViewById(R.id.tv_teacher2_name)).setText(lesson.getSecondSubjectGroupOrTeacherName());
                    ((TextView) lessonView.findViewById(R.id.tv_lesson2_audience)).setText(lesson.getSecondSubjectAudience());
                }
            }
            holder.lessonContainerLinearLayout.addView(lessonView);
        }
    }

    @Override
    public int getItemCount() {
        return mDays.size();
    }

    @Override
    public void setData(List<Day> data) {
        mDays = data;
    }

    class ScheduleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_credit_name) TextView dayNameTextView;

        @BindView(R.id.tv_day_date) TextView dayDateTextView;

        @BindView(R.id.tv_update_date) TextView updateDateTextView;

        @BindView(R.id.linear_lesson_container) LinearLayout lessonContainerLinearLayout;

        ScheduleViewHolder (View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
