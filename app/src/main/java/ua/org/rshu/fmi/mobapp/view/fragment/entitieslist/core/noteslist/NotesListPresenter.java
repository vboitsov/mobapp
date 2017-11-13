package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.noteslist;

import android.support.v4.view.MenuItemCompat;
import android.view.MenuItem;

import ua.org.rshu.fmi.mobapp.persistent.entity.Note;
import ua.org.rshu.fmi.mobapp.service.form.NoteForm;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.EntitiesListPresenter;

/**
 * @author Andrii Bei <psihey1@gmail.com>
 */

public interface NotesListPresenter extends EntitiesListPresenter<Note, NoteForm>, MenuItemCompat.OnActionExpandListener {

    void disposeSearch();

    void subscribeSearchView(MenuItem searchItem);
}
