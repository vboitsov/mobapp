package ua.org.rshu.fmi.mobapp.view.adapter.impl;

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
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Day;
import ua.org.rshu.fmi.mobapp.view.adapter.DataPostSetAdapter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.core.ScheduleListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.schedulelist.core.ScheduleListPresenter;

/**
 * Created by vb on 04/01/2018.
 */

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ScheduleViewHolder> implements DataPostSetAdapter<Day> {

    private ScheduleListFragment mScheduleListFragment;

    private List<Day> mDays = new ArrayList<>();

    private ScheduleListPresenter mScheduleListPresenter;

    public ScheduleListAdapter(ScheduleListFragment scheduleListFragment, ScheduleListPresenter scheduleListPresenter) {
        mScheduleListFragment = scheduleListFragment;
        mScheduleListPresenter = scheduleListPresenter;
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int position) {
        Day day = mDays.get(position);
        //TODO
//
//        holder.creditNameTextView.setText(exam.getExamName());
//        holder.teacherNameTextView.setText(exam.getTeacherName());
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

        @BindView(R.id.tv_title_exam) TextView creditNameTextView;

        @BindView(R.id.tv_teacher_name) TextView teacherNameTextView;

        ScheduleViewHolder (View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
