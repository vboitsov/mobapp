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
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Group;
import ua.org.rshu.fmi.mobapp.view.adapter.DataPostSetAdapter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.core.GroupsListPresenter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.groupslist.core.GroupsListFragment;


/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class GroupsListAdapter extends RecyclerView.Adapter<GroupsListAdapter.GroupViewHolder> implements DataPostSetAdapter<Group> {

    private GroupsListFragment mGroupsListFragment;

    private List<Group> mGroups = new ArrayList<>();

    private GroupsListPresenter mGroupsListPresenter;

    public GroupsListAdapter(GroupsListFragment groupsListFragment, GroupsListPresenter groupsListPresenter) {
        mGroupsListFragment = groupsListFragment;
        mGroupsListPresenter = groupsListPresenter;
    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, parent, false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, int position) {
        Group group = mGroups.get(position);

        holder.groupNameTextView.setText(group.getGroupName());


        holder.itemView.setOnClickListener(v -> mGroupsListFragment.showNext(group));

    }

    @Override
    public int getItemCount() {
        return mGroups.size();
    }

    @Override
    public void setData(List<Group> data) {
        mGroups = data;
    }

    class GroupViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title_group) TextView groupNameTextView;

        GroupViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


