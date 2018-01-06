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
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.creditslist.CreditsListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.creditslist.CreditsListPresenter;


/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class CreditsListAdapter extends RecyclerView.Adapter<CreditsListAdapter.CreditViewHolder> implements DataPostSetAdapter<Exam> {

    private CreditsListFragment mCreditsListFragment;

    private List<Exam> mCredits = new ArrayList<>();

    private CreditsListPresenter mCreditsListPresenter;

    public CreditsListAdapter(CreditsListFragment creditsListFragment, CreditsListPresenter creditsListPresenter) {
        mCreditsListFragment = creditsListFragment;
        mCreditsListPresenter = creditsListPresenter;
    }

    @Override
    public CreditViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam, parent, false);
        return new CreditViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CreditViewHolder holder, int position) {
        Exam exam = mCredits.get(position);

        holder.creditNameTextView.setText(exam.getExamName());
        holder.teacherNameTextView.setText(exam.getTeacherName());
    }

    @Override
    public int getItemCount() {
        return mCredits.size();
    }

    @Override
    public void setData(List<Exam> data) {
        mCredits = data;
    }

    class CreditViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title_exam) TextView creditNameTextView;

        @BindView(R.id.tv_teacher_name) TextView teacherNameTextView;

        CreditViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


