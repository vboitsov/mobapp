package ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.noteslist;

import android.support.v4.view.MenuItemCompat;
import android.view.MenuItem;

import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.entity.Note;
import ua.org.rshu.fmi.mobapp.view.fragment.entitieslist.core.EntitiesListPresenter;

/**
 * @author Andrii Bei <psihey1@gmail.com>
 */

public interface NotesListPresenter extends EntitiesListPresenter<Note>, MenuItemCompat.OnActionExpandListener {

    void disposeSearch();

    void subscribeSearchView(MenuItem searchItem);
}
