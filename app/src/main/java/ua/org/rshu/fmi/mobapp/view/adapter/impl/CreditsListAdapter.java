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
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Credit;
import ua.org.rshu.fmi.mobapp.view.adapter.DataPostSetAdapter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.creditslist.core.CreditsListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.creditslist.core.CreditsListPresenter;


/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class CreditsListAdapter extends RecyclerView.Adapter<CreditsListAdapter.CreditViewHolder> implements DataPostSetAdapter<Credit> {

    private CreditsListFragment mCreditsListFragment;

    private List<Credit> mCredits = new ArrayList<>();

    private CreditsListPresenter mCreditsListPresenter;

    public CreditsListAdapter(CreditsListFragment creditsListFragment, CreditsListPresenter creditsListPresenter) {
        mCreditsListFragment = creditsListFragment;
        mCreditsListPresenter = creditsListPresenter;
    }

    @Override
    public CreditViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_credit, parent, false);
        return new CreditViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CreditViewHolder holder, int position) {
        Credit credit = mCredits.get(position);
        holder.creditNameTextView.setText(credit.getCreditName());
        holder.teacherNameTextView.setText(credit.getGroupName());
        holder.groupNameTextView.setText(credit.getTeacherName());
    }

    @Override
    public int getItemCount() {
        return mCredits.size();
    }

    @Override
    public void setData(List<Credit> data) {
        mCredits = data;
    }

    class CreditViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_credit_name) TextView creditNameTextView;

        @BindView(R.id.tv_teacher_name) TextView teacherNameTextView;

        @BindView(R.id.tv_group_name) TextView groupNameTextView;

        CreditViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


