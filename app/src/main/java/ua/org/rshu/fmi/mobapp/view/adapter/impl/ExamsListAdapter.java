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
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Exam;
import ua.org.rshu.fmi.mobapp.view.adapter.DataPostSetAdapter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.examslist.core.ExamsListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.examslist.core.ExamsListPresenter;


/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class ExamsListAdapter extends RecyclerView.Adapter<ExamsListAdapter.ExamViewHolder> implements DataPostSetAdapter<Exam> {

    private ExamsListFragment mExamsListFragment;

    private List<Exam> mExams = new ArrayList<>();

    private ExamsListPresenter mCreditsListPresenter;

    public ExamsListAdapter(ExamsListFragment examsListFragment, ExamsListPresenter examsListPresenter) {
        mExamsListFragment = examsListFragment;
        mCreditsListPresenter = examsListPresenter;
    }

    @Override
    public ExamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam, parent, false);
        return new ExamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExamViewHolder holder, int position) {
        Exam exam = mExams.get(position);

        holder.examNameTextView.setText(exam.getExamName());
        holder.examDateTextView.setText(exam.getExamDate());
        holder.examTimeTextView.setText(exam.getExamTime());
        holder.audienceTextView.setText(exam.getExamAudience());
        holder.groupNameTextView.setText(exam.getGroupName());
        holder.teacherNameTextView.setText(exam.getTeacherName());
    }

    @Override
    public int getItemCount() {
        return mExams.size();
    }

    @Override
    public void setData(List<Exam> data) {
        mExams = data;
    }

    class ExamViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_credit_name) TextView examNameTextView;

        @BindView(R.id.tv_teacher_name) TextView teacherNameTextView;

        @BindView(R.id.tv_exam_date) TextView examDateTextView;

        @BindView(R.id.tv_group_name) TextView groupNameTextView;

        @BindView(R.id.tv_audience) TextView audienceTextView;

        @BindView(R.id.tv_exam_time) TextView examTimeTextView;

        ExamViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


