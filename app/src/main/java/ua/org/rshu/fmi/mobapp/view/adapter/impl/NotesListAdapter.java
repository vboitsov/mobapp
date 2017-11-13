package ua.org.rshu.fmi.mobapp.view.adapter.impl;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.persistent.entity.Note;
import ua.org.rshu.fmi.mobapp.view.adapter.DataPostSetAdapter;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.noteslist.NotesListFragment;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.noteslist.NotesListPresenter;


/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NoteViewHolder> implements DataPostSetAdapter<Note> {

    private NotesListFragment mAllNotesFragment;

    private List<Note> mNotes;

    private NotesListPresenter mNoteListPresenter;

    public NotesListAdapter(NotesListFragment allNotesFragment,NotesListPresenter notesListPresenter) {
        mAllNotesFragment = allNotesFragment;
        mNoteListPresenter = notesListPresenter;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note note = mNotes.get(position);

        holder.tvTitle.setText(mNotes.get(position).getTitle());
        holder.createDateTextView.setText(convertMillisToDate(mNotes.get(position).getCreationTime()));
        holder.updateDateTextView.setText(convertMillisToDate(mNotes.get(position).getUpdateTime()));

        holder.itemView.setOnClickListener(v -> mAllNotesFragment.showSelectedNote(note));

    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    @Override
    public void setData(List<Note> data) {
        mNotes = data;
    }

    private String convertMillisToDate(long dateInMillis) {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date(dateInMillis));
    }


    //TODO: maybe create one class for it.
    class NoteViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title_note) TextView tvTitle;

        @BindView(R.id.tv_create_date) TextView createDateTextView;

        @BindView(R.id.tv_update_date) TextView updateDateTextView;

        NoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


