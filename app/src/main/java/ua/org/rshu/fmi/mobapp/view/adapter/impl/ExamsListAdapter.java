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

    private List<Exam> mCredits = new ArrayList<>();

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
        Exam exam = mCredits.get(position);

//        holder.creditNameTextView.setText(exam.getExamName());
//        holder.teacherNameTextView.setText(exam.getTeacherName());
//        holder.examDateTextView.setText(exam.getDate());
    }

    @Override
    public int getItemCount() {
        return mCredits.size();
    }

    @Override
    public void setData(List<Exam> data) {
        mCredits = data;
    }

    class ExamViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title_exam) TextView creditNameTextView;

        @BindView(R.id.tv_teacher_name) TextView teacherNameTextView;

        @BindView(R.id.tv_exam_date) TextView examDateTextView;

        ExamViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


