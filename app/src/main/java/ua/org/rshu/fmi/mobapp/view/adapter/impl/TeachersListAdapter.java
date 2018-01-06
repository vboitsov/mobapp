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
import ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity.Teacher;
import ua.org.rshu.fmi.mobapp.view.adapter.DataPostSetAdapter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.teacherslist.core.TeacherListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.teacherslist.core.TeacherListPresenter;


/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class TeachersListAdapter extends RecyclerView.Adapter<TeachersListAdapter.TeacherViewHolder> implements DataPostSetAdapter<Teacher> {

    private TeacherListFragment mTeacherListFragment;

    private List<Teacher> mTeachers = new ArrayList<>();

    private TeacherListPresenter mTeacherListPresenter;

    public TeachersListAdapter(TeacherListFragment teacherListFragment, TeacherListPresenter teacherListPresenter) {
        mTeacherListFragment = teacherListFragment;
        mTeacherListPresenter = teacherListPresenter;
    }

    @Override
    public TeacherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teacher, parent, false);
        return new TeacherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeacherViewHolder holder, int position) {
        Teacher teacher = mTeachers.get(position);

        holder.teacherNameTextView.setText(teacher.getTeacherName());
//        holder.itemView.setOnClickListener(v -> mTeacherListFragment.showTeacherSchedule(teacher));

    }

    @Override
    public int getItemCount() {
        return mTeachers.size();
    }

    @Override
    public void setData(List<Teacher> data) {
        mTeachers = data;
    }

    class TeacherViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title_teacher) TextView teacherNameTextView;

        TeacherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


